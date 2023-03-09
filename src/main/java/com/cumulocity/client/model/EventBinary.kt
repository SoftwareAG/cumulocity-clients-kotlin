// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class EventBinary {

	/**
	 * Name of the attachment. If it is not provided in the request, it will be set as the event ID.
	 */
	var name: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Unique identifier of the event.
	 */
	var source: String? = null

	/**
	 * Media type of the attachment.
	 */
	var type: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
