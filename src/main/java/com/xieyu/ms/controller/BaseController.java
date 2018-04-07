package com.xieyu.ms.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xieyu.ms.domain.User;
import com.xieyu.ms.exceptions.MyException;
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

}
