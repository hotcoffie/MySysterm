/**
 * Copyright © {year}, Forp Co., LTD
 *
 * All Rights Reserved.
 *
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
 */

package com.xieyu.ms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xieyu.ms.domain.User;
import com.xieyu.ms.service.impl.UserServiceImpl;

/**
 * 类功能描述 用户
 *
 * @author	谢宇
 * @version	2018年3月22日 下午7:57:17
 */
@RestController
public class UserController extends BaseController
{
	private Logger lg = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserServiceImpl userService;

	/**
	 * 跳转到登录界面
	 * @return
	 */
	@RequestMapping("/to-login")
	public ModelAndView toLogin()
	{
		lg.debug("请求跳转到登录页面");
		return new ModelAndView("login");
	}

	@RequestMapping("/login")
	public Object login(User user, HttpSession session) throws Exception
	{
		lg.debug("登录请求");
		userService.checkUser(user);
		session.setAttribute("user", user);
		return success();
	}

	/**
	 * 登出
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest req)
	{
		User user = getSessionUser(req);
		lg.debug("登出请求,用户ID:{}", user.getId());
		return new ModelAndView("login");
	}

}
