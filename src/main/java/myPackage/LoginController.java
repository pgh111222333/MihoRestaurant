package myPackage;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String SESSION_ACCOUNT = "account";
    public static final String COOKIE_REMEMBER = "username";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute(SESSION_ACCOUNT) != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        // Kiểm tra cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_REMEMBER.equals(cookie.getName())) {
                    session = req.getSession(true);
                    session.setAttribute("UsernameInput", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/waiting");
                    return;
                }
            }
        }
        req.getRequestDispatcher("/html/LoginPage.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        
        String username = req.getParameter("UsernameInput");
        String password = req.getParameter("PasswordInput");
        boolean isRememberMe = "on".equals(req.getParameter("remember"));

        String alertMsg = "";
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            resp.sendRedirect(req.getContextPath() + "/html/LoginPage.html?alert=" + URLEncoder.encode(alertMsg, "UTF-8"));
            return;
        }

        UserServiceImpl service = new UserServiceImpl();
        User user = service.login(username, password);
        
        
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute(SESSION_ACCOUNT, user);
            if (isRememberMe) {
                saveRememberMe(resp, username);
            }
            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 60); // 30 phút
                response.addCookie(cookie);
    }
}
