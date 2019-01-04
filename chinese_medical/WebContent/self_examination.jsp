<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中医辅助诊断系统-疾病自查</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<style type="text/css">
	.content{
		width:942px;
		margin:auto;
	}
	.xzzz{
		font-size:16px;
		margin-left:40px;
		margin-top:40px;
		color:#898989;
		font-weight: bolder;
	}
	.searchimg{
		width:16px;
		height:16px;
	}
	.zzzc{
		border:1px solid #194069;
		width:100px;
		height:40px;
		background-color: #194069;
		border-radius:5px 5px 0px 0px;
		color:white;
		line-height: 40px;
		text-align: center;
		margin-top:20px;
		float: left;
		cursor: pointer;
	}
	.jg{
		float: left;
		border:1px solid #DEDEDE;
		width:100px;
		height:35px;
		background-color: #DEDEDE;
		border-radius:0px 5px 0px 0px;
		color: #303030;
		line-height: 35px;
		text-align: center;
		margin-top:25px;
	}
	.box{
		border:2px solid #194069;
	}
	.result{
		border:2px solid #194069;
		width:942px;
		height:700px;
		display: none;
	}
	.box .symptom{
		border-radius:5px;
		border:1px solid #898989;
		height:34px;
		width:300px;
		margin-left:250px;
		margin-top:20px;
	
	}
	.box .search{
		width:200px;
		height:34px;
		border-radius: 5px;
		background-color: #194069;
		color:white;
		margin-left:350px;
		margin-top:100px;
		margin-bottom: 30px;
	}
	.box .plus{
		margin-left:5px;
		width:30px;
		height:30px;
		cursor: pointer;
	}
	.box .flag{
		display:none;
		margin-left:30px;
		width:30px;
		heigth:30px;
	}
	.qz{
		padding-left:20px;
		color:#898989
	}
	.qzvalue{
		width:25px;
		border-radius: 3px;
		border:1px solid #194069;
		text-align: center;
	}
	.leftResult,.disease{
		float: left;
	}
	.leftResult{
		border:1px solid #DEDEDE;
		width:300px;
		height:700px;
	}
	.disease{
		width:638px;
		height:700px;
		border-left:1px solid #DEDEDE;
	}
	.total{
		height:660px;
		overflow: auto;
		margin-left:20px;
	}
	.inputzz{
		height:350px;
	}
	.yjtjzz tr{
		width:300px;
		border-bottom:1px solid #DEDEDE;
		height:34px;
	}
	.yjtj_content{
		width:300px;
		height:34px;
	}
	.yjtj_content tr{
		border-bottom:1px dashed #DEDEDE;
		height:34px;
	}
	.yjtjzz tr td,.yjtj_content tr td{
		text-align: center;
		width:100px;
	}
	.delet{
		width:20px;
		height:20px;
		cursor: pointer;
	}
	.xgzzdiv{
		font-size:16px;
		color:#194069;
		font-weight:bolder;
		text-align: center;
		border-top: 1px solid #DEDEDE;
		border-bottom: 1px solid #DEDEDE;
		height:34px;
		line-height: 34px;
	}
	.xgzzul{
		overflow:auto;
		height:303px;
	}
	.xgzzul li{
		border-bottom:1px dashed #DEDEDE;
		height:34px;
		text-align: center;
		line-height: 34px;
		cursor: pointer;
	}
	.firstrow{
		margin-top:20px;
	}
	.dis{
		background-color: #A0D494;
		color:white;
		width: 37px;
		text-align: center;
	}
	.qzv input{
		width:100px;
		text-align: center;
	}
	.suggest{
		height: auto;
		margin-left:250px;
		display:none;
		width:300px;
		z-index: 99999;
		position: absolute;
		border-left: 1px solid #D6D6D6;
		border-right: 1px solid #D6D6D6;
		border-bottom: 1px solid #D6D6D6;
		background-color: white;
	}
	.suggest ul li{
		margin-left:10px;
	}
	.suggest ul li:hover{
	
		background-color: #DEDEDE;
		cursor: pointer;
	}
	
