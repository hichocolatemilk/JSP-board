<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file ="../view/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <h1>파일 수정</h1>

    <form id="fileForm">
        <div>
            <input type="hidden" id="fileId" c:out value="${file.id}">
            <input type="hidden" id="boardId" c:out value="${board.id}">
        </div>
        <input type="file" id="file"/>
        <button type="button" class="btn btn-primary" id="btn-file-update">파일 수정</button>
        <button type="button" class="btn btn-danger" id="btn-file-delete">
            <i class="bi bi-x-square">삭제</i>
        </button>
        <a onClick="location.href='boardDtl.jsp'" href="/board/view/${board.id}" role="button" class="btn btn-secondary"> 취소 </a>
    </form>

</div>
<%@include file ="../view/footer.jsp" %>