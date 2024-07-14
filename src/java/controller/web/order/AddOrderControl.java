package controller.web.order;

import dao.CartDAO;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Cart;
import model.Item;
import model.Order;
import model.User;

@WebServlet(name = "AddOrderControl", urlPatterns = {"/order"})
public class AddOrderControl extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        String username = user.getUserName();
        String deliveryAddress = request.getParameter("deliveryAddress");
        double totalMoney = Double.parseDouble(request.getParameter("money"));
        String receiver = request.getParameter("receiver");
        String phone = request.getParameter("phoneNumber");
        Cart cart = (Cart) session.getAttribute("cart");
        List<Item> items = cart.getListItems();
        cart.setListItems(new ArrayList<>());
        CartDAO cartDAO = new CartDAO();
        cartDAO.updateCart(cart);

        // Create Order object
        Order order = new Order(0, new Date(), receiver, phone, username, deliveryAddress, totalMoney);

        // Add order to the database
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.addOrder(order, items);

        // Redirect or forward to success page
        session.setAttribute("cart", cart);
        request.setAttribute("mess", "Đặt hàng thành công!");
        request.getRequestDispatcher("viewcart").forward(request, response);
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
