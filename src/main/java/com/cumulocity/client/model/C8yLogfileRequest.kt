// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Request a device to send a log file and view it in Cumulocity IoT's log viewer.
 */
class C8yLogfileRequest {

	/**
	 * Indicates the log file to select.
	 */
	var logFile: String? = null

	/**
	 * Start date and time of log entries in the log file to be sent.
	 */
	var dateFrom: String? = null

	/**
	 * End date and time of log entries in the log file to be sent.
	 */
	var dateTo: String? = null

	/**
	 * Provide a text that needs to be present in the log entry.
	 */
	var searchText: String? = null

	/**
	 * Upper limit of the number of lines that should be sent to Cumulocity IoT after filtering.
	 */
	var maximumLines: Int? = null

	/**
	 * A link to the log file request.
	 */
	var file: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
