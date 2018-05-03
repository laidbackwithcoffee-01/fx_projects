<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" type="text/css" href="../css/base.css" />
<script type="text/javascript" src="../js/base.js"></script>
<script type="text/javascript">
<!--
disabled_back();

function check(){

	if(document.form1.employeeNo.value == "" ||
			document.form1.name.value == "") {
		window.alert('必須項目に未入力がありました');
		return false;
	}

	document.form1.conditionEmployeeNo.value = document.form1.employeeNo.value;

	return true;
}
-->
</script>
<title>新規追加画面</title>
</head>
<body>
<div id="page-box">
<form action="./EmployeeManagement" name="form1" method="POST" onSubmit="return check()">
<div id="site-box">
<h1>入力してください</h1>
</div>
<div id="table-box">
<table align="center">
<tr>
	<td>社員No：</td>
	<td><input type="text" name="employeeNo" pattern="^[0-9A-Za-z]+$" placeholder="英数字のみ" size="30" maxlength="10" /></td>
</tr>
<tr>
	<td>氏名：</td>
	<td><input type="text" name="name" size="30" maxlength="50" /></td>
</tr>
</table>
</div>
<p>
<input type="submit" id="submit_button" value="登録する" />
<input type="button" onClick="location.href='./EmployeeManagementMenu.jsp'" id="submit_button" value="メニュー" />
</p>
<input type="hidden" name="mode" value="insert" />
<input type="hidden" name="conditionEmployeeNo" />
</form>
</div>
</body>
</html>