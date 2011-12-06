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
package com.moisespsena.vraptor.resourcetranslator.interceptors;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.MethodInfo;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.ParametersInstantiatorInterceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.moisespsena.vraptor.advancedrequest.RequestMethodInfo;
import com.moisespsena.vraptor.advancedrequest.RequestMethodInfoStatic;

/**
 * 
 * @author Moises P. Sena &lt;moisespsena@gmail.com&gt;
 * @since 1.0 01/06/2011
 * 
 */
@RequestScoped
@Intercepts(after = ParametersInstantiatorInterceptor.class)
public class RequestMethodInfoSetParametersInterceptor implements Interceptor {
	private final MethodInfo methodInfo;
	private final HttpServletRequest servletRequest;

	public RequestMethodInfoSetParametersInterceptor(
			final MethodInfo methodInfo, final HttpServletRequest servletRequest) {
		this.methodInfo = methodInfo;
		this.servletRequest = servletRequest;
	}

	@Override
	public boolean accepts(final ResourceMethod method) {
		if (RequestMethodInfoStatic.isValid(servletRequest)) {
			return true;
		}
		return false;
	}

	@Override
	public void intercept(final InterceptorStack stack,
			final ResourceMethod method, final Object resourceInstance) {
		final RequestMethodInfo requestMethodInfo = RequestMethodInfoStatic
				.extractMethodInfo(servletRequest);

		methodInfo.setParameters(requestMethodInfo.getParameters());

		stack.next(method, resourceInstance);
	}
}
