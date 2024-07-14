/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;



@WebServlet(name = "AddProductServlet", urlPatterns = {"/addProduct"})
public class AddProductServlet extends HttpServlet {

    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String pname = request.getParameter("productname");
        int cateid = Integer.parseInt(request.getParameter("categoryid"));
        String size = request.getParameter("size");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int quantitysold = Integer.parseInt(request.getParameter("quantitysold"));
        
        
        String img = request.getParameter("image");
        String img2 = request.getParameter("image2");
        String img3 = request.getParameter("image3");
        String img4 = request.getParameter("image4");
        String description= request.getParameter("description");
        float discount = Float.parseFloat(request.getParameter("discount"));
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        
        Product product = new Product(cateid, pname, cateid, size, price, quantity, quantitysold, img4, img2, img3, img4, description, discount, true);
        ProductDAO dao = new ProductDAO();
        dao.addProduct(product);
        request.setAttribute("mess", "Product Added!");
        List<Product> list = dao.getAllProducts();
        request.setAttribute("listP", list);
        request.getRequestDispatcher("admin/manageProduct.jsp").forward(request, response);
    }

    
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
