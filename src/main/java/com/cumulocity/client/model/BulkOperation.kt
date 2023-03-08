// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class BulkOperation {

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Unique identifier of this bulk operation.
	 */
	var id: String? = null

	/**
	 * Identifies the target group on which this operation should be performed.
	 * 
	 * > **ⓘ Info:** `groupId` and `failedParentId` are mutually exclusive. Use only one of them in your request.
	 */
	var groupId: String? = null

	/**
	 * Identifies the failed bulk operation from which the failed operations should be rescheduled.
	 * 
	 * > **ⓘ Info:** `groupId` and `failedParentId` are mutually exclusive. Use only one of them in your request.
	 */
	var failedParentId: String? = null

	/**
	 * Date and time when the operations of this bulk operation should be created.
	 */
	var startDate: String? = null

	/**
	 * Delay between every operation creation in seconds.
	 */
	var creationRamp: Float? = null

	/**
	 * Operation to be executed for every device in a group.
	 */
	var operationPrototype: OperationPrototype? = null

	/**
	 * The status of this bulk operation, in context of the execution of all its single operations.
	 */
	var status: Status? = null

	/**
	 * The general status of this bulk operation. The general status is visible for end users and they can filter and evaluate bulk operations by this status.
	 */
	var generalStatus: GeneralStatus? = null

	/**
	 * Contains information about the number of processed operations.
	 */
	var progress: Progress? = null

	/**
	 * The status of this bulk operation, in context of the execution of all its single operations.
	 */
	enum class Status(val value: String) {
		@SerializedName(value = "ACTIVE")
		ACTIVE("ACTIVE"),
		@SerializedName(value = "IN_PROGRESS")
		INPROGRESS("IN_PROGRESS"),
		@SerializedName(value = "COMPLETED")
		COMPLETED("COMPLETED"),
		@SerializedName(value = "DELETED")
		DELETED("DELETED")
	}

	/**
	 * The general status of this bulk operation. The general status is visible for end users and they can filter and evaluate bulk operations by this status.
	 */
	enum class GeneralStatus(val value: String) {
		@SerializedName(value = "SCHEDULED")
		SCHEDULED("SCHEDULED"),
		@SerializedName(value = "EXECUTING")
		EXECUTING("EXECUTING"),
		@SerializedName(value = "EXECUTING_WITH_ERRORS")
		EXECUTINGWITHERRORS("EXECUTING_WITH_ERRORS"),
		@SerializedName(value = "SUCCESSFUL")
		SUCCESSFUL("SUCCESSFUL"),
		@SerializedName(value = "FAILED")
		FAILED("FAILED"),
		@SerializedName(value = "CANCELED")
		CANCELED("CANCELED")
	}

	/**
	 * Operation to be executed for every device in a group.
	 */
	class OperationPrototype {
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}



	/**
	 * Contains information about the number of processed operations.
	 */
	class Progress {
	
		/**
		 * Number of pending operations.
		 */
		var pending: Int? = null
	
		/**
		 * Number of failed operations.
		 */
		var failed: Int? = null
	
		/**
		 * Number of operations being executed.
		 */
		var executing: Int? = null
	
		/**
		 * Number of operations successfully processed.
		 */
		var successful: Int? = null
	
		/**
		 * Total number of processed operations.
		 */
		var all: Int? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
