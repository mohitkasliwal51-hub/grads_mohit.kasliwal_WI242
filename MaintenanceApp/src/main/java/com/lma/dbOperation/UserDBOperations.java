package com.lma.dbOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lma.controller.LoginController;
import com.lma.dao.UserDAO;
import com.lma.model.User;
import com.lma.util.BufferReader;
import com.lma.util.DBConnection;

public class UserDBOperations implements UserDAO {

    @Override
    public boolean addUser(User user) {
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        return true;
    }

    @Override
    public boolean deleteUser(int userId) {
        return true;
    }

    @Override
    public boolean login() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.println("===== LOGIN SCREEN =====");
            System.out.print("email :");
            String email = BufferReader.br.readLine();
            System.out.print("password :");
            String password = BufferReader.br.readLine();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM users where email = ? and password  = ?");

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");

                if ("ADMIN"
                        .equals(role)) {
                    LoginController.showAdminMenu();

                } else if ("OWNER"
                        .equals(role)) {
                    int ownerId = rs.getInt("user_id");
                    LoginController.showOwnerMenu(ownerId);
                }
            } else {
                System.out.println("Invalid Credentials!!");
                new UserDBOperations().login();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
