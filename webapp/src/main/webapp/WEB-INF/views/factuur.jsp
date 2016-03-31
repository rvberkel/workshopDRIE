<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create or update factuur</title>
</head>
<body>

<form ID="createFactuur?idFactuur=${factuur.idFactuur}" method="POST" action="/webapp/createfactuur">
        ID-Factuur : 
        <input type="text" readonly="readonly" name="idFactuur" value="<c:out value="${factuur.idFactuur}" />" /> <br /> 
          Factuurdatum (JJJJ/MM/DD): 
        <fmt:formatDate var="fmtDate" value="${factuur.factuurDatum}" pattern="yyyy/MM/dd"/>
        <input type="text" name="factuurDatum" value="<c:out value="${fmtDate}" />" /> <br /> 
        Bijbehorende bestelling : 
        <input type="text" name="idBestelling" value="<c:out value="${factuur.bestelling.idBestelling}" />" /> <br />
			<input type="submit" value="Submit" />
    </form>
</body>
</html>