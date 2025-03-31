<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<div class="row-content">
    <div class="card">
        <div class="card-body">
            <h2 class="card-title"> 게시글 등록 </h2>
            <form method="post" action="/board/insert">
                <div class="mb-3">
                    <label for="bno" class="form-label">BOARD NUMBER</label>
                    <input type="text" class="form-control" name="bno" id="bno" value="${board.bno}">
                </div>
                <div class="mb-3">
                    <label for="title" class="form-label">TITLE</label>
                    <input type="text" class="form-control" name="title" id="title" value="${board.title}">
                </div>
                <div class="mb-3">
                    <label for="author" class="form-label">AUTHOR</label>
                    <input type="text" class="form-control" name="author" id="author" value="${board.author}">
                </div>
                <div class="mb-3">
                    <label for="postdate" class="form-label">POSTDATE</label>
                    <input type="text" class="form-control" name="postdate" id="postdate" value="${board.postdate}">
                </div>
                <div class="mb-3">
                    <label for="readcount" class="form-label">READCOUNT</label>
                    <input type="text" class="form-control" name="readcount" id="readcount" value="${board.readcount}">
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">CONTENT</label>
                    <textarea class="form-control" name="content" id="content" rows="3">${board.content}</textarea>
                </div>
                <button type="button" class="btn btn-outline-primary">MODIFY</button>
                <button type="button" class="btn btn-outline-secondary">BOARDLIST</button>
            </form>
        </div>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>