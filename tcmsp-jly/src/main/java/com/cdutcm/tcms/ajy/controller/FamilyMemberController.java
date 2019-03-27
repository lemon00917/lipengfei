package com.cdutcm.tcms.ajy.controller;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.Const;
import com.cdutcm.core.util.DateUtil;
import com.cdutcm.core.util.IdWorker;
import com.cdutcm.tcms.ajy.model.FamilyMember;
import com.cdutcm.tcms.ajy.service.DictService;
import com.cdutcm.tcms.ajy.service.FamilyMemberService;
import com.cdutcm.tcms.biz.model.BaseRecipelItem;
import com.cdutcm.tcms.ajy.model.Dict;
import com.cdutcm.tcms.sys.entity.User;

import net.sf.json.JSON;
import net.sf.json.JSONArray;


@RestController
@RequestMapping(value = "aFamilyMember")
public class FamilyMemberController {

	@Autowired
	private FamilyMemberService aFamilyMemberService;
	@Autowired
	private DictService dictService;
	
	@RequestMapping(value = "/showAlone")
	public ModelAndView  showAlone(Long id,Dict record){
		ModelAndView m=new ModelAndView("/ajy/showAlone.html");
		FamilyMember f=aFamilyMemberService.selectByPrimaryKey(id);	
		List<Dict> d=dictService.listallDict(record);
		m.addObject("data", f);
		m.addObject("dicts", d);
		return m;
	}
	//跳转到修改个人档案信息
	@RequestMapping(value = "/editFamilyMember", method = RequestMethod.GET)
	public ModelAndView  editFamilyMember(Long id,Dict record){
		ModelAndView m=new ModelAndView("/ajy/aloneInfo.html");
		FamilyMember f=aFamilyMemberService.selectByPrimaryKey(id);	
		List<Dict> d=dictService.listallDict(record);
		m.addObject("data", f);
		m.addObject("dicts", d);
		return m;
	}
	
