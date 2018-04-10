/**
 * 页面初始化
 */
$(function() {
	// 绑定标签页点击事件
	$('.login-tag-btn').click(clickTagBtn);
});

/**
 * 标签页点击事件 
 * 1.播放标签切换动画 
 * 2.切换显示注册/登录表格
 */
function clickTagBtn() {
	var $btn = $(this);
	if (!$btn.hasClass('active')) {
		var $tag = $btn.parents('.login-tag:first');// 标签框体
		var $btnAct = $tag.find('.active');// 原来被选中的标签

		// 1.移除选中
		$tag.find('.login-tag-btn').removeClass('active');

		// 2.初始化动画线宽度
		var $line = $('<div class="login-tag-line"></div>');
		$line.width($btnAct.width());
		$btnAct.append($line);

		// 3.获取动画线偏移量('+='表示相对当前位置偏移,不加的话总是相对最初位置偏移)
		var toLeft = ($btn.offset().left - $btnAct.offset().left) + 'px';
		var toTop = ($btn.offset().top - $btnAct.offset().top) + 'px';
		// 4.播放动画
		$line.animate({
			left: toLeft,
			top: toTop
		}, function() {
			$line.remove();
			$btn.addClass('active');
			$('#login').toggleClass('hide');
			$('#regist').toggleClass('hide');
		});
	}
}

/**
 * 发起登录请求
 * @returns
 */
function clickLogin() {
	// 1.获取表单对象
	var $form = $('#login');

	// 2.表单验证
	$form.validate();
	if (!$form.valid()) {
		warn('表单信息不完整，请根据提示调整内容');
		return;
	}

	// 3.发送ajax请求
	$.ajax({
		url: '/login',
		data: $form.serialize(),
		success: function(rs) {
			if (rs.success) {
				$('#toIndex')[0].click();
			} else {
				error(rs.msg);
			}
		}
	});
}

/**
 * 发起注册请求
 * @returns
 */
function clickRegist() {
	// 1.获取表单对象
	var $form = $('#regist');

	// 2.表单验证
	$form.validate();
	if (!$form.valid()) {
		warn('表单信息不完整，请根据提示调整内容');
		return;
	}

	// 3.发送ajax请求
	$.ajax({
		url: '/regist',
		data: $form.serialize(),
		success: function(rs) {
			if (rs.success) {
				info('注册成功!', function() {
					$('#toIndex')[0].click();
				});
			} else {
				error(rs.msg);
			}
		}
	});
}
