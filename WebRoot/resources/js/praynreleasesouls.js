//祈福类法事，通过ajax查询共享数据 
	function queryPray(){
//  	    	  var building_name = encodeURI($("#").val());
//  	    	  var dan_name = encodeURI($("#").val()); 
  	    	  var size=encodeURI($("#size").val()); 
  	    	  
  	    	  
  	          $.ajax({
  	          url:"prayNReleaseSoulsController.do?pray",
  	          type:"post",
  	          dataType:"text",
  	          data:{
  	        	building_name:building_name/* building_name */,
  	        	dan_name:dan_name/* dan_name */,
  	        	"size":size
  	          },
  	          success:function(responseText){
  	        	var data = eval(responseText);
  	        	var total = data.total;
  	        	var prays=data.rows;
  			    var str = "";
  			    
  			    if(total==0){
  			    //查询不到记录，提示
  		     		str="没有相应的记录";
  		     		alert(str);
  			    }else if(total>0){
  			    	str+='
  			    		<table id="receipttable" class="imagetable">
  					<tr>
  						<th></th>
  						<th>序号</th>
  						<th>付款人</th>
  						<th>牌位号</th>
  						<th>牌位大小</th>
  						<th>时间</th>
  						<th>祈福者</th>
  						<th>祈福对象</th>
  						<th>地址</th>
  						<th>摘要</th>
  					</tr>
  			    		';
  					for(var j = 0;j < len;j ++){
  						str+='
  						<tr>
  						<td><input type="checkbox" value="${prays[j].id}" name="checkbox"></td>
  						<td>${j+1}</td>
  						<td>${prays[j].paymen}</td>
  						<td>${prays[j].serial}</td>
  						<td>${prays[j].size}</td>
  					 	<td>${prays[j].registertime}</td>
  					 	<td>${prays[j].prayingobj}</td>
  					 	<td>${prays[j].livingmenber}</td>
  					 	<td>${prays[j].address}</td>
  					 	<td>${prays[j].summary}</td>
  					 	</tr>
  							';
  					}
  					str+='
  					</table>
  					<br>
  					<input type="button" class="button" value="确定选择" onclick="selectBox()">
  						';
  					$("#norecord").replaceWith(str);
  			    }
  			    
  	          },
  	          error:function(){
  	            alert("system error");
  	          }
  	        }); 
  	 }
 
 

 //超度类法事，通过Ajax查询共享数据
 function queryReleaseSouls(){
	 console.log("ajax1");
	 //获取查询条件
 	  var ancestor = encodeURI($("#ancestor").val());
 	  var size=encodeURI($("#size").val()); 
// 	  var
 	 console.log("ajax2");
 	  
/*       $.ajax({
       url:"prayNReleaseSoulsController.do?releaseSouls",
       type:"post",
       dataType:"json",
       data:{
     	"ancestor":ancestor building_name ,
//     	dan_name:dan_name/* dan_name */,
/*     	"size":size
       },
       
       success:function(responseText){
     	var data = eval(responseText);
     	var arrays=data.rows;
     	var len=data.total;
     	var str = "";
     	if(len==0){
     		//查询不到记录，提示
     		str="没有相应的记录";
     		alert(str);
     	}else if(len > 0){
		    str += '
		    	<table id = "receipttable" class="imagetable">
			<tr>
				<th></th>
				<th>序号</th>
				<th>付款人</th>
				<th>牌位号</th>
				<th>牌位大小</th>
				<th>时间</th>
				<th>阳上</th>
				<th>超渡对象</th>
				<th>超渡类型</th>
				<th>地址</th>
				<th>摘要</th>
			</tr>
		    	';
		    	
		  
		    for(var i = 0;i < len;i ++){
		    	str += '
		    		<tr>
				<td><input type="checkbox" value="${arrays[i].id}" name="checkbox"></td>
				<td>${i+1}</td>
				<td>${arrays[i].paymen}</td>
				<td>${arrays[i].serial}</td>
				<td>${arrays[i].size}</td>
				<%-- <td>${arrays[i].receiptno}</td> --%>
			 	<td>${arrays[i].registertime}</td>
			 	<td>${arrays[i].prayingobj}</td>
			 	<td>${arrays[i].ancestor}</td>
			 	';
			 	if(arrays[i].type=='2'){
			 		str+='<td>十方法界</td>';
			 	}else if(arrays[i].type=='3'){
			 		str+='<td>门堂历代宗亲</td>';
			 	}else if(arrays[i].type=='4'){
			 		str+='<td>堕胎婴灵</td>';
			 	}else if(arrays[i].type=='1'){
			 		str+='<td>无</td>';
			 	}
			 	
			 	str+='	
			 	<td>${arrays[i].address}</td>
			 	<td>${arrays[i].summary}</td>
			 	</tr>
		    		';
		    }
		    str += '
		    	</table>
		    	<br>
			<input type="button" class="button" value="确定选择" onclick="selectBox()">
		    	';
		    
		    
		  $("#norecord").replaceWith(str);
     	}

       },
       error:function(){
         alert("系统发生错误，请与系统管理员联系");
       }
     }); */
}


