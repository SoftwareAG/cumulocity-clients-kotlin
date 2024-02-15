// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Detailed information about a neighbouring cell tower.
 */
data class C8yCellTower(var mobileCountryCode: Number?, var mobileNetworkCode: Number?, var locationAreaCode: Number?, var cellId: Number?) {
	constructor() : this(mobileCountryCode = null, mobileNetworkCode = null, locationAreaCode = null, cellId = null)

	/**
	 * The radio type of this cell tower. Can also be put directly in root JSON element if all cellTowers have same radioType.
	 */
	var radioType: String? = null

	/**
	 * The timing advance value for this cell tower when available.
	 */
	var timingAdvance: Number? = null

	/**
	 * The signal strength for this cell tower in dBm.
	 */
	var signalStrength: Number? = null

	/**
	 * The primary scrambling code for WCDMA and physical CellId for LTE.
	 */
	var primaryScramblingCode: Number? = null

	/**
	 * Specify with 0/1 if the cell is serving or not. If not specified, the first cell is assumed to be serving.
	 */
	var serving: Number? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
