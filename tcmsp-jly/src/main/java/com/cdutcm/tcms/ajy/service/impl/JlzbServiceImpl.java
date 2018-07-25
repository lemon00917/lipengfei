package com.cdutcm.tcms.ajy.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cdutcm.tcms.ajy.model.Jlzb;
import com.cdutcm.tcms.ajy.service.JlzbService;
@Service
public class JlzbServiceImpl implements JlzbService{

	@Override
	public Jlzb rejlzb(String result){		 
		 NumberFormat df = NumberFormat.getNumberInstance();  
		 df.setMaximumFractionDigits(2);   //保留两位小数		 
		 Jlzb j=new Jlzb();
		 double sum = 0; 
		 ArrayList<Double> arr1=new ArrayList<Double>();
		//M=20.22
		/*将检测结果转换成数组*/
		String[] arrStr=result.split(",");
		for(String s:arrStr){
			Double d=Double.parseDouble(s);
			arr1.add(d);
		}
		
		List<Double> ojlzb=new ArrayList<Double>();
		List<Double> jlzb=new ArrayList<Double>(); //接收经络指标的数组
		
		List<Double> list1=new ArrayList<Double>(); //接收左边穴位数组
		List<Double> list2=new ArrayList<Double>(); //接收右边穴位数组
		for(int i=0;i<arr1.size();i++){
			Double m=arr1.get(i);
			ojlzb.add(m);
		}
	
		for(int i=0;i<ojlzb.size();i++){
			sum=sum+ojlzb.get(i); //求总数
		}
		
		Double	M=sum/24; //总能量平均值		

		j.setM(M);
		
		for(Double m3:ojlzb){ //将处理后的数值 放入数组中
			jlzb.add(m3-M); //数值=采集的值-M
		}
		/*取24个数值中的最大值*/
	
		/*由最大值找出所在数组位置，根据位置，找出经络*/
		String jl="";
	if(jlzb.size()>0){		
		Double jlzbmax=(double) 0;
		int index=0;
		for(int i=0;i<jlzb.size();i++){
			if(Math.abs(jlzb.get(i))>jlzbmax){ //取绝对值
				jlzbmax=jlzb.get(i);
				index=i;
			}
		}
		if(index==0||index==1){
			jl="肺经";
		}
		if(index==2||index==3){
			jl="大肠经";
		}
		if(index==4||index==5){
			jl="胃经";
		}
		if(index==6||index==7){
			jl="脾经";
		}
		if(index==8||index==9){
			jl="心经";
		}
		if(index==10||index==11){
			jl="小肠经";
		}
		if(index==12||index==13){
			jl="膀胱经";
		}
		if(index==14||index==15){
			jl="肾经";
		}
		if(index==16||index==17){
			jl="心包经";
		}
		if(index==18||index==19){
			jl="三焦经";
		}
		if(index==20||index==21){
			jl="胆经";
		}
		if(index==22||index==23){
			jl="肝经";
		}
	}
		
	j.setJl(jl);
		
		Double A1= (double) jlzb.get(1)+jlzb.get(17)+jlzb.get(9);//右手三阴能量总和即肺经  （1）+ 心包经（9） + 心经  （5）
		Double A=(double)jlzb.get(0)+jlzb.get(16)+jlzb.get(8);//左手三阴能量总和即肺经  （1）+ 心包经（9） + 心经  （5）
		Double C1=(double)jlzb.get(7)+jlzb.get(23)+jlzb.get(15); //右足三阴能量总和即脾经  （4）+ 肝经  （12）+ 肾经  （8）
		Double C=(double)jlzb.get(6)+jlzb.get(22)+jlzb.get(14);// 左足三阴能量总和即脾经  （4）+ 肝经  （12）+ 肾经  （8）
		Double B1=(double)jlzb.get(3)+jlzb.get(19)+jlzb.get(11);//右手三阳能量总和即大肠经（2）+ 三焦经（10）+ 小肠经（6）
		Double B=(double)jlzb.get(2)+jlzb.get(18)+jlzb.get(10);//左手三阳能量总和即大肠经（2）+ 三焦经（10）+ 小肠经（6）
		Double D1=(double)jlzb.get(5)+jlzb.get(21)+jlzb.get(13);//右足三阳能量总和即胃经  （3）+ 胆经  （11）+ 膀胱经（7）
		Double D=(double)jlzb.get(4)+jlzb.get(20)+jlzb.get(12);// 左足三阳能量总和即胃经  （3）+ 胆经  （11）+ 膀胱经（7）
		Double L1=M*0.8;
		Double L2=M*0.6;
		Double L3=M*0.4;
		Double H1=M*1.2;
		Double H2=M*1.4;
		Double H3=M*1.6;
		j.setH1((double) Math.round(H1));
		j.setH2((double) Math.round(H2));
		j.setH3((double) Math.round(H3));
		j.setL1((double) Math.round(L1));
		j.setL2((double) Math.round(L2));
		j.setL3((double) Math.round(L3));
		Double[] ayy2={A,A1,B,B1,C,C1,D,D1};
		/*传感平衡数据*/
		Double yin=A1+A+C+C1; //阴=A1+A+C+C1 
		Double yang=B1+B+D+D1;//阳=B1+B+D+D1  
		Double shang=A1+A+B+B1;//上=A1+A+B+B1 
		Double xia=C1+C+D+D1;// 下=C1+C+D+D1       
		Double zuo=A+B+C+D;//  左=A+B+C+D     
		Double you=A1+B1+D1+C1; //右=A1+B1+D1+C1
		Double max=(double) 0;  //最大值
		Double min=(double) 0;  //最小值
		
		/*计算最大值最小值*/
		for(int i=0;i<ayy2.length;i++){
			if(ayy2[i]>max){
				max=ayy2[i];
			}
			if(ayy2[i]<min){
				min=ayy2[i];
			}
		}
	/*	System.out.println("最大值:"+max);
		System.out.println("最小值："+min);*/
		Double zlsj=max/min; //自律神经
		Double yinyang=yin/yang;//阴/阳=（A1+A+C+C1）/（B1+B+D+D1）
		Double shangxia=shang/xia;// 上/下=（A1+A+B+B1）/（C1+C+D+D1）
		Double zuoyou=zuo/you; // 左/右=（A+B+C+D）/（A1+B1+D1+C1）
		
	/*取两位小数*/
		/*zlsj=Double.valueOf(df.format(zlsj));
		yinyang=Double.valueOf(df.format(yinyang));
		shangxia=Double.valueOf(df.format(shangxia));
		zuoyou=Double.valueOf(df.format(zuoyou));*/
		
		
		j.setZlsj(Double.valueOf(df.format(zlsj)));
		j.setYinYang(Double.valueOf(df.format(yinyang)));
		j.setShangXia(Double.valueOf(df.format(shangxia)));
		j.setZuoYou(Double.valueOf(df.format(zuoyou)));
		
		for(int i=0;i<jlzb.size();i=i+2){
			Double l1=(double) Math.round(jlzb.get(i));
			list1.add(l1);			
		}
		for(int i=1;i<jlzb.size();i=i+2){
			Double l1=(double) Math.round(jlzb.get(i));
			list2.add(l1);		
		}
		j.setList1(list1);
		j.setList2(list2);
		/*System.out.println(m1);
		System.out.println(m2);*/
		
		/*for(Double zz:jlzb){
			System.out.println("经络指标:"+zz);
		}*/
    	/*System.out.println("平衡值："+M);
		System.out.println("L1:"+L1);
		System.out.println("L2:"+L2);
		System.out.println("L3:"+L3);
		System.out.println("H1:"+H1);
		System.out.println("H2:"+H2);
		System.out.println("H3:"+H3);
		
		System.out.println("自律神经:"+zlsj);
		System.out.println("阴/阳:"+yinyang);
		System.out.println("上/下:"+shangxia);
		System.out.println("左/右:"+zuoyou);*/
		
		return j;	
	}
}
