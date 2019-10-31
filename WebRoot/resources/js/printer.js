var printer = 0;

/*
 * 更新打印机列表
 */
function createPrinterList()
{
	var printerList = document.getElementById("PrinterList");
	/*
	 * 清空打印机列表
	 */
	while(printerList.firstChild)
	{
		var oldNode = printerList.removeChild(printerList.firstChild);
		oldNode = null;
	}
	
	var iPrinterCount = LODOP.GET_PRINTER_COUNT();
	
	for(var i=0;i<iPrinterCount;i++)
	{
		var option = document.createElement("option");
		option.innerHTML=LODOP.GET_PRINTER_NAME(i);
		option.value=i;
		printerList.appendChild(option);
	}

}
/*
 * 选中打印机
 */
function selectPrinter()
{
	var printerSelecter = document.getElementById("PrinterList");
	printer = printerSelecter.value;
}

/*
 * 大牌的size汉字和数字的对应转换
 * 小->1
 * 中->2
 * 大->3
 */
function sizeChange(size)
{
	if(size=="小")
		size = 1;
	else if(size=="大")
		size = 2;
	else if(size=="拈香")
		size = 3;
	return size;
}


/*
 * 从牌位的json数据中获得图片的url
 */
function pictureURLGetter(json)
{
	var pictureUrl;
	for(object in json)
	{
		pictureUrl = json[object].url;
	}
	
	return pictureUrl;
}
