package com.apptech.base.security;

import io.jsonwebtoken.*;
import org.joda.time.DateTime;

import java.util.Calendar;

public class FrameAuthResolver {
    private static final String AUDIENCE = "Xtion";

    private static final String ISSUER = "crm.xtion.net";

    private static final String SIGNING_KEY = "SignKeysSecret";

    public static String createJsonWebToken(String userId, Long durationSeconds) {

        Calendar cal = Calendar.getInstance();
        String compactJws = Jwts.builder()
                .setSubject(userId)
                .setAudience(AUDIENCE)
                .setIssuer(ISSUER)
                .setIssuedAt(new DateTime(cal.getTimeInMillis()).toDate())
                .setExpiration(new DateTime(cal.getTimeInMillis() + 1000L * durationSeconds).toDate())
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();

        return compactJws;
    }

    public static String verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SIGNING_KEY)
                    .requireIssuer(ISSUER)
                    .requireAudience(AUDIENCE)
                    .parseClaimsJws(token);

            //OK, we can trust this JWT
            return claims.getBody().getSubject();

        } catch (SignatureException e) {
            //don't trust the JWT!
        }

        return null;
    }
}
