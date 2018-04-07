package com.xieyu.ms.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class CodeUtils
{

	/**
	 * MD5加密:摘要算法 
	 * 特点:任意长度字节处理成等长结果,且结果不可逆 
	 * Base64:a-z A-Z 0-9 = +
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5(String src) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] output = md.digest(src.getBytes());
		String ret = Base64.encodeBase64String(output);
		return ret;

	}

	// UUID生成ID
	public static String createId()
	{
		String id = UUID.randomUUID().toString();
		return id;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException
	{
		System.out.println(md5("123456"));
	}
}
