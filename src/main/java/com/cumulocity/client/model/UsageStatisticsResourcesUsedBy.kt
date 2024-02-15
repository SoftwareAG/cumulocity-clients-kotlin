// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

class UsageStatisticsResourcesUsedBy {

	/**
	 * Reason for calculating statistics of the specified microservice.
	 */
	var cause: String? = null

	/**
	 * Number of CPU usage for a single microservice.
	 */
	var cpu: Int? = null

	/**
	 * Number of memory usage for a single microservice.
	 */
	var memory: Int? = null

	/**
	 * Name of the microservice.
	 */
	var name: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
