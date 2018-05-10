package com.xieyu.ms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the base_logs database table.
 * 
 */
@Entity
@Table(name = "base_logs")
@NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l")
public class Logs implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "add_time", insertable = false, updatable = false)
	private Date addTime;

	@Column(name = "`desc`", updatable = false)
	private String desc;

	@Column(name = "log_type", updatable = false)
	private int logType;

	@Column(name = "operator_id", updatable = false)
	private Long operatorId;

	public Logs()
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

	public Date getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime(Date addTime)
	{
		this.addTime = addTime;
	}

	public String getDesc()
	{
		return this.desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public int getLogType()
	{
		return this.logType;
	}

	public void setLogType(int logType)
	{
		this.logType = logType;
	}

	public Long getOperatorId()
	{
		return this.operatorId;
	}

	public void setOperatorId(Long operatorId)
	{
		this.operatorId = operatorId;
	}

}