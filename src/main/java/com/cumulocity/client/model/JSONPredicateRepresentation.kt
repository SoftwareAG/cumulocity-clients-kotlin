// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Represents a predicate for verification. It acts as a condition which is necessary to assign a user to the given groups and permit access to the specified applications.
 */
class JSONPredicateRepresentation {

	/**
	 * Nested predicates.
	 */
	var childPredicates: Array<JSONPredicateRepresentation>? = null

	/**
	 * Operator executed on the parameter from the JWT access token claim pointed by `parameterPath` and the provided parameter `value`.
	 */
	var operator: Operator? = null

	/**
	 * Path to the claim from the JWT access token from the external authorization server.
	 */
	var parameterPath: String? = null

	/**
	 * Given value used for parameter verification.
	 */
	var value: String? = null

	/**
	 * Operator executed on the parameter from the JWT access token claim pointed by `parameterPath` and the provided parameter `value`.
	 */
	enum class Operator(val value: String) {
		@SerializedName(value = "EQ")
		EQ("EQ"),
		@SerializedName(value = "NEQ")
		NEQ("NEQ"),
		@SerializedName(value = "GT")
		GT("GT"),
		@SerializedName(value = "LT")
		LT("LT"),
		@SerializedName(value = "GTE")
		GTE("GTE"),
		@SerializedName(value = "LTE")
		LTE("LTE"),
		@SerializedName(value = "IN")
		IN("IN"),
		@SerializedName(value = "AND")
		AND("AND"),
		@SerializedName(value = "OR")
		OR("OR")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
