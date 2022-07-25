$(function() {
	/*图片变大的样式*/
	$(".move-img").hover(function() {
		$(this).animate({
			"background-size": "110%"
		}, "fast");
	}, function() {
		$(this).animate({
			"background-size": "100%"
		}, "fast");
	})
})