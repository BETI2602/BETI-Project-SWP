/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CartDAO;
import DAOs.UserDAO;
import Modals.Cart;
import Modals.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Dell
 */
public class CartController extends HttpServlet {

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
            out.println("<title>Servlet CartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
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
        if (path.startsWith("/F-Store/Cart/")) {
            String[] s = path.split("/");
            String id = s[s.length - 1];
            UserDAO dao = new UserDAO();
            User u = dao.GetUserId(id);
            HttpSession session = request.getSession();
            if (u != null) {
                CartDAO cdao = new CartDAO();
                List<Cart> mycart = cdao.ShowinCart(Integer.parseInt(id));
                session.setAttribute("userIn4", u);
                request.setAttribute("mycartlist", mycart);
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
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

        if (request.getParameter("deletebtn") != null) {
            int cartID = Integer.parseInt(request.getParameter("cartID"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            int productID = Integer.parseInt(request.getParameter("productID"));
            String size = request.getParameter("size");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Cart c = new Cart(cartID, userID, productID, size, quantity);
            CartDAO dao = new CartDAO();
            dao.delete(productID);
            
            HttpSession session = request.getSession();
            session.setAttribute("cartList", c);
            response.sendRedirect("/F-Store/Cart/" + userID);
        }

        if (request.getParameter("editCartbtn") != null) {
            int cartID = Integer.parseInt(request.getParameter("cartID"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            int productID = Integer.parseInt(request.getParameter("productID"));
            String size = request.getParameter("size");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Cart c = new Cart(cartID, userID, productID, size, quantity);
            CartDAO dao = new CartDAO();
            int rs = dao.update(c);
            if (rs != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("thongtingiohang", c);
                request.setAttribute("thongBaoCapNhat", "Chỉnh sửa thành công.");
                response.sendRedirect("/F-Store/Cart/" + userID);
            } else {
                request.setAttribute("thongBaoCapNhat", "Error.");
                response.sendRedirect("/F-Store/Cart/" + userID);
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
