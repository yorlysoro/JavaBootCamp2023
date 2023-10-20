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
package org.java.threads;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class FrameBounce extends JFrame {
    private FrameBall frame;
    private Thread t;
    private Thread t2;
    private Thread t3;
    private JButton run1;
    private JButton run2;
    private JButton run3;
    private JButton stop1;
    private JButton stop2;
    private JButton stop3;
    
    
    public FrameBounce(){
        setBounds(600,300,600,350);
        setTitle("Boucings");
        frame = new FrameBall();
        add(frame, BorderLayout.CENTER);
        JPanel frameButtons = new JPanel();
        /*putButton(frameButtons, "Run!", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                startGame();
            }
        });
        putButton(frameButtons, "Exit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        putButton(frameButtons, "Stop", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               stopThread();
            }
        });*/
        run1 = new JButton("Thread1");
        run1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                startGame(ae);
            }
            
        });
        frameButtons.add(run1);
        run2 = new JButton("Thread2");
        run2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                startGame(ae);
            }
            
        });
        frameButtons.add(run2);
        run3 = new JButton("Thread3");
        run3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                startGame(ae);
            }
            
        });
        frameButtons.add(run3);
        stop1 = new JButton("Stop1");
        stop1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                stopThread(ae);
            }
            
        });
        frameButtons.add(stop1);
        stop2 = new JButton("Stop2");
        stop2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                stopThread(ae);
            }
            
        });
        frameButtons.add(stop2);
        stop3 = new JButton("Stop3");
        stop3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                stopThread(ae);
            }
            
        });
        frameButtons.add(stop3);
        add(frameButtons, BorderLayout.SOUTH);
    }
    
    /*public void putButton(Container c, String title, ActionListener listener){
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }*/
    
    public void startGame(ActionEvent ae){
        Ball ball = new Ball();
        frame.add(ball);
        Runnable r = new BallThreads(ball, frame);
        if(ae.getSource().equals(run1)){
            t = new Thread(r);
            t.start();
        } else if(ae.getSource().equals(run2)){
            t2 = new Thread(r);
            t2.start();
        } else if(ae.getSource().equals(run3)){
            t3 = new Thread(r);
            t3.start();
        }
    }
    
    public void stopThread(ActionEvent ae){
        //t.stop();
        if(ae.getSource().equals(stop1)){
            t.interrupt();
        }else if(ae.getSource().equals(stop2)){
            t2.interrupt();
        }else if(ae.getSource().equals(stop3)){
            t3.interrupt();
        }
    }
}
