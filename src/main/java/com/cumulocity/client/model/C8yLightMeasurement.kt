// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Light is measured with two main alternative sets of units.
 * 
 * Radiometry consists of measurements of light power at all wavelengths, while photometry measures light with wavelength weighted with respect to a standardized model of human brightness perception. Photometry is useful, for example, to quantify illumination (lighting) intended for human use.
 */
class C8yLightMeasurement {

	/**
	 * A measurement is a value with a unit.
	 */
	var e: C8yMeasurementValue? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
