// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class NewDeviceRequest {

	/**
	 * External ID of the device.
	 */
	var id: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Status of this new device request.
	 */
	var status: Status? = null

	
	/**
	 * Status of this new device request.
	 * [WAITING_FOR_CONNECTION, PENDING_ACCEPTANCE, ACCEPTED]
	 */
	enum class Status(val value: String) {
		@SerializedName(value = "WAITING_FOR_CONNECTION")
		WAITINGFORCONNECTION("WAITING_FOR_CONNECTION"),
		@SerializedName(value = "PENDING_ACCEPTANCE")
		PENDINGACCEPTANCE("PENDING_ACCEPTANCE"),
		@SerializedName(value = "ACCEPTED")
		ACCEPTED("ACCEPTED")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
