/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.keycloak.quickstart.springboot.web;

import javax.servlet.http.HttpServletRequest;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.keycloak.representations.adapters.config.AdapterConfig;

/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
@RestController
public class ApplicationController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AdapterConfig adapterConfig;

    @RequestMapping(value = "/api/resourcea", method = RequestMethod.GET)
    public String handleResourceA() {
        return "Access Granted";
    }

    @RequestMapping(value = "/api/resourceb", method = RequestMethod.GET)
    public String handleResourceB() {
        return "Access Granted";
    }

    @RequestMapping(value = "/api/premium", method = RequestMethod.GET)
    public String handlePremiumResource() {
        return "Access Granted";
    }

    @RequestMapping(value = "/api/belongtojdoe", method = RequestMethod.GET)
    public String handleResourceJdoe() {
        KeycloakSecurityContext ksc = getKeycloakSecurityContext();
        System.out.println("getPolicyEnforcerConfig.getUserManagedAccess : " + adapterConfig.getPolicyEnforcerConfig().getUserManagedAccess());
        System.out.println("KeycloakSecurityContext : " + ksc);
        System.out.println("AuthorizationContext : " + ksc.getAuthorizationContext());
        return "Access Granted";
    }

    @RequestMapping(value = "/api/forcelebrities", method = RequestMethod.GET)
    public String handleCelebrity() {
        return "Access Granted";
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
}
