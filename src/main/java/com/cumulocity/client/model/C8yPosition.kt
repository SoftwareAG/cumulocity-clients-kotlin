// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Reports the geographical location of an asset in terms of latitude, longitude and altitude.
 * 
 * Altitude is given in meters. To report the current location of an asset or a device, `c8y_Position` is added to the managed object representing the asset or device. To trace the position of an asset or a device, `c8y_Position` is sent as part of an event of type `c8y_LocationUpdate`.
 */
class C8yPosition {

	/**
	 * In meters.
	 */
	var alt: Number? = null

	var lng: Number? = null

	var lat: Number? = null

	/**
	 * Describes in which protocol the tracking context of a positioning report was sent.
	 */
	var trackingProtocol: String? = null

	/**
	 * Describes why the tracking context of a positioning report was sent.
	 */
	var reportReason: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
