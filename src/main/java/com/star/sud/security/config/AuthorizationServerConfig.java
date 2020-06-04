/**
 * 
 */
package com.star.sud.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Sudarshan
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String CLIENT_ID = "sudarshan";
	private static final String CLIENT_SECRET = "password";
	private static final String GRANT_TYPE_PASSWORD = "password";
	private static final String AUTHORIZATION_CODE = "authorization_code";;
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String IMPLICIT = "implicit";
	private static final String SCOPE_READ = "read";
	private static final String SCOPE_WRITE = "write";
	private static final String SCOPE_TRUST = "trust";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder encoder;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("as466gf");
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer configure) throws Exception {
		/*
		 * configure.inMemory().withClient(CLIENT_ID).secret(CLIENT_SECRET)
		 * .authorizedGrantTypes(encoder.encode(GRANT_TYPE_PASSWORD),
		 * AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT) .scopes(READ, WRITE, TRUST);
		 */

		configure.inMemory().withClient(CLIENT_ID).secret(encoder.encode(CLIENT_SECRET))
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
				.scopes(SCOPE_READ, SCOPE_WRITE, SCOPE_TRUST);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		/*
		 * endpoints.tokenStore(tokenStore()).authenticationManager(
		 * authenticationManager) .accessTokenConverter(accessTokenConverter());
		 */
		 endpoints
         /*.pathMapping("/oauth/token", "/users/user/login")*/.tokenStore(tokenStore())
			.authenticationManager(authenticationManager)
			.accessTokenConverter(accessTokenConverter());
	}

}
