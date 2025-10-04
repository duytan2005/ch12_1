<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>The SQL Gateway</title>
  <style>
    body { font-family: Arial; margin: 40px; }
    h2 { color: #00796b; }
    textarea { width: 100%; height: 100px; font-family: monospace; font-size: 14px; }
    button { margin-top: 10px; padding: 8px 20px; background-color: #e0e0e0; border: 1px solid #999; cursor: pointer; }
    table { margin-top: 20px; border-collapse: collapse; }
    th, td { border: 1px solid black; padding: 6px 10px; }
    th { background-color: #f0f0f0; }
  </style>
</head>
<body>
  <h2>The SQL Gateway</h2>
  <p>Enter an SQL statement and click the Execute button.</p>
  <p>Note <br>
        CreatEmail: INSERT INTO users(email, first_name, last_name) VALUES () <br>
        AllEmail: select * from users</p>

  <form action="sqlGateway" method="post">
    <label><b>SQL statement:</b></label><br/>
    <textarea name="sql"><%= request.getAttribute("sql") != null ? request.getAttribute("sql") : "" %></textarea><br/>
    <button type="submit">Execute</button>
  </form>

  <h3>SQL result:</h3>
  <div>
    <%= request.getAttribute("result") != null ? request.getAttribute("result") : "" %>
  </div>
</body>
</html>
