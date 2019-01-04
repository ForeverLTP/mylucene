<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中医辅助诊断系统-常见疾病</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<style type="text/css">
.content {
	width: 942px;
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
.kind-item2 {
	width:942px;
	margin-top: 20px;
}
.kind-item2 li {
	cursor: pointer;
	color: #000;
	float: left;
	line-height: 15px;
	padding: 5px 10px 10px 5px;
}
.kind-item2 li:hover {
	color: #f60;
}
.kind-item2 .woman {
	float: left;
	margin-left: 40px;
	padding-left:60px;
	padding-top:10px;
	width: 300px;
	height: 60px;
	background: url("images/woman.png") no-repeat;
}
.kind-item2 .child {
	margin-left: 500px;
	padding-top:10px;
	padding-left:65px;
	width: 280px;
	height: 80px;
	background: url("images/child.png") no-repeat;
	background-size: 80px 80px;
}
.kind-item2 .man {
	float:left;
	margin-left: 32px;
	margin-top:-30px;
	padding-top:18px;
	padding-left:65px;
	width: 350px;
	height: 80px;
	background: url("images/man.png") no-repeat;
	background-size: 85px 85px;
}
.kind-item2 .oldman {
	margin-left: 500px;
	margin-top:-20px;
	padding-left:65px;
	width: 350px;
	height: 80px;
	background: url("images/oldman.png") no-repeat;
	background-size: 80px 80px;
}
.firstrow{
	margin-top:30px;
}
.disname{
	font-size: 17px;
	text-decoration: none;
	cursor: pointer;
}
.disname:hover,.sy:hover{
	color: #f60;
}
.disease{
	background-color: #A0D494;
	color:white;
	width: 37px;
	text-align: center;
}
.data{
	margin-left:10px;
}
.xgzzdiv{
	background-color:#F5F5F5;
	margin-top:10px;
}
.xgzz{
	color:#51932D;
}
.sy{
	color:#333;
	cursor: pointer;
}
.pagination li{
	cursor: pointer;
}
.hand{
	cursor: pointer;
}
.current a{
	color: white;
}
#homePage a,#previousPage a {
	display: none;
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
		
		$(".kind-item2 li").each(function(){
			$(this).click(function(){
				$(".form-control").val($(".form-control").val()+" "+$(this).text());
			});
		});
		/* $(".pagination li:not(.active)").each(function(){
			$(this).click(function(){
				//替换聚焦按钮
				$(".active").removeClass("active");
				$(this).addClass("active");
			});
		}); */
		
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
		function getData(data){
			$.each(data,function(index,sy){
				var salias = "";
				var disease = "";
				if(sy.salias.trim() != ""){
					salias = "(别名："+ sy.salias +")";
				}
				for(var i = 0;i< sy.disList.length;i++){
					disease +="<a class=\"sy\">"+ sy.disList[i].dname + "</a>&nbsp;&nbsp;";
				}
				$(".data").append("<div class=\"firstrow\"><a class=\"disname\">"
					+sy.sname+"</a> &nbsp;&nbsp;<label  class=\"disease\">症状</label>"
					+"&nbsp;&nbsp;<span style=\"color:#666666;\">"+ salias +"</span></div>"
					+"<div>&nbsp;&nbsp;"+ sy.sdescription +"<a href=\"getSyDetails.action?sid="+ sy.sid +"\" class=\"hand\">[详情]</a></div>"
					+"<div class=\"xgzzdiv\">&nbsp;<span class=\"xgzz\">相关疾病</span>&nbsp;&nbsp;"
					+ disease +"</div>"
				);
				if(null != sy.page){
					$(".maxNum").text(sy.page.count);
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
						var value1 = Math.floor(preNum/10);
						var value2 = Math.floor(totalPageNum/10);
						if(value1 == value2){//表示上一页仍然和最后一页在同一行
							getPageNum(integer*10+1,totalPageNum,preNum);
						}else{
							getPageNum(integer*10+1,integer*10+10,preNum);
						}
					}
					
				}else if(pageNums == "下一页"){
					
					var nextNum = pointNum - -1;
					num = nextNum;
					if(pointNum%10==0){//说明为一排数字的最后一个数即要更换页码
						
						var value3 = Math.floor(num/10);
						var value4 = Math.floor(totalPageNum/10);
						if(value3==value4){
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
				validate(pageNums);
				setAjax(num,'turnPageSy.action');
				 
			//});
		});
		
		
		//页码跳转功能
		$(".turn").click(function(){
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
				if(pageNums%10 == 0){
					getPageNum(pageNums-9,pageNums,pageNums);
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
			setAjax(pageNums,'turnPageSy.action');
		});
		//搜索功能
		$(this).on("click",".search",function(){
			
			var field = $("#searchButton").text().trim();
			var value = $(".form-control").val();
			$.ajax({
				type:'post',
				url:'searchOfSy.action',
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
		
	});
</script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<br />
	<div class="content">
		<div>
			&nbsp;<span>当前位置：首页>症状查询</span>
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
							<li class="hand" ><a>症状名称</a></li>
							<li class="hand" ><a>症状别名</a></li>
							<li class="hand" ><a>疾病名称</a></li>
						</ul>
					</div>
					<input type="text" class="form-control" style="width: 300px;" />
					<button type="button" class="search">搜索</button>
				</div>
			</div>
		</div>
		<div class="kind-item2">
			<div id="woman">
				<ul class="woman">
					<li>月经不调</li>
					<li>子宫肌瘤</li>
					<li>不孕症</li>
					<li>不孕不育</li>
					<li>功血</li>
					<li>葡萄胎</li>
				</ul>
			</div>
			<div id="child">
				<ul class="child">
					<li>手足口病</li>
					<li>小儿感冒</li>
					<li>小儿发烧</li>
					<li>小儿肺炎</li>
					<li>小儿咳嗽</li>
					<li>小儿干咳</li>
				</ul>
			</div>
			<div class="clear"></div>
			<div id="man">
				<ul class="man">
					<li>前列腺炎</li>
					<li>男性不育</li>
					<li>前列腺癌</li>
					<li>不孕不育</li>
					<li>性障碍</li>
					<li>淋球菌感染</li>
				</ul>
			</div>
			<div id="oldman">
				<ul class="oldman">
					<li>血压高</li>
					<li>糖尿病</li>
					<li>冠心病</li>
					<li>心肌梗塞</li>
					<li>骨质疏松</li>
					<li>动脉硬化</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div>
		&nbsp;&nbsp;记录：<font class="maxNum" color="red">${COUNT}</font>条
		<hr/>
		</div>
		<div class="data">
			<c:forEach var="sy" items="${SYLIST}">
				<div class="firstrow"><a class="disname">${sy.getSname()}</a> &nbsp;&nbsp; 
					<label  class="disease">症状</label>
					&nbsp;&nbsp;
					<c:if test="${not empty sy.getSalias()}">
						<span style="color:#666666;">(别名：${sy.getSalias()})</span>
					</c:if>
				</div>
				<div>
					&nbsp;&nbsp;${sy.getSdescription()}<a href="getSyDetails.action?sid=${sy.getSid()}" class="hand">[详情]</a>
				</div>
				<div class="xgzzdiv">
					&nbsp;<span class="xgzz">相关症状</span>
					&nbsp;&nbsp;
					<c:forEach items="${sy.getDisList()}" var="disease">
						<a class="sy">${disease.getDname()}</a>
						&nbsp;&nbsp;
					</c:forEach>
				</div>
			</c:forEach>
		</div>
		<div  class="pagenum" style="text-align: center;">
			<ul  class="pagination">
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
			   <c:if test="${PAGE.getTotalNum()!=1}">
			   		<li id="nextPage"><a>下一页</a></li>
			    	<li id="endPage"><a>末页</a></li>
			   </c:if>
			    
			    <li class="turndiv">
			    	<input class="turnNum" style="width:50px;border-top:1px solid #ddd;border-left:1px solid #ddd;border-bottom:1px solid #ddd;outline:none;text-align: center;height:34px;margin-left:10px;" type="text" value="1" />
			    	<input style="width:30px;border-top:1px solid #ddd;border-right:1px solid #ddd;border-bottom:1px solid #ddd;margin-left:-10px;height:34px;" readonly="readonly" type="text" value="/${PAGE.getTotalNum()}" />
			    	<button type="button" class="turn">跳转</button>
			    </li>
			</ul>
		</div>
	</div>
</body>
</html>