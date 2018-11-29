<html>
<body>
<a href="/user/info">Hello Word</a>




<form action="/RequestMapping/TestModelAttribute" method="post">
     <input type="hidden" name="id" value="1">
    age:<input name="age" type="text" value="12">
    <button>submit</button>
</form>


<form action="/RequestMapping/TestPojo" method="post">
    <input type="hidden" name="id" value="222">
    username:<input name="name" type="text">
    age:<input name="age" type="text">
    city:<input name="address.city" type="text">
    province:<input name="address.province" type="text">
    <button>submit</button>
</form>


<form action="/RequestMapping/TestMethod" method="post">
    <button>submit</button>
</form>

<a href="/RequestMapping/TestMethod">Test</a>
<div>
<a href="/RequestMapping/TestParam?userID=110">TestParma</a>
</div>

<form action="/RequestMapping/TestRest/1" method="post">
    <input type="hidden" name="_method" value="PUT">
    <button>Test REST PUT</button>
    <input type="submit" value="TestRest PUT"/>
</form>

</body>
</html>
