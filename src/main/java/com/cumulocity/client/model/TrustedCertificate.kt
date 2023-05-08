// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class TrustedCertificate {

	/**
	 * Algorithm used to decode/encode the certificate.
	 */
	var algorithmName: String? = null

	/**
	 * Indicates whether the automatic device registration is enabled or not.
	 */
	var autoRegistrationEnabled: Boolean? = null

	/**
	 * Trusted certificate in PEM format.
	 */
	var certInPemFormat: String? = null

	/**
	 * Unique identifier of the trusted certificate.
	 */
	var fingerprint: String? = null

	/**
	 * The name of the organization which signed the certificate.
	 */
	var issuer: String? = null

	/**
	 * Name of the certificate.
	 */
	var name: String? = null

	/**
	 * The end date and time of the certificate's validity.
	 */
	var notAfter: String? = null

	/**
	 * The start date and time of the certificate's validity.
	 */
	var notBefore: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The certificate's serial number.
	 */
	var serialNumber: String? = null

	/**
	 * Indicates if the certificate is active and can be used by the device to establish a connection to the Cumulocity IoT platform.
	 */
	var status: Status? = null

	/**
	 * Name of the organization to which the certificate belongs.
	 */
	var subject: String? = null

	/**
	 * Version of the X.509 certificate standard.
	 */
	var version: Int? = null

	/**
	 * Indicates if the certificate is active and can be used by the device to establish a connection to the Cumulocity IoT platform.
	 */
	enum class Status(val value: String) {
		@SerializedName(value = "ENABLED")
		ENABLED("ENABLED"),
		@SerializedName(value = "DISABLED")
		DISABLED("DISABLED")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
