// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

/**
 * Statistics of a specific device (identified by an ID).
 */
class DeviceStatistics {

	/**
	 * Sum of measurements, events and alarms created and updated for the specified device.
	 */
	var count: Int? = null

	/**
	 * Unique identifier of the device.
	 */
	var deviceId: String? = null

	/**
	 * List of unique identifiers of parents for the corresponding device. Available only with monthly data.
	 */
	var deviceParents: Array<String>? = null

	/**
	 * Value of the `type` field from the corresponding device. Available only with monthly data.
	 */
	var deviceType: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
