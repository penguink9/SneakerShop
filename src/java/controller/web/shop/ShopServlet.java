/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.web.shop;

import dao.CategoryDAO;
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
import model.Category;
import model.Product;

@WebServlet(name = "ShopServlet", urlPatterns = {"/shop"})
public class ShopServlet extends HttpServlet {

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
        ProductDAO pDAO = new ProductDAO();
        List<Product> listP = new ArrayList<>();
        if (request.getParameter("cid") != null) {
            session.setAttribute("cid", request.getParameter("cid"));
        }
        int cid = Integer.parseInt((String) session.getAttribute("cid"));
        if (cid == 0) {
            listP = pDAO.getAllProducts();
        } else {
            listP = pDAO.getProductsByCategoryid(cid);
        }
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }
        if (session.getAttribute("searched")!=null) {
            if((Boolean)session.getAttribute("searched"))
            listP = (List<Product>) session.getAttribute("searchList");
        }
        int indexPage = Integer.parseInt(index);
        // Pagination logic
        int itemsPerPage = 6;
        int allProduct = listP.size();
        int endPage = allProduct / itemsPerPage;
        if (allProduct % itemsPerPage != 0) {
            endPage++;
        }
        int start = (indexPage - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, allProduct);

        List<Product> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(listP.get(i));
        }
        request.setAttribute("tag", indexPage);
        request.setAttribute("endPage", endPage);
        session.setAttribute("searched", false);
        session.setAttribute("listP", list);

        request.getRequestDispatcher("shop.jsp").forward(request, response);
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
