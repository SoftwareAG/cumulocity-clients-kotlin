// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Holds basic connectivity-related information, such as the equipment identifier of the modem (IMEI) in the device. This identifier is globally unique and often used to identify a mobile device.
 */
data class C8yMobile(var imei: String?, var cellId: String?, var iccid: String?) {
	constructor() : this(imei = null, cellId = null, iccid = null)

	/**
	 * Other possible values are: `c8y_Mobile.imsi`, `c8y_Mobile.currentOperator`, `c8y_Mobile.currentBand`, `c8y_Mobile.connType`, `c8y_Mobile.rssi`, `c8y_Mobile.ecn0`, `c8y_Mobile.rcsp`, `c8y_Mobile.mnc`, `c8y_Mobile.lac` and `c8y_Mobile.msisdn`.
	 */
	var customFragments: MutableMap<String, String> = hashMapOf()
	
	operator fun get(key: String): String? = customFragments[key]
	operator fun set(key: String, value: String): String? = customFragments.put(key, value)

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
