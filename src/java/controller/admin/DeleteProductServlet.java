
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



@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/deleteProduct"})
public class DeleteProductServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("pid"));
        ProductDAO pdao = new ProductDAO();
        
        pdao.deleteProduct(id);
        
        request.setAttribute("mess", "Product Deleted!");
        List<Product> list = pdao.getAllProducts();
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int totalProducts = list.size();
        int totalPages = (int) Math.ceil((double) totalProducts / 6);
        int start = (page - 1) * 6;
        int end = Math.min(start + 6, totalProducts);

        List<Product> paginatedList = list.subList(start, end);
        request.setAttribute("listP", paginatedList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
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
