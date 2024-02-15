// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

class ApplicationBinaries {

	/**
	 * An array of attachments.
	 */
	var attachments: Array<Attachments>? = null

	class Attachments {
	
		/**
		 * The application context path.
		 */
		var contextPath: String? = null
	
		/**
		 * The date and time when the attachment was created.
		 */
		var created: String? = null
	
		/**
		 * A description for the attachment.
		 */
		var description: String? = null
	
		/**
		 * A download URL for the attachment.
		 */
		var downloadUrl: String? = null
	
		/**
		 * The ID of the attachment.
		 */
		var id: String? = null
	
		/**
		 * The length of the attachment, in bytes.
		 */
		var length: Int? = null
	
		/**
		 * The name of the attachment.
		 */
		var name: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
