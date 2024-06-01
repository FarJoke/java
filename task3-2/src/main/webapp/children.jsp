<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Children</title>
</head>
<body>
<h2>Children</h2>
<table border="1">
    <tr>
        <th>Number</th>
        <th>Name</th>
        <th>Gender</th>
        <th>Age</th>
        <th>Group Number</th>
    </tr>
    <c:forEach var="child" items="${children}">
        <tr>
            <td>${child.number}</td>
            <td>${child.name}</td>
            <td>${child.gender}</td>
            <td>${child.age}</td>
            <td>${child.groupNumber}</td>
        </tr>
    </c:forEach>
</table>
<h3>Add Child</h3>
<form action="children" method="post">
    <input type="hidden" name="action" value="add">
    Number: <input type="text" name="number"><br>
    Name: <input type="text" name="name"><br>
    Gender: <input type="text" name="gender"><br>
    Age: <input type="text" name="age"><br>
    Group Number: <input type="text" name="groupNumber"><br>
    <input type="submit" value="Add">
</form>
</body>
</html>
