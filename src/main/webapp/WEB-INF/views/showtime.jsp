<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding= "UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajax短轮询实现显示服务器时间</title>
</head>
<body>
<h1>Ajax短轮询实现显示服务器时间</h1>
<div>
    <div>
        <h2>Ajax短轮询实现显示服务器当前时间为：</h2>
        <div style="color:#F00"><b><p id="serverTime">  </p></b></div>
    </div>

</div>
<script type="text/javascript" src="assets/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

    //showTime();

    function showTime(){
        $.get("showTime",function (data) {
            console.log(data);
            $("#serverTime").html(data);
        })
    }

    /**
     * ajax 短轮询，在前端不断的请求后台
     */
    //1秒一次
    setInterval(showTime, 1000);



</script>
</body>
</html>