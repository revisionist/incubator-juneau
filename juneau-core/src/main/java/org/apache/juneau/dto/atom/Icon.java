// ***************************************************************************************************************************
// * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.  See the NOTICE file *
// * distributed with this work for additional information regarding copyright ownership.  The ASF licenses this file        *
// * to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance            *
// * with the License.  You may obtain a copy of the License at                                                              *
// *                                                                                                                         *
// *  http://www.apache.org/licenses/LICENSE-2.0                                                                             *
// *                                                                                                                         *
// * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an  *
// * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the        *
// * specific language governing permissions and limitations under the License.                                              *
// ***************************************************************************************************************************
package org.apache.juneau.dto.atom;

import static org.apache.juneau.dto.atom.Utils.*;
import static org.apache.juneau.xml.annotation.XmlFormat.*;

import java.net.URI;

import org.apache.juneau.annotation.*;
import org.apache.juneau.xml.annotation.*;

/**
 * Represents an <code>atomIcon</code> construct in the RFC4287 specification.
 * <p>
 * <h6 class='figure'>Schema</h6>
 * <p class='bcode'>
 * 	atomIcon = element atom:icon {
 * 		atomCommonAttributes,
 * 		(atomUri)
 * 	}
 * </p>
 * <p>
 * Refer to <a class='doclink' href='package-summary.html#TOC'>org.apache.juneau.dto.atom</a> for further information about ATOM support.
 */
@Bean(typeName="icon")
@SuppressWarnings("hiding")
public class Icon extends Common {

	private URI uri;


	/**
	 * Normal constructor.
	 *
	 * @param uri The URI of the icon.
	 */
	public Icon(URI uri) {
		uri(uri);
	}

	/**
	 * Normal constructor.
	 *
	 * @param uri The URI of the icon.
	 */
	public Icon(String uri) {
		uri(uri);
	}

	/** Bean constructor. */
	public Icon() {}


	//--------------------------------------------------------------------------------
	// Bean properties
	//--------------------------------------------------------------------------------

	/**
	 * Returns the URI of this icon.
	 *
	 * @return The URI of this icon.
	 */
	@Xml(format=ELEMENTS)
	public URI getUri() {
		return uri;
	}

	/**
	 * Sets the URI of this icon.
	 *
	 * @param uri The URI of this icon.
	 * @return This object (for method chaining).
	 */
	@BeanProperty("uri")
	public Icon uri(URI uri) {
		this.uri = uri;
		return this;
	}

	/**
	 * Sets the URI of this icon.
	 *
	 * @param uri The URI of this icon.
	 * @return This object (for method chaining).
	 */
	@BeanProperty("uri")
	public Icon uri(String uri) {
		this.uri = toURI(uri);
		return this;
	}


	//--------------------------------------------------------------------------------
	// Overridden setters (to simplify method chaining)
	//--------------------------------------------------------------------------------

	@Override /* Common */
	public Icon base(URI base) {
		super.base(base);
		return this;
	}

	@Override /* Common */
	public Icon lang(String lang) {
		super.lang(lang);
		return this;
	}
}