</style>
<script type="text/javascript">
	$(function(){
		$(this).on("click",".plus",function(){
			$(".house").append("<div class=\"inputbox\">"
					+"<input type=\"text\" class=\"symptom\" /><span class=\"qz\">权重:</span>"
					+"<input type=\"text\" class=\"qzvalue\" value=\"1\"/><div class=\"suggest\">"
					+ "<ul></ul></div></div>");
		});	
		$(this).on("focus",".qzvalue,.qzv input",function(){
			$(this).val("");//获取焦点的时候清除数据
		});
		$(this).on("blur",".qzvalue,.qzv input",function(){
			var input = $(this).val();
			if(isNaN(input)){//数字为false
				alert("权重只能为数字");
			}else{
				if(input>10){
					$(this).val(10);
					
				}else if(input<0){
					$(this).val(1)
				}
			}
			//失去焦点的时候清除数据
			if(input.trim() == ""){
				$(this).val(1);
			}
		});
		
		//获取数据
		function getData(data){
			$.each(data,function(index,dis){
				if(null != dis){
					var dalias = "";
					if(dis.dalias.trim() != ""){
						dalias = "(别名："+ dis.dalias +")";
					}
					var pro = Number(dis.probability / 100).toFixed(2);
					
					if(dis.probability != 0){
						$(".total").append("<div class=\"firstrow\"><a class=\"disname\"><input type=\"hidden\" value=\""+ dis.did +"\"/>"
							+dis.dname+"</a> &nbsp;&nbsp;<label  class=\"dis\">疾病</label>"
							+"&nbsp;&nbsp;<span style=\"color:#666666;\">"+ dalias +"</span></div>"
							+"<div>&nbsp;&nbsp;"+ dis.ddescription +"<a href=\"getDisDetails.action?did="+dis.did+"\" class=\"hand\">[详情]</a></div>"
							+"<div class=\"knx\">可能性：<span style=\"color:blue;\">"+ pro +"%</span></div>"
						);
					}
				}
			});
		}
		
		//预处理
		function pretreatment(){
			
		}
		
		//搜索
		$(this).on("click",".search",function(){
			var arr = new Array();//定义一个数组
			var brr = new Array();
			//获取查询条件
			$(".symptom").each(function(){
				var value = $(this).val();
				var qzvalue = $(this).next().next().val();
				if(value.trim() != ""){
					arr.push(value);
					brr.push(qzvalue);
				}
			});
			$.ajax({
				type:'post',
				url:'selfSelect.action',
				dataType:'json',
				traditional : true,//传递数组时要用
				data:{
					field:'dsympton',   //查询的域
					value: arr,       //查询内容
					qzvalue: brr     //权重
				},
				success:function(data){
					$(".box").css("display","none");
					$(".result").css("display","block");
					$(".jg").css("background-color","#194069");
					$(".jg").css("border","1px solid #194069");
					$(".jg").css("color","white");
					$(".yjtj_content").empty();
					$(".xgzzul").empty();
					$(".total").empty();
					$.each(data,function(index,dis){
						if(null != dis){
							if(null != dis.map){
								$.each(dis.map,function(key,value){
									$(".yjtj_content").append("<tr><td class=\"zz\">"+ key +"</td>"
											+"<td class=\"qzv\"><input type=\"text\" readonly=\"readonly\" value=\""+ dis.map[key] +"\"/></td>"
											+"<td><img class=\"delet\" title=\"删除\" src=\"images/delet.png\" /></td></tr>");
								});
							}
							if(null != dis.strSet){
								for(var i = 1;i< dis.strSet.length;i++){
									$(".xgzzul").append("<li>"+ dis.strSet[i] +"</li>");
								}
							}
						}
					})
					getData(data);
				}
				
			});
		});
		//设置ajax
		function setAjax(){
			
			//功能
			var arr = new Array();//定义一个数组
			var brr = new Array();
			//获取查询条件
			$(".yjtj_content tr").each(function(){
				var value = $(this).find(".zz").text();
				var qzvalue = $(this).find(".qzv input").val();
				if(value.trim() != ""){
					arr.push(value);
					brr.push(qzvalue);
				}
			});
			$.ajax({
				type:'post',
				url:'selfSelect.action',
				dataType:'json',
				traditional : true,//传递数组时要用
				data:{
					field:'dsympton',   //查询的域
					value: arr,       //查询内容
					qzvalue: brr     //权重
				},
				success:function(data){
					$(".box").css("display","none");
					$(".result").css("display","block");
					$(".total").empty();
					getData(data);
				},error:function(){
					$(".total").empty();
					alert("小编已经努力了，你搜索的疾病不存在。请联系我们！");
				}
			});
		}
		
		//添加症状事件
		$(this).on("click",".xgzzul li",function(){
			//样式
			$(".yjtj_content").append("<tr><td class=\"zz\">"+ $(this).text() +"</td>"
					+"<td class=\"qzv\"><input type=\"text\" readonly=\"readonly\" value=\""+ 1 +"\"/></td>"
					+"<td><img class=\"delet\" title=\"删除\" src=\"images/delet.png\" /></td></tr>");
			setAjax();
		});
		//删除事件
		$(this).on("click",".delet",function(){
			if($(".yjtj_content tr").length>1){
				$(this).parent().parent().remove();
				setAjax();
			}else{
				alert("只有一个了,可怜可怜我");
			}
			
		});
		//编辑事件
		$(this).on("click",".edit",function(){
			
			if($(this).text() == "编辑"){
				$(".edit").text("保存");
				$(".qzv input").removeAttr("readonly");
				$(".qzv input").css("border","1px solid #DEDEDE");
				setAjax();
			}else{
				$(".edit").text("编辑");
				$(".qzv input").attr("readonly","readonly");
				$(".qzv input").css("border","1px hidden #DEDEDE");
				
				setAjax();
			}
			
		});
		
		$(this).on("click",".zzzc",function(){
			window.location.href = "self_examination.jsp";
		});
		$(this).on("click",".disname",function(){       
			var value = $(this).find("input").val();
			window.location.href = "getDisDetails.action?did=" + value;
			
		});	
		
		function setAjaxParm(field,value){
			$.ajax({
				type:'post',
				url:'getSuggest.action',
				dataType:'json',
				//traditional : true,//传递数组时要用
				data:{
					field: field,   //查询的域
					value: value,   //查询内容
				},
				success:function(data){
					$(".suggest ul").empty();
					$.each(data,function(index,str){
						$(".suggest").css("display","block");
						$(".suggest ul").append("<li>"+ str +"</li>");
					});
				}
			});
		}
		
		$('.symptom').bind('input propertychange', function() {
			var value = $('.symptom').val();
			if (value == "") {
				$(this).find(".suggest").css("display", "none");
			} else {
				setAjaxParm("sname", value);
			}
		});
		$(this).on("click", ".suggest ul li", function() {
			var value = $(this).text();
			$(".symptom").val(value);
			$(".suggest").css("display", "none");
			//$(".flag").css("display","inline");
		});
		
		/* function liClick(){
			$(".suggest ul li").click(function() {
				alert(1);
				var value = $(this).text();
				$(".symptom").val(value);
				$(".suggest").css("display", "none");
				//$(".flag").css("display","inline");
			});
		} */
		
		/*待定 无法解决 焦点失去 与点击事件的冲突
		  验证每个输入框的值是否存在
		$(".symptom").each(function(){
				$(this).on("blur",this,function(){
					var value=$(this).val();
					$(this).next().next().next().next().attr("id","temp");//设置当前操作行falg的id
					$(".suggest").css("display", "none");
					$("#temp").removeAttr("id");//先清除id为temp的所有id
					//setTimeout(function(){
						 
						$.ajax({
							type:'post',
							url:'verification.action',
							dataType:'json',
							data:{
								field: 'sname',   //查询的域
								value: value,   //查询内容
							},
							success:function(data){
								$("#temp").attr('src',"images/right.png");
								$("#temp").css("display","inline");
							},error:function(){
								$("#temp").attr('src',"images/delete1.png");
								$("#temp").css("display","inline");
							}
						}); 
						
					//},100);
					
					
				});
			});  */
		
			
		
		
		/*** 琢磨了很久 ***/
		$(this).on("click",".box:not(.suggest ul li,.symptom)",function(){
			$(".suggest").css("display", "none");
		});
		
		
	});
