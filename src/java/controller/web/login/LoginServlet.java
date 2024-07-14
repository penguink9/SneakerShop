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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

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
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uName = request.getParameter("username");
        String uPass = request.getParameter("password");
        String remember = request.getParameter("remember");
        UserDAO ud = new UserDAO();
        HttpSession session = request.getSession();
        if (!ud.checkUser(uName, uPass)) {
            request.setAttribute("error", "Username or password invalid!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            User user = ud.getUserByUsername(uName);
            session.setAttribute("account", user);

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
            String image = user.getImageURL();
            CartDAO cartDAO = new CartDAO();
            Cart cart = cartDAO.getCartByUsername(uName);
            if (cartDAO.getCartByUsername(uName) == null) {
                cart = new Cart(uName);
                cartDAO.addCart(cart);
            } else {
                cart = cartDAO.getCartByUsername(uName);
            }
            if (user.getRoleID() == 0) {
                session.setAttribute("role", "admin");
            } else {
                session.setAttribute("role", "user");
            }
            session.setAttribute("account", user);
            session.setAttribute("cart", cart);
            session.setAttribute("imageUser", image);
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
