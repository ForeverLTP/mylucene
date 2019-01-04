<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中医辅助诊断系统-症状详细信息</title>
<style type="text/css">
	.content{
		width:942px;
		margin:auto;
		margin-top:30px;
	}
	.head1{
		font-size:18px;
		font-weight: bolder;
		margin-top:10px;
	}
	.symptom,.reasion{
		margin-top:10px;
	}
	
</style>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="content">
		<div>
			&nbsp;<span>当前位置：首页>症状查询>症状详情</span></div>
		<div class="head1">什么是关节疼痛</div>
		<div class="symptom">${SYDETAILS.getSsymptom()}</div>
		<div class="head1">关节疼痛是怎么引起的？</div>
		<div class="reasion">${SYDETAILS.getSreason()}</div>
		
	</div>

</body>
</html>