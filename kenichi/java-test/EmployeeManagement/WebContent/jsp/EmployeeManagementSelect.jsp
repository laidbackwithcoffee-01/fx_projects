<%@ page import="com.practice.employeemanagement.dto.EmployeeManagementDto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/base.css" />
<script type="text/javascript" src="../js/base.js"></script>
<script type="text/javascript">
<!--
disabled_back();

function upd_confirm(p_employeeNo, p_name) {

	var name = window.prompt("氏名を入力してください", p_name);
	if (name != null && name != '') {
		document.form1.mode.value = "update";
		document.form1.employeeNo.value = p_employeeNo;
		document.form1.name.value = name;
		document.form1.submit();
	}

	return false;
}

function del_confirm(p_employeeNo) {

	if (window.confirm('以下の情報を削除します。よろしいですか？\r\n' + p_employeeNo)){
		document.form1.mode.value = "delete";
		document.form1.employeeNo.value = p_employeeNo;
		document.form1.submit();
	}

	return false;
}
-->
</script>
<title>検索画面</title>
</head>
<body>
<div id="page-box">
<form action="./EmployeeManagement" name="form1" method="POST">
<div id="site-box">
<h1>検索条件（AND条件）</h1>
</div>
<div id="table-box">
<table align="center">
<tr>
	<td>社員No（前方一致）</td>
	<td><input type="text" name="conditionEmployeeNo" pattern="^[0-9A-Za-z]+$" placeholder="英数字のみ" size="30" maxlength="10" value="<%= request.getAttribute("conditionEmployeeNo") %>" /></td>
</tr>
<tr>
	<td>氏名（曖昧検索）</td>
	<td><input type="text" name="conditionName" size="30" maxlength="50" value="<%= request.getAttribute("conditionName") %>" /></td>
</tr>
</table>
</div>
<p>
<input type="submit" id="submit_button" value="検索する" />
<input type="button" onClick="location.href='./EmployeeManagementMenu.jsp'" id="submit_button" value="メニュー" />
</p>
<input type="hidden" name="mode" value="select" />
<input type="hidden" name="employeeNo" />
<input type="hidden" name="name" />
<p id="msg"><%= request.getAttribute("msg") %></p>
<div id="site-box">
<h1>検索結果</h1>
</div>
<table align="center" border="1">
<tr><th>社員No</th><th>氏名</th><th>更新</th><th>削除</th></tr>
<%
	List<EmployeeManagementDto> list = (List<EmployeeManagementDto>) request.getAttribute("data");
	for (EmployeeManagementDto data : list) {
		out.println("<tr>");
		out.println("<td>");
		out.println(data.getEmployeeNo());
		out.println("</td>");
		out.println("<td>");
		out.println(data.getName());
		out.println("</td>");
		out.println("<td>");
		out.println("<input type=\"button\" id=\"submit_button_edit\" value=\"Edit\" onClick=\"return upd_confirm('" + data.getEmployeeNo() + "', '" + data.getName() + "')\" />");
		out.println("</td>");
		out.println("<td>");
		out.println("<input type=\"button\" id=\"submit_button_del\" value=\"Del\" onClick=\"return del_confirm('" + data.getEmployeeNo() + "')\" />");
		out.println("</td>");
		out.println("</tr>");
	}
%>
</table>
</form>
</div>
</body>
</html>