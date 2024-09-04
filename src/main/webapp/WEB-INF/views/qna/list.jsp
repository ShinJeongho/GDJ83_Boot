<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QNA List</title>
</head>
<body>
<h1> QNA LIST</h1>

    <table border="2">
    <thead>
        <tr>
            <th>NUM</th>
            <th>Title</th>
            <th>Writer</th>
            <th>Date</th>
        </tr>
    </thead>        
    <tbody>
        <c:forEach var="qna" items="${list}">
            <tr>
                <td>${qna.boardNum}</td>
                <td><a href="./detail?boardNum=${qna.boardNum}">${qna.boardTitle}</a></td>
                <td>${qna.boardWriter}</td>
                <td>${qna.createDate}</td>
            </tr>
        </c:forEach>
    </tbody>
    </table>
    <a href="./add">Add</a>


</body>
</html>
