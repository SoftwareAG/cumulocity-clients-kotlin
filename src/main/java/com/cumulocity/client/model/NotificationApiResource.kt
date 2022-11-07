// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class NotificationApiResource {

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Collection of all notification subscriptions.
	 */
	var notificationSubscriptions: NotificationSubscriptions? = null

	/**
	 * Read-only collection of all notification subscriptions for a specific source object. The placeholder {source} must be a unique ID of an object in the inventory.
	 */
	var notificationSubscriptionsBySource: String? = null

	/**
	 * Read-only collection of all notification subscriptions of a particular context and a specific source object.
	 */
	var notificationSubscriptionsBySourceAndContext: String? = null

	/**
	 * Read-only collection of all notification subscriptions of a particular context.
	 */
	var notificationSubscriptionsByContext: String? = null

	/**
	 * Collection of all notification subscriptions.
	 */
	class NotificationSubscriptions {
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		var subscriptions: Array<NotificationSubscription>? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
