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

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class CountriesLinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        LinkedList<String> countries = new LinkedList<>();
        countries.add("España");
        countries.add("Colombia");
        countries.add("Mexico");
        countries.add("Perú");
        
        LinkedList<String> capitals = new LinkedList<>();
        capitals.add("Madrid");
        capitals.add("Bogotá");
        capitals.add("DF");
        capitals.add("Lima");
        
        System.out.println(countries);
        System.out.println(capitals);
        
        ListIterator<String> iterA = countries.listIterator();
        ListIterator<String> iterB = capitals.listIterator();
        
        while(iterB.hasNext()){
            if(iterA.hasNext()){
                iterA.next();
            }
            iterA.add(iterB.next());
        }
        
        System.out.println(countries);
        
        iterB = capitals.listIterator();
        while(iterB.hasNext()){
            iterB.next();
            if(iterB.hasNext()){
                iterB.next();
                iterB.remove();
            }
        }
        
        System.out.println(capitals);
        
        countries.removeAll(capitals);
        System.out.println(countries);
    }
    
}
