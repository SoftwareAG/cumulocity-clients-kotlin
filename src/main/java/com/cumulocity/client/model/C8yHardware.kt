// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Contains basic hardware information for a device, such as make and serial number. Often, the hardware serial number is printed on the board of the device or on an asset tag on the device to uniquely identify the device within all devices of the same make.
 */
class C8yHardware {

	/**
	 * A text identifier of the device's hardware model.
	 */
	var model: String? = null

	/**
	 * A text identifier of the hardware revision.
	 */
	var revision: String? = null

	/**
	 * The hardware serial number of the device.
	 */
	var serialNumber: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
