// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

class DeviceControlApiResource {

	/**
	 * Collection of all operations.
	 */
	var operations: Operations? = null

	/**
	 * Read-only collection of all operations with a particular status.
	 */
	var operationsByStatus: String? = null

	/**
	 * Read-only collection of all operations targeting a particular agent.
	 */
	var operationsByAgentId: String? = null

	/**
	 * Read-only collection of all operations targeting a particular agent and with a particular status.
	 */
	var operationsByAgentIdAndStatus: String? = null

	/**
	 * Read-only collection of all operations to be executed on a particular device.
	 */
	var operationsByDeviceId: String? = null

	/**
	 * Read-only collection of all operations with a particular status, that should be executed on a particular device.
	 */
	var operationsByDeviceIdAndStatus: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Collection of all operations.
	 */
	class Operations {
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		/**
		 * An array containing the registered operations.
		 */
		var operations: Array<OperationReference>? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
