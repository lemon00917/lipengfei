package com.cdutcm.tcms.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdutcm.tcms.sys.entity.Menu;
import com.cdutcm.tcms.sys.entity.User;
import com.cdutcm.tcms.sys.service.MenuService;
import com.cdutcm.tcms.sys.service.UserRoleService;
import com.cdutcm.tcms.sys.service.UserService;

public class UserRealm  extends AuthorizingRealm{
	
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    
   
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo (PrincipalCollection principals)  {
		// TODO Auto-generated method stub
		    String username = (String)principals.getPrimaryPrincipal();
		    User user=userService.selectuserbyaccount(username);
		 //   List<Role> roles=userRoleService.selectallrolebyuserid(user.getId());
		    long parentid = 0;
		    List<Menu> menus=menuService.selectmenubyuserid(user.getId(), parentid);
	        List<String> userRoles=new ArrayList<String>();
	        List<String> userPermissions=new ArrayList<String>();
	        if(null != user){  
	           /* for (int i = 0; i < roles.size(); i++) {
	                userRoles.add(roles.get(i).getRole_name());
	            }*/
	            for (int i = 0; i < menus.size(); i++) {
	            	userPermissions.add(menus.get(i).getParams());
				}
	        }else{  
	            throw new AuthorizationException();  
	            
	        }  
	        System.out.println("#######获取角色："+userRoles);
	        System.out.println("#######获取权限："+userPermissions);
	        //为当前用户设置角色和权限  
	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	        authorizationInfo.addRoles(userRoles);
	        authorizationInfo.addStringPermissions(userPermissions); 
	        System.out.println("未见异常");
	        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = (String)authenticationToken.getPrincipal();
	    //通过username从数据库中查找 User对象，如果找到，没找到.
	    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
	    User user=userService.selectuserbyaccount(username);
	  
	    if(user == null){
	        return null;
	    }
	    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	    		username, //用户名
	            user.getPassword(), //密码
	           /* ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
*/	            getName()  //realm name
	    );
	    return authenticationInfo;
	
	}

}
