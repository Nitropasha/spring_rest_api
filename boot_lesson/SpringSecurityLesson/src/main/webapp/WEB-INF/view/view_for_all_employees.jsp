<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>
<h3>Info for all employees</h3>
<security:authorize access="hasRole('HR')">
<input type="button" value="Salary" onclick="window.location.href = 'hr_info'">
Only for HR stuff</security:authorize>
<br><br>
<security:authorize access="hasRole('MANAGER')">
<input type="button" value="Salary" onclick="window.location.href = 'manager_info'">
    Only for manager </security:authorize>
</body>
</html>