// ------------------------------------------------------------------------------------------
// 标题:MessageBox
// 说明:基于layer插件封装的便捷消息提示函数
// 作者:谢宇
// 时间:2018-03-26
// ------------------------------------------------------------------------------------------

/**
 * 提示对话框
 */
function info(message, callback) {
	if (callback) {
		layer.open({
			skin: 'layui-layer-lan', // 蓝色风格
			closeBtn: 0,
			icon: 1,
			title: '提示',
			content: message,
			yes: function(index, layero) {
				if (callback) callback.call(this);
				layer.close(index); // 如果设定了yes回调，需进行手工关闭
			}
		});
	} else {
		layer.msg(message, {
			icon: 1
		});
	}
}

/**
 * 警告对话框
 */
function warn(message, callback) {
	layer.open({
		skin: 'layui-layer-lan', // 蓝色风格
		closeBtn: 0,
		icon: 0,
		title: '警告',
		content: message,
		yes: function(index, layero) {
			if (callback) callback.call(this);
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
		}
	});
}

/**
 * 错误对话框
 */
function error(message, callback) {
	layer.open({
		skin: 'layui-layer-lan', // 蓝色风格
		closeBtn: 0,
		icon: 2,
		title: '错误',
		content: message,
		yes: function(index, layero) {
			if (callback) callback.call(this);
			layer.close(index); // 如果设定了yes回调，需进行手工关闭
		}
	});
}

/**
 * 确认对话框
 *
 * @param	message				对话框提示信息
 * @param	confirmCallback		确定按钮点击回调function
 * @param	cancelCallback		取消按钮点击回调function
 */
function confirm(message, confirmCallback, cancelCallback) {
	layer.confirm(message, {
		skin: 'layui-layer-lan', // 蓝色风格
		closeBtn: 0,
		icon: 3,
		title: '提示',
		btn: ['确定', '取消']
	// 按钮
	}, function(index) {
		if (confirmCallback) confirmCallback.call(this);
		layer.close(index);
	}, function(index) {
		if (cancelCallback) cancelCallback.call(this);
		layer.close(index);
	});
}

// ------------------------------------------------------------------------------------------
// 说明:设置jQuery Ajax全局的参数
// 作者:谢宇
// 时间:2018-03-26
// ------------------------------------------------------------------------------------------
$(function() {
	$.ajaxSetup({
		type: "POST",
		// 统一处理ajax常见异常
		error: function(jqXHR, textStatus, errorThrown) {
			switch (jqXHR.status) {
			case (400):
				alert("您输入的数据格式有误");
				break;
			case (401):
				alert("您长时间没有操作，需要重新验证您的账号安全。<br/>请重新登录系统！");
				break;
			case (403):
				alert("您无权限执行此操作");
				break;
			case (404):
				alert("请求的路径不存在");
				break;
			case (408):
				alert("请求超时");
				break;
			case (500):
				alert("系统出现错误,请联系管理员");
				break;
			default:
				alert("未知错误");
			}
		},
		// 设置遮罩层
		beforeSend: function(xhr) {
			xhr.layerIndex = layer.load(2, {
				shade: [0.3, '#000']
			});
		},
		complete: function(xhr, status) {
			layer.close(xhr.layerIndex);
		}
	});
});
// ------------------------------------------------------------------------------------------
// 说明:设置Validation错误提示
// 作者:谢宇
// 时间:2018-04-3
// ------------------------------------------------------------------------------------------
if ($.validator) {
	$.validator.setDefaults({
		highlight: function(element, errorClass, validClass) {
			// 添加[输入错误]边框样式提示
			$(element).parent().addClass("has-error");
		},
		unhighlight: function(element, errorClass, validClass) {
			// 添加[输入正确]边框样式提示
			$(element).parent().removeClass("has-error");

			// 清除之前的OpenTip
			var tips = $(element).data("opentips");
			if (tips && tips.length > 0) {
				tips[0].deactivate();
			}
		},
		errorPlacement: function(error, element) {
			// 设置OpenTip
			var msg = $(error).text();
			// 有错误消息的设置提示,没有错误消息的清除提示
			if (!msg) return;
			// tip
			var tips = $(element).data("opentips");
			if (tips && tips.length > 0) {
				tips[0].activate();
				tips[0].setContent(msg);
			} else {
				$(element).opentip(msg, {
					target: false,
					removeElementsOnHide: true
				});
			}
		}
	});
}

// ------------------------------------------------------------------------------------------
// 说明:常用方法
// 作者:谢宇
// 时间:2018-03-27
// ------------------------------------------------------------------------------------------

/**
* 回车键执行指定方法 [适用于工具栏中的查询条件回车键直接查询功能]
*
* @param event     回车键事件
* @param searchFn	search function
*/
function enterKey(event, doFn) {
	if (13 == event.keyCode && doFn) doFn.call(this);
}
