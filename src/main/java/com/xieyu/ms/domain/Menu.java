package com.xieyu.ms.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the base_menu database table.
 * 
 */
@Entity
@Table(name = "base_menu")
@NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
public class Menu implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "add_time")
	private Date addTime;

	@Column(name = "add_user_id")
	private int addUserId;

	private String cid;

	private String icon;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_time")
	private Date lastTime;

	private String name;

	@Column(name = "order_index")
	private byte orderIndex;

	private int pid;

	private String url;

	/**
	 * 所有子菜单
	 */
	@Transient
	private List<Menu> children;

	public List<Menu> getChildren()
	{
		return children;
	}

	public void setChildren(List<Menu> children)
	{
		this.children = children;
	}

	public Menu()
	{
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Date getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Date addTime)
	{
		this.addTime = addTime;
	}

	public int getAddUserId()
	{
		return this.addUserId;
	}

	public void setAddUserId(int addUserId)
	{
		this.addUserId = addUserId;
	}

	public String getCid()
	{
		return this.cid;
	}

	public void setCid(String cid)
	{
		this.cid = cid;
	}

	public String getIcon()
	{
		return this.icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public Date getLastTime()
	{
		return this.lastTime;
	}

	public void setLastTime(Date lastTime)
	{
		this.lastTime = lastTime;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public byte getOrderIndex()
	{
		return this.orderIndex;
	}

	public void setOrderIndex(byte orderIndex)
	{
		this.orderIndex = orderIndex;
	}

	public int getPid()
	{
		return this.pid;
	}

	public void setPid(int pid)
	{
		this.pid = pid;
	}

	public String getUrl()
	{
		return this.url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	@Override
	public String toString()
	{
		return "Menu [id=" + id + ", addTime=" + addTime + ", addUserId=" + addUserId + ", cid=" + cid + ", icon=" + icon + ", lastTime=" + lastTime + ", name=" + name + ", orderIndex=" + orderIndex
				+ ", pid=" + pid + ", url=" + url + "]";
	}

}