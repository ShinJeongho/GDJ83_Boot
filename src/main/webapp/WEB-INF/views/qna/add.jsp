<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> Qna Add</h1>
<%-- <form action="" method="post" enctype="multipart/form-data">
	<input type="text" name="boardTitle">
	<input type="text" name="boardWriter">
	<textarea rows="" cols="" name="boardContents"></textarea>
	<input type="file" name="attaches">
	<input type="file" name="attaches">
	<input type="file" name="attaches">
	<button>Add</button>
</form>
 --%>
<form:form modelAttribute="qnaVO">
	<form:input path="boardWriter"/> <!-- Vo의 setter/getter의 이름을 이야기함 (path) --><br>
	<form:errors path="boardWriter"></form:errors><br>
	<form:input path="boardTitle"/><br>
	<form:errors path="boardTitle"></form:errors><br>
	<form:textarea path="boardContents"/><br>
	<input type="file" name="attaches"><br>
	<input type="file" name="attaches"><br>
	<input type="file" name="attaches"><br>
	<button>Add</button><br>
	<form:button>Add</form:button>
</form:form>

</body>
</html>