/**
 * 
 */
// 链表播放界面初始化
function newPlayer_sort(getstr) {
	if ($.isArray(getstr)) {
		var i;
		var num = getstr.length;
		var spangroup = new Array(num);
		var leftt = ($('#sort-canvas').width() - num * 75) / 2;
		var topp = ($('#sort-canvas').height() - 250) / 2;
		if (num <= 15) {
			for (i = 0; i < num-1; i++) {
				$("div")
						.append(
								"<span id='sort-sign-"
										+ i
										+ "' style='background: #FFFFFF; border:3px solid #a1a1a1; border-radius: 25px; height: 40px; width: 40px; display: inline-block; vertical-align: top; position: absolute;'>"
										+ " <span style='display: block; color: #000000; height: 40px; width: 40px; line-height: 40px; text-align: center; position: absolute;'>"
										+ getstr[i]
										+ "</span>"
										+ " <span style='display: block; height: 2px;width:50px; background-color: #999; position: absolute; top: 20px; left: 40px; ' />"
										+ "</span>");
				var spann = document.getElementById("sort-sign-" + i);
				setX(spann, leftt + 90 * i);
				setY(spann, 250 );
				spangroup[i] = "sort-sign-" + i;
			}
			$("div")
						.append(
								"<span id='sort-sign-"
										+ i
										+ "' style='background: #FFFFFF; border:3px solid #a1a1a1; border-radius: 25px; height: 40px; width: 40px; display: inline-block; vertical-align: top; position: absolute;'>"
										+ " <span style='display: block; color: #000000; height: 40px; width: 40px; line-height: 40px; text-align: center; position: absolute;'>"
										+ getstr[i]
										+ "</span>");
				var spann = document.getElementById("sort-sign-" + i);
				setX(spann, leftt + 90 * i);
				setY(spann, 250 );
				spangroup[i] = "sort-sign-" + i;
			return spangroup;
		}
		if (num >= 16) {
			alert("请不要输入超过15个元素！");
		}
	}
	if (!$.isArray(getstr)) {
		alert("error!");
	}
}
function setX(elem, pos) {
	elem.style.left = pos + "px";
}
function setY(elem, pos) {
	elem.style.top = pos + "px";
}
function addX(elem, pos) {
	setX(elem.offsetLeft + pos);
}
function addY(elem, pos) {
	setY(elem.offsetTop + pos);
}
function addeletail(number, myspan) {
	var l = myspan.length;
	var leftt = ($('#sort-canvas').width() - l * 75) / 2;
	var w = 
		$("div")
			.append(
				"<span id='sort-sign-"
										+ l
										+ "' style='display: block; height:2px; width:50px; background-color: #999; position: absolute; top: 272px; left: "
										+ (leftt + 90 * l - 45)
										+ "px;'>"
										+ " </span>"
										+ " <span id='back' style='background: #FFFFFF; border:3px solid #a1a1a1; border-radius: 25px; height: 40px; width: 40px; display: inline-block; vertical-align: top; position: absolute; top: 250px; left:"
										+ (leftt + 90 * l)
										+ "px;'>"
										+ " <span style='display: block; color:#000000; height:40px; width: 40px; line-height: 40px; text-align: center; position:absolute;'>"
										+ number
										+ "</span>"
										+ "</span>");
				var spann = document.getElementById("sort-sign-" + l);
				setTimeout(function() {
			        myspan.push(spann);
						}, 1000);
				setTimeout(function() {
			       chargecolor_sort(document.getElementById("back"), "#20B2AA");
						}, 2000);
}
function addelehead(number, myspan) {
	var l = myspan.length;
	var leftt = ($('#sort-canvas').width() - l * 75) / 2;
	var w = 
		$("div")
			.append(
				"<span id='sort-sign-"
										+ l
										+ "' style='display: block; height: 2px;width:50px; background-color: #999; position: absolute; top: 272px; left: "
										+ (leftt - 90 + 40)
										+ "px;'>"
										+ " </span>"
										+ " <span id='back' style='background: #FFFFFF; border:3px solid #a1a1a1; border-radius: 25px; height: 40px; width: 40px; display: inline-block; vertical-align: top; position: absolute; top: 250px; left:"
										+ ( leftt - 90 )
										+ "px;'>"
										+ " <span style='display: block; color: #000000; height: 40px; width: 40px; line-height: 40px; text-align: center; position:absolute;'>"
										+ number
										+ "</span>"
										+ "</span>");
				var spann = document.getElementById("sort-sign-" + l);
				setTimeout(function() {
			        myspan.push(spann);
						}, 1000);
				setTimeout(function() {
			       chargecolor_sort(document.getElementById("back"), "#20B2AA");
						}, 2000);
}
// 位移动画
function displayment_sort(ele, targetX, targetY) {
	var a1 = setInterval(function() {
		if (ele.offsetTop == targetY) {
			window.clearInterval(a1);
			var a2 = setInterval(function() {
				if (ele.offsetLeft == targetX) {
					window.clearInterval(a2);
				} else {
					ele.style.left = ele.offsetLeft
							+ (ele.offsetLeft < targetX ? 1 : -1) * 1 + "px";
				}
			}, 1);
		} else {
			ele.style.top = ele.offsetTop + (ele.offsetTop < targetY ? 1 : -1)
					* 1 + "px";
		}
	}, 1);
}
// 变色动画
function chargecolor_sort(ele, newcolor) {
	ele.style.background = newcolor;
}