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
package org.java.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class Collections {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Client cl1 = new Client("Antonio Banderas", "00001", 200000);
        Client cl2 = new Client("Rafael Nadal", "00002", 250000);
        Client cl3 = new Client("Penelope Cruz", "00003", 300000);
        Client cl4 = new Client("Julio Iglesias", "00004", 50000);
        Client cl5 = new Client("Julio Iglesias", "00004", 50000);
        
        Set<Client> clientsBank = new HashSet<Client>();
        clientsBank.add(cl1);
        clientsBank.add(cl2);
        clientsBank.add(cl3);
        clientsBank.add(cl4);
        clientsBank.add(cl5);
        
        Iterator<Client> it = clientsBank.iterator();
        while(it.hasNext()){
            Client c = it.next();
            if(c.getClient().equals("Julio Iglesias")){
                it.remove();
            }  
        }
        
        for(Client c: clientsBank){
            System.out.println(c.getClient() + " " +
                c.getAmount() + " " + c.getAmount());
        }
        
    }
    
}
