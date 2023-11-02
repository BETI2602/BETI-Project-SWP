/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Modals.Item;
import Modals.Order;
import Modals.Product;
import Modals.User;
import Modals.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class orderDAO {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public orderDAO() {
        conn = DBContext.DBContext.getConnection();
    }

    //addOrder
    public void addOrder(User u, Cart c, Product p, Order o) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try {
            //Add Order
            String sql = "insert into [Order] values (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getProductID());
            ps.setInt(2, c.getUserID());
            ps.setInt(3, c.getCartID());
            ps.setDouble(4, o.getTotalPrice());
            ps.setString(5, date);
            ps.executeUpdate();
            //lay id order moi add
            String sql1 = "select top 1 orderID from [Order] order by orderID desc";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rss = ps1.executeQuery();
//            //add orderDetails
//            if (rss.next()) {
//                int oid = rss.getInt("orderID");
//                for (Items i : c.()) {
//                    String sql2 = "insert into [OrderDetail] values(?,?,?,?)";
//                    PreparedStatement ps2 = conn.prepareStatement(sql2);
//                    ps2.setInt(1, oid);
//                    ps2.setInt(2, i.getProduct().getProductID());
//                    ps2.setDouble(3, i.getPrice());
//                    ps2.setInt(4, i.getQuantity());
//                    ps2.executeUpdate();
//                }
//            }
//            //Update so luong san pham 
//            String sql3 = "update product set productQuantity = productQuantity-? where id=?";
//            PreparedStatement ps3 = conn.prepareStatement(sql3);
//            for (Items i : c.getItems()) {
//                ps3.setInt(1, i.getQuantity());
//                ps3.setInt(2, i.getProduct().getProductQuantity());
//                ps3.executeUpdate();
//            }
        } catch (Exception e) {

        }
    }

    //doanh thu theo thang
    public List<Order> monthlyRevenue() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT MONTH(purchaseDate) AS month, SUM(O.totalPrice) AS revenue\n"
                + "FROM [Order] AS O\n"
                + "INNER JOIN Cart AS C ON O.cartID = C.cartID\n"
                + "GROUP BY MONTH(O.purchaseDate)\n"
                + "ORDER BY MONTH(O.purchaseDate) DESC;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getDouble(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(orderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //getall
    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT O.orderID, O.userID, O.productID, O.cartID, O.totalPrice, O.purchaseDate\n"
                + "FROM [Order] AS O\n"
                + "INNER JOIN Cart AS C ON O.cartID = C.cartID;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getFloat(5),
                        rs.getDate(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(orderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Delete Order
    public int DeleteOrder(int id) {
        int kq = 0;
        String sql = "delete from [Order] where orderID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            kq = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(orderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    //get Order
    public int getOrderByAC(int id) {
        rs = null;
        int kq = 0;
        String sql = "select order_id from [Order] where account_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                kq = rs.getInt("order_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(orderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
}
