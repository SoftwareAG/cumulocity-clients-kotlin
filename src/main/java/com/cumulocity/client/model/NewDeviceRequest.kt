// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class NewDeviceRequest {

	/**
	 * External ID of the device.
	 */
	var id: String? = null

	/**
	 * ID of the group to which the device will be assigned.
	 */
	var groupId: String? = null

	/**
	 * Type of the device.
	 */
	var type: String? = null

	/**
	 * Tenant who owns the device.
	 */
	var tenantId: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Status of this new device request.
	 */
	var status: Status? = null

	/**
	 * Owner of the device.
	 */
	var owner: String? = null

	/**
	 * Date and time when the device was created in the database.
	 */
	var creationTime: String? = null

	/**
	 * When accepting a device request, the security token is verified against the token submitted by the device when requesting credentials.See [Security token policy](https://cumulocity.com/docs/device-management-application/registering-devices/#security-token-policy) for details on configuration.See [Create device credentials](/#operation/postDeviceCredentialsCollectionResource) for details on creating token for device registration.`securityToken` parameter can be added only when submitting `ACCEPTED` status.
	 */
	var securityToken: String? = null

	/**
	 * Status of this new device request.
	 */
	enum class Status(val value: String) {
		@SerializedName(value = "WAITING_FOR_CONNECTION")
		WAITINGFORCONNECTION("WAITING_FOR_CONNECTION"),
		@SerializedName(value = "PENDING_ACCEPTANCE")
		PENDINGACCEPTANCE("PENDING_ACCEPTANCE"),
		@SerializedName(value = "ACCEPTED")
		ACCEPTED("ACCEPTED")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
