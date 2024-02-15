// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Binary {

	/**
	 * Fragment to identify this managed object as a file.
	 */
	@SerializedName(value = "c8y_IsBinary")
	var c8yIsBinary: C8yIsBinary? = null

	/**
	 * Media type of the file.
	 */
	var contentType: String? = null

	/**
	 * Unique identifier of the object.
	 */
	var id: String? = null

	/**
	 * Date and time of the file's last update.
	 */
	var lastUpdated: String? = null

	/**
	 * Size of the file in bytes.
	 */
	var length: Int? = null

	/**
	 * Name of the managed object. It is set from the `object` contained in the payload.
	 */
	var name: String? = null

	/**
	 * Username of the owner of the file.
	 */
	var owner: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Type of the managed object. It is set from the `object` contained in the payload.
	 */
	var type: String? = null

	/**
	 * Fragment to identify this managed object as a file.
	 */
	class C8yIsBinary {
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
