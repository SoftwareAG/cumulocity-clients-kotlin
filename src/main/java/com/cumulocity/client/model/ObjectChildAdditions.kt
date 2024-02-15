// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * A collection of references to child additions.
 */
class ObjectChildAdditions {

	/**
	 * The total number of child additions. Only present if the value is greater than 0.
	 */
	var count: Int? = null

	/**
	 * An array with the references to child devices.
	 */
	var references: Array<ManagedObjectReferenceTuple>? = null

	/**
	 * Link to this resource's child additions.
	 */
	var self: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
