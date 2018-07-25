package com.cdutcm.core.exception;

import java.io.Writer;

import org.beetl.core.ConsoleErrorHandler;
import org.beetl.core.exception.BeetlException;
import org.springframework.stereotype.Component;

import com.cdutcm.core.util.SpringUtil;
import com.cdutcm.tcms.biz.mapper.SysLogMapper;
import com.cdutcm.tcms.biz.model.SysLog;

/**
 * @author       zhufj
 * @Description  重载页面报错处理类
 * @Date         2018-3-21
 */
@Component
public class ReThrowConsoleErrorHandler extends ConsoleErrorHandler{
	
    @Override
    public void processExcption(BeetlException ex, Writer writer){
    	SysLogMapper logMapper = (SysLogMapper) SpringUtil.getBean("sysLogMapper");
    	SysLog log = logMapper.selectByPrimaryKey(126470515362627584L);
    	System.out.println(log.getDescription());
        super.processExcption(ex, writer);
        throw ex;
    }
}