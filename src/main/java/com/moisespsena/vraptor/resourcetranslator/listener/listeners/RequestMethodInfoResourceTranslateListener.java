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

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.http.MutableRequest;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.DefaultResourceClass;
import br.com.caelum.vraptor.resource.DefaultResourceMethod;
import br.com.caelum.vraptor.resource.ResourceClass;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.moisespsena.vraptor.advancedrequest.RequestMethodInfo;
import com.moisespsena.vraptor.advancedrequest.RequestMethodInfoStatic;
import com.moisespsena.vraptor.listenerexecution.ExecutionStack;
import com.moisespsena.vraptor.listenerexecution.topological.ListenerOrder;
import com.moisespsena.vraptor.resourcetranslator.listener.ResourceTranslate;
import com.moisespsena.vraptor.resourcetranslator.listener.ResourceTranslateListener;
import com.moisespsena.vraptor.resourcetranslator.listener.ResourceTranslateListenerExecutor;

/**
 * @author Moises P. Sena (http://moisespsena.com)
 * @since 1.0 20/09/2011
 */
@Component
@ApplicationScoped
@ResourceTranslate
@ListenerOrder
public class RequestMethodInfoResourceTranslateListener implements
		ResourceTranslateListener {

	@Override
	public boolean accepts(final ResourceTranslateListenerExecutor executor) {
		final HttpServletRequest servletRequest = executor.getRequestInfo()
				.getRequest();
		return RequestMethodInfoStatic.isValid(servletRequest);
	}

	@Override
	public ResourceMethod translate(
			final ExecutionStack<ResourceTranslateListener> stack,
			final ResourceTranslateListenerExecutor executor) {
		final RequestInfo info = executor.getRequestInfo();
		final MutableRequest request = info.getRequest();

		final RequestMethodInfo requestMethodInfo = RequestMethodInfoStatic
				.extractMethodInfo(request);

		final ResourceClass resourceClass = new DefaultResourceClass(
				requestMethodInfo.getResourceClass());
		final ResourceMethod resourceMethod = new DefaultResourceMethod(
				resourceClass, requestMethodInfo.getMethod());

		return resourceMethod;
	}
}
