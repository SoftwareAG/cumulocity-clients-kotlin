// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import com.cumulocity.client.model.IdentityApiResource

/**
 * Cumulocity IoT can associate devices and assets with multiple external identities.For instance, devices can often be identified by the IMEI of their modem, by a micro-controller serial number or by an asset tag.This is useful, for example, when you have non-functional hardware and must replace the hardware without losing the data that was recorded.
 * 
 * The identity API resource returns URIs and URI templates for associating external identifiers with unique identifiers.
 */
interface IdentityApi {

	companion object Factory {
		fun create(baseUrl: String): IdentityApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): IdentityApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(IdentityApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve URIs to collections of external IDs
	 * 
	 * Retrieve URIs and URI templates for associating external identifiers with unique identifiers.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_IDENTITY_READ 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the URIs are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.identityapi+json, application/vnd.com.nsn.cumulocity.error+json")
	@GET("/identity")
	fun getIdentityApiResource(
	): Call<IdentityApiResource>
}
