// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
	 * Indicates whether the messages for this subscription are persistent or non-persistent, meaning they can be lost if consumer is not connected.
	 */
	var nonPersistent: Boolean? = null

	/**
	 * The context within which the subscription is to be processed.
	 * 
	 * > **ⓘ Info:** If the value is `mo`, then `source` must also be provided in the request body.
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
		 * For the `mo` (Managed object) context, notifications from the `alarms`, `alarmsWithChildren`, `events`, `eventsWithChildren`, `managedobjects` (Inventory), `measurements` and `operations` (Device control) APIs can be subscribed to.
		 * The `alarmsWithChildren` and `eventsWithChildren` APIs subscribe to alarms and events respectively from the managed object identified by the `source.id` field, and all of its descendant managed objects.
		 * 
		 * For the `tenant` context, notifications from the `alarms`, `events` and `managedobjects` (Inventory) APIs can be subscribed to.
		 * 
		 * For all contexts, the `*` (wildcard) value can be used to subscribe to notifications from all of the available APIs in that context.
		 * 
		 * > **ⓘ Info:** The wildcard `*` cannot be used in conjunction with other values.
		 * > **ⓘ Info:** When filtering Events in the `tenant` context it is required to also specify the `typeFilter`.
		 */
		var apis: Array<String>? = null
	
		/**
		 * Used to match the `type` property of the data. This must either be a string to match one specific type exactly, or be an `or` OData expression, allowing the filter to match any one of a number of types.
		 * 
		 * > **ⓘ Info:** The use of a `type` attribute is assumed, for example when using only a string literal `'c8y_Temperature'` (or using `c8y_Temperature`, as quotes can be omitted when matching a single type) it is equivalent to a `type eq 'c8y_Temperature'` OData expression.
		 * > **ⓘ Info:** Currently only the `or` operator is allowed when using an OData expression. Example usage is `'c8y_Temperature' or 'c8y_Pressure'` which will match all the data with types `c8y_Temperature` or `c8y_Pressure`.
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
