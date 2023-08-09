<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file ="../view/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
    <div class="board-button">
        <a href="/board/post">
            <button type="button" class="btn btn-primary">게시글 쓰기</button>
        </a>
    </div>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">수정일자</th>
            <th scope="col">조회수</th>
            <th scope="col">댓글</th>
        </tr>
        </thead>
        <tbody>

        <%--  반복문을 통한 board 내용       --%>
        <c:forEach items="${boardList.content}" var="board" varStatus="status"> <%-- jsp에서 content 중요--%>
            <tr>
                <td>${board.id}</td>
                <td onClick="location.href='boardDtl.jsp'"><a href="/board/view/${board.id}">${board.title}</a></td>
                <td>${board.writer}</td>
                <td>${board.modifiedDate}</td>
                <td>${board.hit}</td>
                <td>${fn:length(board.commentList)}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="text-xs-center">
        <ul class="pagination">

            <%--  이전 --%>
            <c:choose>
                <c:when test="${boardList.first}"></c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/?page=0&searchTitle=${param.searchTitle}">처음</a></li>
                    <li class="page-item"><a class="page-link" href="/?page=${boardList.number-1}&searchTitle=${param.searchTitle}">&larr;</a></li>
                </c:otherwise>
            </c:choose>

            <%-- 페이징 --%>
            <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                <c:choose>
                    <c:when test="${boardList.pageable.pageNumber+1 == i}">
                        <li class="page-item disabled"><a class="page-link" href="/?page=${i-1}&searchTitle=${param.searchTitle}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="/?page=${i-1}&searchTitle=${param.searchTitle}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <%--다음 --%>
            <c:choose>
                <c:when test="${boardList.last}"></c:when>
                <c:otherwise>
                    <li class="page-item "><a class="page-link" href="?page=${boardList.number+1}&searchTitle=${param.searchTitle}">&rarr;</a></li>
                    <li class="page-item "><a class="page-link" href="?page=${boardList.totalPages-1}&searchTitle=${param.searchTitle}">마지막</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>

</div>
<%@include file ="../view/footer.jsp" %>