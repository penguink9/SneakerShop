package controller.admin;

import dao.OrderDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Order;
import model.User;

@WebServlet(name = "OrderManager", urlPatterns = {"/OrderManager"})
public class OrderManager extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        OrderDAO dao = new OrderDAO();
        UserDAO dao2 = new UserDAO();

        double sumAllInvoice = dao.sumAllOrders();

        List<Order> listAllInvoice;
        List<User> listAllAccount = dao2.getAllUsers();

        String action = request.getParameter("action");
        if (action != null && action.equals("searchByDate")) {
            String dateString = request.getParameter("dateHoaDon");
            listAllInvoice = dao.getOrdersByDate(dateString);
            request.setAttribute("dob", dateString);
        } else {
            listAllInvoice = dao.getAllOrders();
        }

        // Pagination logic
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int totalOrders = listAllInvoice.size();
        int totalPages = (int) Math.ceil((double) totalOrders / 10);
        int start = (page - 1) * 10;
        int end = Math.min(start + 10, totalOrders);

        List<Order> paginatedList = listAllInvoice.subList(start, end);

        request.setAttribute("listAllInvoice", paginatedList);
        request.setAttribute("listAllAccount", listAllAccount);
        request.setAttribute("sumAllInvoice", sumAllInvoice);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("admin/manageOrder.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
//        String action = request.getParameter("action");
//
//        if (action != null && action.equals("searchByDate")) {
//            String dateString = request.getParameter("dateHoaDon");
//
//            OrderDAO dao = new OrderDAO();
//            UserDAO dao2 = new UserDAO();
//
//            List<Order> listAllInvoice = dao.getOrdersByDate(dateString);
//            List<User> listAllAccount = dao2.getAllUsers();
//            request.setAttribute("dob", dateString);
//            request.setAttribute("listAllInvoice", listAllInvoice);
//            request.setAttribute("listAllAccount", listAllAccount);
//
//            request.getRequestDispatcher("admin/manageOrder.jsp").forward(request, response);
//        } else {
//            processRequest(request, response);
//        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
