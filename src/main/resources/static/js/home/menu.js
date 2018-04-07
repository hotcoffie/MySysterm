$(document).ready(function() {
	/*
	 * menu
	 */
	$('#main-menu').metisMenu();

	$(window).bind("load resize", function() {
		if ($(this).width() < 768) {
			$('div.sidebar-collapse').addClass('collapse')
		} else {
			$('div.sidebar-collapse').removeClass('collapse')
		}
	});
	/*
	 * 收起菜单
	 */
	$("#sideNav").click(function() {
		if ($(this).hasClass('closed')) {
			$('.navbar-side').animate({
				left: '0px'
			});
			$(this).removeClass('closed');
			$('#page-wrapper').animate({
				'margin-left': '260px'
			});

		} else {
			$(this).addClass('closed');
			$('.navbar-side').animate({
				left: '-260px'
			});
			$('#page-wrapper').animate({
				'margin-left': '0px'
			});
		}
	});
});