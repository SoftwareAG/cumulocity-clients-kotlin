// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class EventsApiResource {

	/**
	 * Collection of all events
	 */
	var events: Events? = null

	/**
	 * Read-only collection of all events for a specific source object. The placeholder {source} must be a unique ID of an object in the inventory.
	 */
	var eventsForSource: String? = null

	/**
	 * Read-only collection of all events of a particular type and a specific source object.
	 */
	var eventsForSourceAndType: String? = null

	/**
	 * Read-only collection of all events of a particular type.
	 */
	var eventsForType: String? = null

	/**
	 * Read-only collection of all events containing a particular fragment type.
	 */
	var eventsForFragmentType: String? = null

	/**
	 * Read-only collection of all events for a particular time range.
	 */
	var eventsForTime: String? = null

	/**
	 * Read-only collection of all events for a specific source object in a particular time range.
	 */
	var eventsForSourceAndTime: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Collection of all events
	 */
	class Events {
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		var events: Array<Event>? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
