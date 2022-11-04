// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class CurrentUserTotpSecret {

	/**
	 * Secret used by two-factor authentication applications to generate the TFA codes.
	 */
	var rawSecret: String? = null

	/**
	 * URL used to set the two-factor authentication secret for the TFA application.
	 */
	var secretQrUrl: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
