<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<body>
<h2>Hello Employee</h2>
<br>
<br>
<br>
<br>

Your name ${employee.name}   <br>
Your surname ${employee.surname}   <br>
Your salary ${employee.salary}   <br>
Your dep ${employee.department}   <br>
Your car ${employee.carBrand}   <br>
Your lang <ul>
    <c:forEach var="lang" items="${employee.languages}">
        <li>${lang}</li>
    </c:forEach> <br>
    Your phone ${employee.phoneNumber}   <br>
    Your email ${employee.email}   <br>
</ul>
</body>
</html>