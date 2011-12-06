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

import java.io.Serializable;

import br.com.caelum.vraptor.resource.HttpMethod;

/**
 * 
 * @author Moises P. Sena &lt;moisespsena@gmail.com&gt;
 * @since 1.0 05/08/2011
 * 
 */
public class ResourceMethodRequestImpl implements Serializable {
	private static final long serialVersionUID = 8902789464120437840L;

	private HttpMethod httpMethod;
	private String methodName;
	private Object[] parameters;
	private Class<?>[] parametersTypes;
	private Class<?> type;

	public ResourceMethodRequestImpl() {

	}

	/**
	 * @param httpMethod
	 * @param methodName
	 * @param parameters
	 * @param parametersTypes
	 * @param type
	 */
	public ResourceMethodRequestImpl(final HttpMethod httpMethod,
			final String methodName, final Object[] parameters,
			final Class<?>[] parametersTypes, final Class<?> type) {
		super();
		this.httpMethod = httpMethod;
		this.methodName = methodName;
		this.parameters = parameters;
		this.parametersTypes = parametersTypes;
		this.type = type;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public Class<?>[] getParametersTypes() {
		return parametersTypes;
	}

	/**
	 * @return the type
	 */
	public Class<?> getType() {
		return type;
	}

	public void setHttpMethod(final HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public void setMethodName(final String methodName) {
		this.methodName = methodName;
	}

	public void setParameters(final Object[] parameters) {
		this.parameters = parameters;
	}

	public void setParametersTypes(final Class<?>[] parametersTypes) {
		this.parametersTypes = parametersTypes;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final Class<?> type) {
		this.type = type;
	}

}