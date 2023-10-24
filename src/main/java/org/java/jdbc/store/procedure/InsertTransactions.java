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
package org.java.jdbc.store.procedure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class InsertTransactions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String url = "jdbc:mariadb://localhost:3306/test?user=yorlys&password=yorlys";
        Connection myConn = null;
        try {
            myConn = DriverManager.getConnection(url);
            Statement myStatement = myConn.createStatement();
            myConn.setAutoCommit(false);
            String sql1 = "INSERT INTO clients(name, code, company, address, poblation, phone, seller )"
                    + " values('Yorlys', 'C02', 'INGEINT', 'Carora Torres Lara', 'Carora', '04245544884', 'Juan')";
            myStatement.executeUpdate(sql1);
            String sql1_2 = "INSERT INTO orders(client,date,payment_type,discount,send)"
                    + " values(1, '2023-10-23', 'Counting', 0.23, 1)";
            myStatement.executeUpdate(sql1_2);
            myConn.commit();
        } catch (SQLException ex) {
            try {
                myConn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(InsertTransactions.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(InsertTransactions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
