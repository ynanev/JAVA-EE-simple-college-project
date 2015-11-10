

/*

Yavor Nanev,           11060247
*/
package jjb;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MyCheck {
    
   
 private static int num=0;
 private static  int bt =0;
 private static  int sbt =0;
 private static  int ct =0;
    public static int getBToken() {
        return bt;
    }
    public static int getSingleBToken() {
        return sbt;
    }
     public static int getCToken() {
        return ct;
    }
    public static void setNum(int num) {
        MyCheck.num = num;
    }
   
  public static void setBt()
  {
      bt =singleTocken();
  }
  public static void setSbt()
  {
     sbt =singleTocken();
  }
  public static void setCt()
  {
      ct =singleTocken();
  }
 
 public static boolean validateBT(int value)
 {
     return value==bt;
 }
  public static boolean validateSBT(int value)
 {
     return value==sbt;
 }
   public static boolean validateCT(int value)
 {
     return value==ct;
 }
 public static boolean validateRandom(int value)
 {
     return value==num;
 }
 public static int create()
{
  return num= (int) (Math.random()*50000005)+1;
}
 public static int singleTocken()
{
  return (int) (Math.random()*50000005)+1;
}

    public static int getNum() {
        return num;
    }
    
}