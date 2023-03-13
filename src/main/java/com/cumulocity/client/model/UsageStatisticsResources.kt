// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Resources usage for each subscribed microservice application.
 */
class UsageStatisticsResources {

	/**
	 * Total number of CPU usage for tenant microservices, specified in CPU milliseconds (1000m = 1 CPU).
	 */
	var cpu: Int? = null

	/**
	 * Total number of memory usage for tenant microservices, specified in MB.
	 */
	var memory: Int? = null

	/**
	 * Collection of resources usage for each microservice.
	 */
	var usedBy: Array<UsageStatisticsResourcesUsedBy>? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
