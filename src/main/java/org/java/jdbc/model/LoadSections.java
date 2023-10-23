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
package org.java.jdbc.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.java.jdbc.app.FrameApp;
import org.java.jdbc.controller.ConnectionDB;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class LoadSections {
    private ConnectionDB myConn;
    public ResultSet result;
    
    public LoadSections(){
        myConn = new ConnectionDB();
    }
    
    public String executeQueries(){
        Products myProduct = null;
        Connection accesDB = myConn.getConnection();
        try {
            Statement myStatement = accesDB.createStatement();
            String querSql = "SELECT DISTINCTROW section FROM products;";
            result = myStatement.executeQuery(querSql);
            while(result.next()){
                myProduct = new Products();
                myProduct.setSection(result.getString("section"));
                return myProduct.getSection();
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrameApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myProduct.getSection();
    }
}
