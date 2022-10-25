// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class MeasurementApiResource {

	/**
	 * Collection of all measurements
	 */
	var measurements: Measurements? = null

	/**
	 * Read-only collection of all measurements for a specific source object. The placeholder {source} must be a unique ID of an object in the inventory.
	 */
	var measurementsForSource: String? = null

	/**
	 * Read-only collection of all measurements of a particular type and a specific source object.
	 */
	var measurementsForSourceAndType: String? = null

	/**
	 * Read-only collection of all measurements of a particular type.
	 */
	var measurementsForType: String? = null

	/**
	 * Read-only collection of all measurements containing a particular fragment type.
	 */
	var measurementsForValueFragmentType: String? = null

	/**
	 * Read-only collection of all measurements for a particular time range.
	 */
	var measurementsForDate: String? = null

	/**
	 * Read-only collection of all measurements for a specific source object in a particular time range.
	 */
	var measurementsForSourceAndDate: String? = null

	/**
	 * Read-only collection of all measurements for a specific fragment type and a particular time range.
	 */
	var measurementsForDateAndFragmentType: String? = null

	/**
	 * Read-only collection of all measurements for a specific source object, particular fragment type and series, and an event type.
	 */
	var measurementsForSourceAndValueFragmentTypeAndValueFragmentSeries: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Collection of all measurements
	 */
	class Measurements {
	
		var measurements: Array<Measurement>? = null
	
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
