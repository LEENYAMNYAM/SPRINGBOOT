<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ include file="../includes/header.jsp" %>
<div class="container mt-5">
	<h2>Board VIEW</h2>
	<input type="hidden" name="num" id="num" value="${board.num }">
	<table class="table table-hover">
		<tr>
			<th>글번호</th>
			<td>${board.num }</td>
			<th>조회수</th>
			<td>${board.readcount }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.userid }</td>
			<th>작성일</th>
			<td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd"/> </td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${board.subject }</td>
		</tr>
		<tr>
			<th>내용</th>
		<td colspan="3">${board.content }</td>
		</tr>
	</table>
		<button type="button" class="btn btn-primary"
		 onclick="location.href='boardUpdate?num=${board.num}'">수정</button>
		<button type="button" class="btn btn-secondary"
		 onclick="location.href='boardDelete?num=${board.num}'">삭제</button>
</div>

<div class = "container mt-3">
   <label for="msg">msg:</label>
  <textarea class="form-control" id="msg" name="msg" 
			   placeholder="Content goes here" rows=5></textarea>
	<button class="btn btn-success btn-sm mt-3" id="btnComment">Comment Write</button>

   <div class="mt-3">Comment(<span class="cntSpan"></span>)</div>
   <div id="result"></div>

</div>

</body>
</html>




