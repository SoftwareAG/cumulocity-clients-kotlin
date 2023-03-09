// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class PlatformApiResource {

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	var alarm: AlarmsApiResource? = null

	var audit: AuditApiResource? = null

	var deviceControl: DeviceControlApiResource? = null

	var event: EventsApiResource? = null

	var identity: IdentityApiResource? = null

	var inventory: InventoryApiResource? = null

	var measurement: MeasurementApiResource? = null

	var tenant: TenantApiResource? = null

	var user: UserApiResource? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
