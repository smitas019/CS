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
<P>${successMsg}</p>
<P>Hello ${userName}</p>
<form action="createPost" method="post">
		<input type="text" name="content" text-align="centre"><br>
		 <h3><input
			type="submit" value="POST" text-align="centre"></h3>
	</form>
	<form action="search" method="post">
			<input type="text" name="name" text-align="centre">
			 <h3><input
			type="submit" value="Search A Friend" text-align="centre"></h3><br></form>
<br>
 <!--provide an html table start tag -->
   <table style="border: 1px solid;">
   <!-- iterate over the collection using forEach loop -->

   <tr>
       <!-- create cells of row -->
       <td>Posted By</td>
       <td></td>
        <td>Contents</td>
        <td></td>
         <td>Posted At</td>
       </tr>
   <c:forEach var="post" items="${posts}">
       <!-- create an html table row -->
       <tr>
       <!-- create cells of row -->
       <td>${post.postedBy}</td>
       <td></td>
       <td>${post.contents}</td>
       <td></td>
       <td>${post.postedAt}</td>
      <%--  <td>
       <c:choose>
        <c:when test="${post.isAvailable == 'yes'}">
       <a href="book?postId=${post.postId}&postName=${post.postName}">Book</a>
       </c:when>
       <c:otherwise>
        <a href="checkout?postId=${post.postId}&postName=${post.postName}">Checkout</a>
       </c:otherwise>
       </c:choose>
       </td> --%>
       <!-- close row -->
       </tr>
       <!-- close the loop -->
   </c:forEach>
   <!-- close table -->
   
   
   </table>
</body>
</html>