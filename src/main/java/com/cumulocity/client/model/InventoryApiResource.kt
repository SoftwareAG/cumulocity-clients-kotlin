// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

class InventoryApiResource {

	/**
	 * Read-only collection of all managed objects with a particular fragment type or capability (placeholder {fragmentType}).
	 */
	var managedObjectsForFragmentType: String? = null

	/**
	 * Read-only collection of all managed objects of a particular type (placeholder {type}).
	 */
	var managedObjectsForType: String? = null

	/**
	 * Read-only collection of managed objects fetched for a given list of ids, for example, “ids=41,43,68”.
	 */
	var managedObjectsForListOfIds: String? = null

	/**
	 * Collection of all managed objects
	 */
	var managedObjects: ManagedObjects? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Collection of all managed objects
	 */
	class ManagedObjects {
	
		/**
		 * An array containing the referenced managed objects.
		 */
		var references: Array<ManagedObject>? = null
	
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
