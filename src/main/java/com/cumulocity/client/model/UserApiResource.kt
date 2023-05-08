// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

class UserApiResource {

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Collection of all users belonging to a given tenant.
	 */
	var users: String? = null

	/**
	 * Reference to a resource of type user.
	 */
	var userByName: String? = null

	/**
	 * Reference to the resource of the logged in user.
	 */
	var currentUser: String? = null

	/**
	 * Collection of all users belonging to a given tenant.
	 */
	var groups: String? = null

	/**
	 * Reference to a resource of type group.
	 */
	var groupByName: String? = null

	/**
	 * Collection of all roles.
	 */
	var roles: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
