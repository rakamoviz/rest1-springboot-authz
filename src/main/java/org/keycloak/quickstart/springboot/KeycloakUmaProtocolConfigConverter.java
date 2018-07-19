package org.keycloak.quickstart.springboot;

import org.keycloak.representations.adapters.config.PolicyEnforcerConfig.UserManagedAccessConfig;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author laurent
 * https://github.com/lbroudoux/spring-boot-keycloak-authz/blob/master/src/main/java/com/github/lbroudoux/springbootkeycloakauthz/KeycloakUmaProtocolConfigConverter.java
 */
@Component
@ConfigurationPropertiesBinding
public class KeycloakUmaProtocolConfigConverter implements Converter<String, UserManagedAccessConfig> {

   @Override
   public UserManagedAccessConfig convert(String source) {
      if (source == null){
         return null;
      }
      return new UserManagedAccessConfig();
   }
}