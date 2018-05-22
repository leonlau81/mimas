/**
 *利用正则表达式格去掉小数点后多余的0
 */
function formatAmount(value)
{
	if(value.indexOf(".") > 0)
	{    
		value = parseFloat(value).toFixed(6)+"";
		value = value.replace(/0+?$/g, "");//去掉多余的0    
		value = value.replace(/[.]$/g, "");//如最后一位是.则去掉    
		return value;
	}else{
		if(parseFloat(value)>0){
			return parseFloat(value);
		}else{
			return 0;
		}
		
	}
}	

function add0(m){return m<10?'0'+m:m }
function formatDateTime(millisecond)
{
	//millisecond是整数，否则要parseInt转换
	var time = new Date(millisecond);
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y+'-'+add0(m)+'-'+add0(d);
}

function getTodayString(millisecond)
{
	var time = new Date();
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y+'-'+add0(m)+'-'+add0(d);
}


//制保留2位小数，如：2，会在2后面补上00.即2.00  
function formatFloat(num) {  
    var f = parseFloat(num);  
    if (isNaN(f)) {  
        return false;  
    }  
    var f = Math.round(num*100)/100;  
    var s = f.toString();  
    var rs = s.indexOf('.');  
    if (rs < 0) {  
        rs = s.length;  
        s += '.';  
    }  
    while (s.length <= rs + 2) {  
        s += '0';  
    }  
    return s;  
}  

/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 *
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatAmount(num) {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    cents = num%100;
    num = Math.floor(num/100).toString();
    if(cents<10)
    cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num + '.' + cents);
}
 
/**
 * 将表单序列化成object
 */
$.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};

/**
 * 获取项目根路径
 * @returns {} 
 */
function getContextPath(){ 
	var pathName = document.location.pathname; 
	var index = pathName.substr(1).indexOf("/"); 
	var result = pathName.substr(0,index+1); 
	return result; 
}
//var contextPath=getContextPath();


//获取url中的参数
/*function getUrlParam(name) {
	alert("name=="+name);
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    alert('r=='+r);
    if (r != null) return unescape(r[2]); return null; //返回参数值
}*/
