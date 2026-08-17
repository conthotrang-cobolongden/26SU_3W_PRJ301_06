/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoutils;

/**
 *
 * @author Le Nhat Tung
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

import java.sql.PreparedStatement;

public class BaseDAO {

    protected boolean executeUpdate(
            String sql,
            SQLConsumer parameterSetter) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(sql);

            parameterSetter.accept(ps);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, ps, null);
        }
    }

    protected <T> ArrayList<T> executeQuery(
            String sql,
            SQLConsumer parameterSetter,
            SQLMapper<T> mapper) {

        ArrayList<T> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(sql);
            parameterSetter.accept(ps);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapper.map(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }

        return list;
    }

    // Helper method to close resources
    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
