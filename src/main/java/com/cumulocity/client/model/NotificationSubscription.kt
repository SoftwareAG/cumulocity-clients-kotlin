// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class NotificationSubscription(var context: Context?, var subscription: String?) {
	constructor() : this(context = null, subscription = null)

	/**
	 * Transforms the data to *only* include specified custom fragments. Each custom fragment is identified by a unique name. If nothing is specified here, the data is forwarded as-is.
	 */
	var fragmentsToCopy: Array<String>? = null

	/**
	 * Unique identifier of the subscription.
	 */
	var id: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The managed object to which the subscription is associated.
	 */
	var source: Source? = null

	/**
	 * Applicable filters to the subscription.
	 */
	var subscriptionFilter: SubscriptionFilter? = null

	
	/**
	 * The context within which the subscription is to be processed.
	 * > **&#9432; Info:** If the value is `mo`, then `source` must also be provided in the request body.
	 * 
	 * [mo, tenant]
	 */
	enum class Context(val value: String) {
		@SerializedName(value = "mo")
		MO("mo"),
		@SerializedName(value = "tenant")
		TENANT("tenant")
	}


	/**
	 * The managed object to which the subscription is associated.
	 */
	class Source {
	
		/**
		 * Unique identifier of the object.
		 */
		var id: String? = null
	
		/**
		 * Human-readable name that is used for representing the object in user interfaces.
		 */
		var name: String? = null
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	/**
	 * Applicable filters to the subscription.
	 */
	class SubscriptionFilter {
	
		/**
		 * The Notifications are available for Alarms, Device control, Events, Inventory and Measurements for the `mo` context and for Alarms and Inventory for the `tenant` context. Alternatively, the wildcard `*` can be used to match all the permissible APIs within the bound context.
		 * 
		 * > **&#9432; Info:** the wildcard `*` cannot be used in conjunction with other values.
		 * 
		 */
		var api: Array<String>? = null
	
		/**
		 * The data needs to have the specified value in its `type` property to meet the filter criteria.
		 */
		var typeFilter: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
