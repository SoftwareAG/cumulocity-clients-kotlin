// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class ApplicationVersion {

	/**
	 * Unique identifier of the version.
	 */
	var version: String? = null

	/**
	 * Unique identifier of the binary file assigned to the version.
	 */
	var binaryId: String? = null

	/**
	 * Tag assigned to the version. Version tags must be unique across all versions and version fields of application versions.
	 */
	var tag: Array<String>? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