	//跳转到修改家庭成员档案
		@RequestMapping(value = "/editFamily", method = RequestMethod.GET)
		public ModelAndView  editFamily(Long id,Dict record){
			ModelAndView m=new ModelAndView("/ajy/editFamily.html");
			FamilyMember f=aFamilyMemberService.selectByPrimaryKey(id);	
			List<Dict> d=dictService.listallDict(record);
			m.addObject("data", f);
			m.addObject("dicts", d);
			return m;
		}
	@RequestMapping(value = "/watchFamilyMember", method = RequestMethod.GET)
	public ModelAndView watchFamilyMember(Long id,Dict record){
		ModelAndView m=new ModelAndView("/ajy/watchAloneInfo.html");
		FamilyMember f=aFamilyMemberService.selectByPrimaryKey(id);	
		List<Dict> d=dictService.listallDict(record);
		m.addObject("data", f);
		m.addObject("dicts", d);
		return m;
	}
	//更换头像
	@ResponseBody
	@RequestMapping(value = "/saveImg",method = RequestMethod.POST)
	public FamilyMember saveImg(@RequestParam(value="file",required=false)MultipartFile file, HttpServletRequest request,FamilyMember familyMember) throws UnknownHostException{
		    File targetFile=null;
		    String headImg="";//返回存储路径
		    int code=1;
	        IdWorker i = new IdWorker();
	        String fileName=file.getOriginalFilename();//获取文件名加后缀
	            String returnUrl = request.getScheme() + "://" + InetAddress.getLocalHost().getHostAddress().toString() + ":" + request.getServerPort() + request.getContextPath() +"/img/headImg/";//存储路径
	            String path = request.getSession().getServletContext().getRealPath("/img/headImg"); //文件存储位置
	            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
	            fileName=new Date().getTime()+"_"+i.nextId()+fileF;//新的文件名
	          
	            //先判断文件是否存在
	            String fileAdd = DateUtil.format(new Date(),"yyyyMMdd");
	            File file1 =new File(path+"/"+fileAdd); 
	            //如果文件夹不存在则创建    
	            if(!file1 .exists()  && !file1 .isDirectory()){       
	                file1 .mkdir();  
	            }
	            targetFile = new File(file1, fileName);
	        
	            try {
	                file.transferTo(targetFile);	             
	                headImg=returnUrl+fileAdd+"/"+fileName;
	                code=0;
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	           
	        return familyMember.result(code, headImg);	    
} 
	
	//修改个人信息
	@RequestMapping(value = "/saveFamily", method = RequestMethod.POST)
	public SysMsg saveFamily(FamilyMember familyMember,HttpServletResponse response,HttpServletRequest request) throws Exception{				
		   SysMsg m=new SysMsg();
		   String img=familyMember.getHeadImg();	  
		   if(img==""){
			   img=request.getScheme() + "://" + InetAddress.getLocalHost().getHostAddress().toString()
					   + ":" + request.getServerPort() + request.getContextPath() +"/img/headImg/"+"user.png";
			   familyMember.setHeadImg(img);		 
		   }
           int i= aFamilyMemberService.updateByPrimaryKey(familyMember);
          
           if(i>0){
        	   m.setStatus("TS");
        	   m.setContent("修改成功");
           }else{
        	   m.setStatus("FS");
        	   m.setContent("修改失败");
           }
			return m;          	
	}
	//跳转到家庭成员表
	@RequestMapping(value = "/familyTable")
	public ModelAndView  familyTable(Long  rid,Dict record){
		ModelAndView m=new ModelAndView("/ajy/family_table.html");
		List<FamilyMember> family=aFamilyMemberService.selectByRId(rid);
		List<Dict> d=dictService.listallDict(record);		
		m.addObject("dicts", d);
		m.addObject("datas", family);
		return m; 
	}
	//跳转添加页面
	@RequestMapping(value = "/insertfamily")
	public ModelAndView  insertfamily(FamilyMember record,HttpSession session){
		ModelAndView m=new ModelAndView("/ajy/addfamily.html");
		User user= (User) session.getAttribute(Const.SESSION_USER);
		Long userID=user.getId(); //获取userID
		FamilyMember familyMember=aFamilyMemberService.selectByUserId(userID);
		Long rid=familyMember.getId();
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String date = format.format(new Date());
		int num=(int) ((Math.random()*9+1)*1000);
		Integer.toString(num);
		String user_briefid=date+num; //birefId 日期加随机数字
		m.addObject("bid", user_briefid);
		m.addObject("rid", rid); //关联ID
		return m; 
	}
	//删除
	@RequestMapping(value = "/delFamily")
	public SysMsg delFamily(Long familyId){
		SysMsg m=new SysMsg();
		int i=aFamilyMemberService.deleteByPrimaryKey(familyId);
		if (i > 0) {
			m.setStatus("TS");
			m.setContent("成功删除");
		} else {
			m.setStatus("FS");
			m.setContent("删除失败!");
		}
		return m;
		
	}
	
	//添加家庭成员信息
	@RequestMapping(value = "/addFamily", method = RequestMethod.POST)
	public SysMsg addFamily(FamilyMember record,HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception {
		SysMsg m=new SysMsg();
		    IdWorker w = new IdWorker();
			Long id=w.nextId();
			record.setId(id); //成员ID
			 String img=record.getHeadImg();	  
			   if(img==""){
				   img=request.getScheme() + "://" + InetAddress.getLocalHost().getHostAddress().toString()
						   + ":" + request.getServerPort() + request.getContextPath() +"/img/headImg/"+"user.png";
				   record.setHeadImg(img);	 //没有设置头像的 默认设置user.png头像	 
			   }
			int i=aFamilyMemberService.insert(record);
			if(i>0){
				m.setStatus("TS");
				m.setContent("添加成功");
			}else{
				m.setStatus("FS");
				m.setContent("添加失败");
			}
			return m;
			
	}
	
}
