package com.tetrasoft.rolebasedoauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	private static final String CLIEN_ID = "devglan-client";
	private static final String CLIENT_SECRET ="$2a$04$1VGGg98BkCSvSLs4RDSyUu8MrYf0jkY3dgCLAy8GHJe6QA4VAM/X2";
	private static final String GRANT_TYPE_PASSWORD = "password";
	private static final String AUTHORIZATION_CODE = "authorization_code";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String IMPLICIT = "implicit";
	private static final String SCOPE_READ = "read";
	private static final String SCOPE_WRITE = "write";
	private static final String TRUST = "trust";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {		
		System.out.println("AuthorizationServerConfig = 33");
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("as466gf");
		System.out.println("Token==============="+converter);
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		System.out.println("AuthorizationServerConfig = 41");
		return new JwtTokenStore(accessTokenConverter());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		System.out.println("AuthorizationServerConfig = 48");
		configurer
				.inMemory()
				.withClient(CLIEN_ID)
				.secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		System.out.println("AuthorizationServerConfig = 59");
        endpoints
                .pathMapping("/oauth/token", "/users/user/login").tokenStore(tokenStore())
				.authenticationManager(authenticationManager)
				.accessTokenConverter(accessTokenConverter());
        
	}


}