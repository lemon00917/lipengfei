	function dayin(){
		var LODOP=getLodop();
		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_分页打印综合表格");
		LODOP.SET_PRINT_STYLE("FontName","宋体")	
		var strStyle="<style> .head{border-bottom: 2px solid black;border-top: 2px solid black;background-color: #D3D3D3; }</style>"
		//LODOP.ADD_PRINT_HTM(20,"3%","94%",900,strStyle+document.getElementById("d1").innerHTML);
			LODOP.ADD_PRINT_HTM(20,"3%","94%",130,document.getElementById("d1").innerHTML);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3)
		LODOP.ADD_PRINT_HTM(220,"3%","94%",900,strStyle+document.getElementById("d5").innerHTML);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3) //经络传感数据图表
		
		var d2style = "<style>.dashtable tr td {height:5px;width:5px;border-bottom:1.5px dotted #000;}</style>";
		LODOP.ADD_PRINT_HTM(650,"3%","94%",1000,d2style+strStyle+document.getElementById("d2").innerHTML);
		
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3) //平衡传感问题分析
		LODOP.ADD_PRINT_TEXT(1070,330,"76.25%",40,"本报告辅助临床诊断");
		LODOP.ADD_PRINT_TEXT(1070,600,"76.25%",40,"CopyRight@ 2018 e-health");
		LODOP.ADD_PRINT_HTM(1,600,300,100,"当前：<font color='#0000ff' format='ChineseNum'><span tdata='pageNO'>第##页</span>/<span tdata='pageCount'>共##页</span></font>");
		LODOP.NewPageA();
		
		LODOP.ADD_PRINT_HTM(50,"3%","90%",900,strStyle+document.getElementById("d3").innerHTML);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3) //体质特征
		
		LODOP.ADD_PRINT_HTM(900,"3%","90%",900,strStyle+document.getElementById("d4").innerHTML);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3) //体质特征
		LODOP.ADD_PRINT_TEXT(1070,330,"76.25%",40,"本报告辅助临床诊断");
		LODOP.ADD_PRINT_TEXT(1070,600,"76.25%",40,"CopyRight@ 2018 e-health");
		LODOP.ADD_PRINT_HTM(1,600,300,100,"当前：<font color='#0000ff' format='ChineseNum'><span tdata='pageNO'>第##页</span>/<span tdata='pageCount'>共##页</span></font>");	
		
		/*var ym= document.getElementById("d2").innerHTML;
		LODOP.ADD_PRINT_HTM(20,"3%","94%",130,document.getElementById("d2").innerHTML);
		LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
		LODOP.SET_PRINT_STYLEA(0,"LinkedItem",1);	
	    LODOP.ADD_PRINT_HTM(0,"3%","94%",30,document.getElementById("d3").innerHTML);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
		LODOP.ADD_PRINT_TEXT(0,330,"76.25%",40,"本报告辅助临床诊断");
		LODOP.ADD_PRINT_TEXT(0,600,"76.25%",40,"CopyRight@ 2018; e-health");
		LODOP.ADD_PRINT_HTM(1,600,300,100,"当前：<font color='#0000ff' format='ChineseNum'><span tdata='pageNO'>第##页</span>/<span tdata='pageCount'>共##页</span></font>");
		 
		
		var d5style = "<style>	.dashtable tr td {height:5px;width:5px;border-bottom:1.5px dotted #000;}</style>";
		LODOP.ADD_PRINT_HTM(150,"3%","94%",900,d5style+strStyle+document.getElementById("d5").innerHTML);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3);	
		LODOP.ADD_PRINT_HTM(20,"3%","94%",130,document.getElementById("d4").innerHTML);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
	    LODOP.ADD_PRINT_HTM(1010,"3%","94%",30,document.getElementById("d3").innerHTML);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
		LODOP.ADD_PRINT_TEXT(1070,330,"76.25%",40,"本报告辅助临床诊断");
		LODOP.ADD_PRINT_TEXT(1070,600,"76.25%",40,"CopyRight@ 2018 e-health");
		LODOP.ADD_PRINT_HTM(1,600,300,100,"当前：<font color='#0000ff' format='ChineseNum'><span tdata='pageNO'>第##页</span>/<span tdata='pageCount'>共##页</span></font>");*/
        LODOP.PREVIEW();  
}
