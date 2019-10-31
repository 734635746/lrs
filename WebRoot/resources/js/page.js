$(document).ready(function(){
// 焦点自动在搜索框
$("#searchtext").focus();

// 回车搜索功能
$('#searchtext').on('keyup', function(e){  
    var ev = document.all?window.event:e;  
    if(ev.keyCode === 13){  
        $("#searchsubmit").click();  
    }  
}).on('keydown', function(e){  
    var ev = document.all?window.event:e;  
    if(ev.keyCode === 13){  
        return false;  
    }  
}); 
//搜索点击事件
$("#searchsubmit").click(function(e){
	var select = $("#searchselect").find("option:selected").val();
	var searchValue = $("#searchtext").val();
	var searchname = $("#searchtext").val();
	alert(searchValue + select); 
	//if (!searchname) {$("#searchtext").focus();return 0;};
	var path = $("#path").val();/*"association/findMembers";*/
	$('div').load(path,
			 {
	  	//需要传递到后台的数据
		select:select,
		searchValue:searchValue
			  });
	// 搜索完成，再次聚焦搜索框便于多次搜索
	$("#searchtext").focus();
});


});


/**
 *@param i 是要跳转的路径
 *@param j 是上一页，下一页的方式的时候所要跳转到的页面树
 *@param k 在使用输入框输入页面树进行调整的时候的页面树
 */
function goPage(i,j)
{
	var pageNo;
	var searchValue;
	var selected;
	searchValue = $('div.active').find("#searchkey").val();
	selected = $('div.active').find("#selected").val();

	if(j!='0')
	{
		 pageNo =  j;
	}
	else 
	{
		
		pageNo = $('div.active').find("#pageNo").val();
	}
	
	/*跳转页数合法性的验证*/
	var totalPage = $('div.active').find("#totalPage").val();
	var validateResult = validateOfPageNo(pageNo,totalPage);
	if(validateResult == 0||validateResult<0)
	{
		return 0;
	}
	else
	{
		/*var href;
		href = i+"?pageNo="+pageNo+"&searchValue="+searchValue+"&select="+selected;*/
	    $('div.active').load(i,
				 {
		  	//需要传递到后台的数据
	    	pageNo:pageNo,
			searchValue:searchValue,
			select:selected
				  });
	}
}

function entersearch(i,event){  
    if (event.keyCode == 13)  
       {  
    	  goPage(i,'0');
    	// 跳转完成，再次聚焦输入框
    	$("#pageNo").focus();
       }  
}

/*分页的验证*/
function validateOfPageNo(pageNo,totalPage)
{
	if(isInt(pageNo) == false) {
		return 0;
	}
	if(parseInt(pageNo) > totalPage )
	{
		alert("输入的页数太大了，请重新输入");
		return 0;
	}
	else if(pageNo <=0)
	{
		alert("输入的页数太小了，请重新输入");
		return -1;
	}
	else 
	{
		return 1;
	}
} 	

/*判断是否为整数*/
function isInt(ss){

 	var   type="^[0-9]*[1-9][0-9]*$"; 
    var   re   =   new   RegExp(type);
    result =  re.test(ss);
    if(result == false) 
    {
    	alert( "请输入大于零的整数!"); 
        return false;
    }

}
