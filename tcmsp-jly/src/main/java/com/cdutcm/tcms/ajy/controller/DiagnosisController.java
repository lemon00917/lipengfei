package com.cdutcm.tcms.ajy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DiagnosisController {
	
	@RequestMapping("/Diagnosis")
	public ModelAndView ToDiagnosisPage(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/ajy/Diagnosis.html");
		return mv;
		}

}
