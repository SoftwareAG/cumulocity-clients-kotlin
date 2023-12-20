// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

class VerifyCertificateChain {

	/**
	 * The result of validating the certificate chain.
	 */
	var successfullyValidated: Boolean? = null

	/**
	 * The tenant ID used for validation.
	 */
	var tenantId: String? = null

	/**
	 * The name of the organization which signed the certificate.
	 */
	var issuer: String? = null

	/**
	 * The name of the organization to which the certificate belongs.
	 */
	var subject: String? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
