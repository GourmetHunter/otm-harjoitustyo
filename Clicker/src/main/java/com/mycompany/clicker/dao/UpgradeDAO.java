/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clicker.dao;

import com.mycompany.clicker.assets.Assets;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Olli K. Kärki
 */
public class UpgradeDAO {

    private Database database;

    /**
     *
     * @param database Database
     */
    public UpgradeDAO(Database database) {
        this.database = database;
    }

    /**
     * Checks if data for upgrades exist in database. Returns true if the data
     * exists, else returns false.
     *
     * @return boolean
     * @throws SQLException correcly initialize database in commons.
     */
    public boolean upgradesExist() throws SQLException {

        boolean r = false;

        try (Connection conn = database.getConnection()) {

            PreparedStatement stm = conn.prepareStatement("SELECT Count(*) AS count FROM UPGRADE");
            ResultSet rs = stm.executeQuery();

            int exists = rs.getInt("count");

            if (exists > 0) {
                r = true;
            }

            rs.close();
            stm.close();

        }

        return r;

    }

    /**
     * Initializes upgrades table in the database. Shoul be called only if
     * saveExists returns false.
     *
     * @throws SQLException correcly initialize database in commons.
     */
    public void initializeUpgrades() throws SQLException {
        database.executeStatement("INSERT INTO upgrade (key, level) VALUES (0, 0)");
        database.executeStatement("INSERT INTO upgrade (key, level) VALUES (1, 0)");
    }

    /**
     * Loads levels for upgrades of the normal shop. Should be called after
     * initialization of Assets class.
     *
     * @throws SQLException correcly initialize database in commons.
     */
    public void loadUpgrades() throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM upgrade");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int key = rs.getInt("key");
                BigInteger level = new BigInteger(rs.getInt("level") + "");
                Assets.upgrades.get(key).setLevel(level);
            }
            rs.close();
            stm.close();
        }
    }

    public void deleteAll() throws SQLException {
        database.executeStatement("DELETE FROM upgrade");
    }

    /**
     * Updates level of specified upgrade from the shop.
     *
     * @param key int
     * @param level int
     * @throws SQLException correcly initialize database in commons.
     */
    public void updateUpgrade(int key, int level) throws SQLException {
        database.executeStatement("UPDATE upgrade set level = " + level + " WHERE key = " + key);
    }

}
