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
package org.apache.juneau.transform;

import org.apache.juneau.*;

/**
 * Simple bean filter that simply identifies a class to be used as an interface
 * 	class for all child classes.
 * <p>
 * These objects are created when you pass in non-<code>BeanFilterBuilder</code> classes to {@link PropertyStore#addToProperty(String,Object)},
 * 	and are equivalent to adding a <code><ja>@Bean</ja>(interfaceClass=Foo.<jk>class</jk>)</code> annotation on the <code>Foo</code> class.
 */
public class InterfaceBeanFilterBuilder extends BeanFilterBuilder {

	/**
	 * Constructor.
	 *
	 * @param interfaceClass The class to use as an interface on all child classes.
	 */
	public InterfaceBeanFilterBuilder(Class<?> interfaceClass) {
		super(interfaceClass);
		interfaceClass(interfaceClass);
	}
}
