package com.cdutcm.ajy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Auther: Mxq
 * @Date: 2018/4/26 
 * @Description: 健康分析
 */

@Controller
@RestController
public class healthAnalyseController {
	@GetMapping("/healthAnalyse")
	public ModelAndView healthAnalyse(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/ajy/healthAnalyse.html");
        return mv;
	}
	@GetMapping("/healthAnalyseContent")
	public ModelAndView healthAnalyseContent(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/ajy/healthAnalyseContent.html");
        return mv;
	}
}
