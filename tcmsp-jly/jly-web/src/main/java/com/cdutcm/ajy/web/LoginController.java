package com.cdutcm.ajy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.common.util.Const;
import com.cdutcm.core.shiro.entity.User;
import com.cdutcm.core.shiro.service.MenuService;
import com.cdutcm.core.shiro.service.UserService;

/**
/**
 * @author zhufj
 *
 */
@Controller
@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService; 
//	@Autowired
//	private CountdataService countdataService;
	
	
	/**
	 * 访问登录页
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loginGet(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/login.html");
		return mv;
	}
	 @RequestMapping(value="/userlogin",method=RequestMethod.POST)
		public ModelAndView userlogin(HttpSession session,@RequestParam String loginname,@RequestParam String password,
				String systemType,Model model,HttpServletResponse response,HttpServletRequest request) throws Exception{

		    ModelAndView mv = new ModelAndView();
		    String errInfo = "";// && sessionCode.equalsIgnoreCase(code)
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(loginname, password);
			try{
			subject.login(token);
			}catch (AuthenticationException e) {
	           
	            errInfo = "用户名或密码有误！";
	            mv.addObject("errInfo", errInfo);
				mv.addObject("loginname",loginname);
				mv.addObject("password",password);
				mv.setViewName("/login.html");
				return mv;
	        }
			User u=new User();
			u.setAccount(loginname);
			u.setPassword(password);
			User user=userService.selectuserbypsd(u);
			session.setAttribute(Const.SESSION_USER, user);
//			Lunar l = new Lunar(System.currentTimeMillis());
//			String lunar = "农历：" + l.getLunarDateString() + "（" + l.getTermString() + "），干支历：" + l.getCyclicalDateString();
//			session.setAttribute(Const.LUNAR, lunar);
			mv.setViewName("redirect:/main");
			return mv;
	
		}
		
	//注册
	@RequestMapping(value="/rigister",method=RequestMethod.GET)
	public ModelAndView rigisterGet(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/rigister.html");
		return mv;
	}
	
    //跳转未授权页面
	@RequestMapping(value="/Unauthorized")
	public ModelAndView Unauthorized(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/page_500.html");
		return mv;
	}
}
