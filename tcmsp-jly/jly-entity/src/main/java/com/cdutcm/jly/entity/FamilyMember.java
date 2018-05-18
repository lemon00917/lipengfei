package com.cdutcm.jly.entity;

import java.util.Date;
import java.util.Map;

import com.cdutcm.common.page.Page;

public class FamilyMember {

	private Long id;
	
	private Long userId;
	
	private String userBriefid;
	
	private String headImg;
	
	private Long relationId;
	
	private String sex;
	
	private Date birthday;
	
	private String nation;
	
	private Double height;
	
	private Double weight;
	
	private String profession;
	
	private String idCard;
	
	private String address;
	
	private Integer isIllnesshistory;
	
	private String illnesshistoryDesc;
	
	private Date gmtCreate;
	
	private Date gmtModified;
	
	private Page page;
	
	private String username;
	
	private String relationName;
	
   private Integer code;
	 
	private Map<String, Object> result;
	 
	private String age;
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserBriefid() {
		return userBriefid;
	}

	public void setUserBriefid(String userBriefid) {
		this.userBriefid = userBriefid;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIsIllnesshistory() {
		return isIllnesshistory;
	}

	public void setIsIllnesshistory(Integer isIllnesshistory) {
		this.isIllnesshistory = isIllnesshistory;
	}

	public String getIllnesshistoryDesc() {
		return illnesshistoryDesc;
	}

	public void setIllnesshistoryDesc(String illnesshistoryDesc) {
		this.illnesshistoryDesc = illnesshistoryDesc;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
//上传图片 返回结果
	
	 private static FamilyMember familyMember;
	 
	   public static FamilyMember result(int code, String headImg, Map<String, Object> map){
		   familyMember = new FamilyMember();
		   familyMember.setCode(code);
		   familyMember.setHeadImg(headImg);
		   familyMember.setResult(map);
	        return familyMember;
	    }
	   public static FamilyMember result(int code, String headImg){
		   familyMember = new FamilyMember();
		   familyMember.setCode(code);
		   familyMember.setHeadImg(headImg);
	        return familyMember;
	    }
	   
	   public FamilyMember() {}

	 }


