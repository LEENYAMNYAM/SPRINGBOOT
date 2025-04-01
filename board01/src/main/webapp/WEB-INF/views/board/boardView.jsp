<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<div class="row-content">
    <div class="card">
        <div class="card-body">
            <h2 class="card-title"> 게시글 등록 </h2>
                <form method="post" action="/board/boardUpdate">
                <div class="mb-3">
                    <label for="bno" class="form-label">BOARD NUMBER</label>
                    <input type="text" class="form-control" name="bno" id="bno" value="${board.bno}" readonly="readonly">
                </div>
                <div class="mb-3">
                    <label for="title" class="form-label">TITLE</label>
                    <input type="text" class="form-control" name="title" id="title" value="${board.title}"  readonly="readonly">
                </div>
                <div class="mb-3">
                    <label for="author" class="form-label">AUTHOR</label>
                    <input type="text" class="form-control" name="author" id="author" value="${board.author}"  readonly="readonly">
                </div>
                <div class="mb-3">
                    <label for="postdate" class="form-label">POSTDATE</label>
                    <input type="text" class="form-control" name="postdate" id="postdate" value="${board.postdate}"  readonly="readonly">
                </div>
                <div class="mb-3">
                    <label for="readcount" class="form-label">READCOUNT</label>
                    <input type="text" class="form-control" name="readcount" id="readcount" value="${board.readcount}"  readonly="readonly">
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">CONTENT</label>
                    <textarea class="form-control" name="content" id="content" rows="3" readonly="readonly">${board.content}</textarea>
                </div>
                <button type="button" class="btn btn-outline-primary">UPDATE</button>
                <button type="button" class="btn btn-outline-danger">REMOVE</button>
                <button type="button" class="btn btn-outline-secondary">BOARDLSIT</button>
<%--            </form>--%>
        </div>
        <script>
            /* 클래스 이름이 (btn-outline-primary)인 요소를 클릭할때 함수가 실행되는 이벤트를 주겠다.*/
            document.querySelector(".btn-outline-primary").addEventListener("click", function(e){
                /* 백틱을 쓰면 문자열과 변수를 한번에 인식 가능*/
                self.location=`/board/boardUpdate?bno=${board.bno}`})
            document.querySelector(".btn-outline-danger").addEventListener("click", function(e){
                self.location=`/board/delete?bno=${board.bno}`})
            document.querySelector(".btn-outline-secondary").addEventListener("click", function(e){
                self.location=`/board/list`})
        </script>

    </div>
</div>
<%@include file="../includes/footer.jsp"%>