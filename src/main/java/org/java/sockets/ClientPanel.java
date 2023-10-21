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
package org.java.sockets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class ClientPanel extends JPanel {
    private JTextField field;
    private JTextField nick;
    private JTextField ip;
    private JTextArea txtArea;
    private JButton myButton;
    
    private class SendText implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println(field.getText());
            try {
                Socket mySocket = new Socket("127.0.0.1", 9090);
                PackageSend data = new PackageSend();
                data.setNick(nick.getText());
                data.setIp(ip.getText());
                data.setMsg(field.getText());
                ObjectOutputStream dataPkg = new ObjectOutputStream(mySocket.getOutputStream());
                dataPkg.writeObject(data);
                mySocket.close();
                /*DataOutputStream streamOuput = new DataOutputStream(mySocket.getOutputStream());
                streamOuput.writeUTF(field.getText());
                streamOuput.close();*/
            } catch (IOException ex) {
                Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    public ClientPanel(){
        nick = new JTextField(5);
        add(nick);
        JLabel txtLabel = new JLabel("-CHAT-");
        add(txtLabel);
        ip = new JTextField(8);
        add(ip);
        txtArea = new JTextArea(12,20);
        add(txtArea);
        field = new JTextField(20);
        add(field);
        myButton = new JButton("Send");
        SendText myEvent = new SendText();
        myButton.addActionListener(myEvent);
        add(myButton);
    }
}
