// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class BulkNewDeviceRequest {

	/**
	 * Number of lines processed from the CSV file, without the first line (column headers).
	 */
	var numberOfAll: Int? = null

	/**
	 * Number of created device credentials.
	 */
	var numberOfCreated: Int? = null

	/**
	 * Number of failed creations of device credentials.
	 */
	var numberOfFailed: Int? = null

	/**
	 * Number of successful creations of device credentials. This counts both create and update operations.
	 */
	var numberOfSuccessful: Int? = null

	/**
	 * An array with the updated device credentials.
	 */
	var credentialUpdatedList: Array<CredentialUpdatedList>? = null

	/**
	 * An array with details of the failed device credentials.
	 */
	var failedCreationList: Array<FailedCreationList>? = null

	class CredentialUpdatedList {
	
		/**
		 * The device credentials creation status.
		 */
		var bulkNewDeviceStatus: NewDeviceStatus? = null
	
		/**
		 * Unique identifier of the device.
		 */
		var deviceId: String? = null
	
		/**
		 * The device credentials creation status.
		 */
		enum class NewDeviceStatus(val value: String) {
			@SerializedName(value = "CREATED")
			CREATED("CREATED"),
			@SerializedName(value = "FAILED")
			FAILED("FAILED"),
			@SerializedName(value = "CREDENTIAL_UPDATED")
			CREDENTIALUPDATED("CREDENTIAL_UPDATED")
		}
	
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	class FailedCreationList {
	
		/**
		 * The device credentials creation status.
		 */
		var bulkNewDeviceStatus: NewDeviceStatus? = null
	
		/**
		 * Unique identifier of the device.
		 */
		var deviceId: String? = null
	
		/**
		 * Reason for the failure.
		 */
		var failureReason: String? = null
	
		/**
		 * Line where the failure occurred.
		 */
		var line: String? = null
	
		/**
		 * The device credentials creation status.
		 */
		enum class NewDeviceStatus(val value: String) {
			@SerializedName(value = "CREATED")
			CREATED("CREATED"),
			@SerializedName(value = "FAILED")
			FAILED("FAILED"),
			@SerializedName(value = "CREDENTIAL_UPDATED")
			CREDENTIALUPDATED("CREDENTIAL_UPDATED")
		}
	
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
