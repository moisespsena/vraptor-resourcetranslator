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
package com.moisespsena.vraptor.resourcetranslator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.http.UrlToResourceTranslator;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.moisespsena.vraptor.advancedrequest.ResourcesResolver;
import com.moisespsena.vraptor.listenerexecution.ExecutionStackException;
import com.moisespsena.vraptor.resourcetranslator.listener.ResourceTranslateListenerExecutor;
import com.moisespsena.vraptor.resourcetranslator.listener.ResourceTranslateListeners;

/**
 * @author Moises P. Sena (http://moisespsena.com)
 * @since 1.0 29/08/2011
 */
@Component
@ApplicationScoped
public class RTResourceTranslator implements UrlToResourceTranslator {
	private final Logger logger = LoggerFactory
			.getLogger(RTResourceTranslator.class);
	private final ResourcesResolver resourcesResolver;
	private final ResourceTranslateListeners resourceTranslateListeners;

	public RTResourceTranslator(
			final ResourceTranslateListeners resourceTranslateListeners,
			final ResourcesResolver resourcesResolver) {
		this.resourceTranslateListeners = resourceTranslateListeners;
		this.resourcesResolver = resourcesResolver;
	}

	@Override
	public ResourceMethod translate(final RequestInfo requestInfo) {
		final ResourceTranslateListenerExecutor executor = new ResourceTranslateListenerExecutor(
				requestInfo, resourcesResolver);

		try {
			resourceTranslateListeners.createStackExecution(executor).execute();

			final ResourceMethod resourceMethod = executor.getResourceMethod();

			if (resourceMethod == null) {
				throw new RTResourceTranslatorException(
						"No resourceMethod found");
			} else if (logger.isDebugEnabled()) {
				logger.debug("found resource {}", resourceMethod);
			}

			return resourceMethod;
		} catch (final ExecutionStackException e) {
			throw new RTResourceTranslatorException(e);
		}
	}
}