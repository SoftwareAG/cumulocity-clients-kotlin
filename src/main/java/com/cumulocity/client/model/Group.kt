// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

data class Group(var name: String?) {
	constructor() : this(name = null)

	/**
	 * A list of applications.
	 */
	var applications: Array<Application>? = null

	/**
	 * An object with a list of custom properties.
	 */
	var customProperties: CustomProperties? = null

	/**
	 * A description of the group.
	 */
	var description: String? = null

	/**
	 * An object with a list of the user's device permissions.
	 */
	@Deprecated(message = "This property might be removed in future releases.")
	var devicePermissions: DevicePermissions? = null

	/**
	 * A unique identifier for this group.
	 */
	var id: Int? = null

	/**
	 * An object containing user roles for this group.
	 */
	var roles: Roles? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The list of users in this group.
	 */
	var users: Users? = null

	/**
	 * An object containing user roles for this group.
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

	/**
	 * The list of users in this group.
	 */
	class Users {
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		/**
		 * The list of users in this group.
		 */
		var references: Array<User>? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
