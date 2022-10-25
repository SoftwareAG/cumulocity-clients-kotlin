// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * An inventory role reference.
 */
class InventoryAssignmentReference {

	/**
	 * An array of roles that are assigned to the managed object for the user.
	 */
	var roles: Array<Roles>? = null

	class Roles {
	
		/**
		 * A unique identifier for this inventory role.
		 */
		var id: Int? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
