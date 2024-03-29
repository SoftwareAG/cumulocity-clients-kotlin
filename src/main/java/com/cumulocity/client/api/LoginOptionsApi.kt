// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Header
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.AuthConfig
import com.cumulocity.client.model.AuthConfigAccess
import com.cumulocity.client.model.LoginOptionCollection

/**
 * API methods to retrieve the login options configured in the tenant.
 * 
 * More detailed information about the parameters and their meaning can be found in [Administration > Changing settings](https://cumulocity.com/guides/users-guide/administration/#changing-settings) in the *Users guide*.
 * 
 * > **ⓘ Info:** If OAuth external is the only login option shown in the response, the user will be automatically redirected to the SSO login screen.
 */
interface LoginOptionsApi {

	companion object Factory {
		fun create(baseUrl: String): LoginOptionsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): LoginOptionsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(LoginOptionsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all login options
	 * 
	 * Retrieve all login options available in the tenant.
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the login options are sent in the response.
	 * * HTTP 400 Bad request – invalid parameters.
	 * 
	 * @param management
	 * If this is set to `true`, the management tenant login options will be returned.
	 * 
	 * **ⓘ Info:** The `tenantId` parameter must not be present in the request when using the `management` parameter, otherwise it will cause an error.
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.loginoptioncollection+json")
	@GET("/tenant/loginOptions")
	fun getLoginOptions(
		@Query("management") management: Boolean? = null, 
		@Query("tenantId") tenantId: String? = null
	): Call<LoginOptionCollection>
	
	/**
	 * Create a login option
	 * 
	 * Create an authentication configuration on your tenant.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_ADMIN *OR* ROLE_TENANT_MANAGEMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 A login option was created.
	 * * HTTP 400 Duplicated – The login option already exists.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.authconfig+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.authconfig+json"]) 
	@POST("/tenant/loginOptions")
	@ReadOnlyProperties("self", "id")
	fun createLoginOption(
		@Body body: AuthConfig
	): Call<AuthConfig>
	
	/**
	 * Retrieve a specific login option
	 * 
	 * Retrieve a specific login option in the tenant by the given type or ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ((ROLE_TENANT_ADMIN *OR* ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_USER_MANAGEMENT_OWN_ADMIN *OR* ROLE_USER_MANAGEMENT_CREATE) *AND* tenant access to login option is not restricted by management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the login option is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Login option not found.
	 * 
	 * @param typeOrId
	 * The type or ID of the login option. The type's value is case insensitive and can be `OAUTH2`, `OAUTH2_INTERNAL` or `BASIC`.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.authConfig+json")
	@GET("/tenant/loginOptions/{typeOrId}")
	fun getLoginOption(
		@Path("typeOrId") typeOrId: String
	): Call<AuthConfig>
	
	/**
	 * Update a specific login option
	 * 
	 * Update a specific login option in the tenant by a given type or ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ((ROLE_TENANT_ADMIN *OR* ROLE_TENANT_MANAGEMENT_ADMIN) *AND* tenant access to login option is not restricted by management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 A login option was updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Login option not found.
	 * 
	 * @param body
	 * @param typeOrId
	 * The type or ID of the login option. The type's value is case insensitive and can be `OAUTH2`, `OAUTH2_INTERNAL` or `BASIC`.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.authconfig+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.authconfig+json"]) 
	@PUT("/tenant/loginOptions/{typeOrId}")
	@ReadOnlyProperties("self")
	fun updateLoginOption(
		@Body body: AuthConfig, 
		@Path("typeOrId") typeOrId: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<AuthConfig>
	
	/**
	 * Delete a specific login option
	 * 
	 * Delete a specific login option in the tenant by a given type or ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ((ROLE_TENANT_ADMIN *OR* ROLE_TENANT_MANAGEMENT_ADMIN) *AND* tenant access to login option is not restricted by management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A login option was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Login option not found.
	 * 
	 * @param typeOrId
	 * The type or ID of the login option. The type's value is case insensitive and can be `OAUTH2`, `OAUTH2_INTERNAL` or `BASIC`.
	 */
	@Headers("Accept:application/json")
	@DELETE("/tenant/loginOptions/{typeOrId}")
	fun deleteLoginOption(
		@Path("typeOrId") typeOrId: String
	): Call<ResponseBody>
	
	/**
	 * Update a tenant's access to the login option
	 * 
	 * Update the tenant's access to the authentication configuration.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN *AND* is the management tenant 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The login option was updated.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Tenant not found.
	 * 
	 * @param body
	 * @param typeOrId
	 * The type or ID of the login option. The type's value is case insensitive and can be `OAUTH2`, `OAUTH2_INTERNAL` or `BASIC`.
	 * @param targetTenant
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.authconfig+json"]) 
	@PUT("/tenant/loginOptions/{typeOrId}/restrict")
	fun updateLoginOptionAccess(
		@Body body: AuthConfigAccess, 
		@Path("typeOrId") typeOrId: String, 
		@Query("targetTenant") targetTenant: String
	): Call<AuthConfig>
}
