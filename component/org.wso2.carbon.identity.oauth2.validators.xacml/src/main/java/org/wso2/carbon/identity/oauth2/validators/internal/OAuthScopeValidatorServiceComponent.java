/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.wso2.carbon.identity.oauth2.validators.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.entitlement.EntitlementService;

@Component(
        name = "identity.inbound.auth.scope.validator.xacml",
        immediate = true
)
public class OAuthScopeValidatorServiceComponent {

    private static final Log log = LogFactory.getLog(OAuthScopeValidatorServiceComponent.class);

    @SuppressWarnings("unchecked")
    @Activate
    protected void activate(ComponentContext ctxt) {

        if (log.isDebugEnabled()){
            log.debug("Application XACML based scope validator bundle is activated");
        }
    }

    @Reference(
            name = "identity.entitlement.service",
            service = EntitlementService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetEntitlementService"
    )
    protected void setEntitlementService(EntitlementService entitlementService) {

        if (log.isDebugEnabled()) {
            log.debug("EntitlementService is set in the  XACML based scope validator bundle");
        }
        OAuthScopeValidatorDataHolder.getInstance().setEntitlementService(entitlementService);
    }

    protected void unsetEntitlementService(EntitlementService entitlementService) {

        if (log.isDebugEnabled()) {
            log.debug("EntitlementService is unset in the  XACML based scope validator bundle");
        }
        OAuthScopeValidatorDataHolder.getInstance().setEntitlementService(null);
    }
}
