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

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class ServerFrame extends JFrame implements Runnable {
    private JTextArea txtLabel;
    
    public ServerFrame(){
        setBounds(1200, 300, 280, 350);
        JPanel myFrame = new JPanel();
        myFrame.setLayout(new BorderLayout());
        txtLabel = new JTextArea();
        myFrame.add(txtLabel);
        add(myFrame);
        setVisible(true);
        Thread myThread = new Thread(this);
        myThread.start();
    }

    @Override
    public void run() {
        ServerSocket server;
        try {
            ArrayList<String> ipList = new ArrayList<>();
            server = new ServerSocket(9090);
            String nick;
            String ip;
            String msg;
            PackageSend pkgReceived;
            
            while(true){
                Socket mySocket = server.accept();
                ObjectInputStream dataPkg = new ObjectInputStream(mySocket.getInputStream());
                pkgReceived = (PackageSend)dataPkg.readObject();
                nick = pkgReceived.getNick();
                ip = pkgReceived.getIp();
                msg = pkgReceived.getMsg();
                /*DataInputStream streamInput = new DataInputStream(mySocket.getInputStream());
                String msgText = streamInput.readUTF();
                txtLabel.append("\n"+ msgText);*/
                if(!msg.equals(" online")){
                    txtLabel.append("\n" + nick + ": " + msg + " to " + ip);
                    Socket sendDest = new Socket(ip, 9090);
                    ObjectOutputStream dataSend = new ObjectOutputStream(sendDest.getOutputStream());
                    dataSend.writeObject(pkgReceived);
                    sendDest.close();
                    mySocket.close();
                }else{
                    InetAddress localization = mySocket.getInetAddress();
                    String remoteIp = localization.getHostAddress();
                    System.out.println("Online: " + ip);
                    ipList.add(remoteIp);
                    pkgReceived.setIps(ipList);
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
