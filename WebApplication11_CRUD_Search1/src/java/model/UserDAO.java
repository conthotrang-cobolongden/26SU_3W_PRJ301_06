package model;

import daoutils.BaseDAO;
import daoutils.IDAO;
import daoutils.IDAO;
import daoutils.SQLMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.UserDTO;
import utils.DbUtils;

/**
 * @author Le Nhat Tung
 */
public class UserDAO extends BaseDAO implements IDAO<UserDTO, String> {

    // =============================================
    // Convert a single row from ResultSet to UserDTO object
    // =============================================
    private final SQLMapper<UserDTO> mapRow = rs -> {
        UserDTO user = new UserDTO();
        user.setUserID(rs.getString("userID"));
        user.setFullName(rs.getNString("fullName")); // Use getNString for Unicode/Vietnamese
        user.setPassword(rs.getString("password"));
        user.setRoleID(rs.getString("roleID"));
        user.setStatus(rs.getBoolean("status"));
        return user;
    };

    @Override
    public boolean add(UserDTO t) {
        String sql = "INSERT INTO [user]([userID],[fullName],[password],[roleID],[status]) VALUES (?, ?, ?, ?, ?)";

        return executeUpdate(sql, ps -> {
            ps.setString(1, t.getUserID());
            ps.setNString(2, t.getFullName());
            ps.setString(3, t.getPassword());
            ps.setString(4, t.getRoleID());
            ps.setInt(5, t.isStatus() ? 1 : 0);
        });
    }

    @Override
    public boolean remove(UserDTO t) {

        String sql = "UPDATE [user] SET status = 0 WHERE userID = ?";

        return executeUpdate(sql, ps -> {
            ps.setString(1, t.getUserID());
        });
    }

    @Override
    public boolean update(UserDTO t) {
        String sql = "UPDATE [user] SET fullName = ?, password = ?, roleID = ?, status = ? WHERE userID = ?";

        return executeUpdate(sql, ps -> {
            ps.setNString(1, t.getFullName());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getRoleID());
            ps.setInt(4, t.isStatus() ? 1 : 0);
            ps.setString(5, t.getUserID());
        });
    }

    @Override
    public ArrayList<UserDTO> listAll() {
        String sql = "SELECT * FROM [user]";
        return executeQuery(sql, ps -> {
        }, mapRow);
    }

    @Override
    public UserDTO searchByID(String id) {
        String sql = "SELECT * FROM [user] WHERE userID = ?";
        ArrayList<UserDTO> result = executeQuery(sql, ps -> ps.setString(1, id), mapRow);
        return (result != null && result.size() > 0) ? result.get(0) : null;
    }
}
