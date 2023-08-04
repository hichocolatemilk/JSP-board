<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file ="../view/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <div class="board-button">
        <a href="/post">
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
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${boardList}" var="board" varStatus="status">
            <tr>
                <td>${board.id}</td>
                <td onClick="location.href='boardDtl.jsp'"><a href="/post/view/${board.id}">${board.title}</a></td>
                <td>${board.writer}</td>
                <td>${board.modifiedDate}</td>
                <td>${board.hit}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
<%@include file ="../view/footer.jsp" %>