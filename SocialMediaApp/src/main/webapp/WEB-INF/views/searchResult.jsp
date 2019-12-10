<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="
    padding-left: 506px;">
<P></P>
<h2><a href="logout">Log Out</a></h2>
<h2><a href="dashboard">Home</a></h2>
<P>${successMsg}</p>
<P>Hello ${userName}</p>
 <!--provide an html table start tag -->
   <table style="border: 1px solid;">
   <!-- iterate over the collection using forEach loop -->

   <tr>
       <!-- create cells of row -->
       <td>First Name</td>
       <td></td>
        <td>Last Name</td>
        <td></td>
         <td>date Of Birth</td>
       </tr>
   <c:forEach var="usr" items="${user}">
       <!-- create an html table row -->
       <tr>
       <!-- create cells of row -->
       <td>${usr.firstName}</td>
       <td></td>
       <td>${usr.lastName}</td>
       <td></td>
       <td>${usr.dateOfBirth}</td>
       
       <td>
       
       <c:choose>
        <c:when test="${usr.followed == 'yes'}">
       <a href="unfollow?folowee=${usr.userName}">Unfollow</a>
       </c:when>
       <c:otherwise>
        <a href="follow?folowee=${usr.userName}">Follow</a>
       </c:otherwise>
       </c:choose>
       </td>
       <!-- close row -->
       </tr>
       <!-- close the loop -->
   </c:forEach>
   <!-- close table -->
   
   
   </table>
</body>
</html>