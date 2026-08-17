/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductDTO;
import utils.DbUtils;

/**
 *
 * @author Le Nhat Tung
 */
public class ProductDAO extends BaseDAO implements IDAO<ProductDTO, String> {

    // Hàm map dùng chung cho mọi query trả về ProductDTO
    private final SQLMapper<ProductDTO> mapRow = rs -> {
        ProductDTO product = new ProductDTO();

        product.setProductID(rs.getString("productID"));
        product.setProductName(rs.getString("productName"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setQuantity(rs.getInt("quantity"));
        product.setImage(rs.getString("image"));
        product.setStatus(rs.getBoolean("status"));

        Timestamp timestamp = rs.getTimestamp("createdAt");
        if (timestamp != null) {
            product.setCreatedAt(timestamp.toLocalDateTime());
        }

        return product;
    };

    @Override
    public boolean add(ProductDTO product) {

        String sql = "INSERT INTO [Product]"
                + " ([productID], [productName], [description], [price],"
                + " [quantity], [image], [status], [createdAt])"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return executeUpdate(sql, ps -> {
            ps.setString(1, product.getProductID());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getImage());
            ps.setBoolean(7, product.isStatus());
            ps.setTimestamp(
                    8,
                    Timestamp.valueOf(product.getCreatedAt())
            );
        });
    }

    @Override
    public boolean remove(ProductDTO product) {

        String sql = "UPDATE [Product]"
                + " SET [status] = ?"
                + " WHERE [productID] = ?";

        return executeUpdate(sql, ps -> {
            ps.setBoolean(1, false);
            ps.setString(2, product.getProductID());
        });
    }

    @Override
    public boolean update(ProductDTO product) {

        String sql = "UPDATE [Product]"
                + " SET [productName] = ?,"
                + " [description] = ?,"
                + " [price] = ?,"
                + " [quantity] = ?,"
                + " [image] = ?,"
                + " [status] = ?"
                + " WHERE [productID] = ?";

        return executeUpdate(sql, ps -> {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setString(5, product.getImage());
            ps.setBoolean(6, product.isStatus());
            // Dùng productID để xác định sản phẩm cần update
            ps.setString(7, product.getProductID());
        });
    }

    @Override
    public ArrayList<ProductDTO> listAll() {

        String sql = "SELECT [productID], [productName], [description],"
                + " [price], [quantity], [image], [status], [createdAt]"
                + " FROM [Product]";
              //  + " WHERE [status] = ?";

        return executeQuery(sql,
               // ps -> ps.setBoolean(1, true),
                 ps -> {},
                mapRow);
    }

    @Override
    public ProductDTO searchByID(String id) {

        String sql = "SELECT [productID], [productName], [description],"
                + " [price], [quantity], [image], [status], [createdAt]"
                + " FROM [Product]"
                + " WHERE [productID] = ?";
                //+ " AND [status] = ?";

        ArrayList<ProductDTO> result = executeQuery(sql,
                ps -> {
                    ps.setString(1, id);
                   // ps.setBoolean(2, true);
                },
                mapRow);

        return result.isEmpty() ? null : result.get(0);
    }

    public ArrayList<ProductDTO> searchByName(String name) {

        String sql = "SELECT [productID], [productName], [description],"
                + " [price], [quantity], [image], [status], [createdAt]"
                + " FROM [Product]"
                + " WHERE [productName] LIKE ?";
                //+ " AND [status] = ?";

        ArrayList<ProductDTO> result = executeQuery(sql,
                ps -> {
                    ps.setString(1, "%" + name + "%");
                   // ps.setBoolean(2, true);
                },
                mapRow);

        return result;
    }
}
