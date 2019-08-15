<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All articles</title>
</head>
<body>
<h2>Lista wszytkich artykułów</h2>
<table>
    <thead>
        <tr>
            <td>Id.</td>
            <td>Numer</td>
            <td>Tytuł</td>
            <td>Autor</td>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${articles}" var="article" varStatus="status">
            <tr>
                <td>${status.index}</td>
                <td>${article.id}</td>
                <td>${article.title}</td>
                <td>${article.author}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<h2>Dodaj nowy artykuł</h2>
<form method="post">
    Tytuł: <input type="text" name="title">
    Autor: <input type="text" name="author">
    <input type="submit" value="Dodaj artykuł">
</form>
</body>
</html>
