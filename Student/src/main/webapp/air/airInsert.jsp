<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script type="text/javascript">

	function rand()
	{
		
	}

</script>

<%-- <meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script src="${pageContext.request.contextPath}/js/script.js"></script> --%>

</head>
<body>	
	<div class="container">
		<h2>예약</h2>
		<p>예약하는 사이트입니다.</p>

		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="airInsert">
			<div id="flyid" class="input-group">
				<span class="input-group-text col-md-2">예약번호</span> 
				<input id="fakeregid" name="fakeregid" type="number" 
				class="form-control" disabled="disabled" placeholder="12345">
				<input type="hidden" id="regid" name="regid" value="12345">		
			</div>
			
			
			<div id="flyid" class="input-group">
				<span class="input-group-text col-md-2">항공편 번호</span>
				<input id="fakeflid" name="fakeflid" type="number" class="form-control"	
				placeholder="${requestScope.flids}" disabled>
				<input type="hidden" id="flid" name="flid" value="${requestScope.flids}">
			</div>
			
			<div class="input-group">
					<span class="input-group-text">좌석</span>
					<select class="form-select form-select-lg" id="seat" name="seat">
						  <option value="-">--좌석선택--</option>
						  <option value="first">프레스티지</option>
						  <option value="business">비즈니스</option>
						  <option value="economi">이코노미</option>
					</select>
				</div>
				
			<div class="input-group">
				<span class="input-group-text col-md-2">경유지</span> <input
					id="fstopover" name="fstopover" type="text" class="form-control"
				 placeholder="없음" disabled>
				 <input id="stopover" name="stopover" type="hidden" value="없음">
			</div>


			<div class="input-group">
				<span class="input-group-text col-md-2">인원수</span> <input
					id="passengers" name="passengers" type="number" class="form-control"
					>
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-2">가격</span> <input
					id="fprice" name="fprice" type="number" class="form-control"
					placeholder="150000" disabled>
					<input id="price" name="price" value="150000" type="hidden">
			</div>
			
			
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary btn-lg">예약</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>