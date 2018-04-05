<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="se" uri="/struts-tags" %>
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
    <script type="text/javascript">
        function toReply(hb_id) {
            var b = "reply?hb.ty=1&hb.id=" + hb_id;
            alert("这是一个不成熟的小功能");
            document.form1.action = b;
            document.form1.submit();
        }
    </script>
</head>

<body>
<div id="bott1_1">
    <div id="bott2">
        <h2>未读月报</h2>
    </div>
    <div id="bott4" style="margin-left: 80px;">
        <s:if test="hbs.size()!=0">
        <form action="" method="post"name="form1">
            <table id="subtree1" width="900px;" style="margin-left: auto;margin-right: auto;">
                <tr align="center">
                    <td id="td" bgcolor="#63ebfb" width="8%">序号</td>
                    <td id="td" bgcolor="#63ebfb" width="10%">类型</td>
                    <td id="td" bgcolor="#63ebfb" width="10%">周期</td>
                    <td id="td" bgcolor="#63ebfb" width="20%">日期</td>
                    <td id="td" bgcolor="#63ebfb" width="20%">部门</td>
                    <td id="td" bgcolor="#63ebfb" width="15%">发件人</td>
                    <td id="td" bgcolor="#63ebfb" width="17%" style="border-right: thin dashed gray;">操作</td>
                </tr>
                <s:iterator value="hbs" status="status" var="info">
                    <tr align="center">
                        <td id="td">
                            <s:property value="#status.count"/>
                        </td>

                        <s:if test="#info.ty==1">
                            <td id="td">
                                月报
                            </td>
                        </s:if>
                        <s:if test="#info.ty==1">
                            <td id="td">
                                <s:property value="#info.zyys"/>月
                            </td>
                        </s:if>
                        <td id="td">
                            <s:property value="#info.rq"/>
                        </td>
                        <td id="td">
                            <s:property value="#info.bm_name"/>
                        </td>
                        <td id="td" name="showuserid">
                            <s:property value="#info.csren"/>
                        </td>
                        <td id="td" style="border-right: thin dashed gray;" align="center">
                            <!-- 允许管理员在任何周期批复当前周期之前的汇报。 -->
                                <%--<a href="reply?hb.ty=1">--%>
                                <%--<input type="button" class="inpu1" value='批复'/>--%>
                                <%--</a>--%>
                            <a >
                                <input type="button" class="inpu1" onclick="toReply('$(#info.id)')" value="批复"/>
                            </a>
                        </td>
                    </tr>
                </s:iterator>
                <tr>
                    <td style="border-bottom: thin dashed gray;"></td>
                    <td style="border-bottom: thin dashed gray;"></td>
                    <td style="border-bottom: thin dashed gray;"></td>
                    <td style="border-bottom: thin dashed gray;"></td>
                    <td style="border-bottom: thin dashed gray;"></td>
                    <td style="border-bottom: thin dashed gray;"></td>
                    <td style="border-bottom: thin dashed gray;"></td>
                </tr>
            </table>
        </form>
    </div>
    </s:if>
    <s:if test="hbs.size()==0">
        <div id="bott7">
            <div id="bott10">
                <h1>您没有需要批阅的汇报</h1>
            </div>
        </div>
    </s:if>
</div>
</body>
</html>
