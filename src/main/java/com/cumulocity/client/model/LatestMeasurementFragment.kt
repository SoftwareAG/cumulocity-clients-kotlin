// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * The read only fragment which contains the latest measurements series reported by the device.
 * 
 * > **⚠️ Feature Preview:** The feature is part of the Latest Measurement feature which is still under public feature preview.
 */
class LatestMeasurementFragment {

	var additionalProperties: MutableMap<String, LatestMeasurementValue> = hashMapOf()
	
	operator fun get(key: String): LatestMeasurementValue? = additionalProperties[key]
	operator fun set(key: String, value: LatestMeasurementValue): LatestMeasurementValue? = additionalProperties.put(key, value)

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
