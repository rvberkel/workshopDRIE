<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Add new user</title>
</head>
<body>
    <form method="POST"> <!-- action="webapp/listKlant" name="frmAddUser">-->
        Inlognaam : <input type="text" name="inlognaam"/> <br/> 
        Wachtwoord : <input type="password" name="inlogwachtwoord"/><br /> 
        <input type="submit" value="Submit" />
    </form>
</body>
</html>