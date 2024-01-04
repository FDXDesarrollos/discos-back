package net.fdxdesarrollos.albums.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.*;
import io.jsonwebtoken.UnsupportedJwtException;
import net.fdxdesarrollos.albums.security.entity.Privilegio;

/***
 * Esta clase genera el token mediante un 
 * metodo de validación que nos indique no esta expirado etc.
 */

@Component
public class JwtProvider {
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	public String generateToken(Authentication authentication) {
		Privilegio permiso = (Privilegio) authentication.getPrincipal();
		return Jwts.builder().setSubject(permiso.getUsername())
				   .setIssuedAt(new Date())
				   .setExpiration(new Date(new Date().getTime() + expiration * 1000))
				   .signWith(SignatureAlgorithm.HS512, secret)
				   .compact();
	}
	
	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		}catch(MalformedJwtException e) {
			logger.error("Token mal formado");
		
		}catch(UnsupportedJwtException e) {
			logger.error("Token no Soportado");
		
		}catch(ExpiredJwtException e) {
			logger.error("Token expirado");
			
		}catch(IllegalArgumentException e) {
			logger.error("Token vacio");
		
		}catch(SignatureException e) {
			logger.error("Fallo en la firma");
			
		}

		return false;
	}
}
