<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul>
    <c:set var="aaa" value=""/>

    <c:forEach items="${CURRENT_USER.roles}" var="role">
        <c:forEach items="${role.modules}" var="m">
            <c:if test="${m.ctype ==2}">
                <c:if test="${m.remark==symbol}">
                    <c:if test="${fn:contains(aaa,m.cpermission) eq false}">
                        <c:set var="aaa" value="${aaa},${m.cpermission}"/>
                        <li id="${m.ico }"><a href="#" onclick="${m.curl}">${m.cpermission }</a></li>
                    </c:if>
                </c:if>
            </c:if>
        </c:forEach>
    </c:forEach>

    <%--<c:set var="aaa" value=""></c:set>--%>

    <%--<c:forEach items="${_CURRENT_USER.roles }" var="role">--%>
        <%--<!-- 遍历模块 -->--%>
        <%--<c:forEach items="${role.modules }" var="m">--%>
            <%--<!-- 判断是否是三级菜单 -->--%>
            <%--<c:if test="${m.ctype==2 }">--%>
                <%--<!-- 判断是否是左侧菜单的三级菜单 -->--%>
                <%--<c:if test="${m.remark==symbol }">--%>
                    <%--<!-- 判断是否已经显示 -->--%>
                    <%--<c:if test="${fn:contains(aaa,m.cpermission)  eq false}">--%>
                        <%--<li id="${m.ico }"><a href="#" onclick="${m.curl}">${m.cpermission }</a></li>--%>
                    <%--</c:if>--%>
                <%--</c:if>--%>
            <%--</c:if>--%>
        <%--</c:forEach>--%>
    <%--</c:forEach>--%>
</ul>