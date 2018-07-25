/*package com.cdutcm.tcmsp.ajy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TcmspAjyApplicationTests {

	@Test
	public void contextLoads() {
			Map<Integer, Double> m1 = new HashMap<Integer, Double>(); //左边穴位数组
		    Map<Integer, Double> m2 = new HashMap<Integer, Double>();//右边穴位数组
			double sum = 0;
			int arr1[]={-1,4,6,3,-5,12,3,3,-3,-2,-2,-1,-1,-3,-10,-4,-6,-7,-5,-6,8,6,4,9};
			List<Double> ojlzb=new ArrayList<Double>();
			List<Double> jlzb=new ArrayList<Double>(); //接收经络指标的数组
			for(int i=0;i<arr1.length;i++){
				Double m=(double) arr1[i];
				ojlzb.add(m);
			}
		
			for(int i=0;i<ojlzb.size();i++){
				sum=sum+ojlzb.indexOf(i); //求总数
			}
			Double	M=sum/24; //总能量平均值
			for(Double m3:ojlzb){ //将处理后的数值 放入数组中
				jlzb.add(m3-M); //数值=采集的值-M
			}
			
			for(int i=0;i<ojlzb.size();i++){ //将处理后的数值 放入数组中
				Double mm=ojlzb.indexOf(i)-M; //数值=采集的值-M
				jlzb.add(mm);  
			}
			
			Double A1= (double) jlzb.indexOf(1)+jlzb.indexOf(17)+jlzb.indexOf(9);//右手三阴能量总和即肺经  （1）+ 心包经（9） + 心经  （5）
			Double A=(double)jlzb.indexOf(0)+jlzb.indexOf(16)+jlzb.indexOf(8);//左手三阴能量总和即肺经  （1）+ 心包经（9） + 心经  （5）
			Double C1=(double)jlzb.indexOf(7)+jlzb.indexOf(23)+jlzb.indexOf(15); //右足三阴能量总和即脾经  （4）+ 肝经  （12）+ 肾经  （8）
			Double C=(double)jlzb.indexOf(6)+jlzb.indexOf(22)+jlzb.indexOf(14);// 左足三阴能量总和即脾经  （4）+ 肝经  （12）+ 肾经  （8）
			Double B1=(double)jlzb.indexOf(3)+jlzb.indexOf(19)+jlzb.indexOf(11);//右手三阳能量总和即大肠经（2）+ 三焦经（10）+ 小肠经（6）
			Double B=(double)jlzb.indexOf(2)+jlzb.indexOf(18)+jlzb.indexOf(10);//左手三阳能量总和即大肠经（2）+ 三焦经（10）+ 小肠经（6）
			Double D1=(double)jlzb.indexOf(5)+jlzb.indexOf(21)+jlzb.indexOf(13);//右足三阳能量总和即胃经  （3）+ 胆经  （11）+ 膀胱经（7）
			Double D=(double)jlzb.indexOf(4)+jlzb.indexOf(20)+jlzb.indexOf(12);// 左足三阳能量总和即胃经  （3）+ 胆经  （11）+ 膀胱经（7）
			Double L1=M*0.8;
			Double L2=M*0.6;
			Double L3=M*0.4;
			Double H1=M*1.2;
			Double H2=M*1.4;
			Double H3=M*1.6;
			Double[] ayy2={A,A1,B,B1,C,C1,D,D1};
			传感平衡数据
			Double yin=A1+A+C+C1; //阴=A1+A+C+C1 
			Double yang=B1+B+D+D1;//阳=B1+B+D+D1  
			Double shang=A1+A+B+B1;//上=A1+A+B+B1 
			Double xia=C1+C+D+D1;// 下=C1+C+D+D1       
			Double zuo=A+B+C+D;//  左=A+B+C+D     
			Double you=A1+B1+D1+C1; //右=A1+B1+D1+C1
			Double max=(double) 0;  //最大值
			Double min=(double) 0;  //最小值
			
			计算最大值最小值
			for(int i=0;i<ayy2.length;i++){
				if(ayy2[i]>max){
					max=ayy2[i];
				}
				if(ayy2[i]<min){
					min=ayy2[i];
				}
			}
			System.out.println("最大值:"+max);
			System.out.println("最小值："+min);
			Double zlsj=max/min; //自律神经
			Double yinyang=yin/yang;//阴/阳=（A1+A+C+C1）/（B1+B+D+D1）
			Double shangxia=shang/xia;// 上/下=（A1+A+B+B1）/（C1+C+D+D1）
			Double zuoyou=zuo/you; // 左/右=（A+B+C+D）/（A1+B1+D1+C1）
			
			for(int i=0;i<jlzb.size();i=i+2){
				m1.put(i, (double) jlzb.get(i));			
			}
			for(int i=1;i<jlzb.size();i=i+2){
				m2.put(i, (double) jlzb.get(i));
			}
			
			System.out.println(m1);
			System.out.println(m2);
			
			for(Double zz:jlzb){
				System.out.println("经络指标:"+zz);
			}
			System.out.println("平衡值："+M);
			System.out.println("L1:"+L1);
			System.out.println("L2:"+L2);
			System.out.println("L3:"+L3);
			System.out.println("H1:"+H1);
			System.out.println("H2:"+H2);
			System.out.println("H3:"+H3);
			
			System.out.println("自律神经:"+zlsj);
			System.out.println("阴/阳:"+yinyang);
			System.out.println("上/下:"+shangxia);
			System.out.println("左/右:"+zuoyou);
			
				
		
	}

}
*/