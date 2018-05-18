package com.cdutcm.ajy.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.ajy.service.BaseJltzService;
import com.cdutcm.jly.entity.BaseJltz;

@RestController
@RequestMapping(value = "jltz")
public class BaseJltzController {

	@Autowired
	private BaseJltzService baseJltzService;
	
	@RequestMapping(value = "/selectJltz")
	public ModelAndView selectJltz(BaseJltz record){
		ModelAndView mv=new ModelAndView("/jltz/base_jltz.html");
		List<BaseJltz> tzs=baseJltzService.selectBytz(record);
		mv.addObject("datas", tzs);
		return mv;			
	}
}
