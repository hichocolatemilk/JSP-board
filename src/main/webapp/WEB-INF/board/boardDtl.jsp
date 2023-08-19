<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file ="../view/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div>
    <table  class="table table-bordered">

        <%-- board 내용        --%>
        <tr>
            <th class="trth" >게시글 번호 #</th>
            <td class="trtd"  >${board.id}</td>
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
        <tr>
            <th class="trth">파일 번호</th>
            <td class="trtd" >${file.id}</td>
            <th class="trth">파일명</th>
            <td class="trtd" >${file.fileName}</td>
        </tr>

    </table>

    <%--  댓글 수   --%>
    <div class="commentCount">
        <i class="bi bi-chat-dots" style="font-size: 2rem"></i>
        <span>전체 댓글 수</span>
        <%--        <c:if test="${fn:length(board.commentList) <= 0}">--%>
        <%--        <span>0</span>--%>
        <%--        </c:if>--%>
        <%--        <c:if test="${fn:length(board.commentList) > 0}">--%>
        <span>${fn:length(board.commentList)}</span>
        <%--        </c:if>--%>

    </div>

    <%--  댓글 작성   --%>
    <div class="comment">
        <h2 class="commentHeader"> 댓글 </h2>
        <form class="row">
            <div>
                <input type="hidden" id="id" c:out value="${board.id}">
            </div>
            <div class="input-group-sm mb-3">
                <input type="text" class="form-control" id="commentWriter" placeholder="작성자를 입력하세요">
            </div>
            <div class="commentGroup">
                <textarea class="form-control" id="comment" placeholder="댓글 입력하세요"></textarea>
                <button type="button" class="btn btn-primary" id="btn-comment-save"> 등록 </button>
            </div>
        </form>

        <%--  댓글    --%>
        <div>
            <c:forEach items="${commentList}" var="comment" varStatus="status">
                <div class="commentList">
                    <span>${comment.commentId}</span>
                    <span>${comment.comment}</span>
                    <span>(작성자)${comment.commentWriter}</span>
                    <span>${comment.date}</span>
                    <a onClick="location.href='commentUpdate.jsp'" href="/board/view/${board.id}/comment/${comment.commentId}">
                        <i class="bi bi-clipboard"></i>
                    </a>
                </div>
            </c:forEach>
        </div>

    </div>

    <div class="board-button">
        <a href="/post/view/${board.id}">
            <button onClick="location.href='update.jsp'" type="button" class="btn btn-secondary">게시글 수정</button>
        </a>
    </div>
</div>
<%@include file ="../view/footer.jsp" %>