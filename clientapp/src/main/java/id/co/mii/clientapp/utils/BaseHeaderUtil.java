package id.co.mii.clientapp.utils;

import java.nio.charset.Charset;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseHeaderUtil {

  // create string value auth
  public static String createBasicToken(String username, String password) {
    String auth = username + ":" + password;
    byte[] encodedAuth = Base64.encodeBase64(
      auth.getBytes(Charset.forName("US-ASCII"))
    );
    return new String(encodedAuth);
  }

  // create method headers
  public static HttpHeaders createHttpHeaders() {
    Authentication auth = SecurityContextHolder
      .getContext()
      .getAuthentication();

    return new HttpHeaders() {
      {
        String authHeader = createBasicToken(
          auth.getPrincipal().toString(),
          auth.getCredentials().toString()
        );
        set("Authorization", "Basic " + authHeader);
      }
    };
  }
}
