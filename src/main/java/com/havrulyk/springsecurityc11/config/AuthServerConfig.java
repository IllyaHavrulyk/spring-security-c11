package com.havrulyk.springsecurityc11.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  /*
    grant types.
   * authorization_code / pkce
   * password ----> deprecated
   * client_credentials
   * refresh_token
   * implicit ----> deprecated
   * */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient("password_client")
        .secret("12345")
        .scopes("read")
        .authorizedGrantTypes("password")
        .and()
        .withClient("authorization_code_client")
        .secret("12345")
        .scopes("read")
        .authorizedGrantTypes("authorization_code")
        .and()
        .withClient("credentials_client")
        .secret("12345")
        .scopes("read")
        .authorizedGrantTypes("client_credentials")
        .redirectUris("http://localhost:404");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
  }
}
