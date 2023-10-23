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
package org.java.jdbc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import org.java.jdbc.model.ExecuteQueries;
import org.java.jdbc.view.FrameApp2;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class ControllerSearch implements ActionListener {
    private FrameApp2 theFrame;
    private ExecuteQueries search = new ExecuteQueries();
    
    public ControllerSearch(FrameApp2 theFrame){
        this.theFrame = theFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String selecSection;
        JComboBox jSelecSection = theFrame.getSections();
        selecSection = jSelecSection.getSelectedItem().toString();
        
        String selectCountry;
        JComboBox jSelectCountry = theFrame.getCountries();
        selectCountry = jSelectCountry.getSelectedItem().toString();
        
        JTextArea txtResult = theFrame.getResults();
        txtResult.append(search.filterDB(selecSection, selectCountry));
        txtResult.append("\n");
        theFrame.setResults(txtResult);
        
    }
    
}
