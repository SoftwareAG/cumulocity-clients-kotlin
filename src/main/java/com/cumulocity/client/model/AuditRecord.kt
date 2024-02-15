// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonSerializer
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

@JsonAdapter(AuditRecord.JsonAdapter::class)
data class AuditRecord(var activity: String?, var source: Source?, var text: String?, var time: String?, var type: Type?) {

	companion object Serialization {
	
		@Transient
		var additionalPropertyClasses: MutableMap<String, Class<*>> = HashMap()
	
		fun registerAdditionalProperty(typeName: String, type: Class<*>) {
			additionalPropertyClasses[typeName] = type
		}
	}
	constructor() : this(activity = null, source = null, text = null, time = null, type = null)

	/**
	 * Name of the application that performed the action.
	 */
	var application: String? = null

	/**
	 * Metadata of the audit record.
	 */
	@SerializedName(value = "c8y_Metadata")
	var c8yMetadata: C8yMetadata? = null

	/**
	 * Collection of objects describing the changes that were carried out.
	 */
	var changes: Array<Changes>? = null

	/**
	 * The date and time when the audit record was created.
	 */
	var creationTime: String? = null

	/**
	 * Unique identifier of the audit record.
	 */
	var id: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The severity of the audit action.
	 */
	var severity: Severity? = null

	/**
	 * The user who carried out the activity.
	 */
	var user: String? = null

	/**
	 * It is possible to add an arbitrary number of additional properties as a list of key-value pairs, for example, `"property1": {}`, `"property2": "value"`. These properties can be of any type, for example, object or string.
	 */
	var customProperties: MutableMap<String, Any> = hashMapOf()
	
	operator fun get(key: String): Any? = customProperties[key]
	operator fun set(key: String, value: Any): Any? = customProperties.put(key, value)

	/**
	 * The severity of the audit action.
	 */
	enum class Severity(val value: String) {
		@SerializedName(value = "CRITICAL")
		CRITICAL("CRITICAL"),
		@SerializedName(value = "MAJOR")
		MAJOR("MAJOR"),
		@SerializedName(value = "MINOR")
		MINOR("MINOR"),
		@SerializedName(value = "WARNING")
		WARNING("WARNING"),
		@SerializedName(value = "INFORMATION")
		INFORMATION("INFORMATION")
	}

	/**
	 * Identifies the platform component of the audit.
	 */
	enum class Type(val value: String) {
		@SerializedName(value = "Alarm")
		ALARM("Alarm"),
		@SerializedName(value = "Application")
		APPLICATION("Application"),
		@SerializedName(value = "BulkOperation")
		BULKOPERATION("BulkOperation"),
		@SerializedName(value = "CepModule")
		CEPMODULE("CepModule"),
		@SerializedName(value = "Connector")
		CONNECTOR("Connector"),
		@SerializedName(value = "Event")
		EVENT("Event"),
		@SerializedName(value = "Group")
		GROUP("Group"),
		@SerializedName(value = "Inventory")
		INVENTORY("Inventory"),
		@SerializedName(value = "InventoryRole")
		INVENTORYROLE("InventoryRole"),
		@SerializedName(value = "Operation")
		OPERATION("Operation"),
		@SerializedName(value = "Option")
		OPTION("Option"),
		@SerializedName(value = "Report")
		REPORT("Report"),
		@SerializedName(value = "SingleSignOn")
		SINGLESIGNON("SingleSignOn"),
		@SerializedName(value = "SmartRule")
		SMARTRULE("SmartRule"),
		@SerializedName(value = "SYSTEM")
		SYSTEM("SYSTEM"),
		@SerializedName(value = "Tenant")
		TENANT("Tenant"),
		@SerializedName(value = "TenantAuthConfig")
		TENANTAUTHCONFIG("TenantAuthConfig"),
		@SerializedName(value = "TrustedCertificates")
		TRUSTEDCERTIFICATES("TrustedCertificates"),
		@SerializedName(value = "User")
		USER("User"),
		@SerializedName(value = "UserAuthentication")
		USERAUTHENTICATION("UserAuthentication")
	}

