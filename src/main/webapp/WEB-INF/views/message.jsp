<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<p style="font-weight:bold; color:green; text-align:center;">
	<%
	String message = (String) request.getAttribute("message");
	 if (message != null)
		out.println(message);
	%>

</p>