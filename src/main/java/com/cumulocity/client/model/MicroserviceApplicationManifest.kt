// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * The manifest of the microservice application.
 */
class MicroserviceApplicationManifest {

	/**
	 * Document type format discriminator, for future changes in format.
	 */
	var apiVersion: String? = null

	/**
	 * The billing mode of the application.
	 * 
	 * In case of RESOURCES, the number of resources used is exposed for billing calculation per usage.
	 * In case of SUBSCRIPTION, all resources usage is counted for the microservice owner and the subtenant is charged for subscription.
	 * 
	 */
	var billingMode: BillingMode? = null

	/**
	 * The context path in the URL makes the application accessible.
	 */
	var contextPath: String? = null

	/**
	 * A list of URL extensions for this microservice application.
	 */
	var extensions: Array<Extensions>? = null

	/**
	 * Deployment isolation.
	 * In case of PER_TENANT, there is a separate instance for each tenant.
	 * Otherwise, there is one single instance for all subscribed tenants.
	 * This will affect billing.
	 * 
	 */
	var isolation: Isolation? = null

	var livenessProbe: ApplicationManifestProbe? = null

	/**
	 * Application provider information.
	 * Simple name allowed for predefined providers, for example, c8y.
	 * Detailed object for external provider.
	 * 
	 */
	var provider: Provider? = null

	var readinessProbe: ApplicationManifestProbe? = null

	/**
	 * The minimum required resources for the microservice application.
	 */
	var requestResources: RequestResources? = null

	/**
	 * The recommended resources for this microservice application.
	 */
	var resources: Resources? = null

	/**
	 * Roles provided by the microservice.
	 */
	var roles: Array<String>? = null

	/**
	 * List of permissions required by a microservice to work.
	 */
	var requiredRoles: Array<String>? = null

	/**
	 * Allows to configure a microservice auto scaling policy.
	 * If the microservice uses a lot of CPU resources, a second instance will be created automatically when this is set to `AUTO`.
	 * The default is `NONE`, meaning auto scaling will not happen.
	 * 
	 */
	var scale: Scale? = null

	/**
	 * A list of settings objects for this microservice application.
	 */
	var settings: Array<Settings>? = null

	/**
	 * Allows to specify a custom category for microservice settings.
	 * By default, `contextPath` is used.
	 * 
	 */
	var settingsCategory: String? = null

	/**
	 * Application version.
	 * Must be a correct [SemVer](https://semver.org/) value but the "+" sign is disallowed.
	 * 
	 */
	var version: String? = null

	
	/**
	 * The billing mode of the application.
	 * 
	 * In case of RESOURCES, the number of resources used is exposed for billing calculation per usage.
	 * In case of SUBSCRIPTION, all resources usage is counted for the microservice owner and the subtenant is charged for subscription.
	 * 
	 * [RESOURCES, SUBSCRIPTION]
	 */
	enum class BillingMode(val value: String) {
		@SerializedName(value = "RESOURCES")
		RESOURCES("RESOURCES"),
		@SerializedName(value = "SUBSCRIPTION")
		SUBSCRIPTION("SUBSCRIPTION")
	}

	
	/**
	 * Deployment isolation.
	 * In case of PER_TENANT, there is a separate instance for each tenant.
	 * Otherwise, there is one single instance for all subscribed tenants.
	 * This will affect billing.
	 * 
	 * [MULTI_TENANT, PER_TENANT]
	 */
	enum class Isolation(val value: String) {
		@SerializedName(value = "MULTI_TENANT")
		MULTITENANT("MULTI_TENANT"),
		@SerializedName(value = "PER_TENANT")
		PERTENANT("PER_TENANT")
	}

	
	/**
	 * Allows to configure a microservice auto scaling policy.
	 * If the microservice uses a lot of CPU resources, a second instance will be created automatically when this is set to `AUTO`.
	 * The default is `NONE`, meaning auto scaling will not happen.
	 * 
	 * [NONE, AUTO]
	 */
	enum class Scale(val value: String) {
		@SerializedName(value = "NONE")
		NONE("NONE"),
		@SerializedName(value = "AUTO")
		AUTO("AUTO")
	}


	class Extensions {
	
		/**
		 * The relative path in Cumulocity IoT for this microservice application.
		 */
		var path: String? = null
	
		/**
		 * The type of this extension.
		 */
		var type: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}


	/**
	 * Application provider information.
	 * Simple name allowed for predefined providers, for example, c8y.
	 * Detailed object for external provider.
	 * 
	 */
	class Provider {
	
		/**
		 * The name of the application provider.
		 */
		var name: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	/**
	 * The minimum required resources for the microservice application.
	 */
	class RequestResources {
	
		/**
		 * The required CPU resource for this microservice application.
		 */
		var cpu: String? = null
	
		/**
		 * The required memory resource for this microservice application.
		 */
		var memory: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	/**
	 * The recommended resources for this microservice application.
	 */
	class Resources {
	
		/**
		 * The required CPU resource for this microservice application.
		 */
		var cpu: String? = null
	
		/**
		 * The required memory resource for this microservice application.
		 */
		var memory: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}


	class Settings {
	
		/**
		 * The name of the setting.
		 */
		var key: String? = null
	
		/**
		 * The value schema determines the values that the microservice can process.
		 */
		var valueSchema: ValueSchema? = null
	
		/**
		 * The default value.
		 */
		var defaultValue: String? = null
	
		/**
		 * Indicates if the value is editable.
		 */
		var editable: Boolean? = null
	
		/**
		 * Indicated wether this setting is inherited.
		 */
		var inheritFromOwner: Boolean? = null
	
		/**
		 * The value schema determines the values that the microservice can process.
		 */
		class ValueSchema {
		
			/**
			 * The value schema type.
			 */
			var type: String? = null
		
			override fun toString(): String {
				return Gson().toJson(this).toString()
			}
		}
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
