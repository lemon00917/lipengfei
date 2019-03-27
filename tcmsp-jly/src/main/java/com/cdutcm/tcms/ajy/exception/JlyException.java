package com.cdutcm.tcms.ajy.exception;

import com.cdutcm.tcms.ajy.enums.ResultEnum;

/**
 * 统一异常处理
 * @author mxq
 */
public class JlyException  extends RuntimeException{
	private Integer code;
	public JlyException(Integer code, String msg){
		super(msg);
		this.code = code;
	}
	public JlyException(ResultEnum resultEnum){
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}
}
