// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class LoginForm {

	/**
	 * Used in case of SSO login. A code received from the external authentication server is exchanged to an internal access token.
	 */
	var code: String? = null

	/**
	 * Dependent on the authentication type. PASSWORD is used for OAI-Secure.
	 */
	@SerializedName(value = "grant_type")
	var grantType: GrantType? = null

	/**
	 * Used in cases of basic or OAI-Secure authentication.
	 */
	var password: String? = null

	/**
	 * Current TFA code, sent by the user, if a TFA code is required to log in.
	 */
	@SerializedName(value = "tfa_code")
	var tfaCode: String? = null

	/**
	 * Used in cases of basic or OAI-Secure authentication.
	 */
	var username: String? = null

	/**
	 * Dependent on the authentication type. PASSWORD is used for OAI-Secure.
	 * [PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN]
	 */
	enum class GrantType(val value: String) {
		@SerializedName(value = "PASSWORD")
		PASSWORD("PASSWORD"),
		@SerializedName(value = "AUTHORIZATION_CODE")
		AUTHORIZATIONCODE("AUTHORIZATION_CODE"),
		@SerializedName(value = "REFRESH_TOKEN")
		REFRESHTOKEN("REFRESH_TOKEN")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
