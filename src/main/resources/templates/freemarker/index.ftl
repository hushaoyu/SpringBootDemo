<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>User id is ${user.id}</p>
<p>User name is<#if (user.userName)??>
${user.userName}
<#else>
    0
</#if>
</p>
<p>User age is ${user.age}</p>
</body>
</html>