package com.cdutcm.tcms.ajy.enums;

/**
 * 
 * @author mxq
 * 异常类型枚举
 */
public enum ResultEnum {
	NOT_FIND_RECORD(0,"没有此人的记录")
    ;
    private Integer code;
    private String msg;
    //构造方法
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
	public Integer getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
    
}
