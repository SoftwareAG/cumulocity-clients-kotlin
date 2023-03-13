// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Device capability to manage device profiles. Device profiles represent a combination of a firmware version, one or multiple software packages and one or multiple configuration files which can be deployed on a device.
 */
class C8yProfile {

	/**
	 * The name of the profile.
	 */
	var profileName: String? = null

	/**
	 * The ID of the profile.
	 */
	var profileId: String? = null

	/**
	 * Indicates whether the profile has been executed.
	 */
	var profileExecuted: Boolean? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
