<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中医辅助诊断系统-中药查询</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<style type="text/css">
.content {
	width: 912px;
	margin: auto;
	margin-top: 6px;
	border: 1px solid #f1f1f1;
}
.button {
	border-radius: 2px;
	background-color: #194069;
	color: white;
}

.row {
	padding-left: 200px;
}

.input-group {
	width: 942px;
}

.search {
	float: left;
	border-radius: 5px;
	width: 100px;
	height: 34px;
	color: white;
	background-color: #194069;
	margin-left: 5px;
}
.turn{
	border-radius: 5px;
	width: 50px;
	height: 34px;
	color: black;
}

.firstrow{
	margin-top:20px;
}
.disname{
	font-size: 17px;
	text-decoration: none;
	cursor: pointer;
}
.disname:hover{
	color: #f60;
}
.disease{
	background-color: #A0D494;
	color:white;
	width: 37px;
	text-align: center;
}
.data{
	margin-left:40px;
}

.pagination li{
	cursor: pointer;
}
.hand{
	cursor: pointer;
}
#homePage a,#previousPage a {
	display: none;
}
.gongxiao,.zccf,.gejue{
	margin-top:6px;
}
.gj{
	border-radius: 5px;
	border:1px solid #B8860B;
	color:#B8860B;
}
.xuxian{
	width:800px;
	border:1px dashed #EEE0E5;
}
.onewhole:hover{
	border-radius:8px;
	background-color: #F0F0F0;
	margin-left:-5px;
	cursor: pointer;
	
}
</style>
<script type="text/javascript">
	$(function() {
		
		$(".dropdown-menu li").each(function(){
			$(this).click(function(){
				$("#searchButton").text($(this).find("a").text());
				$("#searchButton").append("&nbsp;<span class=\"caret\"></span>");
			});
		});

		//获取页码
		function getPageNum(startNum,endNum,currentNum){
			$(".pagination").empty();
			$(".pagination").append("<li id=\"homePage\"><a>首页</a></li>"
					+"<li id=\"previousPage\"><a>上一页</a></li>");
			for(var i = startNum;i<= endNum;i++){
				if(i == currentNum){
					$(".pagination").append("<li  class=\"active\"><a>"+ i +"</a></li>");
				}else{
					$(".pagination").append("<li><a>"+ i +"</a></li>");
				}
				
			}
			$(".pagination").append("<li id=\"nextPage\"><a>下一页</a></li>"
				    + "<li id=\"endPage\"><a>末页</a></li>"
				    + "<li class=\"turndiv\"><input class=\"turnNum\" style=\"width:50px;border-top:1px solid #ddd;border-left:1px solid #ddd;border-bottom:1px solid #ddd;outline:none;text-align: center;height:34px;margin-left:10px;\" type=\"text\" value=\"1\" />"
				    + "<input style=\"width:30px;border-top:1px solid #ddd;border-right:1px solid #ddd;border-bottom:1px solid #ddd;margin-left:-10px;height:34px;\" readonly=\"readonly\" type=\"text\" value=\"/"+ $(".totalNum").val() +"\" />"
				    + "<button type=\"button\" class=\"turn\">跳转</button></li>"); 
		}
		
		//验证首页上一页下一页尾页的显示状况
		function validate(pageNums){
			if(pageNums != 1){
				if(pageNums == $(".totalNum").val()){
					$("#homePage a").css("display","block");
					$("#previousPage a").css("display","block");
					$("#nextPage a").css("display","none");
					$("#endPage a").css("display","none");
				}else{
					$("#homePage a").css("display","block");
					$("#previousPage a").css("display","block");
					$("#nextPage a").css("display","block");
					$("#endPage a").css("display","block");
				}
				
			}else{
				$("#homePage a").css("display","none");
				$("#previousPage a").css("display","none");
				$("#nextPage a").css("display","block");
				$("#endPage a").css("display","block");
			} 
		}
		//获取数据
		function getData(data){
			$.each(data,function(index,pre){
				
				$(".data").append("<div class=\"onewhole\"><div class=\"firstrow\"><a class=\"disname\">"
						+ pre.pname +"</a><input class=\"pid\" type=\"hidden\" value=\" "+pre.pid+"\"/>"
						+ "</div><div class=\"zccf\">成分：&nbsp;"+ pre.pcomposition +"</div>"
						+ "<div class=\"gongxiao\">功效：&nbsp;"+ pre.peffect +"</div>"
						+ "<div class=\"gejue\">歌诀：&nbsp;<span class=\"gj\">"+ pre.psong +"</span></div>"
						+"</div><div><hr class=\"xuxian\"/></div>");
				
				if(null != pre.page){
					$(".maxNum").text(pre.page.count);
				}
				
			});
		}
		
		//公共方法异步获取数据
		function setAjax(num,address){
			 $.ajax({
					type:'post',
					url:address,
					dataType:'json',
					data:{
						pageNum:num,
						total: $(".maxNum").text(),
						totalPageNum:$(".totalNum").val()
					},
					success:function(data){
						$(".data").empty();
						getData(data);
						
					}
				});
			
		}
		//页码事件：注：jq当使用append则click事件失效
		//原因append方法是当前页面加载完成后在填新元素,click事件只能获取页面加载完成时的页面元素
		//即新元素无法获取
		$(this).on("click",".pagination li:not(.active,.turndiv)",function(){
		//$(".pagination").find("li:not(.active)").each(function(){
			//$(this).click(function(){
				var num = 1;
				var pointNum  = $(".active a").text();
				var pageNums = $(this).find("a").text();
				var totalPageNum = $(".totalNum").val();
				if(pageNums == "首页"){
					num = 1;
					if(totalPageNum>=10){
						getPageNum(1,10,1);
					}else{
						getPageNum(1,totalPageNum,1);
					}
					
					//$("#homePage a").css("display","none");
					//$("#previousPage a").css("display","none");
					
				}else if(pageNums == "上一页"){
					var preNum = pointNum-1;
					num = preNum;
					if(preNum%10==0){//说明为一排数字的第一个数即要更换页码
						getPageNum(preNum-9,preNum,preNum);
					}else{
						var integer = Math.floor(pointNum/10);
						var integer1 = Math.floor(preNum/10);
						var integer2 = Math.floor(totalPageNum/10);
						if(integer1 == integer2){//表示上一页仍然和最后一页在同一行
							getPageNum(integer*10+1,totalPageNum,preNum);
						}else{
							getPageNum(integer*10+1,integer*10+10,preNum);
						}
					}
					
				}else if(pageNums == "下一页"){
					
					var nextNum = pointNum - -1;
					num = nextNum;
					if(pointNum%10==0){//说明为一排数字的最后一个数即要更换页码
						var value1 = Math.floor(num/10);
					    var value2 = Math.floor(totalPageNum/10);
						if(value1==value2){
							getPageNum(num,totalPageNum,num);
						}else{
							getPageNum(num,num+9,num);
						}
						
					}else{
						var integer1 = Math.floor(pointNum/10);
						var integer2 = Math.floor(num/10);
						var integer3 = Math.floor(totalPageNum/10);
						if(integer2==integer3){//表示上一页仍然和最后一页在同一行
							getPageNum(integer1*10+1,totalPageNum,num);
						}else{
							getPageNum(integer1*10+1,integer1*10+10,num);
						}
					}
					
				}else if(pageNums == "末页"){
					num = $(".totalNum").val();
					if(num%10==0){
						getPageNum(num-9,num,num)
					}else{
						getPageNum(Math.floor(num/10)*10+1,num,num);
					}
					
				}else{
					num = pageNums;
					$(".active").removeClass("active");
					$(this).addClass("active");
				}
				validate(num);
				setAjax(num,'turnPageCM.action');
			//});
		});
		
		//页码跳转
		$(this).on("click",".turn",function(){
		//$(".turn").click(function(){
			var pageNums = $(".turnNum").val();
			var totalPageNum = $(".totalNum").val();
			if(pageNums<=1){
				pageNums = 1;
				if(totalPageNum>=10){
					getPageNum(1,10,1);
				}else{
					getPageNum(1,totalPageNum,1);
				}
			}else if(pageNums>=$(".totalNum").val()){
				pageNums = $(".totalNum").val();
				if(pageNums%10==0){
					getPageNum(pageNums-9,pageNums,pageNums)
				}else{
					getPageNum(Math.floor(pageNums/10)*10+1,pageNums,pageNums);
				}
				
			}else{
				if(pageNums%10==0){
					getPageNum(pageNums-9,pageNums,pageNums);
				}else{
					if(pageNums/10==0){
						getPageNum(1,10,pageNums);
					}else{
						var val1 = Math.floor(pageNums/10);
						var val2 = Math.floor($(".totalNum").val()/10);
						var temp = Math.floor(pageNums/10)*10+1;
						if(val1 == val2){
							getPageNum(temp,$(".totalNum").val(),pageNums);
						}else{
							getPageNum(temp,temp+9,pageNums);
						}
					}
				}
			}
			validate(pageNums);
			setAjax(pageNums,'turnPageCM.action');
		});
		//搜索功能
		$(this).on("click",".search",function(){
			
			var field = $("#searchButton").text().trim();
			var value = $(".form-control").val();
			$.ajax({
				type:'post',
				url:'searchOfPre.action',
				dataType:'json',
				data:{
					field:field,
					value: value
				},
				success:function(data){
					$(".data").empty();
					getData(data);
					$(".pagination").empty();
				}
				
			});
		});
		$(this).on("click",".onewhole",function(){
			var pid = $(this).find(".pid").val();
			window.location.href = "getPreDetails.action?pid=" + pid;
		});
		
		
	});
