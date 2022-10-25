// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.TrustedCertificate
import com.cumulocity.client.model.TrustedCertificateCollection

/**
 * API methods for managing trusted certificates used to establish device connections via MQTT.
 * 
 * More detailed information about trusted certificates and their role can be found in [Device management > Managing device data](https://cumulocity.com/guides/users-guide/device-management/#managing-device-data) in the *User guide*.
 * 
 * > **&#9432; Info:** The Accept header must be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
interface TrustedCertificatesApi {

	companion object Factory {
		fun create(baseUrl: String): TrustedCertificatesApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): TrustedCertificatesApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(TrustedCertificatesApi::class.java)
		}
	}

	/**
	 * Retrieve all stored certificates </br>
	 * Retrieve all the trusted certificates of a specific tenant (by a given ID).  <section><h5>Required roles</h5> (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the trusted certificates are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Tenant not found.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/tenant/tenants/{tenantId}/trusted-certificates")
	fun getTrustedCertificates(
		@Path("tenantId") tenantId: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<TrustedCertificateCollection>
	
	/**
	 * Add a new certificate </br>
	 * Add a new trusted certificate to a specific tenant (by a given ID) which can be further used by the devices to establish connections with the Cumulocity IoT platform.  <section><h5>Required roles</h5> (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 The certificate was added to the tenant.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Tenant not found.</li>
	 * <li>409 Duplicate – A certificate with the same fingerprint already exists.</li>
	 * <li>422 Unprocessable Entity – Invalid certificate data.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@POST("/tenant/tenants/{tenantId}/trusted-certificates")
	@ReadOnlyProperties("notAfter", "serialNumber", "subject", "fingerprint", "self", "algorithmName", "version", "issuer", "notBefore")
	fun addTrustedCertificate(
		@Body body: TrustedCertificate, 
		@Path("tenantId") tenantId: String
	): Call<TrustedCertificate>
	
	/**
	 * Add multiple certificates </br>
	 * Add multiple trusted certificates to a specific tenant (by a given ID) which can be further used by the devices to establish connections with the Cumulocity IoT platform.  <section><h5>Required roles</h5> (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 The certificates were added to the tenant.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Tenant not found.</li>
	 * <li>409 Duplicate – A certificate with the same fingerprint already exists.</li>
	 * <li>422 Unprocessable Entity – Invalid certificates data.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@POST("/tenant/tenants/{tenantId}/trusted-certificates/bulk")
	@ReadOnlyProperties("next", "prev", "self", "statistics")
	fun addTrustedCertificates(
		@Body body: TrustedCertificateCollection, 
		@Path("tenantId") tenantId: String
	): Call<TrustedCertificateCollection>
	
	/**
	 * Retrieve a stored certificate </br>
	 * Retrieve the data of a stored trusted certificate (by a given fingerprint) of a specific tenant (by a given ID).  <section><h5>Required roles</h5> (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the trusted certificate is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint Unique identifier of a trusted certificate.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/tenant/tenants/{tenantId}/trusted-certificates/{fingerprint}")
	fun getTrustedCertificate(
		@Path("tenantId") tenantId: String, 
		@Path("fingerprint") fingerprint: String
	): Call<TrustedCertificate>
	
	/**
	 * Update a stored certificate </br>
	 * Update the data of a stored trusted certificate (by a given fingerprint) of a specific tenant (by a given ID).  <section><h5>Required roles</h5> (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The certificate was updated on the tenant.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Certificate not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint Unique identifier of a trusted certificate.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@PUT("/tenant/tenants/{tenantId}/trusted-certificates/{fingerprint}")
	@ReadOnlyProperties("notAfter", "serialNumber", "subject", "fingerprint", "self", "certInPemFormat", "algorithmName", "version", "issuer", "notBefore")
	fun updateTrustedCertificate(
		@Body body: TrustedCertificate, 
		@Path("tenantId") tenantId: String, 
		@Path("fingerprint") fingerprint: String
	): Call<TrustedCertificate>
	
	/**
	 * Remove a stored certificate </br>
	 * Remove a stored trusted certificate (by a given fingerprint) from a specific tenant (by a given ID).  <section><h5>Required roles</h5> (ROLE_TENANT_MANAGEMENT_ADMIN <b>OR</b> ROLE_TENANT_ADMIN) <b>AND</b> (is the current tenant <b>OR</b> is the management tenant) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 The trusted certificate was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Certificate not found.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint Unique identifier of a trusted certificate.
	 */
	@Headers("Accept:application/json")
	@DELETE("/tenant/tenants/{tenantId}/trusted-certificates/{fingerprint}")
	fun removeTrustedCertificate(
		@Path("tenantId") tenantId: String, 
		@Path("fingerprint") fingerprint: String
	): Call<ResponseBody>
}
