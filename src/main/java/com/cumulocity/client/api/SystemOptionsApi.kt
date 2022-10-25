// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
import com.cumulocity.client.model.SystemOptionCollection
import com.cumulocity.client.model.SystemOption

/**
 * API methods to retrieve the read-only properties predefined in the platform's configuration. </br>
 * 
 */ 
interface SystemOptionsApi {

	companion object Factory {
		fun create(baseUrl: String): SystemOptionsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): SystemOptionsApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(SystemOptionsApi::class.java)
		}
	}

	/**
	 * Retrieve all system options </br>
	 * Retrieve all the system options available on the tenant.
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the system options are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.optioncollection+json")
	@GET("/tenant/system/options")
	fun getSystemOptions(
	): Call<SystemOptionCollection>
	
	/**
	 * Retrieve a specific system option </br>
	 * Retrieve a specific system option (by a given category and key) on your tenant.
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the system option is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param category The category of the system options.
	 * @param key The key of a system option.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
	@GET("/tenant/system/options/{category}/{key}")
	fun getSystemOption(
		@Path("category") category: String, 
		@Path("key") key: String
	): Call<SystemOption>
}
