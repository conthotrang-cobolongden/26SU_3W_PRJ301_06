/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daoutils;

/**
 *
 * @author Le Nhat Tung
 */

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface SQLMapper<T> {

    T map(ResultSet rs) throws SQLException;
}
