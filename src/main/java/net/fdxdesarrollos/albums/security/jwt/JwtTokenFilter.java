package net.fdxdesarrollos.albums.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.fdxdesarrollos.albums.security.service.UserDetailServiceImp;

/***
 * Esta clase se va a ejecutar por cada petición 
 * para verificar que sea valido el token mediante el provider
 * para permitir el acceso al recurso y en caso contrario lanzara la excepción 
 */

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserDetailServiceImp userDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			String token = getToken( request );
			
			if(token != null && jwtProvider.validateToken(token)) {
				String usuario = jwtProvider.getNombreUsuarioFromToken(token);
				UserDetails userDetails = userDetailService.loadUserByUsername(usuario);
				                                            
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			logger.error("Error en el metodo 'doFilter' + " + e.getMessage());
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer"))
			return header.replace("Bearer", "");
		return null;
	}

}
