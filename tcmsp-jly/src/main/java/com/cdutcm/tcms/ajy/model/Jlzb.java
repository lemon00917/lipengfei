package com.cdutcm.tcms.ajy.model;

import java.util.List;

public class Jlzb {

	private List<Double> List1; //左边穴位数值集合
	 
	private List<Double> List2;//右边穴位数值集合
	
	private Double M; //平均值
	
	private Double L1;
	
	private Double L2;
	
	private Double L3;
	
	private Double H1;
	
	private Double H2;
	
	private Double H3;
	
	private Double Zlsj; //自律神经
	
	private Double YinYang;// 阴/阳=（A1+A+C+C1）/（B1+B+D+D1）
	
	private Double ShangXia;// 上/下=（A1+A+B+B1）/（C1+C+D+D1）
	
	private Double ZuoYou; // 左/右=（A+B+C+D）/（A1+B1+D1+C1）

	private String jl;//经络名称
	
	public String getJl() {
		return jl;
	}

	public void setJl(String jl) {
		this.jl = jl;
	}

	public List<Double> getList1() {
		return List1;
	}

	public void setList1(List<Double> list1) {
		List1 = list1;
	}

	public List<Double> getList2() {
		return List2;
	}

	public void setList2(List<Double> list2) {
		List2 = list2;
	}

	public Double getM() {
		return M;
	}

	public void setM(Double m) {
		M = m;
	}

	public Double getL1() {
		return L1;
	}

	public void setL1(Double l1) {
		L1 = l1;
	}

	public Double getL2() {
		return L2;
	}

	public void setL2(Double l2) {
		L2 = l2;
	}

	public Double getL3() {
		return L3;
	}

	public void setL3(Double l3) {
		L3 = l3;
	}

	public Double getH1() {
		return H1;
	}

	public void setH1(Double h1) {
		H1 = h1;
	}

	public Double getH2() {
		return H2;
	}

	public void setH2(Double h2) {
		H2 = h2;
	}

	public Double getH3() {
		return H3;
	}

	public void setH3(Double h3) {
		H3 = h3;
	}

	public Double getZlsj() {
		return Zlsj;
	}

	public void setZlsj(Double zlsj) {
		Zlsj = zlsj;
	}

	public Double getYinYang() {
		return YinYang;
	}

	public void setYinYang(Double yinYang) {
		YinYang = yinYang;
	}

	public Double getShangXia() {
		return ShangXia;
	}

	public void setShangXia(Double shangXia) {
		ShangXia = shangXia;
	}

	public Double getZuoYou() {
		return ZuoYou;
	}

	public void setZuoYou(Double zuoYou) {
		ZuoYou = zuoYou;
	}
	
}
