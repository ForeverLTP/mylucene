<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<title>中医辅助诊断系统-首页</title>
<!-- 先加载jq之后才加载js,否则报错(bootstrap) -->
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>

<style type="text/css">
.current {
	width: 942px;
	margin: auto;
}

ul {
	list-style: none;
}
.top{
	margin:auto;
	width:100%;
	float:left;
	height:265px;
	background-color: #f1f1f1;
	
}
.lunbo {
	width: 585px;
	height: 260px; /*其实就是图片的高度*/
	border: 1px #eeeeee solid;
	margin: auto;
	margin-top: 5px; 
	margin-left:10px;
	position : relative;
	overflow: hidden;
	position: relative; /*此处需要将溢出框架的图片部分隐藏*/
	float: left;
}

.lunbo ul {
	width: 5000px;
	position: relative;
	/*此处需注意relative : 对象不可层叠，但将依据left，right，top，bottom等属性在正常文档流中偏移位置，如果没有这个属性，图片将不可左右移动*/
}

.lunbo ul li {
	float: left; /*让四张图片左浮动，形成并排的横着布局，方便点击按钮时的左移动*/
	width: 942px;
}

.lunbo ul li img {
	width: 585px;
	height: 260px;
}

.lunbo .lunbo_button { /*用绝对定位给数字按钮进行布局*/
	position: absolute;
	right: 10px;
	bottom: 5px;
	text-align: center;
	font-size: 12px;
	line-height: 20px;
}

.lunbo .lunbo_button span {
	cursor: pointer;
	display: block;
	float: left;
	width: 20px;
	height: 20px;
	background: #ff5a28;
	margin-left: 2px;
	color: #fff;
}

.lunbo .lunbo_button  .current {
	background: #b63e1a;
}
.login{
	
	float: left;
	width:230px;
	height:260px;
	margin:auto;
	margin-top:5px;
	margin-left:205px;
	background-color: #fff;
}
.login table tr{
	height:40px;
}
.login table tr td{
	text-align: center;
}
.titlename{
	font-size:20px;
	color:#006faf;
	text-align: center;
	
}
.name{
	text-align: center;
}
.account input,.pw input{
	width:150px;
	height:26px;
	border-radius: 5px;
	border:1px solid #ccc;
}
.loginin,.register{
	width:210px;
	height:30px;
	color:white;
	background-color: #006faf;
}
.middle{
	width:942px;
	margin:auto;
	margin-top:10px;
}
.data_title{
	text-align: center;
}
.middle .data div{
	float: left;
	margin:auto;
	margin-left: 45px;
	margin-right:45px;
	cursor: pointer;
}
.middle .data div p{
	margin:auto;
	margin-left: 30px;
	margin-right:30px;
}
.bottom{
	width:942px;
	border-top:1px solid #f1f1f1;
	margin:auto;
	margin-top:10px;
}
.right{
	float: left;
}
.right ul li{
	width:100px;
	height:45px;
	color:white;
	margin-top:16px;
	margin-left:10px;
	text-align: center;
	line-height: 45px;
	border-radius: 5px;
	cursor: pointer;
}
</style>
<script type="text/javascript">
	$(function() {
		var slideShow = $(".lunbo"), //获取最外层框架的名称
		ul = slideShow.find("ul"), showNumber = slideShow
				.find(".lunbo_button span"), //获取按钮
		oneWidth = slideShow.find("ul li").eq(0).width(); //获取每个图片的宽度
		var timer = null; //定时器返回值，主要用于关闭定时器
		var iNow = 0; //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0

		showNumber.on("click", function() { //为每个按钮绑定一个点击事件 
			$(this).addClass("current").siblings().removeClass("current"); //按钮点击时为这个按钮添加高亮状态，并且将其他按钮高亮状态去掉
			var index = $(this).index(); //获取哪个按钮被点击，也就是找到被点击按钮的索引值
			ul.animate({
				"left" : -oneWidth * iNow, //注意此处用到left属性，所以ul的样式里面需要设置position: relative; 让ul左移N个图片大小的宽度，N根据被点击的按钮索引值index确定
			})
		});

		timer = setInterval(function() { //打开定时器
			iNow++; //让图片的索引值次序加1，这样就可以实现顺序轮播图片
			if (iNow > showNumber.length - 1) { //当到达最后一张图的时候，让iNow赋值为第一张图的索引值，轮播效果跳转到第一张图重新开始
				iNow = 0;
			}
			showNumber.eq(iNow).trigger("click"); //模拟触发数字按钮的click
		}, 2000); //2000为轮播的时间
		
		$(".loginin").click(function(){
			var account = $(".account input").val();
			var pw = $(".pw input").val();
			$.ajax({
				type:'post',
				url:'initUser.action',
				dataType:'json',
				data:{
					uaccountnum :account,
					upassword: pw
				},
				success:function(data){
					window.location.reload();
				},error:function(){
					alert("账号或密码错误，请重试！");
				}
				
			});
		});
	})
