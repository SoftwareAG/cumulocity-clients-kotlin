// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class TenantApiResource {

	/**
	 * Collection of tenant options
	 */
	var options: Options? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Collection of subtenants
	 */
	var tenants: Tenants? = null

	/**
	 * Retrieves subscribed applications.
	 */
	var tenantApplications: String? = null

	/**
	 * Represents an individual application reference that can be viewed.
	 */
	var tenantApplicationForId: String? = null

	/**
	 * Represents an individual tenant that can be viewed.
	 */
	var tenantForId: String? = null

	/**
	 * Represents a category of tenant options.
	 */
	var tenantOptionsForCategory: String? = null

	/**
	 * Retrieves a key of the category of tenant options.
	 */
	var tenantOptionForCategoryAndKey: String? = null

	/**
	 * Retrieves the tenant system options.
	 */
	var tenantSystemOptions: String? = null

	/**
	 * Retrieves the tenant system options based on category and key.
	 */
	var tenantSystemOptionsForCategoryAndKey: String? = null

	/**
	 * Collection of tenant options
	 */
	class Options {
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		var options: Array<Option>? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	/**
	 * Collection of subtenants
	 */
	class Tenants {
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		var tenants: Array<Tenant>? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
