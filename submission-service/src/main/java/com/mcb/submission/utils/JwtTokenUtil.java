package com.mcb.submission.utils;


import com.mcb.submission.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Nullable;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class JwtTokenUtil {

    private static final String TOKEN_SECRET = "MBT14DOEPD6UIEDBKEOR00DEOZDJNEXXYZWTZOIEAQBT";
    private static final long TOKEN_EXPIRATION = 18_000_000;

    private JwtTokenUtil() {
    }

    public static String generateToken(String userName, @Nullable final Map<String, Object> additionalClaims) {
        Objects.requireNonNull(userName, "User Name for token generation must not be null");
        JwtBuilder jwtBuilder = Jwts.builder()
                .issuer("E-LAHATRA")
                .subject(userName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET);

        if (!Objects.isNull(additionalClaims)) {
            jwtBuilder.claims(additionalClaims);
        }
        return jwtBuilder.compact();
    }

    public static String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private static Date extractExpirationDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private static boolean isTokenExpired(String token) {
        Date expirationDate = extractExpirationDate(token);
        return expirationDate.before(new Date());
    }

    public static void validateToken(String token, String userName) throws InvalidTokenException {
        if (!userName.equals(extractUserName(token))) {
            throw new InvalidTokenException("Mismatch between token infos and username");
        }

        if (isTokenExpired(token)) {
            throw new InvalidTokenException("The token is expired");
        }
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


}
