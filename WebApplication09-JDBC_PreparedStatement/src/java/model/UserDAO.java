package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DbUtils;

/**
 * @author Le Nhat Tung
 */
public class UserDAO implements IDAO<UserDTO, String> {

    // =============================================
    // Convert a single row from ResultSet to UserDTO object
    // =============================================
    private UserDTO mapRow(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();
        user.setUserID(rs.getString("userID"));
        user.setFullName(rs.getNString("fullName")); // Use getNString for Unicode/Vietnamese
        user.setPassword(rs.getString("password"));
        user.setRoleID(rs.getString("roleID"));
        user.setStatus(rs.getBoolean("status"));
        return user;
    }

    @Override
    public boolean add(UserDTO t) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtils.getConnection();
            String sql = "INSERT INTO [user]([userID],[fullName],[password],[roleID],[status]) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getUserID());
            ps.setNString(2, t.getFullName());
            ps.setString(3, t.getPassword());
            ps.setString(4, t.getRoleID());
            ps.setInt(5, t.isStatus() ? 1 : 0);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, ps, null);
        }
    }

    @Override
    public boolean remove(UserDTO t) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtils.getConnection();
            String sql = "UPDATE [user] SET status = 0 WHERE userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getUserID());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, ps, null);
        }
    }

    @Override
    public boolean update(UserDTO t) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtils.getConnection();
            String sql = "UPDATE [user] SET fullName = ?, password = ?, roleID = ?, status = ? WHERE userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setNString(1, t.getFullName());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getRoleID());
            ps.setInt(4, t.isStatus() ? 1 : 0);
            ps.setString(5, t.getUserID());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, ps, null);
        }
    }

    @Override
    public ArrayList<UserDTO> listAll() {
        ArrayList<UserDTO> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            String sql = "SELECT * FROM [user]";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return userList;
    }

    @Override
    public UserDTO searchByID(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            String sql = "SELECT * FROM [user] WHERE userID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }
    
    // Helper method to close resources
    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}