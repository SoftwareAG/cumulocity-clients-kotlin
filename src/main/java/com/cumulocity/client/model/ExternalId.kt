// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

data class ExternalId(var externalId: String?, var type: String?) {
	constructor() : this(externalId = null, type = null)

	/**
	 * The managed object linked to the external ID.
	 */
	var managedObject: ManagedObject? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The managed object linked to the external ID.
	 */
	class ManagedObject {
	
		/**
		 * Unique identifier of the object.
		 */
		var id: String? = null
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
