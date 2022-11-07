// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * The availability information computed by Cumulocity IoT is stored in fragments `c8y_Availability` and `c8y_Connection` of the device.
 */
class C8yAvailability {

	/**
	 * The current status, one of `AVAILABLE`, `CONNECTED`, `MAINTENANCE`, `DISCONNECTED`.
	 */
	var status: C8yAvailabilityStatus? = null

	/**
	 * The time when the device sent the last message to Cumulocity IoT.
	 */
	var lastMessage: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
