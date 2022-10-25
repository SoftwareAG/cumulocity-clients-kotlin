// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class ApplicationSettings {

	/**
	 * The name of the setting.
	 */
	var key: String? = null

	/**
	 * The value schema determines the values that the microservice can process.
	 */
	var valueSchema: ValueSchema? = null

	/**
	 * The default value.
	 */
	var defaultValue: String? = null

	/**
	 * Indicates if the value is editable.
	 */
	var editable: Boolean? = null

	/**
	 * Indicated wether this setting is inherited.
	 */
	var inheritFromOwner: Boolean? = null

	/**
	 * The value schema determines the values that the microservice can process.
	 */
	class ValueSchema {
	
		/**
		 * The value schema type.
		 */
		var type: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
