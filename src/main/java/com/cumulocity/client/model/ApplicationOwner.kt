// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

/**
 * Reference to the tenant owning this application. The default value is a reference to the current tenant.
 */
class ApplicationOwner {

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	var tenant: Tenant? = null

	class Tenant {
	
		/**
		 * The tenant ID.
		 */
		var id: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
