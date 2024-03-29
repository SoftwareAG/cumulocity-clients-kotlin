// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import com.cumulocity.client.model.BootstrapUser

/**
 * API methods to retrieve the bootstrap user of an application.
 */
interface BootstrapUserApi {

	companion object Factory {
		fun create(baseUrl: String): BootstrapUserApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): BootstrapUserApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(BootstrapUserApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve the bootstrap user for a specific application
	 * 
	 * Retrieve the bootstrap user for a specific application (by a given ID).
	 * 
	 * This only works for microservice applications.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_APPLICATION_MANAGEMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the bootstrap user of the application is sent in the response.
	 * * HTTP 400 Bad request.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param id
	 * Unique identifier of the application.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
	@GET("/application/applications/{id}/bootstrapUser")
	fun getBootstrapUser(
		@Path("id") id: String
	): Call<BootstrapUser>
}
