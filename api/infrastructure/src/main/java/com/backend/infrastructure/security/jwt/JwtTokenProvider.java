package com.backend.infrastructure.security.jwt;

import com.backend.infrastructure.security.userdetails.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${project.13.jwtExpirationsMs}")
    private long jwtExpiration;

    /**
     * Creates a JWT token for an authenticated user.
     *
     * @return A signed JWT token.
     */
    public String createToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .setSubject(String.valueOf(userDetails.getUser().getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    /**
     * Validates the JWT token.
     *
     * @param token The JWT token to validate.
     * @return True if the token is valid, False otherwise.
     */
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        } catch (SignatureException e) {
            log.error("there is an error with the signature of you token");
        }
        return false;
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public Long getAuthenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Authentication object is null. User is not authenticated.");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getUser().getId();
        } else {
            throw new IllegalStateException("Principal is not a valid user.");
        }
    }
}
