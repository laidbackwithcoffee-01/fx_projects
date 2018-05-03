<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/base.css" />
<script type="text/javascript" src="../js/base.js"></script>
<script type="text/javascript">
<!--
disabled_back();
-->
</script>
<title>社員管理メニュー画面</title>
</head>
<body>
<div id="page-box">
<form action="./EmployeeManagement" name="form1" method="POST">
<div id="site-box">
<h1>選択してください</h1>
</div>
<p>
	<input type="radio" name="mode" value="select" checked="checked">検索・更新・削除
	<input type="radio" name="mode" value="insert">新規追加
</p>
<input type="submit" id="submit_button" value="移動する" />
</form>
</div>
</body>
</html>