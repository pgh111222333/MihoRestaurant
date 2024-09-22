import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login", "/LoginPage"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set content type and character encoding
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try {
            // Đường dẫn đến file HTML
            String filePath = getServletContext().getRealPath("/html/LoginPage.html");
            // Đọc nội dung từ file HTML
            String htmlContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Ghi nội dung vào phản hồi
            try (PrintWriter out = resp.getWriter()) {
                out.println(htmlContent);
            }
        } catch (IOException e) {
            // Gửi lỗi 500 nếu có vấn đề với file
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File not found: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set character encoding for request and response
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try (PrintWriter out = resp.getWriter()) {
            // Get parameter from POST request
            String hoten = req.getParameter("UsernameInput");
            out.println("<h1>Welcome " + hoten + "</h1>");
        }
    }
}
