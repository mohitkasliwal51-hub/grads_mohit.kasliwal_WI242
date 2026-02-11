package com.lma.dbOperation;

import java.lang.Exception;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.lma.dao.PaymentDAO;
import com.lma.util.DBConnection;

public class PaymentDBOperations implements PaymentDAO {

    private MaintenanceDBOperations maintenanceDB = new MaintenanceDBOperations();

    @Override
    public void makePayment(int siteId, int amount) {
        try {
            Connection con = DBConnection.getConnection();

            // Insert payment
            PreparedStatement ps = con.prepareStatement("INSERT INTO payments(maintenance_id, paid_amount, payment_date)VALUES (?, ?, CURRENT_DATE)");

            ps.setInt(1, siteId);
            ps.setInt(2, amount);
            ps.executeUpdate();

            // Total paid so far
            PreparedStatement paidPS = con.prepareStatement("SELECT COALESCE(SUM(paid_amount),0) AS total_paid FROM payments WHERE maintenance_id = ?");

            paidPS.setInt(1, siteId);
            ResultSet rs = paidPS.executeQuery();
            rs.next();
            getPaymentHistory(siteId);
            int totalPaid = rs.getInt("total_paid");
            System.out.println("totalPaid : "+totalPaid);

            PreparedStatement psMonth = con.prepareStatement("SELECT month FROM maintenance WHERE site_id = ?");
            psMonth.setInt(1,siteId);

            ResultSet rsMonth = psMonth.executeQuery();
            String month ="";
            if (rsMonth.next()){
                month = rsMonth.getString("month");
            }

            int maintenanceAmount= maintenanceDB.getMaintenanceAmount(siteId,month);
            System.out.println("maintenanceAmount: "+maintenanceAmount);

            int pendingAmount = maintenanceAmount - totalPaid;

            if (totalPaid >= maintenanceAmount) {
                maintenanceDB.updateMaintenanceStatus(siteId, month, pendingAmount, "PAID");
                System.out.println("✅ Maintenance PAID fully");
            } else {
                maintenanceDB.updateMaintenanceStatus(siteId, month, pendingAmount,"PARTIAL");
                System.out.println("⚠️ Partial payment received");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean collectPayment(int siteId, double amount){
        return true;
    }

    @Override
    public void getPaymentHistory(int siteId) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM payments WHERE maintenance_id = ? ");

            ps.setInt(1, siteId);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();

            System.out.println("-----------------------------------------");
            for (int i = 1; i <= cols; i++) {
                System.out.printf("%-15s", rsmd.getColumnName(i));
            }
            System.out.println("\n-----------------------------------------");

            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.printf("%-15s", rs.getString(i));
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}
