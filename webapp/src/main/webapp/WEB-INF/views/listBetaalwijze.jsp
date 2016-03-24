<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Betaalwijzen/Payment methods</title>
</head>
<body>
    <p><a href="create">Add Betaalwijze</a></p>
    <table border=1>
        <thead>
            <tr>
                <th>Betaalwijze ID</th>
                <th>Betaalwijze omschrijving</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${betaalwijzen}" var="betaalwijze">
                <tr>
                    <td><c:out value="${betaalwijze.idBetaalwijze}" /></td>
                    <td><c:out value="${betaalwijze.omschrijving}" /></td>
                    <td><a href="update?idBetaalwijze=${betaalwijze.idBetaalwijze}">Update</a></td>
                    <td><a href="delete?idBetaalwijze=${betaalwijze.idBetaalwijze}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="create">Add Betaalwijze</a></p>
</body>
</html>