/**
 * 
 */
// 排序播放界面初始化
function newPlayer_sort(getstr) {
	if ($.isArray(getstr)) {
		var num = getstr.length;
		var spangroup = new Array(num);
		var leftt = ($('#sort-canvas').width() - num * 60) / 2;
		var topp = ($('#sort-canvas').height() - 250) / 2;
		if (num <= 15) {

			for (var i = 0; i < num; i++) {
				$("#sort-canvas")
						.append(
								"<span id='sort-sign-"
										+ i
										+ "' style='background: #c9dd22; text-align:center; height:"
										+ getstr[i]
										* 5
										+ "px; width: 50px; position: absolute;'>"
										+ getstr[i] + "</span>");
				var spann = document.getElementById("sort-sign-" + i);
				setX(spann, leftt + 60 * i);
				setY(spann, 20 + 250 - getstr[i] * 5);
				spangroup[i] = "sort-sign-" + i;
			}
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
	// 设置CSS的 'left' 属性
	elem.style.left = pos + "px";
}
// 设置元素的垂直位置
function setY(elem, pos) {
	// 设置CSS的 'top' 属性
	elem.style.top = pos + "px";
}
// 增加水平偏移量
function addX(elem, pos) {
	setX(elem.offsetLeft + pos);
}
// 增加垂直偏移量
function addY(elem, pos) {
	setY(elem.offsetTop + pos);
}
// 位移动画
function displayment_sort(ele, targetX, targetY, speed1, speedelay1) {
	var a1 = setInterval(function() {
		if (ele.offsetTop == targetY) {
			window.clearInterval(a1);
			var a2 = setInterval(function() {
				if (ele.offsetLeft == targetX) {
					window.clearInterval(a2);
				} else {
					ele.style.left = ele.offsetLeft
							+ (ele.offsetLeft < targetX ? 1 : -1) * speed1
							+ "px";
				}
			}, speedelay1);
		} else {
			ele.style.top = ele.offsetTop + (ele.offsetTop < targetY ? 1 : -1)
					* speed1 + "px";
		}
	}, speedelay1);
}
// 变色动画
function chargecolor_sort(ele, newcolor) {
	ele.style.background = newcolor;
}
function getAnimationSpeed(dsclass1) {
	var dsc;
	if (dsclass == 1) {
		dsclass = "1500/1/10";
	} else if (dsclass == 2) {
		dsclass = "1000/1/1";
	} else if (dsclass == 3) {
		dsclass = "500/1/1";
	} else if (dsclass == 4) {
		dsclass = "100/5/1";
	} else if (dsclass == 5) {
		dsclass = "1000/1/1";
	} else if (dsclass == 6) {
		dsclass = "1000/1/1";
	} else if (dsclass == 7) {
		dsclass = "1000/1/1";
	} else if (dsclass == 8) {
		dsclass = "1000/1/1";
	} else {
		dsclass = "1000/1/1";
	}
	return dsclass;
}
// 插入排序动画矩阵计算
function getAnimationArray_InsertionSort(spangroup, getstr) {
	var anArray = new Array();
	var k = 0;

	// 每步动画信息的构建
	anArray[k] = new Array(spangroup.length);
	for (var l = 0; l < spangroup.length; l++) {
		var ele = document.getElementById(spangroup[l]);
		anArray[k][l] = ele.offsetLeft + "/" + ele.offsetTop + "/"
				+ ele.style.backgroundColor + "/" + l;
	}
	k++;

	for (var j = 1; j < spangroup.length; j++) {
		var key = getstr[j];

		// 每步动画信息的构建
		anArray[k] = new Array(spangroup.length);
		for (var l = 0; l < spangroup.length; l++) {
			if (j == l) {
				var message2 = anArray[k - 1][l].split('/');
				anArray[k][l] = message2[0] + "/" + 290 + "/" + "#0eb83a" + "/"
						+ message2[3];
			} else {
				anArray[k][l] = anArray[k - 1][l];
			}
		}
		k++;

		var i = j - 1;

		// 每步动画信息的构建
		anArray[k] = new Array(spangroup.length);
		for (var l = 0; l < spangroup.length; l++) {
			if (i == l) {
				var message2 = anArray[k - 1][l].split('/');
				anArray[k][l] = message2[0] + "/" + message2[1] + "/"
						+ "#70f3ff" + "/" + message2[3];
			} else {
				anArray[k][l] = anArray[k - 1][l];
			}
		}
		k++;

		while (i >= 0 && getstr[i] >= key) {

			// 每步动画信息的构建
			anArray[k] = new Array(spangroup.length);
			for (var l = 0; l < spangroup.length; l++) {
				if (i == l) {
					var message2 = anArray[k - 1][l].split('/');
					anArray[k][l] = message2[0] + "/" + message2[1] + "/"
							+ "#70f3ff" + "/" + message2[3];
				} else {
					anArray[k][l] = anArray[k - 1][l];
				}
			}
			k++;

			getstr[i + 1] = getstr[i];

			// 每步动画信息的构建
			anArray[k] = new Array(spangroup.length);
			for (var l = 0; l < spangroup.length; l++) {
				if (i == l) {
					var message2 = anArray[k - 1][l].split('/');
					var numm1 = parseInt(message2[0]) + 60;
					var nuum1 = i + 1;
					anArray[k][l] = numm1 + "/" + message2[1] + "/"
							+ message2[2] + "/" + message2[3];
				} else if (l == j) {
					var message2 = anArray[k - 1][l].split('/');
					var numm2 = parseInt(message2[0]) - 60;
					anArray[k][l] = numm2 + "/" + message2[1] + "/"
							+ message2[2] + "/" + message2[3];
				} else {
					anArray[k][l] = anArray[k - 1][l];
				}
			}
			k++;

			// 每步动画信息的构建
			anArray[k] = new Array(spangroup.length);
			for (var l = 0; l < spangroup.length; l++) {
				if (i == l) {
					var message2 = anArray[k - 1][l].split('/');
					anArray[k][l] = message2[0] + "/" + message2[1] + "/"
							+ "#f00056" + "/" + message2[3];
				} else {
					anArray[k][l] = anArray[k - 1][l];
				}
			}
			k++;

			i = i - 1;
		}
		getstr[i + 1] = key;

		// 每步动画信息的构建
		console.log("------------");
		anArray[k] = new Array(spangroup.length);
		for (var l = 0; l < spangroup.length; l++) {
			if (l <= i) {
				var message2 = anArray[k - 1][l].split('/');
				anArray[k][l] = message2[0] + "/" + message2[1] + "/"
						+ "#f00056" + "/" + message2[3];
			} else if (l == j) {
				var message2 = anArray[k - 1][j].split('/');
				var numm3 = 20 + 250 - getstr[i + 1] * 5;
				anArray[k][l] = message2[0] + "/" + numm3 + "/" + "#f00056"
						+ "/" + message2[3];
			} else {
				anArray[k][l] = anArray[k - 1][l];
			}
			console.log(anArray[k][l]);
		}
		k++;
		console.log("------------");
		// 每步动画信息的构建
		anArray[k] = new Array(spangroup.length);
		for (var l = 0; l < spangroup.length; l++) {
			if (l <= i) {
				var message2 = anArray[k - 1][l].split('/');
				anArray[k][l] = message2[0] + "/" + message2[1] + "/"
						+ message2[2] + "/" + message2[3];
				console.log(anArray[k][l]);
				console.log("________" + l);
			} else if (l == i + 1) {
				var message2 = anArray[k - 1][j].split('/');
				anArray[k][l] = message2[0] + "/" + message2[1] + "/"
						+ message2[2] + "/" + j;
				console.log(anArray[k][l]);
				console.log("________" + l);
			} else if (l > i + 1 && l <= j) {
				var message2 = anArray[k - 1][l - 1].split('/');
				var num11 = l - 1;
				anArray[k][l] = message2[0] + "/" + message2[1] + "/"
						+ message2[2] + "/" + message2[3];
				console.log(anArray[k][l]);
				console.log("________" + l);
			} else {
				anArray[k][l] = anArray[k - 1][l];
				console.log(anArray[k][l]);
				console.log("________" + l);
			}
		}
		k++;

	}
	return anArray;
}

function getAnimationArray_MergeSort(spangroup, getstr) {
	// 颜色数组
	var rgb = new Array(15);
	rgb[0] = '#ff4300';
	rgb[1] = '#ffa100';
	rgb[2] = '#ffff00';
	rgb[3] = '#e3ff00';
	rgb[4] = '#7cff00';
	rgb[5] = '#00ff2d';
	rgb[6] = '#00ff81';
	rgb[7] = '#00ffb0';
	rgb[8] = '#00e7ff';
	rgb[9] = '#00ffd5';
	rgb[10] = '#0051ff';
	rgb[11] = '#1f00ff';
	rgb[12] = '#7400ff';
	rgb[13] = '#6a00ff';
	rgb[14] = '#b500ff';

	var anArray = new Array();
	var k = 0;

	// 每步动画信息的构建
	anArray[k] = new Array(spangroup.length);
	for (var l = 0; l < spangroup.length; l++) {
		var ele = document.getElementById(spangroup[l]);
		anArray[k][l] = ele.offsetLeft + "/" + ele.offsetTop + "/" + rgb[l]
				+ "/" + l;
	}
	k++;

	console.log(anArray[k - 1]);

	// 每步动画信息的构建
	anArray[k] = new Array(spangroup.length);
	for (var l = 0; l < spangroup.length; l++) {
		var ele = document.getElementById(spangroup[l]);
		anArray[k][l] = ele.offsetLeft + "/" + ele.offsetTop + "/" + rgb[l]
				+ "/" + l;
	}
	k++;

	function MergeSort(nums1, p, r) { // #8DB6CD灰"#8DB6CD"
		if (p < r) { // #98FB98绿"#98FB98"
			var q = parseInt((p + r) / 2);
			MergeSort(nums1, p, q);
			MergeSort(nums1, q + 1, r);

			// 每步动画信息的构建
			anArray[k] = new Array(spangroup.length);
			for (var l = 0; l < spangroup.length; l++) {
				if (l >= p - 1 && l <= r - 1) {
					var message2 = anArray[k - 1][l].split('/');
					anArray[k][l] = message2[0] + "/" + message2[1] + "/"
							+ "#98FB98" + "/" + message2[3];
				} else {
					anArray[k][l] = anArray[k - 1][l];
				}
			}
			k++;

			Merge(nums1, p, q, r);
		}
		return;
	}
	function Merge(nums, p, q, r) {
		var n1 = q - p + 1;
		var n2 = r - q;
		var nums1 = new Array(n1 + 1);
		var nums2 = new Array(n2 + 1);
		for (var s = 0; s < n1; s++)
			nums1[s] = nums[s + p - 1];
		for (var t = 0; t < n2; t++)
			nums2[t] = nums[t + q];
		nums1[n1] = 100000000;
		nums2[n2] = 100000000;
		var i = 0;
		var j = 0;
		anArray[k] = new Array(spangroup.length);
		var keke = new Array(r - p + 1);
		var leftgo = new Array(spangroup.length);
		for (var x = 0; x < spangroup.length; x++) {
			var ms = anArray[k - 1][x].split('/');
			leftgo[x] = ms[0];
		}
		var v = 0;
		for (var u = p - 1; u < r; u++) {
			if (nums1[i] <= nums2[j]) {
				nums[u] = nums1[i];

				anArray[k] = new Array(spangroup.length);
				for (var l = 0; l < spangroup.length; l++) {
					if (l == i + p - 1) {
						var message3 = anArray[k - 1][i + p - 1].split('/');
						var nummm1 = parseInt(message3[1]) + 270;
						anArray[k][i + p - 1] = leftgo[u] + "/" + nummm1 + "/"
								+ message3[2] + "/" + message3[3];
					} else {
						anArray[k][l] = anArray[k - 1][l];
					}
				}
				k++;

				keke[v] = i + p - 1;

				v++;
				i++;
			} else {
				nums[u] = nums2[j];
				anArray[k] = new Array(spangroup.length);
				for (var l = 0; l < spangroup.length; l++) {
					if (l == j + q) {
						var message3 = anArray[k - 1][j + q].split('/');
						var nummm1 = parseInt(message3[1]) + 270;
						anArray[k][j + q] = leftgo[u] + "/" + nummm1 + "/"
								+ message3[2] + "/" + message3[3];
					} else {
						anArray[k][l] = anArray[k - 1][l];
					}
				}
				k++;

				keke[v] = j + q;

				v++;
				j++;
			}
		}

		anArray[k] = new Array(spangroup.length);
		for (var l = 0; l < spangroup.length; l++) {
			if (l >= p - 1 && l <= r - 1) {
				var message2 = anArray[k - 1][keke[l - p + 1]].split('/');
				anArray[k][l] = message2[0] + "/" + message2[1] + "/"
						+ "#8DB6CD" + "/" + message2[3];
			} else {
				anArray[k][l] = anArray[k - 1][l];
			}
		}
		k++;

		//
		anArray[k] = new Array(spangroup.length);
		for (var l = 0; l < spangroup.length; l++) {
			if (l >= p - 1 && l <= r - 1) {
				var message2 = anArray[k - 1][l].split('/');
				var nummm2 = parseInt(message2[1]) - 270;
				anArray[k][l] = message2[0] + "/" + nummm2 + "/" + rgb[q] + "/"
						+ message2[3];
			} else {
				anArray[k][l] = anArray[k - 1][l];
			}
		}
		k++;

		return;
	}

	MergeSort(getstr, 1, getstr.length);

	return anArray;
}