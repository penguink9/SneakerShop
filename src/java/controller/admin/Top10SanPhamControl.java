
package controller.admin;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Product;
@WebServlet(name = "Top10SanPhamControl", urlPatterns = {"/top10product"})
public class Top10SanPhamControl extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
       
        ProductDAO dao = new ProductDAO();
        List<Product> listAllProduct = dao.getAllProducts();
        List<Product> listTop10Product = dao.getTop10BestSelling();
        request.setAttribute("listTop10Product", listTop10Product);
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int totalProducts = listTop10Product.size();
        int totalPages = (int) Math.ceil((double) totalProducts / 5);
        int start = (page - 1) * 5;
        int end = Math.min(start + 5, totalProducts);

        List<Product> paginatedList = listTop10Product.subList(start, end);
        request.setAttribute("listTop10Product", paginatedList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("admin/top10product.jsp").forward(request, response);
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
