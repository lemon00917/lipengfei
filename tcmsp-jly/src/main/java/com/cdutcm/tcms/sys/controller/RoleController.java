/*package com.cdutcm.tcms.sys.controller;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.util.IdWorker;
import com.cdutcm.core.util.RightsHelper;
import com.cdutcm.core.util.StringUtil;
import com.cdutcm.tcms.sys.entity.Menu;
import com.cdutcm.tcms.sys.entity.Role;
import com.cdutcm.tcms.sys.service.MenuService;
import com.cdutcm.tcms.sys.service.RoleService;

@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	
	*//**
	 * 显示角色列表
	 * @param map
	 * @return
	 *//*
	@RequestMapping(value="/listPageRoles")
	public ModelAndView list(Role role){
		ModelAndView mv = new ModelAndView();
		List<Role> roleList = roleService.listPagerole(role);
		mv.addObject("roleList", roleList);
		mv.setViewName("/admin/role_list.html");
		return mv;
	}
	
	*//**
	 * 保存角色信息
	 * @param out
	 * @param role
	 *//*
	//@SysControllerLog(name=Const.SAVE_ROLE)
	@RequestMapping(value="/save")
	public void save(PrintWriter out,Role role){
		int i;
		if(role.getRoleId()!=0){
			
			i= roleService.updaterole(role);
		}else{
			role.setRoleId(new IdWorker().nextId());
			 i = roleService.insertrole(role);
		}
		if(i>0){
			out.write("success");
		}else{
			out.write("failed");
		}
		out.flush();
		out.close();
	}
	
	*//**
	 * 请求角色授权页面
	 * @param roleId
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value="/auth")
	public ModelAndView auth(@RequestParam Long roleId){
		ModelAndView mv = new ModelAndView();
		List<Menu> menuList = alllistList(roleId, 0);
	    JSONArray arr = JSONArray.fromObject(menuList);
		String json = arr.toString();
		json = json.replaceAll("menu_name", "name").replaceAll("submenu", "nodes").replaceAll("hasmenu", "checked");
		mv.addObject("zTreeNodes", json);
		mv.addObject("roleId", roleId);
		model.addAttribute("zTreeNodes", json);
		model.addAttribute("roleId", roleId);
		mv.setViewName("/admin/rolepower_info.html");
		return mv;
	}
	
	*//**
	 * 保存角色权限
	 * @param roleId
	 * @param menuIds
	 * @param out
	 *//*
	//@SysControllerLog(name=Const.SAVE_ROLE_PERMISSIONS)
	@RequestMapping(value="/auth/save")
	public void saveAuth(@RequestParam Long roleId,@RequestParam String menuIds,PrintWriter out){
		BigInteger rights = RightsHelper.sumRights(StringUtil.str2StrArray(menuIds));
		Role role = roleService.getRoleById(roleId);
		role.setRights(rights.toString());
		roleService.updateRoleRights(role);
		out.write("success");
		out.close();
	}
	
	*//**
	 * 删除角色信息
	 * @param roleId
	 * @param menuIds
	 * @param out
	 *//*
	//@SysControllerLog(name=Const.DELETE_ROLE)
	@RequestMapping(value="/del")
	public void del(PrintWriter out,@RequestParam long roleId){
		if(StringUtil.notEmpty((String.valueOf(roleId)) )){
			 roleService.deleteRoleById(roleId);		
			 out.write("success");
		}
		else{	
		out.write("failed");
		}
		out.close();
	}
	*//**
	 * 请求添加页面
	 *//*
	@RequestMapping(value="/add")
	public ModelAndView toAdd(){
		ModelAndView mv = new ModelAndView();
		Role role =new Role();
		mv.addObject("role", role);
		mv.setViewName("/admin/role_info.html");
		return mv;
	}
	@RequestMapping(value="/edit")
	public ModelAndView toedit(@RequestParam long roleId){
		ModelAndView mv = new ModelAndView();
		Role role =roleService.getRoleById(roleId);
		mv.addObject("role", role);
		mv.setViewName("/admin/role_info.html");
		return mv;
	}
    public  List<Menu> alllistList(long roleid,long parentid){
        List<Menu> menu=menuService.selectmenubyroleid(roleid, parentid);
        if(menu!=null){
      	  for (int i = 0; i < menu.size(); i++) {
				menu.get(i).setSubmenu(alllistList(roleid,menu.get(i).getId()));	
			}
        }
		return menu;
}
}
*/