// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * For basic authentication case only.
 */
class BasicAuthenticationRestrictions {

	/**
	 * List of types of clients which are not allowed to use basic authentication. Currently the only supported option is WEB_BROWSERS.
	 */
	var forbiddenClients: Array<String>? = null

	/**
	 * List of user agents, passed in `User-Agent` HTTP header, which are blocked if basic authentication is used.
	 */
	var forbiddenUserAgents: Array<String>? = null

	/**
	 * List of user agents, passed in `User-Agent` HTTP header, which are allowed to use basic authentication.
	 */
	var trustedUserAgents: Array<String>? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
