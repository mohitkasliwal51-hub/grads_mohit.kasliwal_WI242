package com.lma.dbOperation;

import java.lang.Exception;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.lma.dao.UpdateRequestDAO;
import com.lma.util.DBConnection;

public class UpdateRequestDBOperations implements UpdateRequestDAO {

    @Override
    public void raiseRequest(int siteId, String field, String newValue, int ownerId) {
        try {
            Connection con = DBConnection.getConnection();

            // Get old value from sites table

            PreparedStatement oldPs = con.prepareStatement(
                    "SELECT " + field + " FROM sites WHERE site_id = ?"
            );
            oldPs.setInt(1, siteId);

            ResultSet rs = oldPs.executeQuery();
            if (!rs.next()) {
                System.out.println("Site not found");
                return;
            }

            String oldValue = rs.getString(1);

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO update_request "
                            + "(site_id, requested_field, old_value, new_value, status) "
                            + "VALUES (?, ?, ?, ?, 'PENDING'::request_status)"
            );

            ps.setInt(1, siteId);
            ps.setString(2, field);
            ps.setString(3, oldValue);
            if (field.equals("is_open")) {
                boolean newValueBoolean = Boolean.valueOf(newValue);
                ps.setBoolean(4, newValueBoolean);
            } else {
                ps.setString(4, newValue);
            }

            ps.executeUpdate();
            System.out.println("✅ Update request raised successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getPendingRequests() {
        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM update_request WHERE status = 'PENDING'");

            printRS(rs);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void processRequest(int requestId, String decision) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM update_request WHERE request_id = ? ");

            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ Request not found");
                return;
            }

            int siteId = rs.getInt("site_id");
            String field = rs.getString("requested_field");
            String newValue = rs.getString("new_value");

            if ("APPROVE".equalsIgnoreCase(decision)) {

                // Apply update to sites table
                PreparedStatement updateSite;

                if ("is_open".equals(field)) {

                    updateSite = con.prepareStatement(
                            "UPDATE sites SET is_open = ? WHERE site_id = ?"
                    );
                    updateSite.setBoolean(1, Boolean.parseBoolean(newValue));
                    updateSite.setInt(2, siteId);

                } else if ("site_type".equals(field)) {

                    updateSite = con.prepareStatement(
                            "UPDATE sites SET site_type = ?::site_type WHERE site_id = ?"
                    );
                    updateSite.setString(1, newValue);
                    updateSite.setInt(2, siteId);

                } else {

                    updateSite = con.prepareStatement(
                            "UPDATE sites SET " + field + " = ? WHERE site_id = ?"
                    );
                    updateSite.setString(1, newValue);
                    updateSite.setInt(2, siteId);
                }

                updateSite.executeUpdate();


                updateStatus(requestId, "APPROVED");
                System.out.println("✅ Request APPROVED and site updated");

            } else {
                updateStatus(requestId, "REJECTED");
                System.out.println("❌ Request REJECTED");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateStatus(int requestId, String status) throws Exception {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement("         UPDATE update_request SET status = ?::request_status WHERE request_id = ? ");

        ps.setString(1, status);
        ps.setInt(2, requestId);
        ps.executeUpdate();
    }

    private void printRS(ResultSet rs) throws Exception {
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();

        System.out.println("--------------------------------------------------");
        for (int i = 1; i <= cols; i++) {
            System.out.printf("%-18s", rsmd.getColumnName(i));
        }
        System.out.println("\n--------------------------------------------------");

        while (rs.next()) {
            for (int i = 1; i <= cols; i++) {
                System.out.printf("%-18s", rs.getString(i));
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------");
    }
}
