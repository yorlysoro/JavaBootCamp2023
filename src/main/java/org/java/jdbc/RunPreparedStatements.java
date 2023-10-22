/*
 * Copyright (c) 2023, yorly
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yorly
 */
public class RunPreparedStatements {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         String url = "jdbc:mariadb://localhost:3306/test?user=yorlys&password=yorlys";
        try {
            Connection myConn = DriverManager.getConnection(url);
            String querySql = "SELECT * FROM products"
                    + " WHERE section=? AND country=?;";
            PreparedStatement myStatement = myConn.prepareStatement(querySql);
            myStatement.setString(1, "Sport");
            myStatement.setString(2, "Spain");
            ResultSet result = myStatement.executeQuery();
            while(result.next()){
                System.out.println("ID: " + result.getString("id") + " Name: " +
                        result.getString("name") + " Code: " + result.getString("code") +
                        " Price: " + result.getString("price") + " Date: " + result.getString("date")
                + " Country: " + result.getString("country") + 
                        " Setcion: " + result.getString("section"));
            }
            myStatement.setString(1, "Sport");
            myStatement.setString(2, "Venezuela");
            result = myStatement.executeQuery();
            while(result.next()){
                System.out.println("ID: " + result.getString("id") + " Name: " +
                        result.getString("name") + " Code: " + result.getString("code") +
                        " Price: " + result.getString("price") + " Date: " + result.getString("date")
                + " Country: " + result.getString("country") + 
                        " Setcion: " + result.getString("section"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
