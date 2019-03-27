package com.cdutcm.tcms.biz.model;

import java.util.Date;

import com.cdutcm.core.util.JsonIDSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ExecRecipelItem {
	@JsonSerialize(using = JsonIDSerializer.class)
	private long id;
	
    // 处方id
    private long recipelId;
    
	private String name;

	@JsonProperty(value = "time")
	private String time;

	@JsonIgnore
	private Date lastupdate;

	@JsonProperty(value = "zz_method")
	private String zzMethod;

	@JsonProperty(value = "freq")
	private String freq;

	@JsonProperty(value = "strength")
	private String strength;

	@JsonProperty(value = "bx")
	private String bx;

	@JsonProperty(value = "jd")
	private String jd;

	@JsonProperty(value = "sd")
	private String sd;

	@JsonProperty(value = "jl")
	private String jl;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "gj")
	private String gj;

	@JsonProperty(value = "other_gf")
	private String otherGf;

	@JsonProperty(value = "cxxh")
	private String cxxh;

	@JsonProperty(value = "yj")
	private String yj;

	@JsonProperty(value = "jz")
	private String jz;

	@JsonProperty(value = "power")
	private String power;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getZzMethod() {
		return zzMethod;
	}

	public void setZzMethod(String zzMethod) {
		this.zzMethod = zzMethod;
	}

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getBx() {
		return bx;
	}

	public void setBx(String bx) {
		this.bx = bx;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public String getJl() {
		return jl;
	}

	public void setJl(String jl) {
		this.jl = jl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getOtherGf() {
		return otherGf;
	}

	public void setOtherGf(String otherGf) {
		this.otherGf = otherGf;
	}

	public String getCxxh() {
		return cxxh;
	}

	public void setCxxh(String cxxh) {
		this.cxxh = cxxh;
	}

	public String getYj() {
		return yj;
	}

	public void setYj(String yj) {
		this.yj = yj;
	}

	public String getJz() {
		return jz;
	}

	public void setJz(String jz) {
		this.jz = jz;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public long getRecipelId() {
		return recipelId;
	}

	public void setRecipelId(long recipelId) {
		this.recipelId = recipelId;
	}

	

}
