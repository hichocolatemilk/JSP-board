<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file ="../view/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div>
    <table  class="table table-bordered">

        <tr>
            <th class="trth" >게시글 번호 #</th>
            <td class="trtd" >${board.id}</td>
            <th class="trth">제목</th>
            <td class="trtd" >${board.title}</td>
        </tr>
        <tr>
            <th class="trth">작성자</th>
            <td class="trtd" >${board.writer}</td>
            <th class="trth">조회수</th>
            <td class="trtd" >${board.hit}</td>
        </tr>
        <tr>
            <th class="trth">내용</th>
            <td  colspan='3' class="tdContent">${board.content}</td>
        </tr>

    </table>
    <div class="commentCount">
        <i class="bi bi-chat-dots" style="font-size: 2rem"></i>
        <span>댓글</span>
        <%--        <c:if test="${fn:length(board.commentList) <= 0}">--%>
        <%--        <span>0</span>--%>
        <%--        </c:if>--%>
        <%--        <c:if test="${fn:length(board.commentList) > 0}">--%>
        <span>${fn:length(board.commentList)}</span>
        <%--        </c:if>--%>

    </div>
    <div class="comment">
        <h2 class="commentHeader"> 댓글 </h2>
        <div>
            <c:forEach items="${commentList}" var="comment" varStatus="status">
                <span>${comment.comment}</span>
                <span>${comment.commentWriter}</span>
                <span>${comment.date}</span>
            </c:forEach>
        </div>
    </div>

    <div class="board-button">
        <a href="/post/view/${board.id}">
            <button onClick="location.href='update.jsp'" type="button" class="btn btn-secondary">게시글 수정</button>
        </a>
        <a href="/board/post/${board.id}/comment">
            <button onclick="location.href='comment.jsp'" type="button" class="btn btn-info">댓글쓰기</button>
        </a>
    </div>


</div>
<%@include file ="../view/footer.jsp" %>