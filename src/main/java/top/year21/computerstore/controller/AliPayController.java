package top.year21.computerstore.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.year21.computerstore.config.AlipayConfig;
import top.year21.computerstore.service.IOrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理支付宝沙箱支付的接口
 * @date 2022/7/25 1:42
 */
@Slf4j
@Controller
@RequestMapping("/alipay")
public class AliPayController {
    @Autowired
    private IOrderService orderService;


    /**
     * Description : 处理在线支付的请求
     * @date 2022/7/25
     * @param oid 订单id
     * @param totalPrice 订单总金额
     * @return java.lang.String
     **/
    @ResponseBody
    @GetMapping(value = "/pay",produces = "text/html;charset=UTF-8")
    public String goAlipay(String oid,String totalPrice,HttpServletRequest request) throws Exception {

        // 获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.appId,
                AlipayConfig.appPrivateKey, "json", AlipayConfig.charset, AlipayConfig.alipayPublicKey,
                AlipayConfig.signType);

        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.returnUrl);
        alipayRequest.setNotifyUrl(AlipayConfig.notifyUrl);

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = oid;
        // 付款金额，必填
        String total_amount = totalPrice;
        // 订单名称，必填
        String subject = "支付宝沙箱测试商品支付";
        // 商品描述，可空
        String body = "";

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        //从session中取出异步需要用的uid
        Integer str = (Integer) request.getSession().getAttribute("uid");
        String passback_params = String.valueOf(str);
        //对取得的数据进行URLEncoder编码，这一步不做会报错
        String uidStr = URLEncoder.encode(passback_params,"UTF-8");

        //将编码后的数据封装在alipayRequest对象中，参数名字一定要是passback_params才能在异步通知时返回
        //至于参数的变量值这没有要求，只需要与上方编码后的一样即可
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\"," + "\"timeout_express\":\""
                + timeout_express + "\"," + "\"passback_params\":\"" + uidStr + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        // 这个是请求支付宝后台获取的数据，实际上获取是一个表单，然后他会附带一个自动执行的js方法
        //自动替你执行表单，然后进入表单中支付宝生成的页面
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        log.info(result);
        return result;
    }

    /**
     * Description : 处理异步回调的方法
     * @date 2022/7/25
     * @param request  请求对象
     * @param response 响应对象
     * @return void
     **/
    @PostMapping("/notifyNotice")
    @ResponseBody
    public void alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        log.info("支付成功, 进入异步通知接口...");

        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipayPublicKey, AlipayConfig.charset,
                AlipayConfig.signType); // 调用SDK验证签名

        // ——请在这里编写您的程序（以下代码仅作参考）——

        /*
         * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
         * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）， 3、校验通知中的seller_id（或者seller_email)
         * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
         * 4、验证app_id是否为该商户本身。
         */
        if (signVerified) {// 验证成功
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            //在支付接口中保存在session中uid
            String passback_params = new String(request.getParameter("passback_params").getBytes("ISO-8859-1"), "UTF-8");
            //对传回的数据进行编码的逆过程，对参数进行解码
            String uidStr = URLDecoder.decode(passback_params,"UTF-8");

            if (trade_status.equals("TRADE_FINISHED")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序

                // 注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                // 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序

                // 注意：
                // 付款完成后，支付宝系统发送该交易状态通知
                // 编写自己的订单支付成功的业务逻辑
                //将上方取到的uid和将订单号转换为包装类
                Integer uid = Integer.valueOf(uidStr);
                Integer oid = Integer.valueOf(out_trade_no);
                //调用订单业务层修改订单状态信息
                orderService.updateOrderStatusByOid(oid,uid,1);

                log.info("********************** 支付成功(支付宝异步通知) **********************");
                log.info("* 当前支付用户的id: {}", passback_params);
                log.info("* 订单号: {}", out_trade_no);
                log.info("* 支付宝交易号: {}", trade_no);
                log.info("* 实付金额: {}", total_amount);
                log.info("***************************************************************");
            }
            log.info("支付成功...");

        } else {// 验证失败
            log.info("支付, 验签失败...");
        }

//        return "success";
    }

    /**
     * Description : 同步回调方法
     * @date 2022/7/25
     * @param request 请求对象
     * @param response 响应对象
     * @return void
     **/
    @GetMapping("/returnNotice")
    public void alipayReturnNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("支付成功, 进入同步通知接口...");

        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipayPublicKey, AlipayConfig.charset,
                AlipayConfig.signType); // 调用SDK验证签名

        // ——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            //同步调用方法中返回到指定的界面
            //携带订单号并跳转到支付成功的界面
            response.sendRedirect(request.getContextPath() + "/web/paySuccess.html?oid=" + out_trade_no);

            log.info("********************** 支付成功(支付宝同步通知) **********************");
            log.info("* 订单号: {}", out_trade_no);
            log.info("* 支付宝交易号: {}", trade_no);
            log.info("* 实付金额: {}", total_amount);
            log.info("***************************************************************");


        } else {
            log.info("支付, 验签失败...");
        }
    }



}
