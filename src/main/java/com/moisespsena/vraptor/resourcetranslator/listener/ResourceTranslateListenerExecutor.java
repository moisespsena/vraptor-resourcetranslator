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
package com.moisespsena.vraptor.resourcetranslator.listener;

import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.moisespsena.vraptor.advancedrequest.ResourcesResolver;
import com.moisespsena.vraptor.listenerexecution.ExecutionStack;
import com.moisespsena.vraptor.listenerexecution.ExecutionStackException;
import com.moisespsena.vraptor.listenerexecution.ListenerExecutor;

/**
 * @author Moises P. Sena (http://moisespsena.com)
 * @since 1.0 16/09/2011
 */
public class ResourceTranslateListenerExecutor implements
		ListenerExecutor<ResourceTranslateListener> {

	private final RequestInfo requestInfo;
	private ResourceMethod resourceMethod;
	private final ResourcesResolver resourcesResolver;

	public ResourceTranslateListenerExecutor(final RequestInfo requestInfo,
			final ResourcesResolver resourcesResolver) {
		this.requestInfo = requestInfo;
		this.resourcesResolver = resourcesResolver;
	}

	@Override
	public boolean accepts(
			final ResourceTranslateListener resourceTranslateListener) {
		return resourceTranslateListener.accepts(this);
	}

	@Override
	public void execute(final ExecutionStack<ResourceTranslateListener> stack,
			final ResourceTranslateListener instance)
			throws ExecutionStackException {
		resourceMethod = instance.translate(stack, this);

		if (resourceMethod != null) {
			stack.stop();
		}
	}

	public RequestInfo getRequestInfo() {
		return requestInfo;
	}

	public ResourceMethod getResourceMethod() {
		return resourceMethod;
	}

	public ResourcesResolver getResourcesResolver() {
		return resourcesResolver;
	}
}
