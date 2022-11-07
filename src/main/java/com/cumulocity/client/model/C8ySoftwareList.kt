// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Details of the installed software.
 */
class C8ySoftwareList {

	/**
	 * The name of the software.
	 */
	var name: String? = null

	/**
	 * The version of the software.
	 */
	var version: String? = null

	/**
	 * The URL of the software, for example, its code repository.
	 */
	var url: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
