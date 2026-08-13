/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Le Nhat Tung
 */
public class UserDAO implements IDAO<UserDTO, String> {

    private static final List<UserDTO> fakeDB = new ArrayList<>();

    static {
        fakeDB.add(new UserDTO("admin", "1", "Admin", "ADM", true));
        fakeDB.add(new UserDTO("u2", "1", "Tran Thi Binh", "USR", true));
        fakeDB.add(new UserDTO("u3", "1", "Le Nhat Tung", "MNG", true));
        fakeDB.add(new UserDTO("u4", "1", "Hoang Van Khoe", "USR", false));
    }

    @Override
    public boolean add(UserDTO t) {
        if (t == null || t.getUserID() == null || t.getUserID().trim().isEmpty()) {
            return false;
        }

        // Kiểm tra trùng userID
        for (UserDTO u : fakeDB) {
            if (u.getUserID().equalsIgnoreCase(t.getUserID())) {
                return false; // Da ton tai
            }
        }
        fakeDB.add(t);
        return true;
    }

    @Override
    public boolean remove(UserDTO t) {
        if (t == null || t.getUserID() == null) {
            return false;
        }

        return fakeDB.removeIf(u -> u.getUserID().equalsIgnoreCase(t.getUserID()));
    }

    @Override
    public boolean update(UserDTO t) {
        if (t == null || t.getUserID() == null || t.getUserID().trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < fakeDB.size(); i++) {
            if (fakeDB.get(i).getUserID().equalsIgnoreCase(t.getUserID())) {
                fakeDB.set(i, t); // Thay the thong tin moi
                return true;
            }
        }
        return false; // Khong tim thay
    }

    @Override
    public ArrayList<UserDTO> listAll() {
         return new ArrayList<>(fakeDB);
    }

    @Override
    public UserDTO searchByID(String id) {
       if (id == null || id.trim().isEmpty()) return null;
        for (UserDTO u : fakeDB) {
            if (u.getUserID().equals(id)) {
                return u;
            }
        }
        return null;
    }

}
