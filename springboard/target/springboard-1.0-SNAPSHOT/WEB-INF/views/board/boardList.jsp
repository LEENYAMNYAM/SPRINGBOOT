<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
 <%@ include file ="../includes/header.jsp" %>
<div class="container mt-5">
	<h2>Board LIST(${count })</h2>
	
	<table class="table table-hover">
		  <thead>
		        <tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
		      	</tr>
		  </thead>
		  <tbody>
		  <c:forEach items="${barr }" var="board"  varStatus="st">
		  		<tr>
		  			<td>${board.num } </td>
		  			<td><a href="boardView?num=${board.num }">${board.subject }</a></td>
		  			<td>${board.userid }</td>
		  			<td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/></td>
		  		</tr>
		</c:forEach>
  </tbody>
  </table>
	  <form class="d-inline-flex" action="boardList">
		<select class="form-select" id="searchField" name ="searchField">
				<option value="subject">제목</option>
				 <option value="content">내용</option>
		</select>
		<input type="text" class="form-control" id="searchWord" name="searchWord">
		<button type="submit" class="btn btn-success btn-sm">Search</button>
	 </form>
</div>
</body>
</html>