<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
<!-- 先加载jq之后才加载js,否则报错(bootstrap) -->
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/time.js"></script>
<style type="text/css">
.clear {
	clear: both;
}
.title{
	background-color: #006faf;
}
#title{
	width: 942px; 
	margin: 0 auto;
	margin-top:2px;
}
#title li{
	width:94px;
	height:40px;
	background-color: #006faf;
	float: left;
}
#title li a{
	background-color: #194069; 
	width: 94px; 
	line-height:40px; 
	display:block; 
	color: #FFF;
	font-size: 14px; 
	float: left; 
	text-align: center;
	text-decoration:  none;
}
#title li a:hover{
	background-color: #ccc;
	color:#fff;
} 
#fzzd,#grzx,#xtsz{
	cursor: pointer;
} 
</style>
<script type="text/javascript">
	$(function(){
		$(this).on("click","#fzzd,#grzx",function(){
			alert("请登入！");
		})
		
		$(this).on("click","#xtsz",function(){
			alert("权限不够！");
		})	
	});
</script>
</head>
<body>
	<div class="head" style="width: 942px; margin: auto;">
		<div  style=" margin-top: 10px;">
			<c:if test="${not empty USER}">
				<span>欢迎 , <span style="color:red;">${USER.getUname()}</span>&nbsp;| &nbsp;&nbsp;&nbsp;<a
				href="#">加入收藏</a></span>
			</c:if>
			<c:if test="${empty USER}">
				<span>您好，欢迎您的到来！&nbsp;| &nbsp;&nbsp;&nbsp;<a
				href="#">加入收藏</a></span>
			</c:if>
			<span style="margin-left:500px;">当前时间为：</span><span class="time"></span>
		</div>
		<br />
		<div>
			<img alt="" src="images/logo4.png" /> <img alt=""
				src="images/logo6.png" />
		</div>
		<div class="title">
			<ul  id="title">
				<li id="title1"><a href="initIndex.action">首页</a></li>
				<li id="title2"><a href="knowledge.jsp">中医科普</a></li>
				<li id="title3"><a href="initDisease.action">常见疾病</a></li>
				<li id="title4"><a href="initSymptom.action">症状查询</a></li>
				<li id="title5"><a href="initChineseMedicine.action">中药查询</a></li>
				<li id="title6"><a href="initPrescription.action">药方查询</a></li>
				<c:if test="${not empty USER}">
				<li id="title7"><a href="self_examination.jsp">辅助诊断</a></li>
				<li id="title8"><a href="personalcenter.jsp">个人中心</a></li>
				</c:if> 
				<c:if test="${empty USER}">
				<li id="fzzd"><a>辅助诊断</a></li>
				<li id="grzx"><a>个人中心</a></li>
				</c:if>
				<li id="title9"><a href="contact.jsp">联系我们</a></li>
				<c:if test="${not empty USER && USER.getUflag()==0}">
					<li id="title9"><a href="system_settings.jsp">系统设置</a></li>
				</c:if>
				<c:if test="${empty USER || USER.getUflag()!=0}">
					<li id="xtsz"><a>系统设置</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<br/>
</body>
</html>