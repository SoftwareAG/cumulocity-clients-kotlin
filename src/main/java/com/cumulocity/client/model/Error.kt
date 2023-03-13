// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class Error {

	/**
	 * The type of error returned.
	 */
	var error: String? = null

	/**
	 * A human-readable message providing more details about the error.
	 */
	var message: String? = null

	/**
	 * A URI reference [[RFC3986](https://tools.ietf.org/html/rfc3986)] that identifies the error code reported.
	 */
	var info: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
