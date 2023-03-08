// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * The manifest of the web application.
 */
class WebApplicationManifest {

	/**
	 * A legacy flag that identified a certain type of web application that would control the behavior of plugin tab in the application details view.It is no longer used.
	 */
	@Deprecated(message = "This property might be removed in future releases.")
	var pWebpaas: Boolean? = null

	/**
	 * The content security policy of the application.
	 */
	var contentSecurityPolicy: String? = null

	/**
	 * A flag that decides if the application is shown in the app switcher on the UI.
	 */
	var noAppSwitcher: Boolean? = null

	/**
	 * A flag that decides if the application tabs are displayed horizontally or not.
	 */
	var tabsHorizontal: Boolean? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
