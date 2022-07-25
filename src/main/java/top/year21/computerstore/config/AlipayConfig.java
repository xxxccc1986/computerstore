package top.year21.computerstore.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 支付宝沙箱支付配置类
 * @date 2022/7/25 1:29
 */
@Data
@Component
public class AlipayConfig {
    //自己的appId
    public static String appId = "2021000121634026";
    //应用私有秘钥
    public static String appPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDDnIWV/KsE+T3hpSr7+H//GdKsTbVcc/fpsp/RI9Aqfg0z009TrUI+NQ9+6mI4YxQUZnnUM1sRcnAQI4ivnGu2qkZFoot7gv50jwvZD8amIe6b3Joed2DBOKps2IJuyOwjV4Ae+WBJW5bSNiuuYu6UjanX69nK8TU6y6K39p02YnDqC47Z0veKVbAmpbsOnks1gGd+Cgcm5fSsdcx+aGKSt6YEJuYkqri9lUExHSYkZLNfzzAXRujyLCk0lLHdzUNDoatIyukk1HhCyvLosg3oaCtvIzqGC8/XHCEQ5r8JD4nKd/gGoyolWzhJvFcFkKmHjPZA8vT8TP1ZC1z1m1DvAgMBAAECggEBAI0C7JnvByoSsrVTZ+U0grDXYLOtYSxAvVrO1b7iXlIDhGjzz5+2qqZFgeIv/JZBdlwuc2yxiNjO8lHwC7zsugl4Pig8wOhMyjokVJopcT6Z/3SEVuXXkPw5aUIF4iES3oersESj6PF5AQSQ4HRaBTs51FI/R0WxFHpKCgcr1LE6inEn862CvZlbh2Cqh6rqBxB1ESi4eRhAj+FsJlczqWDCLn2dG1Ki7IhkuApdNFs9lurGeXGu/FBphaaprAiTVWuxyBWIdW5TYACjkNZ+oXrzCJK6t0DWsmxAbaZHMiLIVIAypZ6p8/kf/b70zXRYhkXQ6KUKpo983G6dBBPUF0ECgYEA7ta+jK3OZvzwZQeaBXYS4vi28jgpLg/v/DFpdcwaAShKEms/R7XZ8wZ9y4jabQKOuyJX69NS7dhfNv4wdW976BkIevwANM6haFSh5bJsq+EK8fwXEPez2uKPLsIdSgBwwh6En4vClOUTOOprLuyTXM7mQNqSW+AsaLmlaGYDuhcCgYEA0aqkcJMysXnGIc7nbJDiWvsRihe5WMsMCOY6vtjVPMh/qEasT9Hxhv4Gs+Pso15MNxhDuRddp+qgXyuUXGkeii8h+p2MgO5O5tA1T74APaDclmyiuc27Rj27Cc9mbFTWOk5txrXgzqEJjw9HudUHTJmpKoqI2aVEK73X6AdB3ukCgYAqHwk/+i8ajqU+zBZnvCkcikyJb0oj63+hdH1q3vH/HkHh+bQRS4sChzSMPrh23Sqa6jWjS4OmmrBAHJgjPeQWTMPoHKVUqtRgd/yNa+gqb+fkQVc4ENdRVP93eZh8wpMgSQ2OrbFFXRkEwqLghax/g6Wr7mA9f82VMphvTv59RQKBgA3YnxNwJSDjUdpZt57L0qb/faEJAAyFHD5aNfb0iuCAvS13vVloG/M2Q2sN2krPp2jcCVzn1h+Itx6R2jJgHswxYKUUUnsRQdSsW1jwy0NGpEqq0fRDSeLRoNB9Cd6Nm7guBcHhsP70U5VHBQ2Yq+q7GxjcHT2CVIYu+1svX4JBAoGARyBB9IjgM9dH0cH8djojC2qlH7Qar9bbvl55i9EQY51d1J/bC5JBE/CAYu0s1MmIatigcJ6A7FyvJ+nnow+qr4tegi5CG76v+Ue/IrgZXJZyDqMBrHorn/SwRXleOj2gjfxkQ8mh0OkrIntbdD6SDnvtXkxcVkfX9ICY5VIA4DA=";
    //支付宝公钥
    public static String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjtedJ8C1NIO3r4vuMThcvTMZxqO3Jki5VYPfkmnraA/PIKXXgsfOdoSWxCsiqPMIBMRT3Oyk1EsxgAeFBKTFaSM5LC8oinTXFbkv+3XEuOjtfqbp0oIgu9pWfJQDL2gIVSbm3VKmdE4UtJ36nu3hyuTT3U19QQsKVgxMDWHCOIw0eCHcJm1xDPj0zmagL3jC7576sXHcnFxEKARGugMpP9bkBgvFkjKrnkQfMAz3OO8vUSC0lCGo2UrSwhyD6zqXVz39sIduVpKTTg+wpAJQ/RhBhLXNw4JW3UaZpX2BZbmqEx91Hpr+O/95Z90cTqT+rwyu6uW612B5bCPnKa+BCQIDAQAB";
    //异步回调地址
    public static String notifyUrl = "http://1.14.176.219:8080/alipay/notifyNotice";
    //同步回调地址
    public static String returnUrl = "http://1.14.176.219:8080/alipay/returnNotice";
    //推荐使用这个秘钥
    public static String signType = "RSA2";
    //使用的编码格式
    public static String charset = "utf-8";
    //支付宝默认网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

}
