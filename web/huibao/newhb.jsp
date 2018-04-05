<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sharkj
  Date: 2017/11/22
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新建汇报</title>
    <link rel="stylesheet" type="text/css" href="../css/list.css">
</head>
<body>
<div id="bott1">
    <%--<s:if test="hb.ty==0">--%>
    <div id="bott2">
        <h1>员工周报</h1>
    </div>
    <%--</s:if>--%>
    <s:if test="hb.ty==1">
        <div id="bott2">
            <h1>员工月报</h1>
        </div>
    </s:if>
    <s:if test="hb.ty==2">
        <div id="bott2">
            <h1>员工年报</h1>
        </div>
    </s:if>
    <%--如果当前还未编辑汇报，那么就可以新建--%>
    <%--<s:if test="hb.st== 0">--%>
    <%--汇报的头，表示简单的当前汇报信息--%>
    <div id="bott3">
        <table width="1100px">
            <tr>
                <td width="140px;"></td>
                <td width="70px">当前状态:</td>
                <td width="400px">&nbsp;&nbsp;新建</td>
                <td width="90px">当前处理人:</td>
                <td width="400px">&nbsp${sessionScope.name}</td>
            </tr>
        </table>
    </div>

    <%--当前需要汇报的详细内容--%>
    <div id="bott4">
        <form action="" method="post" name="forml">
            <%--隐藏数据区域，表示这些数据需要提交，但不需要显示--%>
            <input type="hidden" name="hb.id" value="0">
            <input type="hidden" name="hb.bmName" value="${hb.bmName}">
            <input type="hidden" name="hb.rq" value="${hb.bm_rq}">
            <input type="hidden" name="hb.m_name" value="${hb.m_name}">
            <input type="hidden" name="hb.zyys" value="${hb.zyys}">
            <table id="subtree1" width="1100px">
                <tr>
                    <td class="td" width="16%" bgcolor="#fffacd">公司</td>
                    <td class="td" width="35%">&nbsp;&nbsp;晓庄</td>
                    <td class="td" width="4%" bgcolor="#fffacd">部门</td>
                    <td class="td" width="45%" style="border-right: thin dashed gray">&nbsp;&nbsp;${hb.bmName}</td>
                </tr>
                <tr>
                    <td class="td" bgcolor="#fffacd">汇报上级</td>
                    <td class="td">&nbsp;&nbsp;${hb.m_name}</td>
                    <td class="td" bgcolor="#fffacd">月份</td>
                    <td class="td" style="border-right: thin dashed">&nbsp;${hb.zyys}月</td>
                </tr>
                <tr>
                    <td class="td" width="16%" bgcolor="#fffacd">上月工作重点</td>
                    <td class="td" width="84%" style="border-right: thin dashed gray" colspan="3">
                        <table id="td_table">
                            <tbody id="n">
                            <tr align="center">
                                <td class="td" width="6%" bgcolor="#fffacd">序号</td>
                                <td class="td" width="74%" bgcolor="#fffacd">承担的任务</td>
                                <td class="td" width="20%" bgcolor="#fffacd" style="border-right: thin dashed gray">
                                    预计完成的时间
                                </td>
                            </tr>
                            <s:iterator value="pts" status="status" var="info">
                                <tr align="center">
                                    <td class="td">
                                        <s:property value="#status.count"/>
                                    </td>
                                    <td class="td">
                                          <textarea style="resize: none;margin-top: 5px;" disabled="disabled" rows="1"
                                                    cols="67" name="precdt"><s:property
                                                  value="#info.cdtask"/></textarea>
                                    </td>
                                    <td class="td" style="border-right: thin dashed gray">
                                            <%--时间拾取器 id成对出现 d4311和d4312--%>
                                        <input name="prerql" id="d4311" value="<s:property value="#info.wcrq"/> "
                                               class="Wdate" disabled="disabled" type="text" onfocus="f1()"
                                               style="height:33px; width:175px; margin-top: 4px;font-size: 21px;"/>
                                            <%--隐藏样式--%>
                                        <div style="display: none">
                                            <input name="prerql" id="d4312" class="Wdate" disabled="disabled"
                                                   type="text" onfocus="f1()"
                                                   style="height:33px; width:175px; margin-top: 4px;font-size: 21px;"/>
                                        </div>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                            <tr>
                                <td style="border-bottom: thin dashed gray"></td>
                                <td style="border-bottom: thin dashed gray"></td>
                                <td style="border-bottom: thin dashed gray"></td>
                                <td style="border-bottom: thin dashed gray"></td>
                            </tr>
                        </table>
                    </td>

                </tr>
            </table>
        </form>
    </div>
    <%--</s:if>--%>


    <%--&lt;%&ndash;当前汇报已发送&ndash;%&gt;--%>
    <%--<s:if test="hb.st==1">--%>
    <%--<s:if test="hb.ty==0">--%>
    <%--<div id="bott7">--%>
    <%--<div id="bott9">--%>
    <%--周报已发送--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</s:if>--%>
    <%--<s:if test="hb.ty==1">--%>
    <%--<div id="bott7">--%>
    <%--<div id="bott9">--%>
    <%--月报已发送--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</s:if>--%>
    <%--<s:if test="hb.ty==2">--%>
    <%--<div id="bott7">--%>
    <%--<div id="bott9">--%>
    <%--年报已发送--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</s:if>--%>
    <%--</s:if>--%>


    <%--&lt;%&ndash;当前汇报已经编辑存在草稿箱中&ndash;%&gt;--%>
    <%--<s:if test="hb.st==2">--%>
    <%--<s:if test="hb.ty==0">--%>
    <%--<div id="bott7">--%>
    <%--<div id="bott8">--%>
    <%--周报已保存--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</s:if>--%>
    <%--<s:if test="hb.ty==1">--%>
    <%--<div id="bott7">--%>
    <%--<div id="bott8">--%>
    <%--月报已保存--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</s:if>--%>
    <%--<s:if test="hb.ty==2">--%>
    <%--<div id="bott7">--%>
    <%--<div id="bott8">--%>
    <%--年报已保存--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</s:if>--%>
    <%--</s:if>--%>
</div>
</body>
</html>
