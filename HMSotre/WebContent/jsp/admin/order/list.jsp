<%@ page language="java" pageEncoding="UTF-8"%>
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
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/jsp/admin/findOrderitem"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单列表</strong>
					</TD>
				</tr>

				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="10%">序号</td>
								<td align="center" width="10%">订单编号</td>
								<td align="center" width="10%">订单金额</td>
								<td align="center" width="10%">收货人</td>
								<td align="center" width="10%">订单状态
									(1=未付款、2=发货、3=已发货、4=订单完成)</td>
								<td align="center" width="50%">订单详情</td>
							</tr>

							<c:forEach items="${allOrders}" var="o" varStatus="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="5%">${status.count}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="20%">${o.oid}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="5%">${o.total}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="5%">${o.name}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="5%">
										<c:if test="${o.state==1}">未付款</c:if> 
										<c:if
											test="${o.state==2}">
											<a
												href="${pageContext.request.contextPath}/jsp/admin/findOrderitem&oid=${o.oid}">发货</a>
										</c:if> 
										<c:if test="${o.state==3}">已发货</c:if> 
										<c:if test="${o.state==4}">订单完成</c:if>
									</td>
									<td align="center" style="HEIGHT: 22px" width="60%">
									<input type="submit" value="订单详情" class="myClass" />
									<input type="hidden" name="oid" value="${o.oid}"/> 
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
			</TBODY>
		</table>
		<%@ include file="/jsp/pageFile.jsp" %>
	</form>
</body>
</HTML>

