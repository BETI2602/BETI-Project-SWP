/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CartDAO;
import DAOs.ProductDAO;
import Modals.Cart;
import Modals.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ProductController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String path = request.getRequestURI();
        if (path.startsWith("/F-Store/Product/Detail/")) {
            String[] s = path.split("/");
            String id = s[s.length - 1];
            ProductDAO dao = new ProductDAO();
            Product p = dao.GetProductId(Integer.parseInt(id));
            if (p == null) {
                response.sendRedirect("/F-Store/");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("thongtinsanpham", p);
                request.getRequestDispatcher("/ProductDetail.jsp").forward(request, response);
            }
        }
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
       if (request.getParameter("addNew") != null) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            int proID = Integer.parseInt(request.getParameter("proID"));
            String size = request.getParameter("options-base");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Cart c = new Cart(userID, proID, size, quantity);
            CartDAO dao = new CartDAO();
            int kq = dao.AddNew(c);
            HttpSession session = request.getSession();
            if (kq != 0) {
                session.setAttribute("alrtMess", "Thêm thành công");
                response.sendRedirect("/F-Store/Home");
            } else {
                session.setAttribute("alrtMess", "Có lỗi xảy ra vui lòng thử lại");
                response.sendRedirect("/F-Store/Home");
            }
        }
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
