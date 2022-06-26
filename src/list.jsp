<%@page import="java.util.ArrayList"%>
<%@page import="o.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
   
   String returns = "";
   String name = request.getParameter("name");
   String para = request.getParameter("para");  
   sql sql_ = new sql();

%>
<%
if (name.equals("select_from_budget2")) {
	String [] param = para.split(",");
	returns = sql_.select_from_budget2(param[0],param[1],param[2]);
      out.println(returns);
      System.out.println(returns);
   }
else if (name.equals("local_button")) {
	String [] param = para.split(",");
	returns = sql_.local_button(param[0],param[1],param[2],param[3]);
      out.println(returns);
      System.out.println(returns);
   }
else if (name.equals("local_search")) {
	String [] param = para.split(",");
	returns = sql_.local_search(param[0],param[1]);
      out.println(returns);
      System.out.println(returns);
   }
else if (name.equals("select_from_subsidy")) {
	String [] param = para.split(",");
	returns = sql_.select_from_subsidy(param[0]);
      out.println(returns);
      System.out.println(returns);
   }
else if (name.equals("central_join_subsidy")) {
	String [] param = para.split(",");
	returns = sql_.central_join_subsidy(param[0],param[1],param[2]);
      out.println(returns);
      System.out.println(returns);
   }
else if (name.equals("i_like")) {
	String [] param = para.split(",");
	returns = sql_.i_like(param[0]);
      out.println(returns);
      System.out.println(returns);
   }
%>