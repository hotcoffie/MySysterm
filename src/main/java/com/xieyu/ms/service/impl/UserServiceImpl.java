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

package com.xieyu.ms.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xieyu.ms.domain.User;
import com.xieyu.ms.enums.AccountStatusEnum;
import com.xieyu.ms.exceptions.MyException;
import com.xieyu.ms.repository.UserRepository;
import com.xieyu.ms.service.UserService;
import com.xieyu.ms.utils.CodeUtils;

/**
 * 类功能描述 用户相关操作
 *
 * @author	谢宇
 * @version	2018年3月16日 上午2:08:02
 */
@Service
public class UserServiceImpl implements UserService
{
	private Logger lg = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	public List<User> search()
	{
		lg.debug("查询用户列表");
		return userRepository.findAllByAccountStatus(AccountStatusEnum.USED.getValue());
	}

	/**
	 * 检测user是否是合法的用户
	 * @param user
	 * @throws NoSuchAlgorithmException 
	*/
	public void checkUser(User user) throws Exception
	{
		// 1.有效性验证
		if (user == null)
		{
			throw new MyException("用户信息有误");
		}
		String account = user.getAccount();
		String password = user.getPassword();
		if (StringUtils.isBlank(account) || StringUtils.isBlank(password))
		{
			throw new MyException("用户信息有误");
		}
		lg.debug("登陆请求,数据有效性验证通过");

		// 2.密码验证
		password = CodeUtils.md5(password);
		User sysUser = userRepository.findByAccountAndAccountStatus(account, AccountStatusEnum.USED.getValue());
		if (!sysUser.getPassword().equals(password))
		{
			throw new MyException("用户信息有误");
		}
		lg.debug("登陆请求,账号密码验证通过");
	}

	/**
	 * 新建用户
	 * @param user
	 * @return
	*/
	public Long createUser(User user) throws Exception
	{
		// 1.有效性验证
		// 1.1输入信息有效性
		String errorMsg = "您提交的注册信息有误，请检查后重新提交";
		if (user == null || StringUtils.isAnyBlank(user.getAccount(), user.getPassword(), user.getName()))
		{
			throw new MyException(errorMsg);
		}
		String account = user.getAccount();
		String password = user.getPassword();

		int len = account.length();
		if (len < 5 || len > 10)
		{
			throw new MyException(errorMsg);
		}
		len = password.length();
		if (len < 6 || len > 12)
		{
			throw new MyException(errorMsg);
		}
		len = user.getName().length();
		if (len < 2 || len > 10)
		{
			throw new MyException(errorMsg);
		}

		// 1.2唯一性验证
		User back = userRepository.findByAccountAndAccountStatus(account, AccountStatusEnum.USED.getValue());
		if (back != null)
		{
			throw new MyException("您申请的账号已存在，请输入新的账号");
		}

		// 2.持久化
		user.setPassword(CodeUtils.md5(password));
		back = userRepository.save(user);

		return back.getId();
	}

}
