/***
 * Copyright (c) 2011 Moises P. Sena - www.moisespsena.com
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * 
 */
package com.moisespsena.vraptor.resourcetranslator.listener.listeners;

import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.http.MutableRequest;
import br.com.caelum.vraptor.http.route.MethodNotAllowedException;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.HttpMethod;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.moisespsena.vraptor.listenerexecution.ExecutionStack;
import com.moisespsena.vraptor.resourcetranslator.listener.ResourceTranslateListener;
import com.moisespsena.vraptor.resourcetranslator.listener.ResourceTranslateListenerExecutor;

/**
 * @author Moises P. Sena (http://moisespsena.com)
 * @since 1.0 20/09/2011
 */
@Component
@ApplicationScoped
public class RouterResourceTranslateListener implements
		ResourceTranslateListener {

	private final Router router;

	public RouterResourceTranslateListener(final Router router) {
		this.router = router;
	}

	@Override
	public boolean accepts(final ResourceTranslateListenerExecutor executor) {
		return true;
	}

	@Override
	public ResourceMethod translate(
			final ExecutionStack<ResourceTranslateListener> stack,
			final ResourceTranslateListenerExecutor executor) {
		final RequestInfo info = executor.getRequestInfo();
		final MutableRequest request = info.getRequest();
		final String resourceName = info.getRequestedUri();
		ResourceMethod resourceMethod = null;

		if (resourceMethod == null) {
			HttpMethod method;

			try {
				method = HttpMethod.of(request);
			} catch (final IllegalArgumentException e) {
				throw new MethodNotAllowedException(
						router.allowedMethodsFor(resourceName),
						request.getMethod());
			}
			resourceMethod = router.parse(resourceName, method, request);
		}

		return resourceMethod;
	}

}
