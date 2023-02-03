// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class User {

	/**
	 * A list of applications for this user.
	 */
	var applications: Array<Application>? = null

	/**
	 * An object with a list of custom properties.
	 */
	var customProperties: CustomProperties? = null

	/**
	 * The user's display name in Cumulocity IoT.
	 */
	var displayName: String? = null

	/**
	 * The user's email address.
	 */
	var email: String? = null

	/**
	 * Indicates whether the user is enabled or not. Disabled users cannot log in or perform API requests.
	 */
	var enabled: Boolean? = null

	/**
	 * The user's first name.
	 */
	var firstName: String? = null

	/**
	 * An object with a list of user groups.
	 */
	var groups: Groups? = null

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
	 * Indicates whether the user is subscribed to the newsletter or not.
	 */
	var newsletter: Boolean? = null

	/**
	 * Identifier of the parent user. If present, indicates that a user belongs to a user hierarchy by pointing to its direct ancestor. Can only be set by users with role USER_MANAGEMENT_ADMIN during user creation. Otherwise it's assigned automatically.
	 */
	var owner: String? = null

	/**
	 * The user's password. Only Latin1 characters are allowed.
	 * 
	 * If you do not specify a password when creating a new user with a POST request, it must contain the property `sendPasswordResetEmail` with a value of `true`.
	 * 
	 */
	var password: String? = null

	/**
	 * Indicates the password strength. The value can be GREEN, YELLOW or RED for decreasing password strengths.
	 */
	var passwordStrength: PasswordStrength? = null

	/**
	 * The user's phone number.
	 */
	var phone: String? = null

	/**
	 * An object with a list of user roles.
	 */
	var roles: Roles? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * When set to `true`, this field will cause Cumulocity IoT to send a password reset email to the email address specified.
	 * 
	 * If there is no password specified when creating a new user with a POST request, this must be specified and it must be set to `true`.
	 * 
	 */
	var sendPasswordResetEmail: Boolean? = null

	/**
	 * Indicates if the user should reset the password on the next login.
	 */
	var shouldResetPassword: Boolean? = null

	/**
	 * Indicates if the user has to use two-factor authentication to log in.
	 */
	var twoFactorAuthenticationEnabled: Boolean? = null

	/**
	 * The user's username. It can have a maximum of 1000 characters.
	 */
	var userName: String? = null

	/**
	 * An object with a list of the user's device permissions.
	 */
	@Deprecated(message = "This property might be removed in future releases.")
	var devicePermissions: DevicePermissions? = null

	/**
	 * Indicates the password strength. The value can be GREEN, YELLOW or RED for decreasing password strengths.
	 * [GREEN, YELLOW, RED]
	 */
	enum class PasswordStrength(val value: String) {
		@SerializedName(value = "GREEN")
		GREEN("GREEN"),
		@SerializedName(value = "YELLOW")
		YELLOW("YELLOW"),
		@SerializedName(value = "RED")
		RED("RED")
	}

	/**
	 * An object with a list of user groups.
	 */
	class Groups {
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		/**
		 * A list of user group references.
		 */
		var references: Array<GroupReference>? = null
	
		/**
		 * Information about paging statistics.
		 */
		var statistics: PageStatistics? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}


	/**
	 * An object with a list of user roles.
	 */
	class Roles {
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		/**
		 * A list of user role references.
		 */
		var references: Array<RoleReference>? = null
	
		/**
		 * Information about paging statistics.
		 */
		var statistics: PageStatistics? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
