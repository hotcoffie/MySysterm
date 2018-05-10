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

package com.xieyu.ms.sysconfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 类功能描述 登录配置
 *
 * @author	谢宇
 * @version	2018年4月13日 下午7:29:17
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter
{
	/**
	 * 登录session key
	 */
	public final static String SESSION_KEY = "user";

	public final static String PATH_TO_LOGIN = "/to-login";
	public final static String PATH_LOGIN = "/login**";
	public final static String PATH_ERROR = "/error**";

	@Bean
	public SecurityInterceptor getSecurityInterceptor()
	{
		return new SecurityInterceptor();
	}

	public void addInterceptors(InterceptorRegistry registry)
	{
		InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

		// 排除配置
		addInterceptor.excludePathPatterns(PATH_TO_LOGIN);
		addInterceptor.excludePathPatterns(PATH_LOGIN);
		addInterceptor.excludePathPatterns(PATH_ERROR);

		// 拦截配置
		addInterceptor.addPathPatterns("/**");
	}

	private class SecurityInterceptor extends HandlerInterceptorAdapter
	{

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
		{
			HttpSession session = request.getSession();
			if (session.getAttribute(SESSION_KEY) != null) return true;

			// 跳转登录
			response.sendRedirect(PATH_TO_LOGIN);
			return false;
		}
	}
}
