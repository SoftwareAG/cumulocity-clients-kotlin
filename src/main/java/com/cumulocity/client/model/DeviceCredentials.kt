// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

class DeviceCredentials {

	/**
	 * The external ID of the device.
	 */
	var id: String? = null

	/**
	 * Password of these device credentials.
	 */
	var password: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Tenant ID for these device credentials.
	 */
	var tenantId: String? = null

	/**
	 * Username of these device credentials.
	 */
	var username: String? = null

	/**
	 * Security token which is required and verified against during device request acceptance.See [Security token policy](https://cumulocity.com/docs/device-management-application/registering-devices/#security-token-policy) for more details on configuration.See [Update specific new device request status](/#operation/putNewDeviceRequestResource) for details on submitting token upon device acceptance.
	 */
	var securityToken: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
