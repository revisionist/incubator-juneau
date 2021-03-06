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
package org.apache.juneau.examples.rest;

import static org.apache.juneau.rest.RestContext.*;

import java.util.*;

import org.apache.juneau.examples.addressbook.*;
import org.apache.juneau.rest.annotation.*;
import org.apache.juneau.rest.remoteable.*;

/**
 * Class showing the functionality of the RemoteableServlet class.
 */
@SuppressWarnings("serial")
@RestResource(
	path="/remoteable",
	messages="nls/SampleRemoteableServlet",
	title="Remoteable Service Proxy API",
	description="Sample class showing how to use remoteable proxies.  The list below are exposed services that can be retrieved using RestClient.getProxyInterface(Class).",
	pageLinks="{up:'request:/..',options:'servlet:/?method=OPTIONS',source:'$C{Source/gitHub}/org/apache/juneau/examples/rest/SampleRemoteableServlet.java'}",
	properties={
		// Allow us to use method=POST from a browser.
		@Property(name=REST_allowMethodParam, value="*")
	},
	config="$S{juneau.configFile}",  // So we can resolve $C{Source/gitHub} above.
	stylesheet="styles/devops.css"
)
public class SampleRemoteableServlet extends RemoteableServlet {

	AddressBook addressBook = new AddressBook();

	@Override /* RemoteableServlet */
	protected Map<Class<?>,Object> getServiceMap() throws Exception {
		Map<Class<?>,Object> m = new LinkedHashMap<Class<?>,Object>();

		// In this simplified example, we expose the same POJO service under two different interfaces.
		// One is IAddressBook which only exposes methods defined on that interface, and
		// the other is AddressBook itself which exposes all methods defined on the class itself.
		m.put(IAddressBook.class, addressBook);
		m.put(AddressBook.class, addressBook);
		return m;
	}
}
