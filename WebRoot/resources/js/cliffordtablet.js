window.onload = function()
{
	createPrinterList();
}

var tn;
var s;
var n;
var tt;

function readyPrint(table_name,tablet_size,tablet_number,tmp_table)
{
	tn = table_name;
	s = tablet_size;
	n=tablet_number;
	tt=tmp_table;
	time();
}

function time()
{
	if(n<=0)
	{
		alert("打印完毕");
		return;
	}
	print(tn, s, n, tt);
	alert(n);
	n--;
	setTimeout(print, 3000);
}

function print(tn,s,n,tt)
{
//	showLoading()
//	alert("打印中");
//	document.getElementById("btn").style.display="none";
//	alert("tableName:"+tableName+"---tmpTable:"+tmpTable);
	
//	if(number%4==0)
//	{
//		number = number / 4;
//	}
//	else
//	{
//		number = number / 4 + 1;
//	}
	
	size = sizeChange(s);
	var result;
	alert("====" + tn + s + tt);
	for(var i=0;i<1;i=i+10)
	{
//		alert("print"+tmpTable+size);
//		result =  eachPrint(tableName,size,tmpTable);
		result =  eachPrint(tn,s,tt);
		if(-1==result)
		{
			//alert("已经打印完啦~");
			break;
		}
	}
//	alert("打印完毕！");
	
}

function eachPrint(tableName,size,tmpTable)
{
//	alert("each print"+tmpTable+size);
	LODOP.PRINT_INIT("祈福牌位");
//	LODOP.ADD_PRINT_IMAGE(20,30,1096,684,"<img border='0' src='"+picture_url+"'>");
	LODOP.SET_PRINTER_INDEX(printer);
	
	for(var i=0;i<1;i++)
	{
		
//		var resutl = CreateAPage(size,tmpTable,tableName);
		var resutl = CreateAPage(size,tmpTable,tableName);
		if(-1 == resutl)
		{
			if(LODOP.CVERSION)
			{
				LODOP.On_Return = function(TaskID,Value)
				{
					if(Value)
					{
						//alert("提前打印完毕");
					}
					else
						alert("打印失败");
				}
				LODOP.PRINT();
			}
			return -1;
		}
	}
	
	
	if(LODOP.CVERSION)
	{
		LODOP.On_Return = function(TaskID,Value)
		{
			if(Value)
			{
				//alert("success");
			}
			else
				alert("failed");
		}
		LODOP.PRINT();
//		alert("end");
	}
	
}


function CreateAPage(size,tmpTable,tableName)
{
	var xmlhttp;
	var picture_url;
	var flag = 0;
	var json;

	size = sizeChange(size);
	if(window.XMLHttpRequest)
	{
		xmlhttp = new XMLHttpRequest();
	}
	else
	{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange = function()
	{
		
		if(xmlhttp.status==200&&xmlhttp.readyState==4)
		{
			
			json = xmlhttp.responseText;
			//alert(json);
			json = JSON.parse(json);
//			alert(json.length);
			if(json.length==0)
			{
				flag =  -1;
			}
			else
			{
				LODOP.NewPage();
				LODOP.SET_PRINT_PAGESIZE(2,2100,2970,"");
				picture_url = pictureURLGetter(json);
				LODOP.ADD_PRINT_IMAGE(0,0,2970,2100,"<img border='0' src='"+picture_url+"'>");
				flag =  1;
				/*
				 * change flag 
				 */
//				changeFlag(tmpTable, size, json);
//				changeFlag(tableName, size, json);
			}
			
		}
		
	}
	alert("create page"+tmpTable+size);
	xmlhttp.open("POST", "/WebPrinterV2/cliffordtablet/print/"+tmpTable+"/"+size,false);
	xmlhttp.send();
//	alert(json);
	return flag;
}
/*
 * 
 * 这个方法有用的，测试方便先注释掉了
function changeFlag(tableName,size,json)
{
	
	var xmlhttp;
	
	if(window.XMLHttpRequest)
	{
		xmlhttp = new XMLHttpRequest();
	}
	else
	{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	for(object in json)
	{
		var id = json[object].id;
		//alert("change:"+tableName+":"+id);
		xmlhttp.open("POST", "/WebPrinterV2/cliffordtablet/change/"+tableName+"/"+size+"/"+id, false);
		xmlhttp.send();
	}
	
}
*/

function cleanPicture()
{
var xmlhttp;
	
	if(window.XMLHttpRequest)
	{
		xmlhttp = new XMLHttpRequest();
	}
	else
	{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange = function()
	{
		if(xmlhttp.status==200&&xmlhttp.readyState==4)
		{
			alert("the picture "+id+" has been deleted");
		}
	}
	
	xmlhttp.open("POST", "clean", false);
	xmlhttp.send();
}
