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
import javax.swing.JOptionPane;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class ProductTransaction {

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
            String sql1 = "DELETE * FROM products WHERE country = 'Spain';";
            String sql1_2 = "DELETE * FROM products WHERE price > 300;";
            String slq1_3 = "UPDATE products SET price = price*1.15;";
            Boolean execute = executeTransaction();
            if(execute){
                myStatement.executeUpdate(sql1);
                myStatement.executeUpdate(sql1_2);
                myStatement.executeUpdate(slq1_3);
                myConn.commit();
                System.out.println("Execution Sucess!!");
            } else {
                System.out.println("Execution Canceled!!");
            }
        } catch (SQLException ex) {
            try {
                myConn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(InsertTransactions.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(InsertTransactions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static Boolean executeTransaction(){
        String executin = JOptionPane.showInputDialog("Execute Transaction?");
        if(executin.equalsIgnoreCase("Yes"))
            return true;
        else
            return false;
    }
    
}
