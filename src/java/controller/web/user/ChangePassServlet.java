/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web.user;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;


@WebServlet(name = "ChangePassServlet", urlPatterns = {"/changepass"})
public class ChangePassServlet extends HttpServlet {

  
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String cfnewpass = request.getParameter("cfnewpass");
        User account= (User) session.getAttribute("account");
        String username = account.getUserName();
        String msg = "";
        UserDAO dao = new UserDAO();
        Boolean a = dao.checkUser(username, oldpass);
        if (a == null) {
            msg = "Old password is incorrect";
            request.setAttribute("error", msg);
            request.getRequestDispatcher("changePassword.jsp").forward(request, response);
        } else {
            if (cfnewpass.equals(newpass)) {
                dao.changePassword(username, newpass);
                account= dao.getUserByUsername(username);
                msg = "Change password successfully!";                
                session.setAttribute("account", account);
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            } else {
                msg = "New password does not match.";
                request.setAttribute("error", msg);
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
        }
    }
        

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