	/**
	 * Metadata of the audit record.
	 */
	class C8yMetadata {
	
		/**
		 * The action that was carried out.
		 */
		var action: Action? = null
	
		/**
		 * The action that was carried out.
		 */
		enum class Action(val value: String) {
			@SerializedName(value = "SUBSCRIBE")
			SUBSCRIBE("SUBSCRIBE"),
			@SerializedName(value = "DEPLOY")
			DEPLOY("DEPLOY"),
			@SerializedName(value = "SCALE")
			SCALE("SCALE"),
			@SerializedName(value = "DELETE")
			DELETE("DELETE")
		}
	
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	class Changes {
	
		/**
		 * The attribute that was changed.
		 */
		var attribute: String? = null
	
		/**
		 * The type of change that was carried out.
		 */
		var changeType: ChangeType? = null
	
		/**
		 * The new value of the object.
		 */
		var newValue: Any? = null
	
		/**
		 * The previous value of the object.
		 */
		var previousValue: Any? = null
	
		/**
		 * The type of the object.
		 */
		var type: String? = null
	
		/**
		 * The type of change that was carried out.
		 */
		enum class ChangeType(val value: String) {
			@SerializedName(value = "ADDED")
			ADDED("ADDED"),
			@SerializedName(value = "REPLACED")
			REPLACED("REPLACED")
		}
	
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}


	/**
	 * The managed object to which the audit is associated.
	 */
	data class Source(var id: String?) {
		constructor() : this(id = null)
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}

	class JsonAdapter: JsonDeserializer<AuditRecord>, JsonSerializer<AuditRecord> {
	
		override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): AuditRecord {
			val auditRecord = AuditRecord()
			json?.let {
				json.asJsonObject.entrySet().forEach { (key, value) ->
					try {
						val field = auditRecord.javaClass.getDeclaredField(key)
						field.isAccessible = true
						val item = context?.deserialize<Any>(value, field.type)
						field.set(auditRecord, item)
					} catch (e: NoSuchFieldException) {
						additionalPropertyClasses[key]?.let {
							val item = context?.deserialize<Any>(value, it)
							item?.let { auditRecord.customProperties.put(key, item) }
						}
					}
				}
			}
			return auditRecord
		}

		override fun serialize(src: AuditRecord?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
			val jsonTree = JsonObject()
			src?.activity?.let { it -> jsonTree.add("activity", context?.serialize(it)) }
			src?.application?.let { it -> jsonTree.add("application", context?.serialize(it)) }
			src?.c8yMetadata?.let { it -> jsonTree.add("c8y_Metadata", context?.serialize(it)) }
			src?.changes?.let { it -> jsonTree.add("changes", context?.serialize(it)) }
			src?.creationTime?.let { it -> jsonTree.add("creationTime", context?.serialize(it)) }
			src?.id?.let { it -> jsonTree.add("id", context?.serialize(it)) }
			src?.self?.let { it -> jsonTree.add("self", context?.serialize(it)) }
			src?.severity?.let { it -> jsonTree.add("severity", context?.serialize(it)) }
			src?.source?.let { it -> jsonTree.add("source", context?.serialize(it)) }
			src?.text?.let { it -> jsonTree.add("text", context?.serialize(it)) }
			src?.time?.let { it -> jsonTree.add("time", context?.serialize(it)) }
			src?.type?.let { it -> jsonTree.add("type", context?.serialize(it)) }
			src?.user?.let { it -> jsonTree.add("user", context?.serialize(it)) }
			src?.customProperties?.let { it ->
				it.forEach { (s, any) -> jsonTree.add(s, context?.serialize(any))}
			}
			return jsonTree
		}
	}
}
