package com.lma.dbOperation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.lma.dao.SiteDAO;
import com.lma.model.Site;
import com.lma.model.SiteType;
import com.lma.util.BufferReader;
import com.lma.util.DBConnection;

public class SiteDBOperations implements SiteDAO {

    @Override
    public boolean addSite(Site site) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO sites VALUES (?,?::site_type, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, site.getSiteId());
            ps.setString(2, site.getSiteType().name());
            ps.setInt(3, site.getLength());
            ps.setInt(4, site.getWidth());
            ps.setBoolean(5, site.isOpen());

            if (site.getOwnerId() == 0) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, site.getOwnerId());
            }

            ps.executeUpdate();
            System.out.println("Site added successfully");
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateSite(Site site) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE sites SET site_type = ?, length = ?, width = ?, is_open = ?,owner_id = ? WHERE site_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, site.getSiteType().name());
            ps.setInt(2, site.getLength());
            ps.setInt(3, site.getWidth());
            ps.setBoolean(4, site.isOpen());
            ps.setInt(5, site.getOwnerId());
            ps.setInt(6, site.getSiteId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteSite(int siteId) {
        try {
            Connection con = DBConnection.getConnection();
            getSiteById(siteId);

            System.out.print("Confirm is this the site you want to delete yes/no : ");
            String confirm = BufferReader.br.readLine();

            if ("yes".equals(confirm)) {
                PreparedStatement ps
                        = con.prepareStatement("DELETE FROM sites WHERE site_id = ?");
                ps.setInt(1, siteId);

                ps.executeUpdate();
                System.out.println("Site with " + siteId + " deleted Successfully!!");
            }
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Site getSiteById(int siteId) {
        Site site = null;

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement("SELECT * FROM sites WHERE site_id = ?");
            ps.setInt(1, siteId);

            ResultSet rs = ps.executeQuery();
            printRS(rs);
            if (rs.next()) {
                site = mapResultSetToSite(rs);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return site;
    }

    @Override
    public List<Site> getAllSites() {
        List<Site> sites = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM sites");

            printRS(rs);

            while (rs.next()) {
                sites.add(mapResultSetToSite(rs));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sites;
    }

    @Override
    public List<Site> getSitesByOwner(int ownerId) {
        List<Site> sites = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement("SELECT * FROM sites WHERE owner_id = ?");
            ps.setInt(1, ownerId);

            ResultSet rs = ps.executeQuery();
            printRS(rs);

            while (rs.next()) {
                sites.add(mapResultSetToSite(rs));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sites;
    }

    // 🔹 Helper method (VERY IMPORTANT)
    private Site mapResultSetToSite(ResultSet rs) throws SQLException {
        Site site = new Site();
        site.setSiteId(rs.getInt("site_id"));
        site.setSiteType(SiteType.valueOf(rs.getString("site_type")));
        site.setLength(rs.getInt("length"));
        site.setWidth(rs.getInt("width"));
        site.setOpen(rs.getBoolean("is_open"));
        site.setOwnerId(rs.getInt("owner_id"));
        return site;
    }

//    public Site getSiteByOwnerId(int ownerId){
//        Site site = new Site();
//        return site;
//    }

    // printing the values in the terminal
    private void printRS(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();

        // Print header
        System.out.println("---------------------------------------------------------------------------------");
        for (int i = 1; i <= columns; i++) {
            System.out.printf("%-13s", rsmd.getColumnName(i));
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------");

        // Print rows
        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                System.out.printf("%-13s", rs.getString(i));
            }
            System.out.println();
        }

        System.out.println("---------------------------------------------------------------------------------");
    }

}
