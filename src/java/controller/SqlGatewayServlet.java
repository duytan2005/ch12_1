package controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import dao.DatabaseUtil;


public class SqlGatewayServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sql = request.getParameter("sql");
        StringBuilder resultHtml = new StringBuilder();

        if (sql != null && !sql.trim().isEmpty()) {
            try (Connection conn = DatabaseUtil.getConnection();
                 Statement stmt = conn.createStatement()) {

                boolean hasResult = stmt.execute(sql);
                if (hasResult) {
                    ResultSet rs = stmt.getResultSet();
                    ResultSetMetaData meta = rs.getMetaData();
                    int columnCount = meta.getColumnCount();

                    resultHtml.append("<table border='1' style='border-collapse:collapse;'>");
                    resultHtml.append("<tr>");
                    for (int i = 1; i <= columnCount; i++) {
                        resultHtml.append("<th>").append(meta.getColumnName(i)).append("</th>");
                    }
                    resultHtml.append("</tr>");

                    while (rs.next()) {
                        resultHtml.append("<tr>");
                        for (int i = 1; i <= columnCount; i++) {
                            resultHtml.append("<td>").append(rs.getString(i)).append("</td>");
                        }
                        resultHtml.append("</tr>");
                    }
                    resultHtml.append("</table>");
                } else {
                    int count = stmt.getUpdateCount();
                    resultHtml.append("<p>Query executed successfully. Rows affected: ").append(count).append("</p>");
                }
            } catch (Exception e) {
                resultHtml.append("<p style='color:red;'>Error: ").append(e.getMessage()).append("</p>");
            }
        }

        request.setAttribute("sql", sql);
        request.setAttribute("result", resultHtml.toString());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
