<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工作汇报</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="./css/list.css">
	<script type="text/javascript">
		function likai(){
			var inputObj = document.getElementById("inp");
			var us = document.getElementById("sp");
			var reg = /[ ]+/g;
			var str = inputObj.value;
			var str1 = str.replace(reg,"");
			if(str1==""){
				us.innerHTML = "请输入部门";
				return false;
			}else{
				inputObj.value=str1;
				us.innerHTML = "";
				return true;
			}
		}
		function sub1(){
			return likai1();
		}
		function likai1(){
			var inputObj = document.getElementById("inp1");
			var us = document.getElementById("sp1");
			var reg = /[ ]+/g;
			var str = inputObj.value;
			var str1 = str.replace(reg,"");
			if(str1==""){
				us.innerHTML = "请输入部门";
				return false;
			}else{
				inputObj.value=str1;
				us.innerHTML = "";
				return true;
			}
		}
		function sub(){
			return likai();
		}
	</script>
  </head>
  
  <body>
    <div id="bott1_1" style="width: 1000px;margin-left: 110px;">
  	<div id="bott2">
  		<h2>部门管理</h2>
  	</div>
  	<div id="bott4" style="margin-left: 40px;">
  		<form action="" method="post">
			<table id="subtree1" width="800px;" style="margin-left: auto;margin-right: auto;">
				<tr align="center">
					<td id="td" bgcolor="#63ebfb" width="10%">序号</td>
					<td id="td" bgcolor="#63ebfb" width="30%">部门</td>
					<td id="td" bgcolor="#63ebfb" width="40%">备注</td>
					<td id="td" bgcolor="#63ebfb" width="20%" style="border-right: thin dashed gray;">操作</td>
				</tr>
				<s:iterator value="bms" status="status" var="info">
				<tr align="center">
					<td id="td">
						<s:property value="#status.count"/>
					</td>
					<td id="td">
						<s:property value="name"/>
					</td>
					<s:if test="desc==null">
					<td id="td">
					</td>
					</s:if>
					<s:if test="desc!=null">
					<td id="td">
						<s:property value="desc"/>
					</td>
					</s:if>
					<td id="td" style="border-right: thin dashed gray;">
						<s:if test="desc==null">
						<a href="javaScript:">
    						<input type="button" class="inpu1" value='删除'/>
    					</a>
    					</s:if>
    					<s:if test="desc!=null">
    						<input type="button" class="inpu1" style="background-color: gray;" disabled="disabled" value='删除'/>
    					</s:if>
    					<a href="javaScript:">
    						<input type="button" class="inpu1" value='修改'/>
    					</a>
					
					</td>
				</tr>
				</s:iterator>
				<tr>
					<td style="border-bottom: thin dashed gray;"></td>
					<td style="border-bottom: thin dashed gray;"></td>
					<td style="border-bottom: thin dashed gray;"></td>
					<td style="border-bottom: thin dashed gray;"></td>
				</tr>
			</table>
		</form>
  	</div>
  	<s:if test="bm.name!=null">
  	<div style=" margin:0 auto;margin-top:-10px;width:505px;height:45px;">
  		<form action="updatebm" method="post">
  		<input type="hidden" value="${bm.id}" name="bm.id"/>
  		您准备将原本<span style="color: blue;">&nbsp;&nbsp;${bm.name}&nbsp;&nbsp;</span>部门，
  		修改为新部门<input id="inp1" type="text" name="bm.name" onblur="likai1()">
  		&nbsp;&nbsp;
    	<input type="submit" class="inpu1" value="添加"/>
    	<br>
    	<span id="sp1" style="color: red;">${mes}</span>
    	</form>
  	</div>
  	</s:if>
  	<div style="margin:0 auto;width:320px;height:45px;">
  		<form action="addbumen" method="post">
  		新增部门名：<input id="inp" type="text" name="bm.name" onblur="likai()">
  		&nbsp;&nbsp;
    	<input type="submit" class="inpu1" value="添加"/>
    	<br>
    	<span id="sp" style="color: red;">${mes}</span>
    	</form>
  	</div>
  </div>
  </body>
</html>
