// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * A list of device permissions.
 */
class UpdatedDevicePermissions {

	var users: Array<Users>? = null

	var groups: Array<Groups>? = null

	class Users {
	
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

	class Groups {
	
		var id: String? = null
	
		/**
		 * An object with a list of the user's device permissions.
		 */
		@Deprecated(message = "This property might be removed in future releases.")
		var devicePermissions: DevicePermissions? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
