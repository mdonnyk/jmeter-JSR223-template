/* 

Genereate signature for TIBCO Mashery APIGW

*/

import java.util.Date;
import java.sql.Timestamp;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

Date date= new Date();
long unixTime = System.currentTimeMillis() / 1000L;


// Set API Key here
String apikey = "YourAPIKEY";

String timestamp = Long.toString(unixTime);

// Set Secret here
String secretKey = "YourSecret";
String data   = apikey+secretKey+timestamp;
String result = "";

result = getMd5(data);

vars.put("signature",result);
vars.put("timestamp",timestamp);
vars.put("data",data);

public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        }
    }