import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.util.Date;
import java.sql.Timestamp;

Date date= new Date();

String payload = "{}";
String timestamp = Long.toString(date.getTime());
String secretKey = "YourSecretKey";
String data   = payload+timestamp+"KeyAgain";
String result = "";



Mac mac = Mac.getInstance("HmacSHA512");
SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");
mac.init(secretKeySpec);
byte[] digest = mac.doFinal(data.getBytes("UTF-8"));

result = bytesToHex(digest);

vars.put("hmac",result);
vars.put("timestamp",timestamp);
vars.put("data",data);

public static String bytesToHex(byte[] bytes) {
        final  char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }