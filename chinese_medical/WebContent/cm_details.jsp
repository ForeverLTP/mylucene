<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中医辅助诊断-中药详情</title>
<style type="text/css">
	.content{
		width:942px;
		margin:auto;
		margin-top:10px;
	}
	.content img{
		display:block;
		margin:auto;
		margin-bottom: 10px;
		
	}
	table td,table th{
		border:1px solid #cccccc;
		text-align: center;
		line-height: 30px;
	}
</style>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<br />
	<div class="content">
		<img alt="${CMDETAILS.getMname()}" title="${CMDETAILS.getMname()}" src="images/chinesemedicine/${CMDETAILS.getMid()}.jpg" />
		<table>
			<tr>
				<th width="100px">中药名称：</th>
				<td>${CMDETAILS.getMname()}</td>
			</tr>
			<tr>
				<th>拼音名：</th>
				<td>${CMDETAILS.getMpinyin()}</td>
			</tr>
			<tr>
				<th>中药别名：</th>
				<td>${CMDETAILS.getMalias()}</td>
			</tr>
			<tr>
				<th>中药类型：</th>
				<td>${CMDETAILS.getMtype()}</td>
			</tr>
			<tr>
				<th>中药来源：</th>
				<td>${CMDETAILS.getMsource()}</td>
			</tr>
			<tr>
				<th>性状：</th>
				<td>${CMDETAILS.getMtraits()}</td>
			</tr>
			<tr>
				<th>性味归经：</th>
				<td>${CMDETAILS.getMsexuality()}</td>
			</tr>
			<tr>
				<th>功效：</th>
				<td>${CMDETAILS.getMfunction()}</td>
			</tr>
			<tr>
				<th>临床应用：</th>
				<td>${CMDETAILS.getMclinical_application()}</td>
			</tr>
			<tr>
				<th>化学成分：</th>
				<td>${CMDETAILS.getMchemical_composition()}</td>
			</tr>
			<tr>
				<th>禁忌：</th>
				<td>${CMDETAILS.getMban()}</td>
			</tr>
			<tr>
				<th>相关处方：</th>
				<td>${CMDETAILS.getMprescription()}</td>
			</tr>

		</table>
	</div>
</body>
</html>