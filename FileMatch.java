/*
 *Emily Zastenchik
 */
package filematch_package_ez;
import java.util.Arrays;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

/**

 */
public class FileMatch {

    public static void main(String[] args) throws IOException, ClassNotFoundException   {
        FileMatch newmatch = new FileMatch();   //creates instance of the FileMatch class
        newmatch.CreateData();
        newmatch.ReadData();
        //newmatch.Update();
 
  }//end MAIN class
 private static ObjectOutputStream outOldMaster, outTransaction;

   public void CreateData() throws FileNotFoundException, IOException
   {/*
       writes object for AccountRecordSerializable and TransactionRecord data to file
      using ObjectOutputStream and FileOutputStream
       Serializes data 
       object -> file
       */
      try
      {
          // file streams for output files
          outOldMaster = new ObjectOutputStream(
                  new FileOutputStream("oldmast.ser"));
          outTransaction = new ObjectOutputStream(
                  new FileOutputStream("trans.ser")); 
      
         outOldMaster.writeObject(new AccountRecordSerializable(
                 100, "Alan", "Jones", 348.17));
         outOldMaster.writeObject(new AccountRecordSerializable(
                 300, "Mary", "Smith", 27.19));
         outOldMaster.writeObject(new AccountRecordSerializable(
                 500, "Sam", "Sharp", 0.00));
         outOldMaster.writeObject(new AccountRecordSerializable(
                 700, "Suzy", "Green", -14.22));
         outTransaction.writeObject(
                 new TransactionRecord(100, 27.14));
         outTransaction.writeObject(
                 new TransactionRecord(300, 62.11));
         outTransaction.writeObject(
                 new TransactionRecord(300, -10.00));
         outTransaction.writeObject(
                 new TransactionRecord(400, 100.56));
         outTransaction.writeObject(
                 new TransactionRecord(900, 82.17)); 
      }
      finally // close the files
      {
          if (outTransaction != null)
              outTransaction.close();
          if (outOldMaster != null)
              outOldMaster.close(); 
      } 
   } //end of CreateData method

public void ReadData() throws IOException, ClassNotFoundException
{/*
    Reads file and creates objects for AccountRecordSerializable and TransactionRecord data 
      using ObjectInputStream and FileInputStream
       Deserializes data 
        file-> object
   
    */
 try
 { 
AccountRecordSerializable[] oldfiles = new AccountRecordSerializable[1];    //creates temp array to hold pointer to object 
         ObjectInputStream oldMasterFile = new ObjectInputStream(new FileInputStream("oldmast.ser"));
        
    for(int i = 0; i < oldfiles.length; ++i)
        { while(true)
            {
             AccountRecordSerializable a =(AccountRecordSerializable)oldMasterFile.readObject();
                oldfiles[i] = a;  
                System.out.println(Arrays.toString(oldfiles));  //print object using toString method
             } 
        }  
 }  catch(EOFException e)
      {
           System.out.printf("%nEnd of File%n");
      }
    catch(IOException | ClassNotFoundException e)
   {
         System.err.println("Invalid object type. Terminating.");
          System.err.println("Error reading from file. Terminating.");
   }
try{
    TransactionRecord[] transfer = new TransactionRecord [1];  //creates temp array to hold pointer to object 
        ObjectInputStream transaction = new ObjectInputStream(new FileInputStream("trans.ser"));
        
    for(int i = 0; i < transfer.length; ++i) 
        { 
            while(true)
            {      
                TransactionRecord a =(TransactionRecord)transaction.readObject();
                transfer[i] = a;  
               System.out.println(Arrays.toString(transfer));//print object using toString method
            } 
        } 
}catch(EOFException e)
{
      System.out.printf("%nEnd of File%n");
}

  }//end ReadData method
    ObjectOutputStream newMaster;
    private static PrintStream logFile;//writes output to file(file automatically created)
    private static TransactionRecord tran;//instance of TransactionRecord
    private static AccountRecordSerializable acct;//instance of AccountRecordSerializable
public void Update() throws FileNotFoundException, IOException, ClassNotFoundException 
{  
  /*
    Reads objects and compares them
    */
   ObjectInputStream transaction = new ObjectInputStream(new FileInputStream("trans.ser"));
   ObjectInputStream oldMasterFile = new ObjectInputStream(new FileInputStream("oldmast.ser"));
    
   
   try
   { 
   int oma = acct.getAccount(); //oldmaster account number
   int tn = tran.getAccount(); //transaction account number
 while(true)
 { 
  
      tran =(TransactionRecord)transaction.readObject();    //reads a TransactionRecord object
       acct =(AccountRecordSerializable)oldMasterFile.readObject(); //reads a AccountRecordSerializable 
       //CASE 1: both account numbers match
     if(acct.getAccount() == tran.getAccount())
     {
         AccountRecordSerializable updateacct = acct;   //creates new instance of AccountRecord
         while(oma == tn)   //used for multiple trnsactions for same account number
         {
           acct = updateacct.CopyUpdate(tran.getBalance());  //uses CopyUpdate method from AccountRecord class to update balance      
           newMaster.writeObject(updateacct); //adds AccountRecord with new balance to newMaster file
//need to read in next AccountRecord object here to exit while loop
         }  
     } 
     //CASE 2: no transaction for account number
     while(oma < tn)
     {
         newMaster.writeObject(acct);   //writes account to newMaster file
         oma = acct.getAccount();   //read in next account number to compare
     }
    //CASE 3: no account for transaction
         while(tn < oma)
         {
             logFile.println("Unmatched transaction record for account number ..."+ oma);//prints account number to logFile
         }
 }
   }catch(EOFException e)
   {
       System.out.println("End of file");
   }
}//end of Update method


}//end of FileMatch class










