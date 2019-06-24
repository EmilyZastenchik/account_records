/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filematch_package_ez;

import java.io.Serializable;

public class TransactionRecord  implements Serializable
{
    public static final long serialVersionUID = 2L;
    private int account;
    private double balance;


    public TransactionRecord()
    {
        this(0, 0.0);   //call other constructor
    }
    public TransactionRecord(int account, double balance)
    {
       this.account = account;
       this.balance = balance;
    }
    public void setAccount(int account)
    {
        this.account = account;
    }
    public int getAccount()
    {
        return account;
    }
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    public double getBalance()
    {
        return balance;
    }
       @Override //used to print out object in array
   public String toString() {
   
        return (this.getAccount()+ "\t" +this.getBalance());
   
   }        
    
}//end of TransactionRecord class