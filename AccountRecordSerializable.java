/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filematch_package_ez;

// Serializable Account class for storing records as objects.

import java.io.Serializable;

public class AccountRecordSerializable implements Serializable
{  
   public static final long serialVersionUID = 1L; 
   private int account;
   private String firstName;
   private String lastName;
   private double balance;
   
   // initializes an Account with default values
   public AccountRecordSerializable() 
   {
      this(0, "", "", 0.0); // call other constructor
   } 
  
   // initializes an Account with provided values
   public AccountRecordSerializable(int account, String firstName, 
      String lastName, double balance)
   {
      this.account = account;
      this.firstName = firstName;
      this.lastName = lastName;
      this.balance = balance;
   }

   // set account number   
   public void setAccount(int account)
   {
      this.account = account;
   } 

   // get account number   
   public int getAccount() 
   { 
      return account; 
   } 
   
   // set first name   
   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   } 

   // get first name   
   public String getFirstName() 
   { 
      return firstName; 
   } 
   
   // set last name   
   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   } 

   // get last name   
   public String getLastName() 
   {
      return lastName; 
   } 
   
   // set balance  
   public void setBalance(double balance)
   {
      this.balance = balance;
   } 

   // get balance   
   public double getBalance() 
   { 
      return balance; 
   } 
   //used to update balance from transaction record
   public AccountRecordSerializable CopyUpdate(double bal)
{
    this.balance = this.balance + bal;
    return this;
}
     
   @Override    //used to print out object in array
   public String toString() {
   
        return (this.getAccount()+ "\t"+this.getFirstName()+ "\t"+ 
                 this.getLastName() + "\t" +this.getBalance());
   
   }
} // end class AccountRecordSerializable