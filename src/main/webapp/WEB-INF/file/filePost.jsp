<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file ="../view/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <h1>파일 업로드</h1>

    <form id="fileForm">
        <div>
            <input type="hidden" id="boardId" c:out value="${board.id}">
        </div>
        <input type="file" id="file"/>
        <button type="button" id="btn-file">파일 등록</button>
    </form>

</div>
<%@include file ="../view/footer.jsp" %>