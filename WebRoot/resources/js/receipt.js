var json;

window.onload = function()
{
	createPrinterList();
	
//	loadReceiptJSON();
	
}

function print(id)
{
	alert("test");
	var picture_url;
	var xmlhttp;
	if(window.XMLHttpRequest)
	{
		xmlhttp = new XMLHttpRequest();
	}
	else
	{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		
	}
	xmlhttp.onreadystatechange = function(){
		
		if(xmlhttp.status==200&&xmlhttp.readyState==4)
		{
			picture_url = xmlhttp.responseText;
//			alert(picture_url);
			LODOP.PRINT_INIT("收据");
			LODOP.ADD_PRINT_IMAGE(0,0,608,380,"<img border='0' src='"+picture_url+"'>");
			LODOP.SET_PRINT_PAGESIZE(1,2150,900,"");
//			alert("painter:"+printer);
			LODOP.SET_PRINTER_INDEX(printer);
			LODOP.PRINT();
			alert("hello");
			cleanPicture(id);
		}
	}
	xmlhttp.open("POST","/WebPrinterV2/receipt/print/"+id,true);
	xmlhttp.send();
	
}

function cleanPicture(id)
{
	alert("hello");
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
	
	xmlhttp.open("POST", "clean/"+id, false);
	xmlhttp.send();
}


function loadReceiptJSON()
{
	
	var xmlhttp;
	if(window.XMLHttpRequest)
	{
		//alert("good");
		xmlhttp = new XMLHttpRequest();
	}
	else
	{
//		alert("bad");
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		
	}
	xmlhttp.onreadystatechange = function(){
		
		if(xmlhttp.status==200&&xmlhttp.readyState==4)
		{
			var json;
			json = xmlhttp.responseText;
			alert(json)
			json = JSON.parse(json);
			makeReceiptTable(json);
		}
	}
	
	xmlhttp.open("POST","list",true);
	xmlhttp.send();
}

function makeReceiptTable(json)
{
//	alert("execute");
	
	var table = document.getElementById("table");
	
	for(object in json)
	{
		var ntr = document.createElement("tr");
		
		var date_th = document.createElement("th");
		var summary_th = document.createElement("th");
		var money_th = document.createElement("th");
		var registrant_th = document.createElement("th");
		var print_th = document.createElement("th");
		
		var button = document.createElement("button");
		
		button.innerHTML = "打印";
		button.id = json[object].id;
		button.onclick = function()
		{
			print(this.id);
		}
		
		date_th.innerHTML = json[object].registertime;
		summary_th.innerHTML = json[object].summary;
		money_th.innerHTML = json[object].money;
		registrant_th.innerHTML = json[object].registrant;
		
		print_th.appendChild(button);
		
		ntr.appendChild(date_th);
		ntr.appendChild(summary_th);
		ntr.appendChild(money_th);
		ntr.appendChild(registrant_th);
		ntr.appendChild(print_th);
		
		table.appendChild(ntr);
	}
	
}