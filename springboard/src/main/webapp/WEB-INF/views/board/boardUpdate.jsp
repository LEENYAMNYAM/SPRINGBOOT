<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file ="../includes/header.jsp" %>

<div class="container mt-5">
	<h2>Board Update</h2>
	 <form action="boardUpdate" method="post" name="frm" onsubmit="return validateForm(this)">
	    <input type="hidden" name="num" value="${board.num }" >
	     <div class="mb-3 mt-3">
	      <label for="userid">Userid:</label>
	      <input type="text" class="form-control" id="userid" placeholder="Enter userid" 
	      name="userid" value="${board.userid}" readonly="readonly">
	    </div>
	    
	   <div class="mb-3 mt-3">
	      <label for="subject">Subject:</label>
	      <input type="text" class="form-control" id="subject" placeholder="Enter subject" 
	      name="subject" value="${board.subject }">
	    </div>

	    <div class="mb-3">
	     <label for="content">content:</label>
			  <textarea class="form-control" id="content" name="content" 
			   placeholder="Content goes here" rows=5>${board.content }</textarea>
	   </div>   
	    
     <div  class="mt-3">
    	<button type="submit" class="btn btn-primary" id="btnSend">수정</button>
    </div>
  </form>
</div>

</body>
</html>