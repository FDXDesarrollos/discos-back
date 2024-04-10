package net.fdxdesarrollos.albums.security.jwt;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/***
 * Esta clase comprueba si existe un token valido 
 * en caso contrario genera una respuesta '401 - Not Authorized'
 */

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		logger.error("Error en el método commnece");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario / Contraseña incorrectos");		
	}
	
}
