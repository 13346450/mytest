<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="register">
  <div class="container box" id="box">
    <div class="row box2" align="center">
      <div class="col-xs-2 col-md-offset-9 close"><span title="关闭" style="margin:10px;"></span></div>
      <div class="col-xs-9 col-md-offset-2" id="logincont">
        <ul>
          <li id="lgstyle">
            <div class="login-ing login1 lgactive">住户</div>
            <div class="login-ing login2">商家</div>
            <div class="login-ing login3">物业</div>
          </li>
          <li  style="padding-top:45px;">
            <div class="ui-form-item" >
              <label class="ui-label"> <span class="ui-icon ui-icon-userDEF">用户名</span> </label>
              <input type="text" class="ui-input ui-input-normal" maxlength="100"  placeholder="请输入用户名" autofocus>
            </div>
          </li>
          <li  style="padding-top:45px;">
            <div class="ui-form-item ">
              <label class="ui-label"> <span class="ui-icon ui-icon-password">密码</span> </label>
              <input type="password" class="ui-input ui-input-normal" placeholder="请输入密码">
              <div class="clear"></div>
            </div>
          </li>
          <li style="padding-top:25px; font-size:14px;">
            <input type="checkbox" class="check">
            <span class="check">自动登录</span>
            <div class="right"><a class="loginbtn4" href="#" style=" color:#07b9d9;">忘记密码 |</a> <a class="loginbtn5" href="#" style=" color:#07b9d9;">新用户注册</a></div>
          </li>
          <li style="padding-top:35px; font-size:14px;">
            <input type="button" class="loginbtn" value="登 录">
          </li>
        </ul>
      </div>
      <div class="col-xs-9 col-md-offset-2"  id="logincont2">
        <ul>
          <li style="font-size:18px;">注  册 i 尚 社 区 账 号</li>
          <li id="lgstyle" style="padding-left:55px; height:39px;">
            <div class="login-ing login1 lgactive">住户</div>
            <div class="login-ing login3">商家</div>
          </li>
          <li  style=" margin-top:-8px;">
            <div class="ui-form-item" >
              <input type="text" class="ui-input1 ui-input-normal" maxlength="100"  placeholder="请输入手机号" autofocus>
            </div>
          </li>
          <li  style="padding-top:23px;">
            <div class="ui-form-item" >
              <input type="password" class="ui-input1 ui-input-normal" placeholder="请输入密码">
            </div>
          </li>
          <li  style="padding-top:23px;">
            <div class="ui-form-item" >
              <input type="password" class="ui-input1 ui-input-normal" placeholder="请再次输入密码">
            </div>
          </li>
          <li  style="padding-top:23px;">
            <div class="ui-form-item ">
              <input type="password" class="ui-input1 ui-input-normal1" placeholder="请输入验证码">
              <label class="verification ui-input-normal1">获取验证码</label>
              <div class="clear"></div>
            </div>
          </li>
          <li style="padding-top:10px; font-size:14px;">
            <input type="button" class="loginbtn" value="登 录">
          </li>
          <li style="padding-top:5px;font-size:14px;">已有账号？<a href="#"><span class="loginbtn3" style="color:#07b9d9;">点此登录</span></a> </li>
        </ul>
      </div>
      <div class="col-xs-9 col-md-offset-2"  id="logincont3">
        <ul>
          <li style="font-size:18px; margin-bottom:15px;">找 回 密 码</li>
          <li  style=" margin-top:-30px;">
            <div class="ui-form-item" >
              <input type="text" class="ui-input1 ui-input-normal" maxlength="100"  placeholder="请输入手机号" autofocus>
            </div>
          </li>
          <li  style="padding-top:32px;">
            <div class="ui-form-item" >
              <input type="password" class="ui-input1 ui-input-normal" placeholder="请输入密码">
            </div>
          </li>
          <li  style="padding-top:32px;">
            <div class="ui-form-item" >
              <input type="password" class="ui-input1 ui-input-normal" placeholder="请再次输入密码">
            </div>
          </li>
          <li  style="padding-top:32px;">
            <div class="ui-form-item ">
              <input type="password" class="ui-input1 ui-input-normal1" placeholder="请输入验证码">
              <label class="verification ui-input-normal1">获取验证码</label>
              <div class="clear"></div>
            </div>
          </li>
          <li style="padding-top:18px; font-size:14px;">
            <input type="button" class="loginbtn" value="确 定">
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>