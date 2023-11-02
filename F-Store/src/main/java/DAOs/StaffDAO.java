/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Modals.Staff;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class StaffDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public StaffDAO() {
        conn = DBContext.DBContext.getConnection();
    }
    
    public String hashPassword(String password, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public Staff GetStaff(String email) {
        Staff staff = new Staff();
        try {
            ps = conn.prepareStatement("select * from Staff where staffEmail=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                staff = new Staff(rs.getInt("staffID"), rs.getString("staffEmail"), rs.getString("staffPassword"), rs.getString("staffName"),
                        rs.getDate("staffDayOfBirth"), rs.getString("staffPhone"), rs.getString("staffAddress"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return staff;
    }
    
    public boolean Login(Staff staff) throws SQLException {
        ResultSet rs = null;
        String sql = "Select * from Staff where staffEmail = ? and staffPassword=?";
        String password = staff.getStaffPassword();
        String hashPassword = hashPassword(password, "MD5");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, staff.getStaffEmail());
            ps.setString(2, hashPassword);
            rs = ps.executeQuery();
        } catch (Exception e) {

        }

        return rs.next();
    }
}
