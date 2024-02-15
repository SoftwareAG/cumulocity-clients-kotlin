// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class NotificationSubscriptionResult {

	/**
	 * The status of the notification subscription deletion.
	 */
	var result: Result? = null

	/**
	 * The status of the notification subscription deletion.
	 */
	enum class Result(val value: String) {
		@SerializedName(value = "DONE")
		DONE("DONE"),
		@SerializedName(value = "SCHEDULED")
		SCHEDULED("SCHEDULED")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
