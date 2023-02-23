// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class RetentionRule {

	/**
	 * The data type(s) to which the rule is applied.
	 */
	var dataType: DataType? = null

	/**
	 * Indicates whether the rule is editable or not. It can be updated only by the Management tenant.
	 */
	var editable: Boolean? = null

	/**
	 * The fragment type(s) to which the rule is applied. Used by the data types EVENT, MEASUREMENT, OPERATION and BULK_OPERATION.
	 */
	var fragmentType: String? = null

	/**
	 * Unique identifier of the retention rule.
	 */
	var id: String? = null

	/**
	 * Maximum age expressed in number of days.
	 */
	var maximumAge: Int? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The source(s) to which the rule is applied. Used by all data types.
	 */
	var source: String? = null

	/**
	 * The type(s) to which the rule is applied. Used by the data types ALARM, AUDIT, EVENT and MEASUREMENT.
	 */
	var type: String? = null

	/**
	 * The data type(s) to which the rule is applied.
	 */
	enum class DataType(val value: String) {
		@SerializedName(value = "ALARM")
		ALARM("ALARM"),
		@SerializedName(value = "AUDIT")
		AUDIT("AUDIT"),
		@SerializedName(value = "BULK_OPERATION")
		BULKOPERATION("BULK_OPERATION"),
		@SerializedName(value = "EVENT")
		EVENT("EVENT"),
		@SerializedName(value = "MEASUREMENT")
		MEASUREMENT("MEASUREMENT"),
		@SerializedName(value = "OPERATION")
		OPERATION("OPERATION"),
		@SerializedName(value = "*")
		ALL("*")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