</script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<br />
	<div class="content">
		<div>
			&nbsp;<span>当前位置：首页>药方查询</span>
			<input class="totalNum" type="hidden" value="${PAGE.getTotalNum()}" />
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="input-group">
					<div class="input-group-btn">
						<button type="button" id="searchButton" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							全文检索&nbsp;<span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li class="hand" ><a>全文检索</a></li>
							<li class="hand" ><a>药方名称</a></li>
							<li class="hand" ><a>功效作用</a></li>
							<li class="hand" ><a>组成成分</a></li>
							<li class="hand" ><a>歌诀</a></li>
						</ul>
					</div>
					<input type="text" class="form-control" style="width: 300px;" />
					<button type="button" class="search">搜索</button>
				</div>
			</div>
		</div>
		<div>
		&nbsp;&nbsp;记录：<font class="maxNum" color="red">${COUNT}</font>条
		<hr/>
		</div>
		<div class="data">
			<c:forEach var="pre" items="${PRELIST}">
				<div class="onewhole">
				<div class="firstrow"><a class="disname">${pre.getPname()}</a> &nbsp;&nbsp; 
					<input class="pid" type="hidden" value="${pre.getPid()}"/>
				</div>
				<div class="zccf">
					成分：&nbsp;${pre.getPcomposition()}
				</div>
				<div class="gongxiao">
					功效：&nbsp;${pre.getPeffect()}
				</div>
				
				<div class="gejue">
					歌诀：&nbsp;<span class="gj">${pre.getPsong()}</span>
				</div>
				</div>
				<div><hr class="xuxian"/></div>
			</c:forEach>
		</div>
		<div  class="pagenum" style="text-align: center;">
			<ul  class="pagination">
				<c:if test="${PAGE.getTotalNum()!=1}">
			    <li id="homePage"><a>首页</a></li>
			    <li id="previousPage"><a>上一页</a></li>
			    
		    	<c:forEach begin="${PAGE.getPageBeginNum()}"  end="${PAGE.getPageEndNum()}" var="i">
		    		<c:if test="${PAGE.getBeginNum() <10 && i == 1 }">
		    			<li class="active"><a>${i}</a></li>
		    		</c:if>
		    		<c:if test="${PAGE.getBeginNum() <10 &&i != 1 }">
		    			<li><a>${i}</a></li>
		    		</c:if>
		    		<c:if test="${PAGE.getBeginNum() >=10}">
		    			<li><a>${i}</a></li>
		    		</c:if>
		    	</c:forEach>
			   
			   		<li id="nextPage"><a>下一页</a></li>
			    	<li id="endPage"><a>末页</a></li>
			   
			    <li class="turndiv">
			    	<input class="turnNum" style="width:50px;border-top:1px solid #ddd;border-left:1px solid #ddd;border-bottom:1px solid #ddd;outline:none;text-align: center;height:34px;margin-left:10px;" type="text" value="1" />
			    	<input style="width:30px;border-top:1px solid #ddd;border-right:1px solid #ddd;border-bottom:1px solid #ddd;margin-left:-10px;height:34px;" readonly="readonly" type="text" value="/${PAGE.getTotalNum()}" />
			    	<button type="button" class="turn">跳转</button>
			    </li>
			    </c:if>
			</ul>
		</div>
	</div>
</body>
</html>