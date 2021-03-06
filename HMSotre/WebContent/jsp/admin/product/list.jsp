<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/jsp/admin/addUI";
			}
		</script>
</HEAD>
<body>
	<br>
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addProduct()">
							&#28155;&#21152;</button>

					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="18%">序号</td>
								<td align="center" width="17%">商品图片</td>
								<td align="center" width="17%">商品名称</td>
								<td align="center" width="17%">商品价格</td>
								<td align="center" width="17%">是否热门 [是(1)/否(0)]</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">下架</td>
							</tr>
							<c:forEach items="${pageBean.list}" var="p" varStatus="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${ status.count }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><img width="40" height="45" 
										src="${ pageContext.request.contextPath }/${p.pimage}">
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${ p.pname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">${ p.shopPrice }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">
										${p.isHot}
										</td>
										<!-- 编辑 -->
									<td align="center" style="HEIGHT: 22px">
									 <a href="${pageContext.request.contextPath}/jsp/admin/editUI?pid=${p.pid}&pimage=${p.pimage}&pname=${p.pname}&marketPrice=${p.marketPrice}&shopPrice=${p.shopPrice}&cid=${p.cid}&pdesc=${p.pdesc}"> 
									 
									 <img
											src="${pageContext.request.contextPath}/img/admin/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a> 
									
									</td>

										<!-- 下架 -->
									<td align="center" style="HEIGHT: 22px">
										<%--下架 pushdown --%>
									<a href="javascript:;" id="${p.pid}" class="delete">
													<img src="${pageContext.request.contextPath}/img/admin/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr align="center">
						<td colspan="7">
							第${ pageBean.currentPageNum }/${ pageBean.totalPageNum }页 &nbsp; &nbsp; &nbsp;
							总记录数:${ pageBean.totalRecords }  &nbsp; 每页显示:${ pageBean.pageSize }
							<c:if test="${ pageBean.currentPageNum != 1 }">
								<a href="${ pageContext.request.contextPath }/jsp/admin/findAllProductsWithPageUp?currPage=1">首页</a>|
								<a href="${ pageContext.request.contextPath }/jsp/admin/findAllProductsWithPageUp?currPage=${ pageBean.currentPageNum - 1}">上一页</a>|
							</c:if>
							&nbsp; &nbsp;
							
							<c:forEach var="i" begin="1" end="${ pageBean.totalPageNum }">
								<c:if test="${ pageBean.currentPageNum == i }">
									[${ i }]
								</c:if>
								<c:if test="${ pageBean.currentPageNum != i }">
									<a href="${ pageContext.request.contextPath }/jsp/admin/findAllProductsWithPageUp?currPage=${ i}">[${ i }]</a>
								</c:if>
							</c:forEach>
							
							&nbsp; &nbsp;
							<c:if test="${ pageBean.currentPageNum != pageBean.totalPageNum }">
								<a href="${ pageContext.request.contextPath }/jsp/admin/findAllProductsWithPageUp?currPage=${ pageBean.currentPageNum + 1}">下一页</a>|
								<a href="${ pageContext.request.contextPath }/jsp/admin/findAllProductsWithPageUp?currPage=${ pageBean.totalPageNum}">尾页</a>|
							</c:if>	
						</td>
					</tr>
			</TBODY>
		</table>
</body>
<script>
$(function(){
	//页面加载完毕之后获取到class的值为delete元素,为其绑定点击事件
	$(".delete").click(function(){
		if(confirm("确认下架?")){
			//获取到被下架pid
			var pid=this.id;
			window.location.href="${pageContext.request.contextPath}/jsp/admin/downProduct?pid="+pid;
		}
	});
});
</script>
</HTML>

