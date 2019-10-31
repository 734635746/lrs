<html>
<script>
function print(){
	alert("printing");
}
function forward(){
	window.location.href="com/sys/printSuccess.jsp";
}
</script>
<body>
<h2>Hello World!</h2>
<input type="button" value="点击" onclick="print();forward()">
</body>
</html>
