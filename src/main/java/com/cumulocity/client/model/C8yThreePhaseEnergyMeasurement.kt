// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Measurement of the three phase energy meter.
 */
class C8yThreePhaseEnergyMeasurement {

	var additionalProperties: MutableMap<String, C8yMeasurementValue> = hashMapOf()
	
	operator fun get(key: String): C8yMeasurementValue? = additionalProperties[key]
	operator fun set(key: String, value: C8yMeasurementValue): C8yMeasurementValue? = additionalProperties.put(key, value)

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
