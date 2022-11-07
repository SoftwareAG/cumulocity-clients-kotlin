// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class TenantTfaData {

	/**
	 * Indicates whether two-factor authentication is enabled on system level or not.
	 */
	var enabledOnSystemLevel: Boolean? = null

	/**
	 * Indicates whether two-factor authentication is enabled on tenant level or not.
	 */
	var enabledOnTenantLevel: Boolean? = null

	/**
	 * Indicates whether two-factor authentication is enforced on system level or not.
	 */
	var enforcedOnSystemLevel: Boolean? = null

	/**
	 * Two-factor authentication is enforced for the specified group.
	 */
	var enforcedUsersGroup: String? = null

	/**
	 * Two-factor authentication strategy.
	 */
	var strategy: Strategy? = null

	/**
	 * Indicates whether two-factor authentication is enforced on tenant level or not.
	 */
	var totpEnforcedOnTenantLevel: Boolean? = null

	/**
	 * Two-factor authentication strategy.
	 * [SMS, TOTP]
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
