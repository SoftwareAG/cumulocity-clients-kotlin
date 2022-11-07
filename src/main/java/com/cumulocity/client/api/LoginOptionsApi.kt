// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import com.cumulocity.client.model.AuthConfig
import com.cumulocity.client.model.LoginOptionCollection

/**
 * API methods to retrieve the login options configured in the tenant.
 * 
 * More detailed information about the parameters and their meaning can be found in [Administration > Changing settings](https://cumulocity.com/guides/users-guide/administration/#changing-settings) in the *Users guide*.
 * > **&#9432; Info:** If OAuth external is the only login option shown in the response, the user will be automatically redirected to the SSO login screen.
 *  </br>
 * 
 */ 
interface LoginOptionsApi {

	companion object Factory {
		fun create(baseUrl: String): LoginOptionsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): LoginOptionsApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(LoginOptionsApi::class.java)
		}
	}

	/**
	 * Retrieve the login options </br>
	 * Retrieve the login options available in the tenant.
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the login options are sent in the response.</li>
	 * <li>400 Bad request – invalid parameters.</li>
	 * </ul>
	 *
	 * @param management If this is set to `true`, the management tenant login options will be returned.  > **&#9432; Info:** The `tenantId` parameter must not be present in the request when using the `management` parameter, otherwise it will cause an error. 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.loginoptioncollection+json")
	@GET("/tenant/loginOptions")
	fun getLoginOptions(
		@Query("management") management: Boolean? = null, 
		@Query("tenantId") tenantId: String? = null
	): Call<LoginOptionCollection>
	
	/**
	 * Create a login option </br>
	 * Create an authentication configuration on your tenant.  <section><h5>Required roles</h5> ROLE_TENANT_ADMIN <b>OR</b> ROLE_TENANT_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A login option was created.</li>
	 * <li>400 Duplicated – The login option already exists.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.authconfig+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.authconfig+json"]) 
	@POST("/tenant/loginOptions")
	@ReadOnlyProperties("self")
	fun createLoginOption(
		@Body body: AuthConfig
	): Call<AuthConfig>
}
