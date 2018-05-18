package com.cdutcm.ajy.web;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.ajy.service.FamilyMemberService;
import com.cdutcm.common.message.SysMsg;
import com.cdutcm.common.util.Const;
import com.cdutcm.common.util.DateUtil;
import com.cdutcm.common.util.IdWorker;
import com.cdutcm.core.shiro.entity.User;
import com.cdutcm.jly.entity.FamilyMember;


@RestController
@RequestMapping(value = "aFamilyMember")
public class FamilyMemberController {

	@Autowired
	private FamilyMemberService aFamilyMemberService;
	
	@RequestMapping(value = "/")
	public ModelAndView  editFamily(FamilyMember fuser){
		ModelAndView m=new ModelAndView("/ajy/aloneInfo.html");
		m.addObject("familyMember", fuser);
		return m;
	}
	//跳转到修改个人档案信息
	@RequestMapping(value = "/editFamilyMember", method = RequestMethod.GET)
	public ModelAndView  editFamilyMember(Long id){
		ModelAndView m=new ModelAndView("/ajy/aloneInfo.html");
		FamilyMember f=aFamilyMemberService.selectByPrimaryKey(id);	
		m.addObject("data", f);
		return m;
	}
	//更换头像
	@ResponseBody
	@RequestMapping(value = "/saveImg",method = RequestMethod.POST)
	public FamilyMember saveImg(@RequestParam(value="file",required=false)MultipartFile file, HttpServletRequest request,FamilyMember familyMember){
		 
		    File targetFile=null;
		    String headImg="";//返回存储路径
		    int code=1;
	        IdWorker i = new IdWorker();
	        String fileName=file.getOriginalFilename();//获取文件名加后缀
	        if(fileName!=null&&fileName!=""){   
	            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/img/headImg/";//存储路径
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
	        }
	        return familyMember.result(code, headImg);
	    }
           
	
	//修改个人信息
	@RequestMapping(value = "/saveFamily", method = RequestMethod.POST)
	public SysMsg saveFamily(FamilyMember familyMember,HttpServletResponse response) throws Exception{				
		   SysMsg m=new SysMsg();
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
	public ModelAndView  familyTable(Long  rid){
		ModelAndView m=new ModelAndView("/ajy/family_table.html");
		List<FamilyMember> family=aFamilyMemberService.selectByRId(rid);
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
		m.addObject("rid", rid); //关联ID
		return m; 
	}
	//删除
	@RequestMapping(value = "/delFamily")
	public SysMsg delFamily(FamilyMember record){
		SysMsg m=new SysMsg();
		int i=aFamilyMemberService.deleteByPrimaryKey(record.getId());
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
	public SysMsg addFamily(FamilyMember record,HttpSession session,HttpServletResponse response) throws Exception {
		SysMsg m=new SysMsg();
		IdWorker w = new IdWorker();
			Long id=w.nextId();
			record.setId(id); //成员ID
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
	//修改信息
	/*@RequestMapping(value = "/editInfo",produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public SysMsg editFamily(HttpServletRequest request) throws Exception{
		SysMsg m=new SysMsg();
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream, "utf-8"));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		body = stringBuilder.toString();
		List<FamilyMember> items=(List<FamilyMember>)JSONArray.toList(JSONArray.fromObject(body), FamilyMember.class);
		for(FamilyMember item:items){
			if (item.getId()==null){
				IdWorker w = new IdWorker();
				item.setId(w.nextId());
				aFamilyMemberService.insert(item);
				}else{
			int i=aFamilyMemberService.updateByPrimaryKey(item);
			if (i > 0) {
				m.setStatus("TS");
				m.setContent("修改成功");
			} else {
				m.setStatus("FS");	
					}
				}
		}
		return m;
	}*/
}
