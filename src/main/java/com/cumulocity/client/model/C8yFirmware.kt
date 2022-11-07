// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Contains information on a device's firmware. In the inventory, `c8y_Firmware` represents the currently installed firmware on the device. As part of an operation, `c8y_Firmware` requests the device to install the indicated firmware. To enable firmware installation through the user interface, add `c8y_Firmware` to the list of supported operations.
 */
class C8yFirmware {

	/**
	 * Name of the firmware.
	 */
	var name: String? = null

	/**
	 * A version identifier of the firmware.
	 */
	var version: String? = null

	/**
	 * A URI linking to the location to download the firmware from.
	 */
	var url: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
