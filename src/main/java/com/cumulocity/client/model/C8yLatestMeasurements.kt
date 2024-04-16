// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * The read only fragment which contains the latest measurements reported by the device.The returned optionally only if the query parameter `withLatestValues=true` is used.
 * 
 * > **⚠️ Feature Preview:** The feature is part of the Latest Measurement feature which is still under public feature preview.
 */
class C8yLatestMeasurements {

	var additionalProperties: MutableMap<String, LatestMeasurementFragment> = hashMapOf()
	
	operator fun get(key: String): LatestMeasurementFragment? = additionalProperties[key]
	operator fun set(key: String, value: LatestMeasurementFragment): LatestMeasurementFragment? = additionalProperties.put(key, value)

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
