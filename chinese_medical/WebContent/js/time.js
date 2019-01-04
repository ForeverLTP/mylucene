/**
		 * 
		 * 获取当前时间
		 */
		function showLocale() {
			var str, colorhead, colorfoot;
			var objD = new Date();
			var yy = objD.getYear();
			if (yy < 1900)
				yy = yy + 1900;
			var MM = objD.getMonth() + 1;
			if (MM < 10)
				MM = '0' + MM;
			var dd = objD.getDate();
			if (dd < 10)
				dd = '0' + dd;
			var hh = objD.getHours();
			if (hh < 10)
				hh = '0' + hh;
			var mm = objD.getMinutes();
			if (mm < 10)
				mm = '0' + mm;
			var ss = objD.getSeconds();
			if (ss < 10)
				ss = '0' + ss;
			var ww = objD.getDay();
			if (ww == 0)
				colorhead = "<font color=\"#000000\">";
			if (ww > 0 && ww < 6)
				colorhead = "<font color=#000000\">";
			if (ww == 6)
				colorhead = "<font color=\"#000000\">";
			colorfoot = "</font>";
			str = colorhead + yy + "/" + MM + "/" + dd + " " + hh + ":" + mm
					+ ":" + ss + "  " + colorfoot;
			return (str);
		}

		setInterval(function() {
			$(".time").html(showLocale)
		}, 1000);
		//设置菜单栏特效
		$("#title li").each(function(){
			$(this).click(function(){
				$(this).find("a").css("background","#CCCCCC");
			});
		});
		
