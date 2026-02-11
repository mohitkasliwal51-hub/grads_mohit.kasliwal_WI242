package com.lma.dbOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.lma.dao.MaintenanceDAO;
import com.lma.util.DBConnection;

public class MaintenanceDBOperations implements MaintenanceDAO {

    @Override
    public void generateMaintenance(String month) {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM sites");


            while (rs.next()) {
                int rate = Boolean.valueOf(rs.getString("is_open")) ? 6 : 9;
                int sqft = rs.getInt("length") * rs.getInt("width");
                int amount = sqft * rate;
                PreparedStatement ps = con.prepareStatement("INSERT INTO maintenance(site_id, total_amount, month, status)VALUES (?, ?, ?,'PENDING')");

                ps.setInt(1, rs.getInt("site_id"));
                ps.setInt(2, amount);
                ps.setString(3, month);

                ps.executeUpdate();
                System.out.println("✅ Maintenance generated for Site ID: " + rs.getInt("site_id"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getPendingMaintenance() {
        try {
            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM maintenance WHERE status IN ('PENDING', 'PARTIAL')");

            printRS(rs);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getMaintenanceAmount(int siteId, String month) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT total_amount FROM maintenance WHERE site_id = ? AND month = ?");

            ps.setInt(1, siteId);
            ps.setString(2, month);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getInt("total_amount"));
                return rs.getInt("total_amount");
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public void updateMaintenanceStatus(int siteId, String month, int pendingAmount, String status) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("UPDATE maintenance SET status = ?::maintenance_status, pending_amount = ? WHERE site_id = ? AND month = ?");

            ps.setString(1, status);
            ps.setInt(2, pendingAmount);
            ps.setInt(3, siteId);
            ps.setString(4, month);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getMaintenanceByOwner(int ownerId) {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("""
                                        SELECT
                                        m.maintenance_id,
                                        m.total_amount,
                                        m.pending_amount,
                                        m.month,
                                        m.status
                                        FROM maintenance m
                                        JOIN sites s ON m.site_id = s.site_id
                                        WHERE s.owner_id = ?
                    """
            );

            ps.setInt(1, ownerId);
            ResultSet rs = ps.executeQuery();
            printRS(rs);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void printRS(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();

        System.out.println("------------------------------------------------");
        for (int i = 1; i <= cols; i++) {
            System.out.printf("%-15s", rsmd.getColumnName(i));
        }
        System.out.println("\n------------------------------------------------");

        while (rs.next()) {
            for (int i = 1; i <= cols; i++) {
                System.out.printf("%-15s", rs.getString(i));
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------");
    }
}
