// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

/**
 * Measurement of the distance.
 */
class C8yDistanceMeasurement {

	/**
	 * A measurement is a value with a unit.
	 */
	var distance: C8yMeasurementValue? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
