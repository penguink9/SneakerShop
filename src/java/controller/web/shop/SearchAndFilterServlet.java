/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web.shop;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Product;
@WebServlet(name = "SearchAndFilterServlet", urlPatterns = {"/search"})
public class SearchAndFilterServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
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
        HttpSession session = request.getSession();
        session.setAttribute("searched", false);
        session.setAttribute("txtS", null);
        session.setAttribute("selectedPriceRange", null);
        request.getRequestDispatcher("shop").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        ProductDAO pDAO = new ProductDAO();
        List<Product> listP = pDAO.getAllProducts();

        // Fetch all products if listP is not already set
        if (listP == null) {
            listP = pDAO.getAllProducts();
        }

        // Get search and filter parameters
        String searchQuery = request.getParameter("txt");
        String priceRange = request.getParameter("priceRange");

        // Apply search filter
        if (searchQuery != null && !searchQuery.isEmpty()) {
            listP = listP.stream()
                    .filter(product -> product.getProductName().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Apply price filter
        if (priceRange != null && !priceRange.isEmpty()) {
            switch (priceRange) {
                case "under1tr":
                    listP = listP.stream()
                            .filter(product -> product.getPrice() < 1000000)
                            .collect(Collectors.toList());
                    break;
                case "1tr2tr":
                    listP = listP.stream()
                            .filter(product -> product.getPrice() >= 1000000 && product.getPrice() <= 2000000)
                            .collect(Collectors.toList());
                    break;
                case "2trabove":
                    listP = listP.stream()
                            .filter(product -> product.getPrice() > 2000000)
                            .collect(Collectors.toList());
                    break;
            }
        }

        // Update session attribute
        session.setAttribute("searched", true);
        session.setAttribute("searchList", listP);
        // Set search and filter parameters as request attributes
        session.setAttribute("txtS", searchQuery);
        session.setAttribute("selectedPriceRange", priceRange);

        // Forward to the shop.jsp page
        request.getRequestDispatcher("shop").forward(request, response);
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
