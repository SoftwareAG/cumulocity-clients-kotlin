// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * The term "agent" refers to the piece of software that connects a device with Cumulocity IoT.
 */
data class C8yAgent(var name: String?, var version: String?) {
	constructor() : this(name = null, version = null)

	/**
	 * The URL of the agent, for example, its code repository.
	 */
	var url: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
