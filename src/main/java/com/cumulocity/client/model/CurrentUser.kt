// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * The current user.
 */
class CurrentUser {

	/**
	 * A list of user roles.
	 */
	var effectiveRoles: Array<Role>? = null

	/**
	 * The user's email address.
	 */
	var email: String? = null

	/**
	 * The user's first name.
	 */
	var firstName: String? = null

	/**
	 * A unique identifier for this user.
	 */
	var id: String? = null

	/**
	 * The user's last name.
	 */
	var lastName: String? = null

	/**
	 * The date and time when the user's password was last changed, in [ISO 8601 datetime format](https://www.w3.org/TR/NOTE-datetime).
	 */
	var lastPasswordChange: String? = null

	/**
	 * The user's password. Only Latin1 characters are allowed.
	 */
	var password: String? = null

	/**
	 * The user's phone number.
	 */
	var phone: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Indicates if the user should reset the password on the next login.
	 */
	var shouldResetPassword: Boolean? = null

	/**
	 * The user's username. It can have a maximum of 1000 characters.
	 */
	var userName: String? = null

	/**
	 * An object with a list of the user's device permissions.
	 */
	@Deprecated(message = "This property might be removed in future releases.")
	var devicePermissions: DevicePermissions? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
