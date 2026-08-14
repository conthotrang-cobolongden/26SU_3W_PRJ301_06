/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import utils.DbUtils;
import java.sql.*;

/**
 *
 * @author tungln
 */
public class UserDAO implements IDAO<UserDTO, String> {

    public UserDAO() {

    }

    // ResultSet → UserDTO
    private UserDTO mapRow(ResultSet rs) throws SQLException {
        return new UserDTO(
                rs.getString("userID"),
                rs.getString("fullName"),
                rs.getString("password"),
                rs.getString("roleID"),
                rs.getBoolean("status") // BIT → boolean tự động
        );
    }

    public boolean add(UserDTO t) {
        String sql = "INSERT INTO [user] (userID, fullName, password, roleID, status)"
                + " VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getUserID());
            ps.setString(2, t.getFullName());
            ps.setString(3, t.getPassword());
            ps.setString(4, t.getRoleID());
            ps.setBoolean(5, t.isStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(UserDTO t) {
        return softDelete(t.getUserID());
    }

    public boolean update(UserDTO t) {
        String sql = "UPDATE [user] SET "
                + "fullName=?, password=?, roleID=?, status=? "
                + "WHERE userID=?";
        try {
            Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, t.getFullName());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getRoleID());
            ps.setBoolean(4, t.isStatus());
            ps.setString(5, t.getUserID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<UserDTO> listAll() {
        ArrayList<UserDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM [user]";
        try {
            Connection conn = DbUtils.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            // Da lay duoc du lieu tu Table User
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public UserDTO searchByID(String id) {
        String sql = "SELECT * FROM [user] WHERE userID=?";
        System.out.println(sql);
        try {
            Connection conn = DbUtils.getConnection();
            //Statement st = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            // Da lay duoc du lieu tu Table User
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<UserDTO> searchByName(String name) {
        ArrayList<UserDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM [user] WHERE fullName LIKE ?";
        System.out.println(sql);
        try {
            Connection conn = DbUtils.getConnection();
            //Statement st = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + name + "%");
            ResultSet rs = pst.executeQuery();
            // Da lay duoc du lieu tu Table User
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean checkLogin(String userID, String password) {
        UserDTO u = searchByID(userID);
        if (u == null) {
            return false;
        }

        if (!u.isStatus()) {
            return false;
        }

        if (!u.getPassword().equals(password)) {
            return false;
        }

        return true;
    }

    public boolean softDelete(String userID) {
        String sql = "UPDATE [user] SET status = 0 WHERE userID = ?";
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userID);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hardDelete(String userID) {
        String sql = "DELETE FROM [user] WHERE userID = ?";
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userID);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
