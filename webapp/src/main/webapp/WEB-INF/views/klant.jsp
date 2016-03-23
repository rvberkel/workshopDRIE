<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Add new klant</title>
</head>
<body>
    <form ID="createKlant?idKlant=${klant.idKlant}" method="POST" action="/webapp/createKlant">
        KlantID : <input type="text" readonly="readonly" name="idKlant"
            value="<c:out value="${klant.idKlant}" />" /> <br /> 
        Voornaam : <input type="text" name="voornaam"
            value="<c:out value="${klant.voornaam}" />" /> <br /> 
        Tussenvoegsel : <input type="text" name="tussenvoegsel"
            value="<c:out value="${klant.tussenvoegsel}" />" /> <br /> 
        Achternaam : <input type="text" name="achternaam"
            value="<c:out value="${klant.achternaam}" />" /> <br /> 
        Email : <input type="text" name="email"
			value="<c:out value="${klant.email}" />" /> <br /> <input
            type="submit" value="Submit" />
    </form>
</body>
</html>