</script>
</head>
<body>
	<div>
		<jsp:include page="head.jsp"></jsp:include>
		<br />
		<div class="content">
			<div class="top">
				<div class="login">
						<table>
							<tr class="titlename"><td colspan="2">用户登录</td></tr>
							<tr>
								<td class="name">账号：</td>
								<td class="account"><input type="text" /></td>
							</tr>
							<tr>
								<td class="name">密码：</td>
								<td class="pw"><input type="password" /></td>
							</tr>
							<tr>
								<td style="padding-left:20px;"><input type="checkbox" />记住</td>
								<td style="padding-left:80px"><a href="#">忘记密码</a></td>
							</tr>
							<tr>
								<td colspan="2"><button class="loginin">登录</button></td>
							</tr>
							<tr>
								<td colspan="2"><button class="register">立即注册新账号</button></td>
							</tr>
						</table>
				</div>
				<div class="lunbo">
					<ul>
						<li><a><img src="images/55.jpg" /></a></li>
						<li><a><img src="images/1.jpg" /></a></li>
						<li><a><img src="images/56.jpg" /></a></li>
						<li><a><img src="images/2.jpg" /></a></li>
						<li><a><img src="images/cm3.jpg" /></a></li>
					</ul>
					<div class="lunbo_button">
						<span class="current">1</span> <span>2</span> <span>3</span> <span>4</span>
						<span>5</span>
					</div>
				</div>
				<div class="right">
					<ul>
						<li style="background-color: #912CEE;">疾病</li>
						<li style="background-color: #8B0000;">症状</li>
						<li style="background-color: #1C86EE;">中药</li>
						<li style="background-color: #00FF7F;">药方</li>
					</ul>
				</div>
				
				<div class="clear"></div>
				<div style="height:5px;width:942px;background-color: #194069;margin:auto; "/>
			</div>
			<div class="clear"></div>
			<div class="middle">
				<div class="data_title"><h3>数据快速预览</h3></div>
				<div class="data">
					<div >
						<img src="images/jibing.png"/>
						<p>疾病&nbsp;&nbsp;${JBCOUNT}种</p>
					</div>
					<div>
						<img src="images/zhengzhuang.png"/>
						<p>症状&nbsp;&nbsp;${ZZCOUNT}种</p>
					</div>
					<div>
						<img src="images/zhongyao.png"/>
						<p>中药&nbsp;&nbsp;${ZYCOUNT}味</p>
					</div>
					<div>
						<img src="images/yaofang.png"/>
						<p>药方&nbsp;&nbsp;${YFCOUNT}种</p>
					</div>
					
				</div>
			</div>
			<div class="clear"></div>
			<div class="bottom">
				<img src="images/bottom.png"/>
			</div>
		</div>
	</div>
</body>
</html>