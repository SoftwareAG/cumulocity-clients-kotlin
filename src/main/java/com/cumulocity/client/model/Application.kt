// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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

@JsonAdapter(Application.JsonAdapter::class)
class Application {
	
	companion object Serialization {
	
		@Transient
		var additionalPropertyClasses: MutableMap<String, Class<*>> = HashMap()
	
		fun registerAdditionalProperty(typeName: String, type: Class<*>) {
			additionalPropertyClasses[typeName] = type
		}
	}

	/**
	 * Application access level for other tenants.
	 */
	var availability: Availability? = null

	/**
	 * The context path in the URL makes the application accessible. Mandatory when the type of the application is `HOSTED`.
	 */
	var contextPath: String? = null

	/**
	 * Description of the application.
	 */
	var description: String? = null

	/**
	 * Unique identifier of the application.
	 */
	var id: String? = null

	/**
	 * Applications, regardless of their form, are identified by an application key.
	 */
	var key: String? = null

	/**
	 * Name of the application.
	 */
	var name: String? = null

	/**
	 * Reference to the tenant owning this application. The default value is a reference to the current tenant.
	 */
	var owner: ApplicationOwner? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The type of the application.
	 */
	var type: Type? = null

	var manifest: Any? = null

	/**
	 * Roles provided by the microservice.
	 */
	var roles: Array<String>? = null

	/**
	 * List of permissions required by a microservice to work.
	 */
	var requiredRoles: Array<String>? = null

	/**
	 * A flag to indicate if the application has a breadcrumbs navigation on the UI.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	var breadcrumbs: Boolean? = null

	/**
	 * The content security policy of the application.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	var contentSecurityPolicy: String? = null

	/**
	 * A URL to a JSON object with dynamic content options.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	var dynamicOptionsUrl: String? = null

	/**
	 * The global title of the application.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	var globalTitle: String? = null

	/**
	 * A flag that shows if the application is a legacy application or not.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	var legacy: Boolean? = null

	/**
	 * A flag to indicate if the application uses the UI context menu on the right side.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	var rightDrawer: Boolean? = null

	/**
	 * A flag that shows if the application is hybrid and using Angular and AngularJS simultaneously.
	 * > **&#9432; Info:** This property is specific to the web application type.
	 * 
	 */
	var upgrade: Boolean? = null

	/**
	 * The active version ID of the application. For microservice applications the active version ID is the microservice manifest version ID.
	 */
	var activeVersionId: String? = null

	/**
	 * URL to the application base directory hosted on an external server. Only present in legacy hosted applications.
	 */
	@Deprecated(message = "This property might be removed in future releases.")
	var resourcesUrl: String? = null

	
	/**
	 * Application access level for other tenants.
	 * [MARKET, PRIVATE]
	 */
	enum class Availability(val value: String) {
		@SerializedName(value = "MARKET")
		MARKET("MARKET"),
		@SerializedName(value = "PRIVATE")
		PRIVATE("PRIVATE")
	}

	
	/**
	 * The type of the application.
	 * [EXTERNAL, HOSTED, MICROSERVICE]
	 */
	enum class Type(val value: String) {
		@SerializedName(value = "EXTERNAL")
		EXTERNAL("EXTERNAL"),
		@SerializedName(value = "HOSTED")
		HOSTED("HOSTED"),
		@SerializedName(value = "MICROSERVICE")
		MICROSERVICE("MICROSERVICE")
	}



	class JsonAdapter: JsonDeserializer<Application>, JsonSerializer<Application> {
	
		override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): Application {
			val application = Application()
			json?.let {
				json.asJsonObject.entrySet().forEach { (key, value) ->
					try {
						val field = application.javaClass.getDeclaredField(key)
						field.isAccessible = true
						val item = context?.deserialize<Any>(value, field.type)
						field.set(application, item)
					} catch (e: NoSuchFieldException) {
						additionalPropertyClasses[key]?.let {
							val item = context?.deserialize<Any>(value, it)
							if (application.manifest == null) {
								application.manifest = HashMap()
							}
							item?.let { application.manifest?.put(key, item) }
						}
					}
				}
			}
			return application
		}
		
		override fun serialize(src: Application?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
			val jsonTree = JsonObject()
			src?.availability?.let { it -> jsonTree.add("availability", context?.serialize(it)) }
			src?.contextPath?.let { it -> jsonTree.add("contextPath", context?.serialize(it)) }
			src?.description?.let { it -> jsonTree.add("description", context?.serialize(it)) }
			src?.id?.let { it -> jsonTree.add("id", context?.serialize(it)) }
			src?.key?.let { it -> jsonTree.add("key", context?.serialize(it)) }
			src?.name?.let { it -> jsonTree.add("name", context?.serialize(it)) }
			src?.owner?.let { it -> jsonTree.add("owner", context?.serialize(it)) }
			src?.self?.let { it -> jsonTree.add("self", context?.serialize(it)) }
			src?.type?.let { it -> jsonTree.add("type", context?.serialize(it)) }
			src?.manifest?.let { it -> jsonTree.add("manifest", context?.serialize(it)) }
			src?.roles?.let { it -> jsonTree.add("roles", context?.serialize(it)) }
			src?.requiredRoles?.let { it -> jsonTree.add("requiredRoles", context?.serialize(it)) }
			src?.breadcrumbs?.let { it -> jsonTree.add("breadcrumbs", context?.serialize(it)) }
			src?.contentSecurityPolicy?.let { it -> jsonTree.add("contentSecurityPolicy", context?.serialize(it)) }
			src?.dynamicOptionsUrl?.let { it -> jsonTree.add("dynamicOptionsUrl", context?.serialize(it)) }
			src?.globalTitle?.let { it -> jsonTree.add("globalTitle", context?.serialize(it)) }
			src?.legacy?.let { it -> jsonTree.add("legacy", context?.serialize(it)) }
			src?.rightDrawer?.let { it -> jsonTree.add("rightDrawer", context?.serialize(it)) }
			src?.upgrade?.let { it -> jsonTree.add("upgrade", context?.serialize(it)) }
			src?.activeVersionId?.let { it -> jsonTree.add("activeVersionId", context?.serialize(it)) }
			src?.resourcesUrl?.let { it -> jsonTree.add("resourcesUrl", context?.serialize(it)) }
			return jsonTree
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
