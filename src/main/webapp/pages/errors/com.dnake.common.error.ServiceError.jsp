<%@page import="com.dnake.common.error.CommonError"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<% 
	CommonError commonError = (CommonError)exception;
	JSON.writeJSONStringTo(commonError.getErrorBean(), response.getWriter());
%>