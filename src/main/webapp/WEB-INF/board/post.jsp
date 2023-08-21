<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file ="../view/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <h1 class="postTitle">게시글 등록</h1>
    <div class="col-md-12">
        <div class="col-md-3 ">
            <form method="post">
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요">
                </div>
                <div class="form-group">
                    <label for="writer"> 작성자 </label>
                    <input type="text" class="form-control" id="writer" placeholder="작성자를 입력하세요">
                </div>
                <div class="form-group">
                    <label for="content"> 내용 </label>
                    <textarea class="form-control" id="content" placeholder="내용을 입력하세요"></textarea>
                </div>
            </form>

            <button type="submit" class="btn btn-primary" id="btn-save"> 등록 </button>
            <a href="/" role="button" class="btn btn-secondary"> 취소 </a>
        </div>
    </div>
</div>
<%@include file ="../view/footer.jsp" %>