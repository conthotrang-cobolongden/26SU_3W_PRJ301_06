/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
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

    // SELECT => chi xem du lieu => ResultSet  ==> executeQuery()
    // INSERT, UPDATE, DELETE => co lam thay doi du lieu => int -> la so dong bi thay doi => executeUpdate()
    @Override
    public boolean add(UserDTO t) {
        Connection conn = null;
        Statement st = null;

        try {
            // Step 1: get database connection
            conn = DbUtils.getConnection();

            // Step 2: Create Statement Object
            st = conn.createStatement();

            // Step 3: Build SQL query 
            String sql = "INSERT INTO [user]([userID],[fullName],[password],[roleID],[status]) "
                    + "VALUES ('" + t.getUserID() + "', N'" + t.getFullName() + "', '"
                    + t.getPassword() + "', '" + t.getRoleID() + "', " + (t.isStatus() ? 1 : 0) + ")";

            // INSERT ... VALUES('u1', 'Nguyen Van A', '123456', ....);
            // Step 4: Excute
            int rowsAffected = st.executeUpdate(sql);

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // Step 5: Always close resources manually
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean remove(UserDTO t) {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DbUtils.getConnection();
            st = conn.createStatement();

            String sql = "UPDATE [user] SET status = 0 WHERE userID = '" + t.getUserID() + "'";
            int rowsAffected = st.executeUpdate(sql);
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean update(UserDTO t) {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DbUtils.getConnection();
            st = conn.createStatement();

            String sql = "UPDATE [user] SET "
                    + "fullName = N'" + t.getFullName() + "', "
                    + "password = '" + t.getPassword() + "', "
                    + "roleID = '" + t.getRoleID() + "', "
                    + "status = " + (t.isStatus() ? 1 : 0) + " "
                    + "WHERE userID = '" + t.getUserID() + "'";

            int rowsAffected = st.executeUpdate(sql);
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public ArrayList<UserDTO> listAll() {
        ArrayList<UserDTO> userList = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM [user]";
            rs = st.executeQuery(sql);
            // Loop through result set and convert each ro to UserDTO
            while (rs.next()) {
                userList.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources in reverse order
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return userList;
    }

    @Override
    public UserDTO searchByID(String id) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            st = conn.createStatement();

            String sql = "SELECT * FROM [user] WHERE userID = '" + id + "'";
            rs = st.executeQuery(sql);

            // If found, convert to UserDTO object
            if (rs.next()) {
                return mapRow(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return null; // Not found
    }

}
