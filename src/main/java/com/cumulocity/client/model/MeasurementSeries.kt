// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

class MeasurementSeries {

	/**
	 * Each property contained here is a date taken from the measurement and it contains an array of objects specifying `min` and `max` pair of values. Each pair corresponds to a single series object in the `series` array. If there is no aggregation used, `min` is equals to `max` in every pair.
	 */
	var values: Values? = null

	/**
	 * An array containing the type of series and units.
	 */
	var series: Array<MeasurementFragmentSeries>? = null

	/**
	 * If there were more than 5000 values, the final result was truncated.
	 */
	var truncated: Boolean? = null

	/**
	 * Each property contained here is a date taken from the measurement and it contains an array of objects specifying `min` and `max` pair of values. Each pair corresponds to a single series object in the `series` array. If there is no aggregation used, `min` is equals to `max` in every pair.
	 */
	class Values {
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
