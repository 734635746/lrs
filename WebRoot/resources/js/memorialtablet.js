window.onload = function()
{
	createPrinterList();
}

var timeout = false;

var tableName;
var size;
var tmpTable;

var loopTimes = 0;
function time()
{
	
	
	if(loopTimes<=0)
	{
		alert("finish!");
		return;
	}
	sayHello(tableName,size,tmpTable);
	setTimeout(time, 1000);
	if(size==2)
		loopTimes = loopTimes - 2;
	else
		loopTimes--;
}

function sayHello(a,b,c)
{
	var xmlhttp;
	var picture_url;
	
	//alert(loopTimes);
	
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
			var json = xmlhttp.responseText;
//			alert(json);
			json = JSON.parse(json);
			if(json.length==0)
			{
				return;
			}	
			
			picture_url = pictureURLGetter(json);
			/*
			 * 
			 */
			changeFlag(tmpTable,size,json);
			
			LODOP.PRINT_INIT("超度牌位");
			LODOP.SET_PRINT_PAGESIZE(2,2100,2970,"");
			//LODOP.SET_PRINT_PAGESIZE(2,2100,2970,"");
			LODOP.ADD_PRINT_IMAGE(70,40,2970,2100,"<img border='0' src='"+picture_url+"'>");
			LODOP.SET_PRINTER_INDEX(printer);
			if (LODOP.CVERSION) 
			{  //用CVERSION属性判断是否云打印
		        LODOP.On_Return=function(TaskID,Value)
		        {
		              if (Value)
		              {
		            	//  alert("已经全部添加到打印机队列中");
		              }
		              else alert("打印失败！");
		        };
		        LODOP.PRINT();
		        /*
		         * change original flag
		         */
		        
		 //       changeFlag(tableName,size,json);
		        
//		        alert(json);
//		        return;
			};
			
			
		}
	};
	
		xmlhttp.open("POST", "/WebPrinterV2/memorialtablet/print/"+c+"/"+b, false);
		xmlhttp.send();
		
	
}

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
		xmlhttp.open("POST", "/WebPrinterV2/memorialtablet/change/"+tableName+"/"+size+"/"+id, false);
		xmlhttp.send();
	}
	
}

//a-->tableName
//b-->size
//c-->number
//d-->tmpTable
function print(a,b,c,d)
{
	
	tableName = a;
	size =  sizeChange(b);
	tmpTable = d;
	loopTimes = c;
	time();
}