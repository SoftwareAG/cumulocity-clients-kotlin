// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Text configuration fragment that allows you to configure parameters and initial settings of your device.
 * 
 * In the inventory, `c8y_Configuration` represents the currently active configuration on the device. As part of an operation, `c8y_Configuration` requests the device to switch the transmitted configuration to the currently active one. To enable configuration through the user interface, add `c8y_Configuration` to the list of supported operations.
 * 
 */
class C8yConfiguration {

	/**
	 * A text in a device-specific format, representing the configuration of the device.
	 */
	var config: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
