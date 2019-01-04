<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中医辅助诊断系统-联系我们</title>
<style type="text/css">
	.content{
		width:942px;
		margin:auto;
	}
	.Y{
		width:40px;
		height:40px;
		border-radius: 20px;
		border:1px solid red;
		background-color: red;
		color:white;
		text-align: center;
		line-height: 40px;
		font-size: 18px;
		float: left;
	}
	.X{
		width:40px;
		height:40px;
		border-radius: 20px;
		border:1px solid blue;
		background-color: blue;
		color:white;
		text-align: center;
		line-height: 40px;
		font-size: 18px;
		float: left;
	}
	.content input{
		border: 1px solid #ccc;
		border-radius:5px;
		width:942px;
		height:30px;
	}
	.content textarea{
		border: 1px solid #ccc;
		width:942px;
		height:200px;
	}
	.content .value{
		float: left;
		padding-left:60px;
		color:#333;
	}
	.content a{
		padding-left:500px;
		cursor: pointer;
	}
	.content button{
		border-radius: 5px;
		width:200px;
		height:30px;
		background-color: #194069;
		color:white;
		margin-top:20px;
		margin-left:50px;
	}
</style>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<br />
	<div class="content">
		<h2 style="color:#000;">联系人</h2>
		<div style="width:942px;height:40px;margin-top:5px;"><div class="Y">Y</div><div style="font-size:18px;line-height: 40px;"><span class="value">杨延云</span><a>联系方式：12345678998</a></div></div>
		<div style="width:942px;height:40px;margin-top:5px;"><div class="X">X</div><div style="font-size:18px;line-height: 40px;"><span class="value">XXXX</span><a>联系方式：12345678998</a></div></div>
		<div style="width:942px;height:40px;margin-top:5px;"><div class="X">X</div><div style="font-size:18px;line-height: 40px;"><span class="value">XXXX</span><a>联系方式：12345678998</a></div></div>
		<div class="clear"></div>
		<br/>
		<span style="font-size:16px;color:#666666">网站留言</span>
		<div style="font-size:14px;color:#666666">邮箱：</div>
		<div><input type="text" /></div>
		<div style="font-size:14px;color:#666666">输入内容：</div>
		<div><textarea cols="" rows="" /></textarea></div>
		<div>
			<button type="button"> 提交</button>
		</div>
	</div>


</body>
</html>