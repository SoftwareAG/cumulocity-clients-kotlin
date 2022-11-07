// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Tenant {

	/**
	 * Email address of the tenant's administrator.
	 */
	var adminEmail: String? = null

	/**
	 * Username of the tenant's administrator.
	 * > **&#9432; Info:** When it is provided in the request body, also `adminEmail` and `adminPass` must be provided.
	 * 
	 */
	var adminName: String? = null

	/**
	 * Password of the tenant's administrator.
	 */
	var adminPass: String? = null

	/**
	 * Indicates if this tenant can create subtenants.
	 */
	var allowCreateTenants: Boolean? = null

	/**
	 * Collection of the subscribed applications.
	 */
	var applications: Applications? = null

	/**
	 * Tenant's company name.
	 */
	var company: String? = null

	/**
	 * Name of the contact person.
	 */
	var contactName: String? = null

	/**
	 * Phone number of the contact person, provided in the international format, for example, +48 123 456 7890.
	 */
	var contactPhone: String? = null

	/**
	 * The date and time when the tenant was created.
	 */
	var creationTime: String? = null

	/**
	 * An object with a list of custom properties.
	 */
	var customProperties: CustomProperties? = null

	/**
	 * URL of the tenant's domain. The domain name permits only the use of alphanumeric characters separated by dots `.`, hyphens `-` and underscores `_`.
	 */
	var domain: String? = null

	/**
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	var id: String? = null

	/**
	 * Collection of the owned applications.
	 */
	var ownedApplications: OwnedApplications? = null

	/**
	 * ID of the parent tenant.
	 */
	var parent: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Current status of the tenant.
	 */
	var status: Status? = null

	/**
	 * Current status of the tenant.
	 * [ACTIVE, SUSPENDED]
	 */
	enum class Status(val value: String) {
		@SerializedName(value = "ACTIVE")
		ACTIVE("ACTIVE"),
		@SerializedName(value = "SUSPENDED")
		SUSPENDED("SUSPENDED")
	}

	/**
	 * Collection of the subscribed applications.
	 */
	class Applications {
	
		/**
		 * An array containing all subscribed applications.
		 */
		var references: Array<Application>? = null
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	/**
	 * Collection of the owned applications.
	 */
	class OwnedApplications {
	
		/**
		 * An array containing all owned applications (only applications with availability MARKET).
		 */
		var references: Array<Application>? = null
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
