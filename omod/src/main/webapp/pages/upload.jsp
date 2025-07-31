<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>XLSX Import</title></head>
<body>
    <h2>Import XLSX File</h2>
    <form method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit" value="Upload"/>
    </form>
    <c:if test="${not empty message}">
        <div style="color:green;">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div style="color:red;">${error}</div>
    </c:if>
</body>
</html>
