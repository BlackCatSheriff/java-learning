<%--
  Created by IntelliJ IDEA.
  User: www
  Date: 2018/10/20
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功</title>
</head>
<body>

内部转发成功!!


time: ${requestScope.time}
<div>
    user: ${requestScope.useR}

    pojo-user: ${requestScope.pojo_class}


    <a href="/user/1" class="wrapper">查看用户</a>
    <form hidden id="a-wrapper" action="" method="post">
        <input type="hidden" name="_method" value="put">
    </form>
    <script type="javascript">
        $(function () {
            $(".wrapper").click(function () {
                let href = $(this).attr('href');
                $("#a-wrapper").attr("href",href).submit();
                return false;
            });
        });
    </script>
</div>
</body>
</html>
