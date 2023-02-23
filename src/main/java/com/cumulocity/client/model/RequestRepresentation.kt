// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class RequestRepresentation {

	/**
	 * Body of the request.
	 */
	var body: String? = null

	/**
	 * Headers of the request.
	 */
	var headers: Headers? = null

	/**
	 * HTTP request method.
	 */
	var method: Method? = null

	/**
	 * Requested operation.
	 */
	var operation: Operation? = null

	/**
	 * Parameters of the request.
	 */
	var requestParams: RequestParams? = null

	/**
	 * Target of the request described as a URL.
	 */
	var url: String? = null

	/**
	 * HTTP request method.
	 */
	enum class Method(val value: String) {
		@SerializedName(value = "GET")
		GET("GET"),
		@SerializedName(value = "POST")
		POST("POST")
	}

	/**
	 * Requested operation.
	 */
	enum class Operation(val value: String) {
		@SerializedName(value = "EXECUTE")
		EXECUTE("EXECUTE"),
		@SerializedName(value = "REDIRECT")
		REDIRECT("REDIRECT")
	}

	/**
	 * Headers of the request.
	 */
	class Headers {
	
		/**
		 * It is possible to add an arbitrary number of headers as a list of key-value string pairs, for example, `"header": "value"`.
		 * 
		 */
		var requestHeaders: MutableMap<String, String> = hashMapOf()
		
		operator fun get(key: String): String? = requestHeaders[key]
		operator fun set(key: String, value: String): String? = requestHeaders.put(key, value)
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}



	/**
	 * Parameters of the request.
	 */
	class RequestParams {
	
		/**
		 * It is possible to add an arbitrary number of parameters as a list of key-value string pairs, for example, `"parameter": "value"`.
		 * 
		 */
		var requestParameters: MutableMap<String, String> = hashMapOf()
		
		operator fun get(key: String): String? = requestParameters[key]
		operator fun set(key: String, value: String): String? = requestParameters.put(key, value)
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
