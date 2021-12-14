/**
 * 
 */
// 排序播放界面初始化
function newPlayer_sort(size,getstr) {
	if ($.isArray(getstr)) {
		var i,j;
		var sl = 0;
		var spangroup = new Array();
		for( var p = 0; p < size; p++){
		    spangroup[p] = new Array();
		}
		var leftt = ($('#sort-canvas').width() - size * 90) / 2;
		if (size <= 20) {
			for (i = 0; i < size; i++) {
				var num = getstr[i].length;
				if(num == 0){
					    $("div")
						    .append(
								"<span id='sort-sign-"
										+ sl
										+ "' style='background: #FFFFFF; border:3px solid #a1a1a1; border-radius: 20px; height: 30px; width: 30px; display: inline-block; vertical-align: top; position: absolute;'>"
										+ " <span style='display: block; color: #000000; height: 30px; width: 30px; line-height: 30px; text-align: center; position: absolute;'>"
										+ "i:"
										+ i
										+ "</span>"
										+ "</span>");
				        var spann = document.getElementById("sort-sign-" + sl);
				        setX(spann, leftt + 100 * i);
				        setY(spann, 180 );
				        spangroup[i][0] = "sort-sign-" + sl;
						sl++;
				}
				else{
				    for (j = 0; j < num; j++){
				        if(j == 0){
					        $("div")
						        .append(
								    "<span id='sort-sign-"
										+ sl
										+ "' style='background: #FFFFFF; border:3px solid #a1a1a1; border-radius: 20px; height: 30px; width: 30px; display: inline-block; vertical-align: top; position: absolute;'>"
										+ " <span style='display: block; color: #000000; height: 30px; width: 30px; line-height: 30px; text-align: center; position: absolute;'>"
										+ "i:"
										+ i
										+ "</span>"
										+ " <span style='display: inline-block; height: 50px;width:2px; background-color: #999; position: absolute;top:30px; left:15px; '>"
										+ "</span>"
										+ "</span>");
				            var spann = document.getElementById("sort-sign-" + sl);
				            setX(spann, leftt + 100 * i);
				            setY(spann, 180 );
				            spangroup[i][0] = "sort-sign-" + sl;
				        }
				        else {
					        $("div")
						        .append(
								    "<span id='sort-sign-"
										+ sl
										+ "' style='background: #FFFFFF; border:3px solid #a1a1a1; border-radius: 20px; height: 30px; width: 30px; display: inline-block; vertical-align: top; position: absolute;'>"
										+ " <span style='display: block; color: #000000; height: 30px; width: 30px; line-height: 30px; text-align: center; position: absolute;'>"
										+ getstr[i][j-1]
										+ "</span>"
										+ " <span style='display: inline-block; height: 50px;width:2px; background-color: #999; position: absolute;top:30px; left:15px; '>"
										+ "</span>"
										+ "</span>");
				            var spann = document.getElementById("sort-sign-" + sl);
				            setX(spann, leftt + 100 * i);
				            setY(spann, 200 + j * 50 + (j - 1) * 30 );
				            spangroup[i][j] = "sort-sign-" + sl;
					    }
					    sl++;
				    }
				    $("div")
						    .append(
								"<span id='sort-sign-"
										+ sl
										+ "' style='background: #FFFFFF; border:3px solid #a1a1a1; border-radius: 20px; height: 30px; width: 30px; display: inline-block; vertical-align: top; position: absolute;'>"
										+ " <span style='display: block; color: #000000; height: 30px; width: 30px; line-height: 30px; text-align: center; position: absolute;'>"
										+ getstr[i][j-1]
										+ "</span>"
										+ "</span>");
				    var spann = document.getElementById("sort-sign-" + sl);
				    setX(spann, leftt + 100 * i);
				    setY(spann, 200 + j * 50 + (j - 1) * 30 );
				    spangroup[i][j] = "sort-sign-" + sl;
                    sl++;					
				}
			} 
			return spangroup;
		}
		if (size >= 20) {
			alert("请不要输入超过20个元素！");
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
function addele(number, myspan, myindex) {
	var l = myspan.length;
	var j = myspan[myindex].length;
	var len = 0;
	for(var p = 0; p < l; p++){
		len = len + myspan[p].length;
	}
	var leftt = ($('#sort-canvas').width() - l * 90) / 2;
		$("div")
			.append(
				"<span id='sort-sign-"
										+ l
										+ "' style='display: block; height: 50px; width:2px; background-color: #999; position: absolute; top:"
										+ (200 + (j - 1) * 80 + 3)
										+ "px; left: "
										+ (leftt + 100 * myindex + 15)
										+ "px;'>"
										+ " </span>"
										+ " <span id = 'back' style='background: #FFFFFF; border:3px solid #a1a1a1; border-radius: 20px; height: 30px; width: 30px; display: inline-block; vertical-align: top; position: absolute; top: "
										+ (200 + j * 50 + (j - 1) * 30)
										+ "px; left:"
										+ ( leftt + 100 * myindex)
										+ "px;'>"
										+ " <span style='display: block; color: #000000; height: 30px; width: 30px; line-height: 30px; text-align: center; position:absolute;'>"
										+ number
										+ "</span>"
										+ "</span>");
				var spann = document.getElementById("sort-sign-" + len);
				setTimeout(function() {
			       chargecolor_sort(document.getElementById("back"), "#20B2AA");
						}, 1000);
				myspan[myindex].push(spann);
}
// 变色动画
function chargecolor_sort(ele, newcolor) {
	ele.style.background = newcolor;
}