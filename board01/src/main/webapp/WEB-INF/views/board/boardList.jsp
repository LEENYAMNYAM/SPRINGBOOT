<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../includes/header.jsp"%>
<div class="row-content">
    <div class="card">
        <div class="card-body">
            <h2 class="card-title"> 게시판 목록보기 </h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">BNO</th>
                    <th scope="col">TITLE</th>
                    <th scope="col">AUTHOR</th>
                    <th scope="col">POSTDATE</th>
                    <th scope="col">ReadCount</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="board" items="${boardlist}">
                        <tr>
                            <th scope="row"> ${board.bno}</th>
                            <td><a href="/board/getBoard?bno=${board.bno}"> ${board.title} </a></td>
                            <td> ${board.author}</td>
                            <td> <fmt:formatDate value="${board.postdate}"/></td>
                            <td> ${board.readcount}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>