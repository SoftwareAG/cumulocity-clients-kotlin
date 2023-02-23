// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Application {

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
	 */
	enum class Availability(val value: String) {
		@SerializedName(value = "MARKET")
		MARKET("MARKET"),
		@SerializedName(value = "PRIVATE")
		PRIVATE("PRIVATE")
	}

	/**
	 * The type of the application.
	 */
	enum class Type(val value: String) {
		@SerializedName(value = "EXTERNAL")
		EXTERNAL("EXTERNAL"),
		@SerializedName(value = "HOSTED")
		HOSTED("HOSTED"),
		@SerializedName(value = "MICROSERVICE")
		MICROSERVICE("MICROSERVICE")
	}



	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
