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
package org.apache.juneau.rest.test;

import org.apache.juneau.html.*;
import org.apache.juneau.rest.*;
import org.apache.juneau.rest.annotation.*;

/**
 * JUnit automated testcase resource.
 * Tests the various permutations of populating the HTML page titles, text, and links.
 */
@RestResource(
	path="/testHtmlProperties",
	children={
		HtmlPropertiesResource.Normal.class,
		HtmlPropertiesResource.NormalInit.class,
		HtmlPropertiesResource.NormalDefaulting.class,
		HtmlPropertiesResource.NormalSubclassed1.class,
		HtmlPropertiesResource.NormalSubclassed2.class,
		HtmlPropertiesResource.LocalizedImplicit.class,
		HtmlPropertiesResource.LocalizedExplicit.class
	}
)
@SuppressWarnings("serial")
public class HtmlPropertiesResource extends RestServletGroupDefault {
	private static final long serialVersionUID = 1L;


	@RestResource(
		path="/Normal",
		pageTitle="Normal-title",
		pageText="Normal-text",
		pageLinks="{link:'Normal-links'}"
	)
	public static class Normal extends RestServletDefault {

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(pageX).
		//----------------------------------------------------------------------------------------------------
		@RestMethod(name="GET", path="/test1")
		public String test1() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(pageX), overridden by @RestMethod(pageX) annotations.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test2", pageTitle="Normal.test2-title", pageText="Normal.test2-text", pageLinks="{link:'Normal.test2-links'}")
		public String test2() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(pageX), overridden by RestResponse.setPageX() methods.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test3")
		public String test3(RestResponse res) {
			res.setPageTitle("Normal.test3-title");
			res.setPageText("Normal.test3-text");
			res.setPageLinks("{link:'Normal.test3-links'}");
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(pageX), overridden by RestResponse.setProperty() method.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test4")
		public String test4(RestResponse res) {
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_title, "Normal.test4-title");
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_text, "Normal.test4-text");
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_links, "{link:'Normal.test4-links'}");
			return "OK";
		}
	}

	@RestResource(
		path="/NormalInit"
	)
	public static class NormalInit extends RestServletDefault {

		@Override
		public void init(RestConfig config) throws Exception {
			config.setPageTitle("NormalInit-title");
			config.setPageText("NormalInit-text");
			config.setPageLinks("{link:'NormalInit-links'}");
			super.init(config);
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from RestConfig.setX() methods.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test1")
		public String test1() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from RestConfig.setX() methods, overridden by @RestMethod(pageX) annotations.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test2", pageTitle="NormalInit.test2-title", pageText="NormalInit.test2-text", pageLinks="{link:'NormalInit.test2-links'}")
		public String test2() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from RestConfig.setX() methods, overridden by RestResponse.setPageX() methods.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test3")
		public String test3(RestResponse res) {
			res.setPageTitle("NormalInit.test3-title");
			res.setPageText("NormalInit.test3-text");
			res.setPageLinks("{link:'NormalInit.test3-links'}");
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from RestConfig.setX() methods, overridden by RestResponse.setProperty() method.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test4")
		public String test4(RestResponse res) {
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_title, "NormalInit.test4-title");
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_text, "NormalInit.test4-text");
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_links, "{link:'NormalInit.test4-links'}");
			return "OK";
		}
	}

	@RestResource(
		path="/NormalDefaulting",
		title="NormalDefaulting-title",
		description="NormalDefaulting-description"
	)
	public static class NormalDefaulting extends RestServletDefault {

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(path/title).
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test1")
		public String test1() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(path/title), overridden by @RestMethod(pageX) annotations.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test2", summary="NormalDefaulting.test2-summary")
		public String test2() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(path/title), overridden by RestResponse.setPageX() methods.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test3")
		public String test3(RestResponse res) {
			res.setPageTitle("NormalDefaulting.test3-title");
			res.setPageText("NormalDefaulting.test3-text");
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(path/title), overridden by RestResponse.setProperty() method.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test4")
		public String test4(RestResponse res) {
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_title, "NormalDefaulting.test4-title");
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_text, "NormalDefaulting.test4-text");
			return "OK";
		}
	}


	@RestResource(
		path="/NormalSubclassed1"
	)
	public static class NormalSubclassed1 extends Normal {

		//----------------------------------------------------------------------------------------------------
		// Values pulled from parent @RestResource(path/title).
		//----------------------------------------------------------------------------------------------------
		@Override
		@RestMethod(path="/test1")
		public String test1() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from parent @RestResource(path/title), overridden by @RestMethod(pageX) annotations.
		//----------------------------------------------------------------------------------------------------
		@Override
		@RestMethod(path="/test2", pageTitle="NormalSubclassed1.test2-title", pageText="NormalSubclassed1.test2-text", pageLinks="{link:'NormalSubclassed1.test2-links'}")
		public String test2() {
			return "OK";
		}
	}

	@RestResource(
		path="/NormalSubclassed2",
		pageTitle="NormalSubclassed2-title",
		pageText="NormalSubclassed2-text",
		pageLinks="{link:'NormalSubclassed2-links'}"
	)
	public static class NormalSubclassed2 extends Normal {

		//----------------------------------------------------------------------------------------------------
		// Values pulled from parent @RestResource(path/title), overridden by child @RestResource(pageTitle/pageText).
		//----------------------------------------------------------------------------------------------------
		@Override
		@RestMethod(path="/test1")
		public String test1() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from parent @RestResource(path/title), overridden by @RestMethod(pageX).
		//----------------------------------------------------------------------------------------------------
		@Override
		@RestMethod(path="/test2", pageTitle="NormalSubclassed2.test2-title", pageText="NormalSubclassed2.test2-text", pageLinks="{link:'NormalSubclassed2.test2-links'}")
		public String test2() {
			return "OK";
		}
	}

	@RestResource(
		path="/LocalizedExplicit",
		messages="HtmlPropertiesResource",
		pageTitle="$L{pageTitle}",
		pageText="$L{pageText}",
		pageLinks="$L{pageLinks}"
	)
	public static class LocalizedExplicit extends RestServletDefault {

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(pageX) with $L variables.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test1")
		public String test1() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(pageX) with $L variables, overridden by @RestMethod(pageX) with $L variables.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test2", pageTitle="$L{test2.pageTitle}", pageText="$L{test2.pageText}", pageLinks="$L{test2.pageLinks}")
		public String test2() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(pageX) with $L variables, overridden by RestResponse.setPageX() with $L variables.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test3")
		public String test3(RestResponse res) {
			res.setPageTitle("$L{test3.pageTitle}");
			res.setPageText("$L{test3.pageText}");
			res.setPageLinks("$L{test3.pageLinks}");
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from @RestResource(pageX) with $L variables, overridden by RestResponse.setProperty() with $L variables.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test4")
		public String test4(RestResponse res) {
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_title, "$L{test4.pageTitle}");
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_text, "$L{test4.pageText}");
			res.setProperty(HtmlDocSerializerContext.HTMLDOC_links, "$L{test4.pageLinks}");
			return "OK";
		}
	}

	@RestResource(
		path="/LocalizedImplicit",
		messages="HtmlPropertiesResource"
	)
	public static class LocalizedImplicit extends RestServletDefault {

		//----------------------------------------------------------------------------------------------------
		// Values pulled from resource bundle.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test1")
		public String test1() {
			return "OK";
		}

		//----------------------------------------------------------------------------------------------------
		// Values pulled from resource bundle, overridden by values in resource bundle at method level.
		//----------------------------------------------------------------------------------------------------
		@RestMethod(path="/test2")
		public String test2() {
			return "OK";
		}
	}
}
