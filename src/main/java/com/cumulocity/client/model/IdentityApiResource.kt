// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

class IdentityApiResource {

	/**
	 * Single external ID, represented by the type and the value of the external ID.
	 */
	var externalId: String? = null

	/**
	 * Represents a collection of external IDs for a specified global ID.
	 */
	var externalIdsOfGlobalId: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
