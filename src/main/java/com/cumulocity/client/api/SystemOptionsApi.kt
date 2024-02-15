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
import com.cumulocity.client.model.SystemOptionCollection
import com.cumulocity.client.model.SystemOption

/**
 * API methods to retrieve the read-only properties predefined in the platform's configuration.
 * 
 * For security reasons, a few system options are considered secured, which means the user must have the required role **ROLE_OPTION_MANAGEMENT_ADMIN** to read their values.
 * 
 * List of options:
 * 
 * |         Category          | Key                           | Considered as secured ||:-------------------------:|:------------------------------|:----------------------||         password          | green.min-length              | yes                   || two-factor-authentication | pin.validity                  | yes                   || two-factor-authentication | token.length                  | yes                   || two-factor-authentication | token.validity                | yes                   ||      authentication       | badRequestCounter             | yes                   ||           files           | microservice.zipped.max.size  | yes                   ||           files           | microservice.unzipped.max.size| yes                   ||           files           | webapp.zipped.max.size        | yes                   ||           files           | webapp.unzipped.max.size      | yes                   || two-factor-authentication | enforced                      | no                    ||       reportMailer        | available                     | no                    ||          system           | version                       | no                    ||          plugin           | eventprocessing.enabled       | no                    ||         password          | limit.validity                | no                    ||         password          | enforce.strength              | no                    || two-factor-authentication | strategy                      | no                    || two-factor-authentication | pin.length                    | no                    || two-factor-authentication | enabled                       | no                    || two-factor-authentication | enforced.group                | no                    || two-factor-authentication | tenant-scope-settings.enabled | no                    || two-factor-authentication | logout-on-browser-termination | no                    ||       connectivity        | microservice.url              | no                    ||       support-user        | enabled                       | no                    ||          support          | url                           | no                    ||         trackers          | supported.models              | no                    ||         encoding          | test                          | no                    ||        data-broker        | bootstrap.period              | no                    ||           files           | max.size                      | no                    ||      device-control       | bulkoperation.creationramp    | no                    ||         gainsight         | api.key                       | no                    ||            cep            | deprecation.alarm             | no                    ||       remoteaccess        | pass-through.enabled          | no                    ||    device-registration    | security-token.policy         | no                    |
 */
interface SystemOptionsApi {

	companion object Factory {
		fun create(baseUrl: String): SystemOptionsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): SystemOptionsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(SystemOptionsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all system options
	 * 
	 * Retrieve all the system options available on the tenant.
	 * 
	 * > **⚠️ Important:** Note that it is possible to call this endpoint without the ROLE_OPTION_MANAGEMENT_ADMIN role, but options that are considered secured (see the list of options above) will be obfuscated with a constant value `"<<Encrypted>>"`.
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the system options are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.optioncollection+json")
	@GET("/tenant/system/options")
	fun getSystemOptions(
	): Call<SystemOptionCollection>
	
	/**
	 * Retrieve a specific system option
	 * 
	 * Retrieve a specific system option (by a given category and key) on your tenant.
	 * 
	 * > **⚠️ Important:** Note that it is possible to call this endpoint without the ROLE_OPTION_MANAGEMENT_ADMIN role, but only the options that are considered not secured (see the list of options above) will be returned. Otherwise, if the option is considered secured and the user does not have the required role, an HTTP response 403 will be returned.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_OPTION_MANAGEMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the system option is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * 
	 * @param category
	 * The category of the system options.
	 * @param key
	 * The key of a system option.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
	@GET("/tenant/system/options/{category}/{key}")
	fun getSystemOption(
		@Path("category") category: String, 
		@Path("key") key: String
	): Call<SystemOption>
}
