package com.cdutcm.ajy.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.ajy.service.FamilyMemberService;
import com.cdutcm.common.util.Const;
import com.cdutcm.core.shiro.entity.Menu;
import com.cdutcm.core.shiro.entity.User;
import com.cdutcm.core.shiro.service.MenuService;
import com.cdutcm.jly.entity.FamilyMember;

/**
 * @Auther: Mxq
 * @Date: 2018/4/24 21:24
 * @Description:
 */
@Controller
@RestController
public class MenuController {
    @Autowired
    private FamilyMemberService aFamilyMemberService;
    @Autowired
    private MenuService menuService;
    //主页
    @RequestMapping(value="/main")
     public ModelAndView mainGet(HttpSession session){
       ModelAndView mv = new ModelAndView();
       // 读取session中user的值
       User user= (User) session.getAttribute(Const.SESSION_USER);
       long uId=user.getId();
       FamilyMember fuser=aFamilyMemberService.selectByUserId(uId);//根据userid查询用户信息
       List<Menu> menus=alllistList(1,0);
       mv.addObject("menus", menus);
       mv.addObject("fuser",fuser);
       mv.setViewName("/main.html");
       return mv;
    }
    @RequestMapping(value="/mainRecordInfo")
    public ModelAndView mainRecordInfoGet(HttpSession session){
        ModelAndView mv = new ModelAndView();
        //读取session中user的值
         User user= (User) session.getAttribute(Const.SESSION_USER);
        Long uId=user.getId();
        FamilyMember fuser=aFamilyMemberService.selectByUserId(uId);//根据userid查询用户信息
        mv.addObject("fuser",fuser);
        mv.setViewName("/ajy/mainRecordInfo.html");
        return mv;
    }
    public  List<Menu> alllistList(long userid,long parentid){
        List<Menu> menu=menuService.selectmenubyuserid(userid, parentid);
        if(menu!=null){
      	  for (int i = 0; i < menu.size(); i++) {
				menu.get(i).setSubmenu(alllistList(userid,menu.get(i).getId()));	
			}
        }
		return menu;
}
}