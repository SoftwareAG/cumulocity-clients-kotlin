// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * A collection of references to asset parent objects.
 */
class ObjectAssetParents {

	/**
	 * An array with the references to asset parent objects.
	 */
	var references: Array<ManagedObjectReferenceTuple>? = null

	/**
	 * Link to this resource's asset parent objects.
	 */
	var self: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
