// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

/**
 * Summary of the usage statistics.
 */
class SummaryTenantUsageStatistics {

	/**
	 * Number of created alarms.
	 */
	var alarmsCreatedCount: Int? = null

	/**
	 * Number of updates made to the alarms.
	 */
	var alarmsUpdatedCount: Int? = null

	/**
	 * Date of this usage statistics summary.
	 */
	var day: String? = null

	/**
	 * Number of devices in the tenant identified by the fragment `c8y_IsDevice`. Updated only three times a day starting at 8:57, 16:57 and 23:57.
	 */
	var deviceCount: Int? = null

	/**
	 * Number of devices which do not have child devices. Updated only three times a day starting at 8:57, 16:57 and 23:57.
	 */
	var deviceEndpointCount: Int? = null

	/**
	 * Number of requests that were issued only by devices against the tenant. Updated every 5 minutes. The following requests are not included:
	 * 
	 * * Requests made to <kbd>/user</kbd>, <kbd>/tenant</kbd> and <kbd>/application</kbd> APIs
	 * * Application related requests (with `X-Cumulocity-Application-Key` header)
	 * 
	 */
	var deviceRequestCount: Int? = null

	/**
	 * Number of devices with children. Updated only three times a day starting at 8:57, 16:57 and 23:57.
	 */
	var deviceWithChildrenCount: Int? = null

	/**
	 * Number of created events.
	 */
	var eventsCreatedCount: Int? = null

	/**
	 * Number of updates made to the events.
	 */
	var eventsUpdatedCount: Int? = null

	/**
	 * Number of created managed objects.
	 */
	var inventoriesCreatedCount: Int? = null

	/**
	 * Number of updates made to the managed objects.
	 */
	var inventoriesUpdatedCount: Int? = null

	/**
	 * Number of created measurements.
	 * 
	 * > **&#9432; Info:** Bulk creation of measurements is handled in a way that each measurement is counted individually.
	 * 
	 */
	var measurementsCreatedCount: Int? = null

	/**
	 * Number of requests that were made against the tenant. Updated every 5 minutes. The following requests are not included:
	 * 
	 * *  Internal SmartREST requests used to resolve templates
	 * *  Internal SLA monitoring requests
	 * *  Calls to any <kbd>/health</kbd> endpoint
	 * *  Device bootstrap process requests related to configuring and retrieving device credentials
	 * *  Microservice SDK internal calls for applications and subscriptions
	 * 
	 */
	var requestCount: Int? = null

	/**
	 * Resources usage for each subscribed microservice application.
	 */
	var resources: UsageStatisticsResources? = null

	/**
	 * Database storage in use, specified in bytes. It is affected by your retention rules and by the regularly running database optimization functions in Cumulocity IoT. If the size decreases, it does not necessarily mean that data was deleted. Updated only three times a day starting at 8:57, 16:57 and 23:57.
	 */
	var storageSize: Int? = null

	/**
	 * Names of the tenant subscribed applications. Updated only three times a day starting at 8:57, 16:57 and 23:57.
	 */
	var subscribedApplications: Array<String>? = null

	/**
	 * Sum of all inbound transfers.
	 */
	var totalResourceCreateAndUpdateCount: Int? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
