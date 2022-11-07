// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * A type of measurement fragment.
 */
class C8ySteam {

	@SerializedName(value = "Temperature")
	var temperature: Temperature? = null

	data class Temperature(var value: Number?) {
		constructor() : this(value = null)
	
		/**
		 * The unit of the measurement.
		 */
		var unit: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
