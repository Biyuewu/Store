<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"  src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
		<script type="text/javascript">
			function addCategory(){
				window.location.href = "${pageContext.request.contextPath}/jsp/admin/category/add.jsp";
			}
		</script>
	</HEAD>
	<body>
		<br>
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>分类列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addCategory()">
&#28155;&#21152;
</button>

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%">
										序号
									</td>
									<td align="center" width="17%">
										分类名称
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
								<c:forEach items="${allCats}" var="c" varStatus="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												${status.count}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${c.cname}
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/jsp/admin/category/edit.jsp?cid=${c.cid}">
													<img src="${pageContext.request.contextPath}/img/admin/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="javascript:;" id="${c.cid}" class="delete">
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
								<a href="${ pageContext.request.contextPath }/jsp/admin/findAllCatsByPage?currPage=1">首页</a>|
								<a href="${ pageContext.request.contextPath }/jsp/admin/findAllCatsByPage?currPage=${ pageBean.currentPageNum - 1}">上一页</a>|
							</c:if>
							&nbsp; &nbsp;
							
							<c:forEach var="i" begin="1" end="${ pageBean.totalPageNum }">
								<c:if test="${ pageBean.currentPageNum == i }">
									[${ i }]
								</c:if>
								<c:if test="${ pageBean.currentPageNum != i }">
									<a href="${ pageContext.request.contextPath }/jsp/admin/findAllCatsByPage?currPage=${ i}">[${ i }]</a>
								</c:if>
							</c:forEach>
							
							&nbsp; &nbsp;
							<c:if test="${ pageBean.currentPageNum != pageBean.totalPageNum }">
								<a href="${ pageContext.request.contextPath }/jsp/admin/findAllCatsByPage?currPage=${ pageBean.currentPageNum + 1}">下一页</a>|
								<a href="${ pageContext.request.contextPath }/jsp/admin/findAllCatsByPage?currPage=${ pageBean.totalPageNum}">尾页</a>|
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
		if(confirm("确认删除?")){
			//获取到被删除pid
			var cid=this.id;
			window.location.href="${pageContext.request.contextPath}/jsp/admin/deleteCategory?cid="+cid;
		}
	});
});
</script>
</HTML>

