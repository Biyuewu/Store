<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
</HEAD>
<body>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center"
		bgColor="#f5fafe" border="0">
		<TBODY>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单详情</strong>
				</td>
			</tr>
			<tr
				style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

				<td align="center" width="10%">序号</td>
				<td align="center" width="10%">商品名</td>
				<td align="center" width="50%">商品图片</td>
				<td align="center" width="10%">单价</td>
				<td align="center" width="10%">数量</td>
			</tr>

			<c:forEach items="${OrderItem}" var="o" varStatus="status">
				<tr onmouseover="this.style.backgroundColor = 'white'"
					onmouseout="this.style.backgroundColor = '#F5FAFE';">

					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="5%">${status.count}</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">${o.product.pname}</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
						<img width="40" height="45" src="${ pageContext.request.contextPath }/${o.product.pimage}" />
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="5%">${o.total}</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="5%">${o.quantity}</td>
				</tr>
			</c:forEach>
		<TBODY>
	</table>
</body>
</HTML>