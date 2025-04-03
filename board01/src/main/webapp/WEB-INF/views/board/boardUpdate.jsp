<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<div class="row-content">
    <div class="card">
        <div class="card-body">
            <h2 class="card-title"> 게시글 수정 </h2>
            <form method="post" action="/board/boardUpdate">
                <input type="hidden" name="bno" value="${board.bno}">
                <div class="mb-3">
                    <label for="title" class="form-label">TITLE</label>
                    <input type="text" class="form-control" name="title" id="title" value="${board.title}">
                </div>
                <div class="mb-3">
                    <label for="author" class="form-label">AUTHOR</label>
                    <input type="text" class="form-control" name="author" id="author" value="${board.author}">
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">CONTENT</label>
                    <textarea class="form-control" name="content" id="content" rows="3">${board.content}</textarea>
                </div>
                <button type="submit" class="btn btn-outline-primary">UPDATE</button>
                <button type="button" class="btn btn-outline-secondary">VIEW</button>
<%--            </form>--%>
        </div>
        <script>
            /* 클래스 이름이 (btn-outline-primary)인 요소를 클릭할때 함수가 실행되는 이벤트를 주겠다.*/
            document.querySelector(".btn-outline-secondary").addEventListener("click", function(e){
                self.location=`/board/getView?bno=${board.bno}`})
        </script>

    </div>
</div>
<%@include file="../includes/footer.jsp"%>