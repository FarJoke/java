<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Groups</title>
</head>
<body>
<h2>Groups</h2>
<table border="1">
    <tr>
        <th>Number</th>
        <th>Name</th>
    </tr>
    <c:forEach var="group" items="${groups}">
        <tr>
            <td>${group.number}</td>
            <td>${group.name}</td>
        </tr>
    </c:forEach>
</table>
<h3>Add Group</h3>
<form action="groups" method="post">
    <input type="hidden" name="action" value="add">
    Number: <input type="text" name="number"><br>
    Name: <input type="text" name="name"><br>
    <input type="submit" value="Add">
</form>
</body>
</html>
