/*
 * Copyright (c) 2023, yorlysoropeza <yorlysoro@gmail.com>
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
package org.java.jdbc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class DatabaseInformation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        showInfoBD();
        showInfoTables();
        
    }
    
    static void showInfoBD(){
        String url = "jdbc:mariadb://localhost:3306/test?user=yorlys&password=yorlys";
        Connection myConn = null;
        try {
            myConn = DriverManager.getConnection(url);
            DatabaseMetaData metadata = myConn.getMetaData();
            System.out.println("Database Manager: " +metadata.getDatabaseProductName());
            System.out.println("Database Version: " + metadata.getDatabaseProductVersion());
            System.out.println("Driver Name: " + metadata.getDriverName());
            System.out.println("Driver Version: " + metadata.getDriverVersion());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInformation.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                myConn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseInformation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    static void showInfoTables(){
        String url = "jdbc:mariadb://localhost:3306/test?user=yorlys&password=yorlys";
        Connection myConn = null;
        ResultSet result = null;
        try {
            myConn = DriverManager.getConnection(url);
            DatabaseMetaData metadata = myConn.getMetaData();
            System.out.println("Tables Info:");
            result = metadata.getTables(null, null, null, null);
            while(result.next()){
                System.out.println(result.getString("TABLE_NAME"));
            }
            System.out.println();
            System.out.println("Fields of Products:");
            result = metadata.getColumns(null, null, "products", null);
            while(result.next()){
                System.out.println(result.getString("COLUMN_NAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInformation.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                myConn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseInformation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
