// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class OperationReference {

	/**
	 * The referenced operation.
	 */
	var operation: Operation? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The referenced operation.
	 */
	class Operation {
	
		/**
		 * Unique identifier of this operation.
		 */
		var id: String? = null
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
