// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Body
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import com.cumulocity.client.model.Application
import com.cumulocity.client.model.ApplicationSettings
import com.cumulocity.client.model.ApplicationUserCollection

/**
 * API methods to retrieve and update the current application and to retrieve its subscribers.It is the authenticated microservice user's application.
 */
interface CurrentApplicationApi {

	companion object Factory {
		fun create(baseUrl: String): CurrentApplicationApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): CurrentApplicationApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(CurrentApplicationApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve the current application
	 * 
	 * Retrieve the current application.This only works inside an application, for example, a microservice.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  Microservice bootstrap user required. 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the current application sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
	@GET("/application/currentApplication")
	fun getCurrentApplication(
	): Call<Application>
	
	/**
	 * Update the current application
	 * 
	 * Update the current application.This only works inside an application, for example, a microservice. This method is deprecated as it is only used by legacy microservices that are not running on Kubernetes.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  Microservice bootstrap user required. 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The current application was updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * 
	 * @param body
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.application+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json"]) 
	@PUT("/application/currentApplication")
	@Deprecated(message = "This resource is deprecated and might be removed in future releases.")
	@ReadOnlyProperties("owner", "activeVersionId", "self", "id", "resourcesUrl")
	fun updateCurrentApplication(
		@Body body: Application
	): Call<Application>
	
	/**
	 * Retrieve the current application settings
	 * 
	 * Retrieve the current application settings.This only works inside an application, for example, a microservice.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  Microservice bootstrap user *OR* microservice service user required. 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the current application settings are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationsettings+json")
	@GET("/application/currentApplication/settings")
	fun getCurrentApplicationSettings(
	): Call<Array<ApplicationSettings>>
	
	/**
	 * Retrieve the subscribed users of the current application
	 * 
	 * Retrieve the subscribed users of the current application.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  Microservice bootstrap user required. 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the list of subscribed users for the current application is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.applicationusercollection+json, application/vnd.com.nsn.cumulocity.error+json")
	@GET("/application/currentApplication/subscriptions")
	fun getSubscribedUsers(
	): Call<ApplicationUserCollection>
}
