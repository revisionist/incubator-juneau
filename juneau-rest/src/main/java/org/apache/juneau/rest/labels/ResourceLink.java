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
package org.apache.juneau.rest.labels;

import static org.apache.juneau.internal.StringUtils.*;

import java.text.*;

import org.apache.juneau.dto.*;
import org.apache.juneau.rest.*;

/**
 * A simple link to a child of a parent resource.
 */
public class ResourceLink extends Link {

	/**
	 * Constructor.
	 *
	 * @param req The HTTP request from the parent resource.
	 * @param childPath The child resource path.
	 * @param args Optional {@link MessageFormat}-style arguments.
	 */
	public ResourceLink(RestRequest req, String childPath, Object...args) {
		super(getName(getPath(childPath,args)), getHref(req, getPath(childPath,args)));
	}

	/**
	 * Constructor.
	 *
	 * @param label The label for the link.
	 * @param req The HTTP request from the parent resource.
	 * @param childPath The child resource path.
	 * @param args Optional {@link MessageFormat}-style arguments.
	 */
	public ResourceLink(String label, RestRequest req, String childPath, Object...args) {
		super(label, getHref(req, getPath(childPath,args)));
	}

	private static String getName(String childPath) {
		String s = childPath;
		if (childPath.indexOf('/') == -1)
			s = childPath;
		else
			s = childPath.substring(childPath.lastIndexOf('/')+1);
		return urlDecode(s);
	}

	private static String getHref(RestRequest req, String childPath) {
		return req.getServletURIBuilder().append('/').append(childPath).toString();
	}

	private static String getPath(String childPath, Object...args) {
		if (args.length > 0)
			childPath = MessageFormat.format(childPath, args);
		return childPath;
	}
}
