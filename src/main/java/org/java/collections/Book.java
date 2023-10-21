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

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class Book {
    private String title;
    private String autor;
    private int ISBN;

    public Book(String title, String autor, int ISBN) {
        this.title = title;
        this.autor = autor;
        this.ISBN = ISBN;
    }
    
    public String getData(){
        return "The title is: " + this.title + " author: " + " and ISBN is: " + this.ISBN;
    }
    
    /*public boolean equals(Object obj){
        if(obj instanceof Book){
            Book other = (Book)obj;
            if(this.ISBN == other.ISBN){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }*/
    
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ISBN;
        return result;
    }
    
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if(this.ISBN != other.ISBN)
            return false;
        return true;
    }
    
}
