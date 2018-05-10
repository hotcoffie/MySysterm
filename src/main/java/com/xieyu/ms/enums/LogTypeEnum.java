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

package com.xieyu.ms.enums;

/**
 * 类功能描述 日志类型
 * 增删改查无
 *
 * @author	谢宇
 * @version	2018年4月13日 下午6:52:16
 */

public enum LogTypeEnum
{
	N(0, "无"), C(1, "新建"), R(1, "查询"), U(1, "修改"), D(1, "删除");

	private int value;
	private String name;

	/**
	 * 创建一个新的实例 LogTypeEnum.
	 *
	 */

	private LogTypeEnum(int value, String name)
	{
		this.value = value;
		this.name = name();
	}

	public int getValue()
	{
		return value;
	}

	public String getName()
	{
		return name;
	}

	public LogTypeEnum get(int value)
	{
		for (LogTypeEnum e : LogTypeEnum.values())
		{
			if (e.getValue() == value)
			{
				return e;
			}
		}
		return null;
	}
}
