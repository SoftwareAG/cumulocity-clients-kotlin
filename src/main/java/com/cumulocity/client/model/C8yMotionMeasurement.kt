// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

/**
 * Measurement of the motion sensor.
 */
class C8yMotionMeasurement {

	/**
	 * Boolean value indicating if motion has been detected (non-zero value) or not (zero value).
	 */
	var motionDetected: MotionDetected? = null

	/**
	 * A measurement is a value with a unit.
	 */
	var speed: C8yMeasurementValue? = null

	/**
	 * Boolean value indicating if motion has been detected (non-zero value) or not (zero value).
	 */
	class MotionDetected {
	
		var value: Number? = null
	
		var type: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
