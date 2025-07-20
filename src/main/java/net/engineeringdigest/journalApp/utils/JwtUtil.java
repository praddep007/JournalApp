package net.engineeringdigest.journalApp.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.Base64;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET_KEYS = "FKmSrP1AEQJaXWu/nPyrJcoY3A5Tte7xCDXTI65Ws7o=";

    private SecretKey getSigningKeys() {
        return Keys.hmacShaKeyFor(SECRET_KEYS.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String userName) {
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userName);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //60 min
                .signWith(getSigningKeys())
                .compact();

    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }



    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    public String extractClaims(String token,String claim){
        return extractAllClaims(token).get(claim,String.class);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKeys())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean validateToken(String token, String username){
        final String extractedUsername = extractUsername(username);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

//    public static void main(String[] args) {
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String base64Key = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println("Your Base64 key: " + base64Key);
//
//    }

}
