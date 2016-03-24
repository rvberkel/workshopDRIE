<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Add new betaalwijze</title>
</head>
<body>
    <form ID="createBetaalwijze?idKlant=${betaalwijze.idBetaalwijze}" method="POST" action="/webapp/createBetaalwijze">
        BetaalwijzeID : <input type="text" readonly="readonly" name="idBetaalwijze"
            value="<c:out value="${betaalwijze.idBetaalwijze}" />" /> <br /> 
        Betaalwijze   : <input type="text" name="betaalwijze"
            value="<c:out value="${betaalwijze.betaalwijze}" />" /> <br /> 
			<input type="submit" value="Submit" />
    </form>
</body>
</html>