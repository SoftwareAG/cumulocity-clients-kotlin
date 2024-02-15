// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * An inventory assignment.
 */
class InventoryAssignment {

	/**
	 * A unique identifier for this inventory assignment.
	 */
	var id: Int? = null

	/**
	 * A unique identifier for the managed object for which the roles are assigned.
	 */
	var managedObject: String? = null

	/**
	 * An array of roles that are assigned to the managed object for the user.
	 */
	var roles: Array<InventoryRole>? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
