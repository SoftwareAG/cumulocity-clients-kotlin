// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class CurrentTenant {

	/**
	 * Indicates if this tenant can create subtenants.
	 */
	var allowCreateTenants: Boolean? = null

	/**
	 * Collection of the subscribed applications.
	 */
	var applications: Applications? = null

	/**
	 * An object with a list of custom properties.
	 */
	var customProperties: CustomProperties? = null

	/**
	 * URL of the tenant's domain. The domain name permits only the use of alphanumeric characters separated by dots `.`, hyphens `-` and underscores `_`.
	 */
	var domainName: String? = null

	/**
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	var name: String? = null

	/**
	 * ID of the parent tenant.
	 */
	var parent: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Collection of the subscribed applications.
	 */
	class Applications {
	
		/**
		 * An array containing all subscribed applications.
		 */
		var references: Array<Application>? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
