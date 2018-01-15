<%@ page language="java" pageEncoding="UTF-8"%>
<pg:pager url="${ctx}/${pager.url}" items="${pager.count}"
    maxPageItems="${pager.pageSize}" maxIndexPages="8"
    export="pageOffset,currentPageNumber=pageNumber">
    <ul class="paginate">
        <pg:first>
            <c:if test="${pager.count > pager.pageSize}">
                <li class="first_page"><a
                    href="${pageUrl}&pageSize=${pager.pageSize}&pageOffset=${pageNumber}${pager.queryString}"
                    class="prev_page">首页</a>
                </li>
            </c:if>
            <c:if
                test="${pager.count < pager.pageSize and pager.count > 0}">
                <li class="first_page">
                    <a href="javaScript:void(0)">首页</a>
                </li>
            </c:if>
        </pg:first>
        <pg:prev ifnull="true">
            <c:choose>
                <c:when test="${pageUrl != null}">
                    <li class="prev">
                        <a href="${pageUrl}&pageSize=${pager.pageSize}&pageOffset=${pageNumber}${pager.queryString}">上一页</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="prev">
                        <a href="javaScript:void(0)">上一页</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </pg:prev>
        <pg:pages>
            <c:choose>
                <c:when test="${pageNumber eq pager.pageOffset}">
                    <li class="active">${pageNumber}</font></li>
                </c:when>
                <c:otherwise>
                    <li><a
                        href="${pageUrl}&pageSize=${pager.pageSize}&pageOffset=${pageNumber}${pager.queryString}"
                        class="current">${pageNumber}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </pg:pages>
        <pg:next ifnull="true">
            <c:choose>
                <c:when test="${pageUrl != null}">
                    <li class="next">
                        <a href="${pageUrl}&pageSize=${pager.pageSize}&pageOffset=${pageNumber}${pager.queryString}">下一页</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="prev">
                        <a href="javaScript:void(0)">下一页</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </pg:next>
        <pg:last>
            <c:if test="${pager.count > pager.pageSize}">
                <li class="last_page"><a
                    href="${pageUrl}&pageSize=${pager.pageSize}&pageOffset=${pageNumber}${pager.queryString}"
                    class="next_page">尾页</a>
                </li>
            </c:if>
            <c:if
                test="${pager.count < pager.pageSize and pager.count > 0}">
                <li class="last_page">
                    <a href="javaScript:void(0)">尾页</a>
                </li>
            </c:if>
        </pg:last>
        <li style="width: 8em; border: 0 none; padding:0px 4px 0px 4px;">共<b>&nbsp;${pager.count}&nbsp;</b>条数据</li>
    </ul>
</pg:pager>
<script type="text/javascript">
$("#ps").change(
    function() {
        var uri = '${ctx}${pager.url}?pageSize=' + $(this).val() + '${pager.queryString}';
        document.location.href = uri;
    });
</script>
