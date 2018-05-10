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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xieyu.ms.domain.Menu;
import com.xieyu.ms.repository.MenuRepository;
import com.xieyu.ms.service.MenuService;

/**
 * 类功能描述 菜单相关操作
 *
 * @author	谢宇
 * @version	2018年4月1日 下午10:10:23
 */
@Service
public class MenuServiceImpl implements MenuService
{
	private Logger lg = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "menuRepository")
	private MenuRepository menuRepository;

	/**
	 * 返回当前客户可见的菜单树
	 */
	public List<Menu> menuList(Long id)
	{
		lg.debug("查询全部菜单");
		Sort sort = new Sort(Sort.Direction.DESC, "orderIndex");
		List<Menu> mList = menuRepository.findAll(sort);

		lg.debug("组装菜单树");
		List<Menu> mTree = createMenuTree(mList);

		lg.debug("获得如下菜单:{}" + mTree.toString());
		return mTree;
	}

	/**
	 * 组装菜单树
	 * @param mList 菜单节点列表
	 * @return 按照树结构拼装的菜单列表
	 */
	private List<Menu> createMenuTree(List<Menu> mList)
	{
		if (mList == null || mList.size() <= 0)
		{
			return null;
		}
		// 1.获取所有一级菜单
		List<Menu> mTree = new ArrayList<Menu>();
		for (Iterator<Menu> iterator = mList.iterator(); iterator.hasNext();)
		{
			Menu menu = iterator.next();
			if (menu.getPid() == -1)
			{
				mTree.add(menu);
				iterator.remove();
			}
		}
		// 2.递归为一级菜单设置子菜单
		for (Menu menu : mTree)
		{
			if (StringUtils.isBlank(menu.getUrl()))
			{
				menu.setChildren(getChildren(mList, menu.getId()));
			}
		}
		return mTree;
	}

	/**
	 * 递归获取菜单的子菜单
	 * @param mList
	 * @param pid
	 * @return
	 */
	private List<Menu> getChildren(List<Menu> mList, int pid)
	{
		if (mList.size() <= 0)
		{
			return null;
		}
		List<Menu> children = new ArrayList<Menu>();
		for (Iterator<Menu> iterator = mList.iterator(); iterator.hasNext();)
		{
			Menu menu = iterator.next();
			if (menu.getPid() == pid)
			{
				children.add(menu);
				iterator.remove();
			}
		}
		for (Menu menu : children)
		{
			if (StringUtils.isBlank(menu.getUrl()))
			{
				menu.setChildren(getChildren(mList, menu.getId()));
			}
		}
		if (children.size() <= 0)
		{
			return null;
		}
		return children;
	}

}
