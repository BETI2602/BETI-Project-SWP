/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CartDAO;
import DAOs.ProductDAO;
import DAOs.orderDAO;
import Modals.Order;
import Modals.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;

/**
 *
 * @author admin
 */
@MultipartConfig
public class ManagerControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        switch (path) {
            case "/F-Store/Manager/manageProduct":
                String type = request.getParameter("type");
                System.out.println("type: " + type);
                ProductDAO productDAO = new ProductDAO();
                CartDAO cartDAO = new CartDAO();

                switch (type) {
                    case "view":
                        List<Product> list = productDAO.getAllProducts();
                        request.setAttribute("list", list);
                        request.getRequestDispatcher("/productManagement.jsp").forward(request, response);
                        break;
                    case "delete":
                        String productIdDelete = request.getParameter("pid");
                        cartDAO.delete(Integer.parseInt(productIdDelete));
                        productDAO.deleteProduct(productIdDelete);
                        response.sendRedirect("manageProduct?type=view");
                        break;
                    case "update":
                        //Product productUpdate = productDAO.getProductById(Integer.parseInt(request.getParameter("pid")));
                                                Product productUpdate = productDAO.GetProductId(Integer.parseInt(request.getParameter("pid")));
                        request.setAttribute("data", productUpdate);
                        request.getRequestDispatcher("/updateProduct.jsp").forward(request, response);
                        break;
                    default:
                        List<Product> listDefault = productDAO.getAllProducts();
                        request.setAttribute("list", listDefault);
                        request.getRequestDispatcher("/productManagement.jsp").forward(request, response);
                        break;
                }
//                ProductDAO productDAO = new ProductDAO();
//                List<Product> list = productDAO.getAllProducts();
//                request.setAttribute("pro", "choose");
//                request.setAttribute("list", list);
//                request.getRequestDispatcher("/productManagement.jsp").forward(request, response);
                break;
            case "/F-Store/Manager/ShowOrder":
                orderDAO order = new orderDAO();
                List<Order> orderList = order.getAllOrder();
                request.setAttribute("ord", "choose");
                request.setAttribute("listOr", orderList);
                request.getRequestDispatcher("/showOrder.jsp").forward(request, response);
                break;
            case "/F-Store/Manager/monthRevenue":
                orderDAO or = new orderDAO();
                List<Order> listRevenue = or.monthlyRevenue();
                request.setAttribute("mth", "choose");
                request.setAttribute("listRevenue", listRevenue);
                request.getRequestDispatcher("/MonthRevenue.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        switch (path) {
            case "/F-Store/Manager/manageProduct":
                String type = request.getParameter("type");
                ProductDAO productDAO = new ProductDAO();
                System.out.println("type post:"+ type);
                switch (type) {
                    case "add":
                        String productName = request.getParameter("name");
                        String productPrice = request.getParameter("price");
                        String productQuantity = request.getParameter("quantity");
                        Part filePart = request.getPart("img");
                        String uploadPath = getServletContext().getRealPath("") + File.separator + "img\\product";
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }

                        String fileName = getFileName(filePart);
                        String filePath = uploadPath + File.separator + fileName;
                        filePart.write(filePath);
                        String category = request.getParameter("category");
                        String description = request.getParameter("description");
                        productDAO.addProduct(productName, productPrice, productQuantity, "./img/product/" + fileName, description, category);
                        response.sendRedirect("manageProduct?type=view");
                        break;
                    case "update":
                        String id = request.getParameter("id");
                        String productNameUpdate = request.getParameter("name");
                        String productPriceUpdate = request.getParameter("price");
                        String productQuantityUpdate = request.getParameter("quantity");
                        Part filePartUpdate = request.getPart("img");
                        String uploadPathUpdate = getServletContext().getRealPath("") + File.separator + "img\\product";
                        File uploadDirUpdate = new File(uploadPathUpdate);
                        if (!uploadDirUpdate.exists()) {
                            uploadDirUpdate.mkdir();
                        }

                        String fileNameUpdate = getFileName(filePartUpdate);
                        String filePathUpdate = uploadPathUpdate + File.separator + fileNameUpdate;
                        filePartUpdate.write(filePathUpdate);
                        String productTypeUpdate = request.getParameter("category");
                        String descriptionUpdate = request.getParameter("description");
                        productDAO.updateProduct(productNameUpdate, productPriceUpdate, productQuantityUpdate, "./img/product/" + fileNameUpdate, descriptionUpdate, productTypeUpdate, id);
                        response.sendRedirect("manageProduct?type=view");
                        break;
                }
                break;
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
