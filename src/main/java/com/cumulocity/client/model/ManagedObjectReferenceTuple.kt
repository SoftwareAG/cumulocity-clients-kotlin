// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class ManagedObjectReferenceTuple {

	/**
	 * Details of the referenced managed object.
	 */
	var managedObject: ManagedObject? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Details of the referenced managed object.
	 */
	class ManagedObject {
	
		/**
		 * Unique identifier of the object.
		 */
		var id: String? = null
	
		/**
		 * Human-readable name that is used for representing the object in user interfaces.
		 */
		var name: String? = null
	
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
