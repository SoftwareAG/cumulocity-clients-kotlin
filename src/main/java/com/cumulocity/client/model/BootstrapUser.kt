// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

class BootstrapUser {

	/**
	 * The bootstrap user tenant username.
	 */
	var name: String? = null

	/**
	 * The bootstrap user tenant password.
	 */
	var password: String? = null

	/**
	 * The bootstrap user tenant ID.
	 */
	var tenant: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
