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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xieyu.ms.domain.User;
import com.xieyu.ms.service.MenuService;
import com.xieyu.ms.service.UserService;

/**
 * 类功能描述 
 *
 * @author	谢宇
 * @version	2018年3月15日 下午8:44:32
 */
@RestController
public class HomeController extends BaseController
{
	private Logger lg = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "menuServiceImpl")
	private MenuService menuService;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	/**
	 * 跳转到主页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/")
	public ModelAndView toIndex() throws Exception
	{
		return new ModelAndView("index");
	}

	/**
	 * 返回主页菜单信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/menu", method = RequestMethod.GET)
	public Object index(HttpServletRequest req) throws Exception
	{
		lg.debug("查询当前用户可见菜单");
		User user = getSessionUser(req);
		return menuService.menuList(user.getId());
	}

}
