// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson

class AuditApiResource {

	/**
	 * Collection of audit records
	 */
	var auditRecords: AuditRecords? = null

	/**
	 * Read-only collection of audit records for a specific application. The placeholder {application} must be the name of a registered application.
	 */
	var auditRecordsForApplication: String? = null

	/**
	 * Read-only collection of audit records for a specific type.
	 */
	var auditRecordsForType: String? = null

	/**
	 * Read-only collection of audit records for a specific user. The placeholder {user} must be a username of a registered user.
	 */
	var auditRecordsForUser: String? = null

	/**
	 * Read-only collection of audit records for specific type and application.
	 */
	var auditRecordsForTypeAndApplication: String? = null

	/**
	 * Read-only collection of audit records for specific type, user and application.
	 */
	var auditRecordsForTypeAndUserAndApplication: String? = null

	/**
	 * Read-only collection of audit records for specific user and application.
	 */
	var auditRecordsForUserAndApplication: String? = null

	/**
	 * Read-only collection of audit records for specific user and type.
	 */
	var auditRecordsForUserAndType: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * Collection of audit records
	 */
	class AuditRecords {
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		var auditRecords: Array<AuditRecord>? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
