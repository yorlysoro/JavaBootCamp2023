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

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yorlysoropeza <yorlysoro@gmail.com>
 */
public class Bank {
    private final double[] accounts;
    private Lock closeBank = new ReentrantLock();
    private Condition sufficientAmount;

    public Bank() {
        this.accounts = new double[100];
        for(int i=0; i < accounts.length; i++){
            this.accounts[i] = 2000;
        }
        //this.sufficientAmount = this.closeBank.newCondition();
    }
    
    public synchronized void transfer(int accountSource, int accountDest, double quantity){
        //closeBank.lock();
        try {
            while(this.accounts[accountSource] < quantity){
                //this.sufficientAmount.await();
                wait();
            }

            System.out.println(Thread.currentThread());

            this.accounts[accountSource] -= quantity;

            System.out.printf("%10.2f from %d to %d", quantity, accountSource, accountDest);

            this.accounts[accountDest] += quantity;

            System.out.printf(" Amount Total: %10.2f%n", getAmountTotal());
            
            //this.sufficientAmount.signalAll();
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //closeBank.unlock();
        }
    }
    
    public double getAmountTotal(){
        double sumAccounts = 0;
        for(double a: this.accounts){
            sumAccounts += a;
        }
        return sumAccounts;
    }
    
}
