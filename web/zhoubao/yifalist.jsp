<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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

</head>

<body>
<div id="bott1_1">
    <div id="bott2">
        <h2>已发周报</h2>
    </div>
    <div id="bott4">
        <%--因为是否有已发文件的have标签是跟随hus对象传过来的，所以要先引入session里的hbs的内容才能判断--%>
        <s:iterator value="#session.hbs" status="status" var="info">
            <%--通过判断是否有已发汇报的have标记的值，确定当前是否有已发的汇报 存在两个并列的if--%>
            <s:if test="#info.have!=0">
                <form action="" method="post">
                    <table id="subtree1" width="800px;" style="margin-left: auto;margin-right: auto;">
                        <tr align="center">
                            <td id="td" bgcolor="#63ebfb" width="10%">序号</td>
                            <td id="td" bgcolor="#63ebfb" width="15%">周期</td>
                            <td id="td" bgcolor="#63ebfb" width="25%">日期</td>
                            <td id="td" bgcolor="#63ebfb" width="20%">收件人</td>
                            <td id="td" bgcolor="#63ebfb" width="30%" style="border-right: thin dashed gray;">操作</td>
                        </tr>
                        <tr align="center">
                            <td id="td">
                                <s:property value="#status.count"/>
                            </td>
                            <td id="td">
                                第<s:property value="#info.zyys"/>周
                            </td>
                            <td id="td">
                                <s:property value="#info.rq"/>
                            </td>
                            <td id="td">
                                <s:property value="#info.m_name"/>
                            </td>
                            <td id="td" style="border-right: thin dashed gray;" align="left">
                                <div style="padding-left: 13px;">
                                    <!-- 通过汇报的状态，判断汇报是否被批阅。 -->
                                    <!--  0表示没有邮件，1表示已发送，2表示待发送 3已经批阅。 -->
                                    <s:if test="#info.st==3">
                                        <div style="width:150px; margin: 0 auto;">
                                            汇报已批阅。
                                            <a style="color: blue;text-decoration: underline;" href="javaScript:">
                                                查看
                                            </a>
                                            &nbsp;&nbsp;
                                            <a style="color: blue;text-decoration: underline;" href="javaScript:">
                                                删除
                                            </a>
                                        </div>
                                    </s:if>
                                    <s:if test="#info.st==1">
                                        <div style="width:92px;margin-left:13px;color: red;">
                                            汇报已发送但未批阅。
                                        </div>
                                    </s:if>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                        </tr>
                    </table>
                </form>
            </s:if>

            <s:if test="#info.have==0">
                <div id="bott7">
                    <div id="bott10">
                        <h1>目前还没有已发周报。</h1>
                    </div>
                </div>
            </s:if>
        </s:iterator>
    </div>
</div>
</body>
</html>
