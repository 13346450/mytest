<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main-header" id="h1" align="center">
  <div class="container contentw">
    <div class="row">
      <div class="col-xs-2" id="top-left1"> <span class="top-font">金山国际&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="ci-right"></i></span>
        <div id="areashow">
          <div id="borderbm" ><span >选择小区</span></div>
          <ul>
            <li style="margin:20px;">城市：
              <select style="margin-left:20px;">
                <option value ="1">福建省</option>
                <option value ="2">北京市</option>
                <option value="3">江西省</option>
                <option value="4">浙江省</option>
              </select>
              <select style="margin-left:20px;">
                <option value ="1">厦门市</option>
                <option value ="2">龙岩市</option>
                <option value="3">三明市</option>
                <option value="4">漳州市</option>
              </select>
              <select style="margin-left:20px;">
                <option value ="1">湖里区</option>
                <option value ="2">海沧区</option>
                <option value="3">集美区</option>
                <option value="4">思明区</option>
              </select>
            </li>
            <li id="area" style="margin:20px;"> <span>金山社区</span></li>
          </ul>
        </div>
      </div>
      <div class="col-xs-10">
        <div id="top-right1" class="right"><i class="icon-tel"></i><span class="top-font">客服热线：888888</span></div>
        <div id="top-center1" class="right"><span class="top-font">&nbsp;&nbsp;<span class="loginbtn2">注册</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span></div>
        <div id="top-center2" class="right"><span class="top-fontb">您好，请<span class="loginbtn1">登录</span>！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</span><i class="icon-man"></i><span class="top-fontb"></span></div>
        <div id="top-left2" class="right"> <i class="icon-phone"></i><span class="top-font">手机APP&nbsp;&nbsp;<i class="ci-right"></i></span><span class="top-font">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|</span>
          <div id="codeshow"></div>
        </div>
      </div>
    </div>
  </div>
</div>