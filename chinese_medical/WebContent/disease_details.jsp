<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中医辅助诊断系统-疾病详细信息</title>
<style type="text/css">
	.content{
		width:942px;
		margin:auto;
		margin-top:30px;
	}
	.introduce{
		margin-top:10px;
	}
	.knowlege,.cure{
		margin-top:-10px;
	}
	.head1{
		font-size:18px;
		font-weight: bolder;
		margin-top:10px;
	}
</style>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="content">
		<div>
			&nbsp;<span>当前位置：首页>常见疾病>疾病详情</span></div>
		<div class="head1">${DISDETAILS.getTitle1()}</div>
		<div class="introduce">${DISDETAILS.getDintroduce()}</div>
		<div class="head1">${DISDETAILS.getTitle2()}</div>
		<div class="knowlege">${DISDETAILS.getDknowlege()}</div>
		<div class="head1">${DISDETAILS.getTitle3()}</div>
		<div class="cure">${DISDETAILS.getDcure()}</div>
	</div>

</body>
</html>