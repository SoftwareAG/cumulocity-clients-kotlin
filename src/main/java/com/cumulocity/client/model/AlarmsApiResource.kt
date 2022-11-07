// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class AlarmsApiResource {

	/**
	 * Collection of all alarms
	 */
	var alarms: Alarms? = null

	/**
	 * Read-only collection of all alarms for a specific source object. The placeholder {source} must be a unique ID of an object in the inventory.
	 */
	var alarmsForSource: String? = null

	/**
	 * Read-only collection of all alarms in a particular status. The placeholder {status} can be one of the following values: ACTIVE, ACKNOWLEDGED or CLEARED
	 */
	var alarmsForStatus: String? = null

	/**
	 * Read-only collection of all alarms for a specific source, status and time range.
	 */
	var alarmsForSourceAndStatusAndTime: String? = null

	/**
	 * Read-only collection of all alarms for a particular status and time range.
	 */
	var alarmsForStatusAndTime: String? = null

	/**
	 * Read-only collection of all alarms for a specific source and time range.
	 */
	var alarmsForSourceAndTime: String? = null

	/**
	 * Read-only collection of all alarms for a particular time range.
	 */
	var alarmsForTime: String? = null

	/**
	 * Read-only collection of all alarms for a specific source object in a particular status.
	 */
	var alarmsForSourceAndStatus: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Collection of all alarms
	 */
	class Alarms {
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		var alarms: Array<Alarm>? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
