// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.ExternalId
import com.cumulocity.client.model.ExternalIds

/**
 * The external ID resource represents an individual external ID that can be queried and deleted.
 * 
 * > **ⓘ Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 */
interface ExternalIDsApi {

	companion object Factory {
		fun create(baseUrl: String): ExternalIDsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): ExternalIDsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(ExternalIDsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all external IDs of a specific managed object
	 * 
	 * Retrieve all external IDs of a existing managed object (identified by ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_IDENTITY_READ *OR* owner of the resource *OR* MANAGED_OBJECT_READ permission on the resource 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and all the external IDs are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalidcollection+json")
	@GET("/identity/globalIds/{id}/externalIds")
	fun getExternalIds(
		@Path("id") id: String
	): Call<ExternalIds>
	
	/**
	 * Create an external ID
	 * 
	 * Create an external ID for an existing managed object (identified by ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_IDENTITY_ADMIN *OR* owner of the resource *OR* MANAGED_OBJECT_ADMIN permission on the resource 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 An external ID was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 409 Duplicate – Identity already bound to a different Global ID.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.externalid+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalid+json"]) 
	@POST("/identity/globalIds/{id}/externalIds")
	@ReadOnlyProperties("managedObject", "self")
	fun createExternalId(
		@Body body: ExternalId, 
		@Path("id") id: String
	): Call<ExternalId>
	
	/**
	 * Retrieve a specific external ID
	 * 
	 * Retrieve a specific external ID of a particular type.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_IDENTITY_READ *OR* owner of the resource *OR* MANAGED_OBJECT_READ permission on the resource 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the external ID is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 External ID not found.
	 * 
	 * @param type
	 * The identifier used in the external system that Cumulocity IoT interfaces with.
	 * @param externalId
	 * The type of the external identifier.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.externalid+json")
	@GET("/identity/externalIds/{type}/{externalId}")
	fun getExternalId(
		@Path("type") type: String, 
		@Path("externalId") externalId: String
	): Call<ExternalId>
	
	/**
	 * Remove a specific external ID
	 * 
	 * Remove a specific external ID of a particular type.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_IDENTITY_ADMIN *OR* owner of the resource *OR* MANAGED_OBJECT_ADMIN permission on the resource 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 An external ID was deleted.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 External ID not found.
	 * 
	 * @param type
	 * The identifier used in the external system that Cumulocity IoT interfaces with.
	 * @param externalId
	 * The type of the external identifier.
	 */
	@Headers("Accept:application/json")
	@DELETE("/identity/externalIds/{type}/{externalId}")
	fun deleteExternalId(
		@Path("type") type: String, 
		@Path("externalId") externalId: String
	): Call<ResponseBody>
}
