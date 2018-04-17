/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clicker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Olli K. Kärki
 */
public class SettingsDAO {

    private Database database;

    /**
     *
     * @param database - Database
     */
    public SettingsDAO(Database database) {
        this.database = database;
    }

    /**
     * Returns boolean value; false = not fullscreen, true = fullscreen.
     *
     * @return boolean
     * @throws SQLException
     */
    public boolean getFullscreen() throws SQLException {

        boolean r = false;

        try (Connection conn = database.getConnection()) {

            PreparedStatement stm = conn.prepareStatement("SELECT DISTINCT fullscreen FROM SETTINGS");
            ResultSet rs = stm.executeQuery();

            r = rs.getBoolean("fullscreen");

            rs.close();
            stm.close();

        }

        return r;
    }

    /**
     * Writes fullscreen value on the database.
     *
     * @param value
     * @throws SQLException
     */
    public void setFullscreen(boolean value) throws SQLException {

        try (Connection conn = database.getConnection()) {

            int val = 0;

            if (value == true) {
                val = 1;
            }

            PreparedStatement stm = conn.prepareStatement("UPDATE Settings SET fullscreen = " + val);
            stm.execute();
            stm.close();

        }

    }

}
