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

@WebServlet(name = "EditProductServlet", urlPatterns = {"/editProduct"})
public class EditProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int pid = Integer.parseInt(request.getParameter("id"));

        String pname = request.getParameter("name");
        int pcateid = Integer.parseInt(request.getParameter("category"));

        String size = request.getParameter("size");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int quantitysold = Integer.parseInt(request.getParameter("quantitysold"));

        String image = request.getParameter("image");
        String image2 = request.getParameter("image2");
        String image3 = request.getParameter("image3");
        String image4 = request.getParameter("image4");
        String pdescription = request.getParameter("description");
        Float discount = Float.parseFloat(request.getParameter("discount"));
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));

        ProductDAO dao = new ProductDAO();

        Product product = new Product(pid, pname, pcateid, size, price, quantity, quantitysold, image, image2, image3, image4, pdescription, discount, status);

        dao.updateProduct(product);
        request.setAttribute("mess", "Edited!");
        List<Product> list = dao.getAllProducts();
        request.setAttribute("listP", list);
        request.getRequestDispatcher("admin/manageProduct.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
