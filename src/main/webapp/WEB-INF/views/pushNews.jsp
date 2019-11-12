<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding= "UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻推送</title>
</head>
<body>
<h1>每日头条</h1>
<div>
    <div>
        <h2>每日头条新闻实时看</h2>
        <div style="color:#F00"><b><p id="realTimeNews">  </p></b></div>
    </div>
    <hr>

    <hr>

</div>
<script type="text/javascript" src="assets/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

    longLoop();

    function longLoop() {
        $.get("realTimeNews",function (data) {
            console.log(data);
            $("#realTimeNews").html(data);
            longLoop();//马上再发起请求
        })
    }


</script>
</body>
</html>