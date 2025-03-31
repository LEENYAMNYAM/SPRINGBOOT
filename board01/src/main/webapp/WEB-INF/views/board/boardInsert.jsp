<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<div class="row-content">
    <div class="card">
        <div class="card-body">
            <h2 class="card-title"> 게시글 등록 </h2>
            <form method="post" action="/board/insert">
                <div class="mb-3">
                    <label for="title" class="form-label">TITLE</label>
                    <input type="text" class="form-control" name="title" id="title" placeholder="input title">
                </div>
                <div class="mb-3">
                    <label for="author" class="form-label">AUTHOR</label>
                    <input type="text" class="form-control" name="author" id="author" placeholder="input author">
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">CONTENT</label>
                    <textarea class="form-control" name="content" id="content" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-outline-primary">REGISTER</button>
                <button type="reset" class="btn btn-outline-secondary">RESET</button>
            </form>
        </div>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>