<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中医辅助诊断-药方详情</title>
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
		<table>
			<tr>
				<th width="100px">药方名称：</th>
				<td>${PREDETAILS.getPname()}</td>
			</tr>
			<tr>
				<th>药方来源：</th>
				<td>${PREDETAILS.getPsource()}</td>
			</tr>
			<tr>
				<th>组成成分：</th>
				<td>${PREDETAILS.getPcomposition()}</td>
			</tr>
			<tr>
				<th>用法：</th>
				<td>${PREDETAILS.getPusage()}</td>
			</tr>
			<tr>
				<th>功效：</th>
				<td>${PREDETAILS.getPeffect()}</td>
			</tr>
			<tr>
				<th>功能主治：</th>
				<td>${PREDETAILS.getPindications()}</td>
			</tr>
			<tr>
				<th>方解：</th>
				<td>${PREDETAILS.getPsolution()}</td>
			</tr>
			<tr>
				<th>临床应用：</th>
				<td>${PREDETAILS.getPclinical_application()}</td>
			</tr>
			<tr>
				<th>注意事项：</th>
				<td>${PREDETAILS.getPban()}</td>
			</tr>
			<tr>
				<th>歌诀：</th>
				<td>${PREDETAILS.getPsong()}</td>
			</tr>
			

		</table>
	</div>
</body>
</html>