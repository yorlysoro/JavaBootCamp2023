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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class ExecuteQueries {
    private ConnectionDB myConn;
    private ResultSet result;
    private PreparedStatement sendSections;
    private final String sectionQuery = "SELECT * FROM products WHERE section = ?;";
    private PreparedStatement sendCountries;
    private final String countryQuery = "SELECT * FROM products WHERE country = ?;";
    private PreparedStatement sendAll;
    private final String sendAllQuery = "SELECT * FROM products WHERE section = ? AND country =?;";
    
    public ExecuteQueries(){
        myConn = new ConnectionDB();
    }
    public ResultSet filterDB(String section, String country){
        Connection conect = myConn.getConnection();
        result = null;
        try {
            if(!section.equals("All") && country.equals("All")){
              sendSections = conect.prepareStatement(sectionQuery);
              sendSections.setString(1, section);
              result = sendSections.executeQuery();
            }else if(section.equals("All") && !country.equals("All")){
               sendCountries = conect.prepareStatement(countryQuery);
               sendCountries.setString(1, country);
               result = sendCountries.executeQuery();
            }else {
               sendAll = conect.prepareStatement(sendAllQuery);
               sendAll.setString(1, section);
               sendAll.setString(2, country);
               result = sendAll.executeQuery();
            }
        } catch (SQLException ex) {
                Logger.getLogger(ExecuteQueries.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return result;
    }
}
