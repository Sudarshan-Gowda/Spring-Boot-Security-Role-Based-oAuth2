/**
 * 
 */
package com.star.sud.user.service;

import org.springframework.security.core.Authentication;

/**
 * @author Sudarshan
 *
 */
public interface AuthenticationFacadeService {

	Authentication getAuthentication();

}
