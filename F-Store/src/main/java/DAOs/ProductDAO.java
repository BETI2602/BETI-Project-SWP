/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modals.Product;
import Modals.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uchih
 */
public class ProductDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProductDAO() {
        con = DBContext.DBContext.getConnection();
    }

    public Product GetProductId(int id) {
        Product p = new Product();
        try {
            ps = con.prepareStatement("select * from [Product] where productID=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Product(rs.getInt("productID"), rs.getString("productName"), rs.getDouble("productPrice"), rs.getInt("productQuantity"),
                        rs.getString("productImg"), rs.getString("productCategory"), rs.getString("productDis"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public ArrayList<Product> getProduct(String search, int index, String sort) {
        String sortby = "";
        switch (sort) {
            case "1":
                sortby = "order by p.productName asc";
                break;
            case "2":
                sortby = "order by p.productPrice asc";
                break;
            case "3":
                sortby = "order by p.productPrice desc";
                break;
            default:
                sortby = "order by p.productName desc";
                break;

        }
        ArrayList<Product> list = new ArrayList<>();
        String sql = "  select * from [Product] p where p.productName like ?  "
                + sortby
                + "  OFFSET ? ROWS FETCH NEXT 8  ROWS ONLY"; // 6 0-2,2-4,4-6
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%"); // Short   %h%  zzzhqwoeoqw hqoweoqwe qưuioeqioweioh
            ps.setInt(2, (index - 1) * 8);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                double productPrice = resultSet.getDouble("productPrice");
                int productQuantity = resultSet.getInt("productQuantity");
                String productImg = resultSet.getString("productImg");
                String productCategory = resultSet.getString("productCategory");
                String productDescription = resultSet.getString("productDis");
                Product product = new Product(productID, productName, productPrice, productQuantity, productImg, productCategory, productDescription);
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (
                 Statement statement = con.createStatement()) {
            String query = "SELECT * FROM Product";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                double productPrice = resultSet.getDouble("productPrice");
                int productQuantity = resultSet.getInt("productQuantity");
                String productImg = resultSet.getString("productImg");
                String productMaterial = resultSet.getString("productCategory");
                String productType = resultSet.getString("productDis");
                Product product = new Product(productID, productName, productPrice, productQuantity, productImg, productMaterial, productType);
                products.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential database errors here
        }

        return products;
    }

    public Product getAll() {
        Product p = null;
        try (
                 Statement statement = con.createStatement()) {
            String query = "SELECT * FROM Product";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                double productPrice = resultSet.getDouble("productPrice");
                int productQuantity = resultSet.getInt("productQuantity");
                String productImg = resultSet.getString("productImg");
                String productCategory = resultSet.getString("productCategory");
                String productDis = resultSet.getString("productDis");
                p = new Product(productID, productName, productPrice, productQuantity, productImg, productCategory, productDis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential database errors here
        }

        return p;
    }

    public List<Product> searchProduct(String name, String typeSort) {
        String sql = "select * from Product where userId like '%?%' ";
        List<Product> products = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            String sqlSort = "";
            switch (typeSort) {
                case "asc":
                    sql += "order by asc";
                    break;
                case "desc":
                    sql += "order by desc";
                    break;
                default:
                    sql = "select * from Product where userId like '%?%' ";
                    break;
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                double productPrice = rs.getDouble("productPrice");
                int productQuantity = rs.getInt("productQuantity");
                String productImg = rs.getString("productImg");
                String productMaterial = rs.getString("productCategory");
                String productType = rs.getString("productDis");
                Product product = new Product(productID, productName, productPrice, productQuantity, productImg, productMaterial, productType);
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getNumberProduct(String search) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "  select count(*) from Product p where p.productName like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ResultSet getAllProduct() {
        String sql = "select*from Product";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addProduct(String productName, String productPrice, String productQuantity, String productImg, String productDiscription, String productCategory) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "([productName]\n"
                + ",[productPrice]\n"
                + ",[productQuantity]\n"
                + ",[productImg]\n"
                + ",[productDis]\n"
                + ",[productCategory])\n"
                + "VALUES\n"
                + "(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            // Thiết lập các giá trị thay thế trong câu SQL
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productPrice);
            preparedStatement.setString(3, productQuantity);
            preparedStatement.setString(4, productImg);
            preparedStatement.setString(5, productCategory);
            preparedStatement.setString(6, productDiscription);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Thêm sản phẩm thành công.");
            } else {
                System.out.println("Không thể thêm sản phẩm.");
            }
        } catch (SQLException e) {
        }
    }

    public void updateProduct(String productName, String productPrice, String productQuantity, String productImg, String productMaterial, String productType, String id) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [productName] = ?,\n"
                + "      [productPrice] = ?,\n"
                + "      [productQuantity] = ?,\n"
                + "      [productImg] = ?,\n"
                + "      [productDis] = ?,\n"
                + "      [productCategory] = ?\n"
                + " WHERE productID = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productPrice);
            preparedStatement.setString(3, productQuantity);
            preparedStatement.setString(4, productImg);
            preparedStatement.setString(5, productMaterial);
            preparedStatement.setString(6, productType);
            preparedStatement.setString(7, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật sản phẩm thành công!");
            } else {
                System.out.println("Không có sản phẩm nào được cập nhật.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(String productId) {
        String sql = "DELETE FROM [dbo].[Product] WHERE productID = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, productId);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Xóa sản phẩm thành công!");
            } else {
                System.out.println("Không có sản phẩm nào bị xóa.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
