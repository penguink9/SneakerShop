
package controller.admin;

import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Product;
import model.User;
@WebServlet(name = "StatisticControl", urlPatterns = {"/statistic"})
public class StatisticControl extends HttpServlet {

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
        ProductDAO pDAO= new ProductDAO();
        int numberProducts= pDAO.getAllProducts().size();
        OrderDAO oDAO= new OrderDAO();
        double totalSales = oDAO.sumAllOrders();
        int numberSneakersSold=oDAO.numberSneakersSold();
        UserDAO uDAO= new UserDAO();
        int numberUsers= uDAO.getNumberUsers();
        int numberNikeSold=oDAO.numberSneakersSoldByCategory(1);
        int numberAdidasSold=oDAO.numberSneakersSoldByCategory(2);;
        int numberPumaSold=oDAO.numberSneakersSoldByCategory(3);;
        int numberNBSold=oDAO.numberSneakersSoldByCategory(4);;
        request.setAttribute("numberProducts", numberProducts);
        request.setAttribute("totalSales", totalSales);
        request.setAttribute("numberSneakersSold", numberSneakersSold);
        request.setAttribute("numberUsers", numberUsers);
        request.setAttribute("numberNikeSold", numberNikeSold);
        request.setAttribute("numberAdidasSold", numberAdidasSold);
        request.setAttribute("numberPumaSold", numberPumaSold);
        request.setAttribute("numberNBSold", numberNBSold);
        request.getRequestDispatcher("admin/statistic.jsp").forward(request, response);
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
