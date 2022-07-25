//为了页面能够同时使用，因此将这些方法抽离出来

//商品列表，鼠标移入时加阴影、移出移除阴影
function classFunction() {
    $(".goods-panel").hover(function() {
        $(this).css("box-shadow", "0px 0px 8px #888888");
    }, function() {
        $(this).css("box-shadow", "");
    })
}

//取消收藏商品
function cancelCollect(fid){
    $.ajax({
        url: "/favorites/updateStatus",
        type: "post",
        data: {fid:fid,status:0},
        dataType: "json",
        success: function (res) {
            alert("取消成功")
        },
        error : function (err) {
            alert("服务器出现错误，取消失败！")
        }
    })
}


//显示或隐藏密码的方法
function showPasswordOrNot(eleId,imgId){
    let pwd = document.getElementById(eleId)
    let img = document.getElementById(imgId)
    if (pwd.type == "password"){
        pwd.type = "text";
        img.src = "../images/img/close.jpeg"
    }else {
        pwd.type = "password";
        img.src = "../images/img/open.jpeg"
    }
}

//给图片验证码绑定点击事件，刷新验证码
function reFlashImg(imgId) {
    let kaptcha = document.getElementById(imgId)
    kaptcha.src = "/kaptcha/kaptcha-image?time="+ new Date();
}




