<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new adres</title>
</head>
<body>
    <form ID="createOrUpdateAdres?idAdres=${adres.idAdres}" method="POST" action="/webapp/createOrUpdateAdres">
        AdresID : <input type="text" readonly="readonly" name="idAdres"
            value="<c:out value="${adres.idAdres}" />" /> <br /> 
        Straatnaam : <input type="text" name="straatnaan"
            value="<c:out value="${adres.straatnaam}" />" /> <br /> 
        Huisnummer : <input type="text" name="huisnummer"
            value="<c:out value="${adres.huisnummer}" />" /> <br /> 
        Postcode : <input type="text" name="postcode"
            value="<c:out value="${adres.postcode}" />" /> <br /> 
        Woonplaats : <input type="text" name="woonplaats"
			value="<c:out value="${adres.woonplaats}" />" /> <br /> <input
            type="submit" value="Submit" />
    </form>
</body>
</html>