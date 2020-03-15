<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Reservation.CustomerDAO"%>

<%
	String customer_id = request.getParameter("customer_id");
	CustomerDAO DAO = CustomerDAO.getInstance();
	boolean selectId = DAO.selectId(customer_id);
	if (selectId) {
%>이미 있는 아이디 입니다. 다른 것을 사용해주십시오.<%
	} else {
%>사용할 수 있는 아이디입니다.<%
	}
%>