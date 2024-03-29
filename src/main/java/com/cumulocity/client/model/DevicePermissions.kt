// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * An object with a list of the user's device permissions.
 */
class DevicePermissions {

	var additionalProperties: MutableMap<String, Array<String>> = hashMapOf()
	
	operator fun get(key: String): Array<String>? = additionalProperties[key]
	operator fun set(key: String, value: Array<String>): Array<String>? = additionalProperties.put(key, value)

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
