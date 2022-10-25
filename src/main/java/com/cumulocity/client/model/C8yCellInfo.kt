// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Provides detailed information about the closest mobile cell towers. When the functionality is activated, the location of the device is determined based on this fragment, in order to track the device whereabouts when GPS tracking is not available.
 */
data class C8yCellInfo(var cellTowers: Array<C8yCellTower>?) {
	constructor() : this(cellTowers = null)

	/**
	 * The radio type of this cell tower.
	 */
	var radioType: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
