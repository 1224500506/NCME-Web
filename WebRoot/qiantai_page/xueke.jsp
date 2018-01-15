<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<li class="so_many item_list">
    <span class="all xueke_quanbu <c:if test = "${xueke eq null || xueke eq '' || xueke eq '全部'}">active</c:if>">全部</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '全科医学'}">active</c:if>">全科医学</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '妇产科学' || xueke eq '妇科学'}">active</c:if>">妇产科学</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '儿科学' || xueke eq '儿科内科学' || xueke eq '儿科外科学' || xueke eq '新生儿科学' || xueke eq '儿科学其他学科'}">active</c:if>">儿科学</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '精神卫生学'}">active</c:if>">精神卫生学</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '康复医学'}">active</c:if>">康复医学</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '医学教育与卫生管理' || xueke eq '医学教育'}">active</c:if>">医学教育与卫生管理</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '传染病学'}">active</c:if>">传染病学</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '骨外科学'}">active</c:if>">骨外科学</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '泌尿外科学'}">active</c:if>">泌尿外科学</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '外科学其他学科'}">active</c:if>">外科学其他学科</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '耳鼻喉科'}">active</c:if>">耳鼻喉科</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '肾脏病学'}">active</c:if>">肾脏病学</span>
    <span class="all xueke_quanbu <c:if test = "${xueke eq '心血管病学'}">active</c:if>">心血管病学</span>
</li>