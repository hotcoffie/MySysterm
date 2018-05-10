package com.xieyu.ms.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xieyu.ms.domain.Logs;
import com.xieyu.ms.domain.User;
import com.xieyu.ms.enums.LogTypeEnum;
import com.xieyu.ms.exceptions.MyException;
import com.xieyu.ms.service.LogService;
import com.xieyu.ms.utils.Result;

/**
 * 基础Controller
 * @author 谢宇
 *
 */
@RestController
@ControllerAdvice
public class BaseController
{

	private Logger lg = LoggerFactory.getLogger(this.getClass());

	@Resource
	private LogService logService;

	/**
	 * 获取Session上的用户数据
	 * @param session
	 * @return
	 */
	public User getSessionUser(HttpServletRequest req)
	{
		return (User) req.getSession().getAttribute("user");
	}

	/**
	 * 全局异常处理
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler
	public Result processException(Exception e)
	{
		Result rs = new Result();
		rs.setSuccess(false);
		if (e instanceof MyException)
		{
			lg.info(e.getMessage());
			rs.setMsg(e.getMessage());
		}
		else
		{
			lg.error("系统出错", e);
			rs.setMsg("系统出现错误,请联系管理员");
		}

		return rs;
	}

	/**
	 * 通用ajax返回方法
	 * @param msg
	 * @return
	 */
	protected Result success()
	{
		Result rs = new Result();
		rs.setSuccess(true);

		return rs;
	}

	/**
	 * 通用ajax返回方法
	 * @param msg
	 * @return
	 */
	protected Result success(String msg)
	{
		Result rs = new Result();
		rs.setSuccess(true);
		rs.setMsg(msg);

		return rs;
	}

	/**
	 * 通用ajax返回方法
	 * @param msg
	 * @return
	 */
	protected Result success(Object data)
	{
		Result rs = new Result();
		rs.setSuccess(true);
		rs.setData(data);

		return rs;
	}

	/**
	 * 通用ajax返回方法
	 * 
	 * @param msg
	 * @return 
	 */
	protected Result error(String msg)
	{
		Result rs = new Result();
		rs.setSuccess(false);
		rs.setMsg(msg);

		return rs;

	}

	/**
	 * 记录本次请求的操作日志
	 * @param desc 日志描述
	 * @param type 日志类型
	 * @param req 请求头
	 * @throws Exception 
	 */
	public void systermLog(String desc, LogTypeEnum type, HttpServletRequest req) throws Exception
	{
		Logs log = new Logs();
		log.setDesc(desc);
		log.setAddTime(new Date());
		log.setLogType(type.getValue());
		User user = getSessionUser(req);
		log.setOperatorId(user.getId());

		logService.createLog(log);
	}

	/**
	 * 记录本次请求的操作日志
	 * @param desc 日志描述
	 * @param type 日志类型
	 * @param userId 操作人ID
	 * @throws Exception 
	 */
	public void systermLog(String desc, LogTypeEnum type, User user) throws Exception
	{
		Logs log = new Logs();
		log.setDesc(desc);
		log.setLogType(type.getValue());
		log.setOperatorId(user.getId());

		logService.createLog(log);
	}
}
