function getPidFromLastHtml(){
    //接收上一个页面传来的连接
    var hrefUrl = location.href;
    //以url中的"="为截断点，形成一个数组
    var param = hrefUrl.split("=")
    //decodeURI解码得到想要的参数
    var pid = decodeURI(param[1]);
    return pid;
}

//返回一个参数
function getOne(){
    var result;
    //返回字符串从url的?处开始
    var url = decodeURI(window.location.search);
    //如果等于-1，代表没有找到，即网页连接没有携带任何参数
    if (url.indexOf("?") != -1){
        //返回一个新的字符串，从url连接=符号处索引+1的位置开始返回
        result = url.substr(url.indexOf("=")+1);
    }
    return result;
}

//有多个参数时，返回json对象
function getMany() {
    var params = "{";
    //假设连接是这样的
    // http://localhost:8080/web/orderConfirm.html?cids=5&cids=4
    //返回字符串从url的?处开始
    var url = decodeURI(window.location.search); //?cids=5&cids=4
    if (url.indexOf("?") != -1){
        var str = url.substr(1) // cids=5&cids=4
        var strItem = str.split("&") //[cids=5,cids=4]
        var key = [strItem.length] // 2
        var value = [strItem.length] // 2
        for (let i = 0; i < strItem.length; i++) { // 0;2;i++
            key[i] = strItem[i].split("=")[0] //key[0]= cids
            value[i] = decodeURI(strItem[i].split("=")[1]) //value[0] = 5
            params = params + "\"" + key[i] + "\":\"" + value[i] + "\""; //params = { "cids":"5",
            if(i != strItem.length - 1) { //i != 1
                params += ','
            }
        }
    }
    params = params + '}'

    return params;
}
//根据指定得name获取参数值
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}