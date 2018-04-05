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

    <title>My JSP 'xinjian.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!-- 设置当前页面允许使用中文 -->
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/list.css">
    <script type="text/javascript" src="./js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="./js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        function f1() {
            WdatePicker({minDate: '%y-%M-{%d}', maxDate: '2050-10-01'});
        }
    </script>
</head>

<body>
<div id="bott1_1">
    <div id="bott2">
        <h2>员工月报</h2>
    </div>
    <div id="bott3">
        <table width="1100px;">
            <tr>
                <td width="140px;"></td>
                <td width="70px;">当前状态:</td>
                <td width="400px;">&nbsp;&nbsp;批阅</td>
                <td width="90px;">发件人:</td>
                <td width="400px;">${hb.userName}</td>
            </tr>
        </table>
    </div>
    <div id="bott4">
        <table id="subtree1" width="1100px;">
            <tr>
                <td id="td" width="16%" bgcolor="#63ebfb">公司</td>
                <td id="td" width="35%">&nbsp;&nbsp;芊芊股份有限公司</td>
                <td id="td" width="4%" bgcolor="#63ebfb">部门</td>
                <td id="td" width="45%" style="border-right: thin dashed gray;">&nbsp;&nbsp;${hb.bmName}</td>
            </tr>
            <tr>
                <td id="td" bgcolor="#63ebfb">发件人</td>
                <td id="td">&nbsp;&nbsp;${hb.userName}</td>
                <td id="td" bgcolor="#63ebfb">日期</td>
                <td id="td" style="border-right: thin dashed gray;">&nbsp;&nbsp;${hb.rq}</td>
            </tr>
            <tr>
                <td id="td" bgcolor="#63ebfb">汇报上级</td>
                <td id="td">&nbsp;&nbsp;${hb.m_name}</td>
                <td id="td" bgcolor="#63ebfb">月份</td>
                <td id="td" style="border-right: thin dashed gray;">&nbsp;&nbsp;第${hb.zyys}月</td>
            </tr>
            <tr>
                <td id="td" bgcolor="#63ebfb">上月工作重点</td>
                <td id="td" style="border-right: thin dashed gray;" colspan="3">
                    <table id="td_table">
                        <tbody id="n">
                        <tr align="center">
                            <td id="td" width="6%" bgcolor="#63ebfb">序号</td>
                            <td id="td" width="74%" bgcolor="#63ebfb">承担的任务</td>
                            <td id="td" width="20%" bgcolor="#63ebfb" style="border-right: thin dashed gray;">预计完成日期
                            </td>
                        </tr>
                        <s:iterator value="pts" status="status" var="info">
                            <tr align="center">
                                <td id="td">
                                    <s:property value="#status.count"/>
                                </td>
                                <td id="td">
                                    <!-- textarea得到Action中的参数格式固定，只能这么写，不然前面会有很多空格。 -->
                                    <textarea name="precdt" disabled="disabled" style="resize:none; margin-top: 5px;"
                                              rows="1" cols="67"><s:property value="#info.cdtask"/></textarea>
                                </td>
                                <td id="td" style="border-right: thin dashed gray;">
                                    <input name="prerq1" id="d4311" disabled="disabled"
                                           value="<s:property value="#info.wcrq"/>" class="Wdate" type="text"
                                           onFocus="f1();"
                                           style="height: 33px; width:175px; margin-top: 4px; font-size: 21px; "/>
                                    <div style="display: none;">
                                        <input name="returnDate" disabled="disabled" id="d4312" class="Wdate"
                                               type="text" onFocus="f2();"
                                               style="height: 33px; width:175px; margin-top: 23px; font-size: 21px;"/>
                                    </div>
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                        <tr>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td id="td" bgcolor="#63ebfb">本月主要工作总结</td>
                <td id="td" style="border-right: thin dashed gray;" colspan="3">
                    <table id="td_table">
                        <tbody id="n1">
                        <tr align="center">
                            <td id="td" width="4%" bgcolor="#63ebfb">序号</td>
                            <td id="td" width="56%" bgcolor="#63ebfb">承担的任务</td>
                            <td id="td" width="20%" bgcolor="#63ebfb">任务下达日期</td>
                            <td id="td" width="20%" bgcolor="#63ebfb" style="border-right: thin dashed gray;">计划完成日期
                            </td>
                        </tr>
                        <!-- s标签if判断取值不需要再特地使用<s:property value="#status.count"/>，直接#要取的值即可。 -->
                        <s:if test="#nowtsSize==0">
                            <tr align="center">
                                <td id="td">
                                    1
                                </td>
                                <td id="td">
                                    <textarea name="nowTask" style="resize:none; margin-top: 5px;" rows="1" cols="43"
                                              disabled="disabled"></textarea>
                                </td>
                                <td id="td">
                                    <input name="nowxDate" id="d4311" class="Wdate" type="text" onFocus="f1();"
                                           disabled="disabled"
                                           style="height: 33px; width:175px; margin-top: 4px; font-size: 21px; "/>
                                </td>
                                <td id="td" style="border-right: thin dashed gray;">
                                    <input name="nowwDate" id="d4312" class="Wdate" type="text" onFocus="f1();"
                                           disabled="disabled"
                                           style="height: 33px; width:175px; margin-top: 4px; font-size: 21px;"/>
                                </td>
                            </tr>
                        </s:if>
                        <s:elseif test="#nowtsSize!=0">
                            <s:iterator value="nowts" status="status" var="info">
                                <s:if test="#status.count==1">
                                    <tr align="center">
                                        <td id="td">
                                            <s:property value="#status.count"/>
                                        </td>
                                        <td id="td">
                                            <textarea name="nowTask" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="43" disabled="disabled"><s:property
                                                    value="#info.cdtask"/></textarea>
                                        </td>
                                        <td id="td">
                                            <input name="nowxDate" id="d4311" class="Wdate" type="text" onFocus="f1();"
                                                   disabled="disabled"
                                                   style="height: 33px; width:175px; margin-top: 4px; font-size: 21px;"
                                                   value="<s:property value='#info.xdrq'/>"/>
                                        </td>
                                        <td id="td" style="border-right: thin dashed gray;">
                                            <input name="nowwDate" id="d4312" class="Wdate" type="text" onFocus="f1();"
                                                   disabled="disabled"
                                                   style="height: 33px; width:175px; margin-top: 4px; font-size: 21px;"
                                                   value="<s:property value='#info.wcrq'/>"/>
                                        </td>
                                    </tr>
                                </s:if>
                                <s:elseif test="#status.count!=1">
                                    <tr align="center">
                                        <td id="td"><s:property value="#status.count"/></td>
                                        <td id="td">
                                            <textarea name="nowTask" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="43" disabled="disabled"></textarea>
                                        </td>
                                        <td id="td">
                                            <input name="nowxDate" id="d4311" class="Wdate" type="text" onFocus="f1();"
                                                   disabled="disabled"
                                                   style="height: 33px; width:175px; margin-top: 4px; font-size: 21px; "/>
                                        </td>
                                        <td id="td" style="border-right: thin dashed gray;">
                                            <input name="nowwDate" id="d4312" class="Wdate" type="text" onFocus="f1();"
                                                   disabled="disabled"
                                                   style="height: 33px; width:175px; margin-top: 4px; font-size: 21px;"/>
                                        </td>
                                    </tr>
                                </s:elseif>
                            </s:iterator>
                        </s:elseif>
                        </tbody>
                        <tr>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td id="td" bgcolor="#63ebfb">下月计划工作重点</td>
                <td id="td" style="border-right: thin dashed gray;" colspan="3">
                    <table id="td_table">
                        <tbody id="n2">
                        <tr align="center">
                            <td id="td" width="6%" bgcolor="#63ebfb">序号</td>
                            <td id="td" width="46%" bgcolor="#63ebfb">承担的任务</td>
                            <td id="td" width="17%" bgcolor="#63ebfb">预计完成日期</td>
                            <td id="td" width="8%" bgcolor="#63ebfb">责任人</td>
                            <td id="td" width="8%" bgcolor="#63ebfb">协助部门</td>
                            <td id="td" width="15%" bgcolor="#63ebfb" style="border-right: thin dashed gray;">备注</td>
                        </tr>
                        <!-- s标签if判断取值不需要再特地使用<s:property value="#status.count"/>，直接#要取的值即可。 -->
                        <s:if test="#nexttsSize==0">
                            <tr align="center">
                                <td id="td">1</td>
                                <td id="td">
                                    <textarea name="nextTask" style="resize:none; margin-top: 5px;" rows="1" cols="33"
                                              disabled="disabled"></textarea>
                                </td>
                                <td id="td">
                                    <input name="nextWCDate" id="d4311" class="Wdate" type="text" onFocus="f1();"
                                           disabled="disabled"
                                           style="height: 33px; width:140px; margin-top: 4px; font-size: 21px; "/>
                                </td>
                                <td id="td">
                                    <select name="nextzrren" disabled="disabled">
                                        <s:iterator value="users" status="status" var="info">
                                            <option value="<s:property value="#info.name"/>">
                                                <s:property value="#info.name"/>
                                            </option>
                                        </s:iterator>
                                    </select>
                                </td>
                                <td id="td">
                                    <select name="nextczbm" disabled="disabled">
                                        <s:iterator value="bms" status="status" var="info">
                                            <option value="<s:property value="#info.name"/>">
                                                <s:property value="#info.name"/>
                                            </option>
                                        </s:iterator>
                                    </select>
                                </td>
                                <td id="td" style="border-right: thin dashed gray;">
                                    <textarea name="nextbz" style="resize:none; margin-top: 5px;" rows="1" cols="12"
                                              disabled="disabled"></textarea>
                                </td>
                            </tr>
                        </s:if>
                        <s:elseif test="#nexttsSize!=0">
                            <s:iterator value="nextts" status="status" var="info">
                                <s:if test="#status.count==1">
                                    <tr align="center">
                                        <td id="td">
                                            <s:property value="#status.count"/>
                                        </td>
                                        <td id="td">
                                            <textarea name="nextTask" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="33" disabled="disabled"><s:property
                                                    value="#info.cdtask"/></textarea>
                                        </td>
                                        <td id="td">
                                            <input name="nextWCDate" id="d4311" class="Wdate" type="text"
                                                   onFocus="f1();" disabled="disabled"
                                                   style="height: 33px; width:140px; margin-top: 4px; font-size: 21px; "
                                                   value="<s:property value="#info.rq"/>"/>
                                        </td>
                                        <td id="td">
                                            <select name="nextzrren" disabled="disabled">
                                                <s:iterator value="users" status="status" var="info">
                                                    <option value="<s:property value="#info.name"/>">
                                                        <s:property value="#info.name"/>
                                                    </option>
                                                </s:iterator>
                                            </select>
                                        </td>
                                        <td id="td">
                                            <select name="nextczbm" disabled="disabled">
                                                <s:iterator value="bumens" status="status" var="info">
                                                    <option value="<s:property value="#info.name"/>">
                                                        <s:property value="#info.name"/>
                                                    </option>
                                                </s:iterator>
                                            </select>
                                        </td>
                                        <td id="td" style="border-right: thin dashed gray;">
                                            <textarea name="nextbz" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="12" disabled="disabled"><s:property
                                                    value="#info.bz"/></textarea>
                                        </td>
                                    </tr>
                                </s:if>
                                <s:elseif test="#status.count!=1">
                                    <tr align="center">
                                        <td id="td">
                                            <s:property value="#status.count"/>
                                        </td>
                                        <td id="td">
                                            <textarea name="nextTask" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="33" disabled="disabled"><s:property
                                                    value="#info.cdtask"/></textarea>
                                        </td>
                                        <td id="td">
                                            <input name="nextWCDate" id="d4311" class="Wdate" type="text"
                                                   onFocus="f1();" disabled="disabled"
                                                   style="height: 33px; width:140px; margin-top: 4px; font-size: 21px; "
                                                   value="<s:property value="#info.rq"/>"/>
                                        </td>
                                        <td id="td">
                                            <select name="nextzrren" disabled="disabled">
                                                <s:iterator value="users" status="status" var="info">
                                                    <option value="<s:property value="#info.name"/>">
                                                        <s:property value="#info.name"/>
                                                    </option>
                                                </s:iterator>
                                            </select>
                                        </td>
                                        <td id="td">
                                            <select name="nextczbm" disabled="disabled">
                                                <s:iterator value="bumens" status="status" var="info">
                                                    <option value="<s:property value="#info.name"/>">
                                                        <s:property value="#info.name"/>
                                                    </option>
                                                </s:iterator>
                                            </select>
                                        </td>
                                        <td id="td" style="border-right: thin dashed gray;">
                                            <textarea name="nextbz" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="12" disabled="disabled"><s:property
                                                    value="#info.bz"/></textarea>
                                        </td>
                                    </tr>
                                </s:elseif>
                            </s:iterator>
                        </s:elseif>
                        </tbody>
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
                </td>
            </tr>
            <tr>
                <td id="td" style="border-bottom: thin dashed gray;" bgcolor="#63ebfb">存在的问题及合理化建议</td>
                <td id="td" style="border-bottom: thin dashed gray;border-right: thin dashed gray;" colspan="3">
                    <table id="td_table">
                        <tbody id="n3">
                        <tr align="center">
                            <td id="td" width="6%" bgcolor="#63ebfb">序号</td>
                            <td id="td" width="36%" bgcolor="#63ebfb">承担的任务</td>
                            <td id="td" width="29%" bgcolor="#63ebfb">主要问题</td>
                            <td id="td" width="29%" bgcolor="#63ebfb" style="border-right: thin dashed gray;">对策及建议</td>
                        </tr>
                        <s:if test="#wtsSize==0">
                            <tr align="center">
                                <td id="td">1</td>
                                <td id="td">
                                    <textarea name="wtrenwu" style="resize:none; margin-top: 5px;" rows="1" cols="25"
                                              disabled="disabled"></textarea>
                                </td>
                                <td id="td">
                                    <textarea name="wtwt" style="resize:none; margin-top: 5px;" rows="1" cols="25"
                                              disabled="disabled"></textarea>
                                </td>
                                <td id="td" style="border-right: thin dashed gray;">
                                    <textarea name="wtdc" style="resize:none; margin-top: 5px;" rows="1" cols="25"
                                              disabled="disabled"></textarea>
                                </td>
                            </tr>
                        </s:if>
                        <s:elseif test="#wtsSize!=0">
                            <s:iterator value="wts" status="status" var="info">
                                <s:if test="#status.count==1">
                                    <tr align="center">
                                        <td id="td">
                                            <s:property value="#status.count"/>
                                        </td>
                                        <td id="td">
                                            <textarea name="wtrenwu" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="25" disabled="disabled"><s:property
                                                    value="#info.cdtask"/></textarea>
                                        </td>
                                        <td id="td">
                                            <textarea name="wtwt" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="25" disabled="disabled"><s:property
                                                    value="#info.zywt"/></textarea>
                                        </td>
                                        <td id="td" style="border-right: thin dashed gray;">
                                            <textarea name="wtdc" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="25" disabled="disabled"><s:property
                                                    value="#info.dcjjy"/></textarea>
                                        </td>
                                    </tr>
                                </s:if>
                                <s:elseif test="#status.count!=1">
                                    <tr align="center">
                                        <td id="td">
                                            <s:property value="#status.count"/>
                                        </td>
                                        <td id="td">
                                            <textarea name="wtrenwu" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="25" disabled="disabled"><s:property
                                                    value="#info.cdtask"/></textarea>
                                        </td>
                                        <td id="td">
                                            <textarea name="wtwt" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="25" disabled="disabled"><s:property
                                                    value="#info.zywt"/></textarea>
                                        </td>
                                        <td id="td" style="border-right: thin dashed gray;">
                                            <textarea name="wtdc" style="resize:none; margin-top: 5px;" rows="1"
                                                      cols="25" disabled="disabled"><s:property
                                                    value="#info.dcjjy"/></textarea>
                                        </td>
                                    </tr>
                                </s:elseif>
                            </s:iterator>
                        </s:elseif>
                        </tbody>
                        <tr>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                            <td style="border-bottom: thin dashed gray;"></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <form action="sanw" method="post">
            <input type="hidden" name="hb.id" value="${hb.id}"/>
            <input type="hidden" name="hb.userName" value="${hb.userName}"/>
            <input type="hidden" name="hb.ty" value="${hb.ty}"/>
            <input type="hidden" name="hb.st" value="${hb.st}"/>
            <div style="width: 1100px;">
                <div style="width: 45px;margin: 0 auto;">
                    <h2>批文</h2>
                </div>
                <textarea rows="8" cols="112" name="pf.des"
                          style="resize:none;margin-left: 2px;margin-top: -18px;"></textarea>
            </div>
            <div id="bott6">
                <table>
                    <s:if test="#session.pow==1">
                        <tr>
                            <td>
                                <input class="a" type="button" value="返&nbsp;&nbsp;&nbsp;&nbsp;回"/>
                            </td>
                            <td>
                                <input class="a" type="button" value="确&nbsp;&nbsp;&nbsp;&nbsp;定"/>
                            </td>
                        </tr>
                    </s:if>
                    <s:if test="#session.pow==2">
                        <tr>
                            <td>
                                <input class="a" type="button" value="返&nbsp;&nbsp;&nbsp;&nbsp;回"/>
                            </td>
                            <td>
                                <input class="a" type="button" value="确&nbsp;&nbsp;&nbsp;&nbsp;定"/>
                            </td>
                        </tr>
                    </s:if>
                </table>
            </div>
        </form>
    </div>
</div>
</body>
</html>
