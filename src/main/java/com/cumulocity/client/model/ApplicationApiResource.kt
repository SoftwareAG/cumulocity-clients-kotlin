// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

class ApplicationApiResource {

	/**
	 * Collection of all applications..
	 */
	var applications: String? = null

	/**
	 * A reference to a resource of type Application.
	 */
	var applicationById: String? = null

	/**
	 * Read-only collection of all applications with a particular name.
	 */
	var applicationsByName: String? = null

	/**
	 * Read-only collection of all applications subscribed by a particular tenant.
	 */
	var applicationsByTenant: String? = null

	/**
	 * Read-only collection of all applications owned by a particular tenant.
	 */
	var applicationsByOwner: String? = null

	/**
	 * Read-only collection of all applications owned by a particular user.
	 */
	var applicationsByUser: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
