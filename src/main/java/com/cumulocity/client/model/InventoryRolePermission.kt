// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * A permission object of an inventory role.
 */
class InventoryRolePermission {

	/**
	 * A unique identifier for this permission.
	 */
	var id: Int? = null

	/**
	 * The permission level.
	 */
	var permission: Permission? = null

	/**
	 * The scope of this permission.
	 */
	var scope: Scope? = null

	/**
	 * The type of this permission. It can be the name of a fragment, for example, `c8y_Restart`.
	 */
	var type: String? = null

	/**
	 * The permission level.
	 */
	enum class Permission(val value: String) {
		@SerializedName(value = "ADMIN")
		ADMIN("ADMIN"),
		@SerializedName(value = "READ")
		READ("READ"),
		@SerializedName(value = "*")
		ALL("*")
	}

	/**
	 * The scope of this permission.
	 */
	enum class Scope(val value: String) {
		@SerializedName(value = "ALARM")
		ALARM("ALARM"),
		@SerializedName(value = "AUDIT")
		AUDIT("AUDIT"),
		@SerializedName(value = "EVENT")
		EVENT("EVENT"),
		@SerializedName(value = "MANAGED_OBJECT")
		MANAGEDOBJECT("MANAGED_OBJECT"),
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
