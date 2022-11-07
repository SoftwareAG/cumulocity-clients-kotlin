// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

class ApplicationManifestProbe {

	/**
	 * The probe failure threshold.
	 */
	var failureThreshold: Int? = null

	/**
	 * The probe period in seconds.
	 */
	var periodSeconds: Int? = null

	/**
	 * The probe timeout in seconds.
	 */
	var timeoutSeconds: Int? = null

	/**
	 * The probe success threshold.
	 */
	var successThreshold: Int? = null

	/**
	 * The probe's initial delay in seconds.
	 */
	var initialDelaySeconds: Int? = null

	/**
	 * The probe's HTTP GET method information.
	 */
	var httpGet: HttpGet? = null

	/**
	 * The probe's HTTP GET method information.
	 */
	class HttpGet {
	
		/**
		 * The HTTP path.
		 */
		var path: String? = null
	
		/**
		 * The HTTP port.
		 */
		var port: Int? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
