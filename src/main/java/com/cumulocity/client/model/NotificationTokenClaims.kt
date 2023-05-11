// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class NotificationTokenClaims(var subscriber: String?, var subscription: String?) {
	constructor() : this(subscriber = null, subscription = null)

	/**
	 * The token expiration duration.
	 */
	var expiresInMinutes: Int? = null

	/**
	 * The subscription type. Currently the only supported type is `notification` .Other types may be added in future.
	 */
	var type: Type? = null

	/**
	 * If `true`, the token will be securely signed by the Cumulocity IoT platform.
	 */
	var signed: Boolean? = null

	/**
	 * If `true`, indicates that the token is used to create a shared consumer on the subscription.
	 */
	var shared: Boolean? = null

	/**
	 * If `true`, indicates that the created token refers to the non-persistent variant of the named subscription.
	 */
	var nonPersistent: Boolean? = null

	/**
	 * The subscription type. Currently the only supported type is `notification` .Other types may be added in future.
	 */
	enum class Type(val value: String) {
		@SerializedName(value = "notification")
		NOTIFICATION("notification")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
