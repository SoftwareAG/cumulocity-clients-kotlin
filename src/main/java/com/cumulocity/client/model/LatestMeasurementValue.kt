// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * The read only fragment which contains the latest measurements series values reported by the device.
 * 
 * > **⚠️ Feature Preview:** The feature is part of the Latest Measurement feature which is still under public feature preview.
 */
data class LatestMeasurementValue(var value: Number?) {
	constructor() : this(value = null)

	/**
	 * The unit of the measurement series.
	 */
	var unit: String? = null

	/**
	 * The time of the measurement series.
	 */
	var time: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
