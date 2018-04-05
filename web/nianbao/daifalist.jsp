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
        <h2>待发年报</h2>
    </div>
    <div id="bott4">
        <%--因为是否有待发文件的have标签是跟随hus对象传过来的，所以要先引入session里的hbs的内容才能判断--%>
        <s:iterator value="#session.hbs" status="status" var="info">
            <%--通过判断是否有待发汇报的have标记的值，确定当前是否有待发的汇报 存在两个并列的if--%>
            <s:if test="#info.have!=0">
                <form action="" method="post">
                    <table id="subtree1" width="600px;" style="margin-left: auto;margin-right: auto;">
                        <tr align="center">
                            <td id="td" bgcolor="#63ebfb" width="10%">序号</td>
                            <td id="td" bgcolor="#63ebfb" width="20%">年期</td>
                            <td id="td" bgcolor="#63ebfb" width="25%">日期</td>
                            <td id="td" bgcolor="#63ebfb" width="20%">收件人</td>
                            <td id="td" bgcolor="#63ebfb" width="25%" style="border-right: thin dashed gray;">操作</td>
                        </tr>
                        <tr align="center">
                            <td id="td">
                                <s:property value="#status.count"/>
                            </td>
                            <td id="td">
                                <s:property value="#info.zyys"/>年度
                            </td>
                            <td id="td">
                                <s:property value="#info.rq"/>
                            </td>
                            <td id="td">
                                <s:property value="#info.m_name"/>
                            </td>
                            <td id="td" style="border-right: thin dashed gray;" align="left">
                                <div style="padding-left: 13px;">
                                    <!-- 通过对比当前汇报的年期数和真实的年期数判断当前汇报是否过期 存在两个并列的if-->
                                    <s:if test="#info.zyys==#info.nowZyys">
                                        <a style="color: blue;text-decoration: underline;"
                                           href="toSendhb?hb.ty=2">发送</a>
                                        &nbsp;
                                        <a style="color: blue;text-decoration: underline;"
                                           href="edithb?hb.ty=2">编辑</a>
                                        &nbsp;
                                        <a style="color: blue;text-decoration: underline;"
                                           href="deletehb?hb.ty=2">删除</a>
                                    </s:if>
                                    <s:if test="#info.zyys!=#info.nowZyys">
                                        <%--通过汇报的状态，判断汇报是在过期前是未发送状态--%>
                                        <%--0表示没有邮件，1表示已发送，2表示待发送 3已经批阅。--%>
                                        <%--但是因为进入daifa.jsp的汇报都是待发属性的所以不再判断--%>
                                        <%--<s:if test="#info.st==2">--%>
                                        <span style="color: red;">
                                                汇报过期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </span>
                                        <a style="color: blue;text-decoration: underline;" href="javaScript:">删除</a>
                                        <%--</s:if>--%>
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
                        <h1>目前还没有待发年报。</h1>
                    </div>
                </div>
            </s:if>
        </s:iterator>

    </div>
</div>
</body>
</html>
