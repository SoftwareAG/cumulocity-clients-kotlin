// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class InventoryRoleCollection {

	/**
	 * A URI reference [[RFC3986](https://tools.ietf.org/html/rfc3986)] to a potential next page of managed objects.
	 */
	var next: String? = null

	/**
	 * A URI reference [[RFC3986](https://tools.ietf.org/html/rfc3986)] to a potential previous page of managed objects.
	 */
	var prev: String? = null

	/**
	 * An array of inventory roles.
	 */
	var roles: Array<InventoryRole>? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Information about paging statistics.
	 */
	var statistics: PageStatistics? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
