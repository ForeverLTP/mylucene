<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中医辅助诊断系统-个人中心</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<style type="text/css">
	.top{
		width:942px;
		margin:auto;
	}
	.img img{
		width:125px;
		height:125px;
		float: left;
	}
	.img{
		color:#898989;
	}
	.img div{
		float: left;
		margin-top:50px;
	}
	.tip{
		color:orange;
	}
	.exit{
		color:#EE0000;
		cursor: pointer;
	}
	.myspace{
		margin-top:10px;
		margin-left:20px;
		width:860px;
	}
	.myspace ul{
		float:left;
		border-bottom: 5px solid #194069;
	}
	.myspace ul li{
		float: left;
		width:215px;
		height:30px;
		text-align: center;
		line-height:35px;
		list-style: none;
		cursor: pointer;
	}
	.current{
		background-color: #194069;
		color:white;
	}
	.info,.change{
		margin-left:250px;
		font-size: 20px;
		
	}
	.info div,.change div{
		margin-top:10px;
	}
	.name{
		color:#898989;
	}
	.value{
		padding-left:20px;
	}
	.news,.record,.change{
		display: none;
	}
	.change input{
		border:1px solid #898989;
	}
	.change button{
		border-radius: 5px;
		width:200px;
		background-color: #194069;
		color:white;
		margin-top:20px;
		margin-left:50px;
	}
</style>
<script type="text/javascript">
	$(function(){
		$(this).on("click",".myspace ul li:not(.current)",function(){
			$(".current").removeClass("current");
			$(this).addClass("current");
			$(".info").css("display","none");
			$(".news").css("display","none");
			$(".record").css("display","none");
			$(".change").css("display","none");
			var id = $(this).attr("id");
			$("."+id).css("display","block");
		});
		$(this).on("click",".change button",function(){
			var nc = $(".nc").val();
			var sjh=$(".sjh").val();
			var yx = $(".yx").val();
			var pw = $(".pw").val();
			
			$.ajax({
				type:'post',
				url:'updateUser.action',
				dataType:'json',
				data:{
					uname :nc,
					upassword: pw,
					uemail: yx,
					uphone: sjh
				},
				success:function(data){
					alert("修改成功！")
				}
			});
		});
	});
</script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="top">
		<div class="img">
			<img  src="images/touxiang.png"/>
			<div>
				<span class="position">当前位置：首页>个人中心</span>
				<br style="height:40px;"/>
				<span class="infotip"><span class="tip">欢迎您：
				</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${USER.getUname()}
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="exit.action" class="exit">[退出登录]</a></span>
			</div>
		</div>
		<div class="clear"></div>
		<div class="myspace">
			<ul>
				<li id="info" class="current">个人信息</li>
				<li id="news">消息&nbsp;(<span style="color:red;">1</span>)</li>
				<li id="record">诊断记录</li>
				<li id="change">账号修改</li>
			</ul>
			<div class="clear"></div>
			<div class="info">
				<div><span class="name">昵&nbsp;&nbsp;&nbsp;称：</span><span class="value" >${USER.getUname()}</span></div>
				<div><span class="name">账&nbsp;&nbsp;&nbsp;号：</span><span class="value">${USER.getUaccountnum()}</span></div>
				<div><span class="name">邮&nbsp;&nbsp;&nbsp;箱：</span><span class="value">${USER.getUemail()}</span></div>
				<div><span class="name">手机号：</span><span class="value">${USER.getUphone()}</span></div>
			</div>
			<div class="news">
			
			</div>
			<div class="record">
			
			</div>
			<div class="change">
				<div><span class="name">昵&nbsp;&nbsp;&nbsp;称：</span><input class="nc" type="text" value="${USER.getUname()}"/></div>
				<div><span class="name">账&nbsp;&nbsp;&nbsp;号：</span><input readonly="readonly" style="border:hidden;" type="text" value="${USER.getUaccountnum()}"/></div>
				<div><span class="name">密&nbsp;&nbsp;&nbsp;码：</span><input class="pw" type="password" value="${USER.getUpassword()}"/></div>
				<div><span class="name">邮&nbsp;&nbsp;&nbsp;箱：</span><input class="yx" type="text" value="${USER.getUemail()}"/></div>
				<div><span class="name">手机号：</span><input class="sjh"  type="text" value="${USER.getUphone()}"/></div>
				<button  type="button">提交</button>
			</div>
			
		</div>
	</div>
	
</body>
</html>