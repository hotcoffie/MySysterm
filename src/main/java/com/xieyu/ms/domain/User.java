package com.xieyu.ms.domain;

import java.io.Serializable;
import java.util.Date;

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
 * The persistent class for the base_user database table.
 * 
 */
@Entity
@Table(name = "base_user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String account;

	@Column(name = "account_status", insertable = false)
	private int accountStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "add_time", insertable = false, updatable = false)
	private Date addTime;

	@Column(name = "dept_id")
	private Long deptId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_time", insertable = false)
	private Date lastTime = new Date();

	private String name;

	private String password;

	@Transient
	private String deptName;

	public User()
	{
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getAccount()
	{
		return this.account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public int getAccountStatus()
	{
		return this.accountStatus;
	}

	public void setAccountStatus(int accountStatus)
	{
		this.accountStatus = accountStatus;
	}

	public Date getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Date addTime)
	{
		this.addTime = addTime;
	}

	public Long getDeptId()
	{
		return this.deptId;
	}

	public void setDeptId(Long deptId)
	{
		this.deptId = deptId;
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

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", account=" + account + ", accountStatus=" + accountStatus + ", addTime=" + addTime + ", deptId=" + deptId + ", lastTime=" + lastTime + ", name=" + name
				+ ", password=" + password + ", deptName=" + deptName + "]";
	}

}