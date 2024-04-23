// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Login option properties.
 */
class LoginOption {

	/**
	 * For basic authentication case only.
	 */
	var authenticationRestrictions: BasicAuthenticationRestrictions? = null

	/**
	 * Indicates if password strength is enforced.
	 */
	var enforceStrength: Boolean? = null

	/**
	 * The grant type of the OAuth configuration.
	 */
	var grantType: GrantType? = null

	/**
	 * Minimum length for the password when the strength validation is enforced.
	 */
	var greenMinLength: Int? = null

	/**
	 * Unique identifier of this login option.
	 */
	var id: String? = null

	/**
	 * A URL linking to the token generating endpoint.
	 */
	var initRequest: String? = null

	/**
	 * The tenant domain.
	 */
	var loginRedirectDomain: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The session configuration properties are only available for OAuth internal. See [Changing settings > OAuth internal](https://cumulocity.com/guides/users-guide/administration/#oauth-internal) for more details.
	 */
	var sessionConfiguration: OAuthSessionConfiguration? = null

	/**
	 * Enforce password strength validation on subtenant level. `enforceStrength` enforces it on all tenants in the platform.
	 */
	var strengthValidity: Boolean? = null

	/**
	 * Two-factor authentication being used by this login option. TFA supported: SMS and TOTP.
	 */
	var tfaStrategy: String? = null

	/**
	 * The type of authentication. See [Authentication](#section/Authentication) for more details.
	 */
	var type: String? = null

	/**
	 * Specifies if the users are managed internally by Cumulocity IoT (`INTERNAL`) or if the users data are managed by a external system (`REMOTE`).
	 */
	var userManagementSource: String? = null

	/**
	 * Indicates if this login option is available in the login page (only for SSO).
	 */
	var visibleOnLoginPage: Boolean? = null

	/**
	 * The type of authentication.
	 */
	@Deprecated(message = "This property might be removed in future releases.")
	var pType: String? = null

	/**
	 * The grant type of the OAuth configuration.
	 */
	enum class GrantType(val value: String) {
		@SerializedName(value = "PASSWORD")
		PASSWORD("PASSWORD"),
		@SerializedName(value = "AUTHORIZATION_CODE")
		AUTHORIZATIONCODE("AUTHORIZATION_CODE")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
