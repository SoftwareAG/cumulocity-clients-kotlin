// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class ApplicationUserCollection {

	/**
	 * A list of users who are subscribed to the current application.
	 */
	var users: Array<Users>? = null

	/**
	 * A user who is subscribed to the current application.
	 */
	class Users {
	
		/**
		 * The username.
		 */
		var name: String? = null
	
		/**
		 * The user password.
		 */
		var password: String? = null
	
		/**
		 * The user tenant.
		 */
		var tenant: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
