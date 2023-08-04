<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file ="../view/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    </table>

    <div class="board-button">
        <a href="/update.jsp" href="/post/${board.id}">
            <button type="button" class="btn btn-secondary">게시글 수정</button>
        </a>
    </div>
</div>
<%@include file ="../view/footer.jsp" %>