</script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<br />
	<div class="content">
		<div style="margin-top:10px;font-size:15px;">&nbsp;<span>当前位置：首页>疾病自查</span></div>
		<div><div class="zzzc">疾病自查</div><div class="jg">结果</div></div>
		<div class="clear"></div>
		<div class="box">
			<div class="xzzz">
				<img class="searchimg" src="images/search.png">选择症状
			</div>
			<div class="house">
				<div class="inputbox">
				<input type="text" class="symptom" />
				<span class="qz">权重:</span><input type="text" class="qzvalue" value="1"/>
				<img class="plus" src="images/plus.png"/>
				<img class="flag" src="images/right.png"/>
				<div class="suggest">
					<ul>
					
					</ul>
				</div>
				</div>
			</div>
			<button type="button" class="search">搜索</button>
		</div>
		<div class="clear"></div>
		<div class="result">
			<div class="leftResult">
				<div class="inputzz">
					<table class="yjtjzz">
						<tr>
							<td colspan="2" style="font-size:16px;color:#194069;font-weight:bolder; width:200px;">
								+ &nbsp;已添加症状
							</td>
							<td><a class="edit" style="cursor: pointer;">编辑</a></td>
						</tr>
						<tr>
							<td style="font-weight: bolder;">疾病名称</td>
							<td style="font-weight: bolder;">权重</td>
							<td></td>
						</tr>
					</table>
					<table class="yjtj_content">
						
					</table>
				</div>
				<div class="xgzz">
					<div class="xgzzdiv" >相关症状</div>
					<ul class="xgzzul">
					</ul>
				</div>
			</div>
			<div class="disease">
				<div style="height:34px;font-size: 16px;text-align: center;line-height: 34px;color:#194069;font-weight:bolder; ;background-color: #f2f2f2">疾病自查结果</div>
				<div class="total">
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>