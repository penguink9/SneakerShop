/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web.login;

import dao.CartDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.User;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] arr = request.getCookies();
        if (arr != null) {
            for (Cookie cookie : arr) {
                if (cookie.getName().equals("cUName")) {
                    request.setAttribute("uName", cookie.getValue());
                }
                if (cookie.getName().equals("pUName")) {
                    request.setAttribute("uPass", cookie.getValue());
                }
                if (cookie.getName().equals("reMem")) {
                    request.setAttribute("reMem", cookie.getValue());
                }
            }
        }
        
        HttpSession session = request.getSession();
        Long lockTime = (Long) session.getAttribute("lockTime");
        if (lockTime != null) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lockTime < 60000) { // 60000 ms = 1 minute
                request.setAttribute("isLocked", true);
                request.setAttribute("error", "Tài khoản của bạn đã bị khóa. Vui lòng thử lại sau 1 phút.");
            } else {
                session.removeAttribute("lockTime");
                session.setAttribute("failedAttempts", 0);
            }
        }
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uName = request.getParameter("username");
        String uPass = request.getParameter("password");
        String remember = request.getParameter("remember");
        UserDAO ud = new UserDAO();
        HttpSession session = request.getSession();

        Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
        if (failedAttempts == null) {
            failedAttempts = 0;
        }

        Long lockTime = (Long) session.getAttribute("lockTime");
        if (lockTime != null) {
long currentTime = System.currentTimeMillis();
            if (currentTime - lockTime < 60000) { // 60000 ms = 1 minute
                request.setAttribute("isLocked", true);
                request.setAttribute("error", "Tài khoản của bạn đã bị khóa. Vui lòng thử lại sau 1 phút.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            } else {
                session.removeAttribute("lockTime");
                session.setAttribute("failedAttempts", 0);
            }
        }

        if (!ud.checkUser(uName, uPass)) {
            failedAttempts++;
            session.setAttribute("failedAttempts", failedAttempts);
            if (failedAttempts >= 5) {
                session.setAttribute("lockTime", System.currentTimeMillis());
                request.setAttribute("isLocked", true);
                request.setAttribute("error", "Tài khoản của bạn đã bị khóa do đăng nhập sai 5 lần. Vui lòng thử lại sau 1 phút.");
            } else {
                request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            session.setAttribute("account", ud.getUserByUsername(uName));
            session.setAttribute("failedAttempts", 0);  // Đặt lại số lần đăng nhập sai về 0 sau khi đăng nhập thành công

            Cookie u = new Cookie("cUName", uName);
            Cookie p = new Cookie("pUName", uPass);
            Cookie r = new Cookie("reMem", remember != null ? "true" : "false");

            int maxAge = remember != null ? 60 * 60 * 24 * 30 * 3 : 0;
            u.setMaxAge(maxAge);
            p.setMaxAge(maxAge);
            r.setMaxAge(maxAge);

            response.addCookie(u);
            response.addCookie(p);
            response.addCookie(r);

            User user = ud.getUserByUsername(uName);
            CartDAO cartDAO = new CartDAO();
            Cart cart = cartDAO.getCartByUsername(uName);
            if (cart == null) {
                cart = new Cart(uName);
                cartDAO.addCart(cart);
            }

            session.setAttribute("role", user.getRoleID() == 0 ? "admin" : "user");
            session.setAttribute("cart", cart);
            session.setAttribute("imageUser", user.getImageURL());
            session.setAttribute("address", user.getAddress());
            session.setAttribute("name", user.getFullName());
            session.setAttribute("phone", user.getPhone());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("birthdate", user.getBirthDay());

            response.sendRedirect("home");
        }
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
return "Short description";
    }

}
