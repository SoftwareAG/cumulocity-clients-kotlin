// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class UserTfaData {

	/**
	 * Latest date and time when the user has used two-factor authentication to log in.
	 */
	var lastTfaRequestTime: String? = null

	/**
	 * Two-factor authentication strategy.
	 */
	var strategy: Strategy? = null

	/**
	 * Indicates whether the user has enabled two-factor authentication or not.
	 */
	var tfaEnabled: Boolean? = null

	/**
	 * Indicates whether two-factor authentication is enforced by the tenant admin or not.
	 */
	var tfaEnforced: Boolean? = null

	/**
	 * Two-factor authentication strategy.
	 */
	enum class Strategy(val value: String) {
		@SerializedName(value = "SMS")
		SMS("SMS"),
		@SerializedName(value = "TOTP")
		TOTP("TOTP")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
