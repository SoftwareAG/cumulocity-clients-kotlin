// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

/**
 * The session configuration properties are only available for OAuth internal. See [Changing settings > OAuth internal](https://cumulocity.com/guides/users-guide/administration/#oauth-internal) for more details.
 */
class OAuthSessionConfiguration {

	/**
	 * Maximum session duration (in milliseconds) during which a user does not have to login again.
	 */
	var absoluteTimeoutMillis: Int? = null

	/**
	 * Maximum number of parallel sessions for one user.
	 */
	var maximumNumberOfParallelSessions: Int? = null

	/**
	 * Amount of time before a token expires (in milliseconds) during which the token may be renewed.
	 */
	var renewalTimeoutMillis: Int? = null

	/**
	 * Switch to turn additional user agent verification on or off during the session.
	 */
	var userAgentValidationRequired: Boolean? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
