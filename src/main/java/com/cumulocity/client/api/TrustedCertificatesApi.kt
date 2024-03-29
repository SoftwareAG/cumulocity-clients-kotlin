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
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.UploadedTrustedCertificate
import com.cumulocity.client.model.UploadedTrustedCertificateCollection
import com.cumulocity.client.model.TrustedCertificate
import com.cumulocity.client.model.UploadedTrustedCertSignedVerificationCode
import com.cumulocity.client.model.TrustedCertificateCollection
import com.cumulocity.client.model.VerifyCertificateChain

/**
 * API methods for managing trusted certificates used to establish device connections via MQTT.
 * 
 * More detailed information about trusted certificates and their role can be found in [Device management > Managing device data](https://cumulocity.com/guides/users-guide/device-management/#managing-device-data) in the *User guide*.
 * 
 * > **ⓘ Info:** The Accept header must be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 */
interface TrustedCertificatesApi {

	companion object Factory {
		fun create(baseUrl: String): TrustedCertificatesApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): TrustedCertificatesApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(TrustedCertificatesApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all stored certificates
	 * 
	 * Retrieve all the trusted certificates of a specific tenant (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_ADMIN) *AND* (is the current tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the trusted certificates are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Tenant not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
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
	 * Add a new certificate
	 * 
	 * Add a new trusted certificate to a specific tenant (by a given ID) which can be further used by the devices to establish connections with the Cumulocity IoT platform.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_ADMIN) *AND* (is the current tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 The certificate was added to the tenant.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Tenant not found.
	 * * HTTP 409 Duplicate – A certificate with the same fingerprint already exists.
	 * * HTTP 422 Unprocessable Entity – Invalid certificate data.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param addToTrustStore
	 * If set to `true` the certificate is added to the truststore.
	 * 
	 * The truststore contains all trusted certificates. A connection to a device is only established if it connects to Cumulocity IoT with a certificate in the truststore.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@POST("/tenant/tenants/{tenantId}/trusted-certificates")
	fun addTrustedCertificate(
		@Body body: UploadedTrustedCertificate, 
		@Path("tenantId") tenantId: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null, 
		@Query("addToTrustStore") addToTrustStore: Boolean? = null
	): Call<TrustedCertificate>
	
	/**
	 * Add multiple certificates
	 * 
	 * Add multiple trusted certificates to a specific tenant (by a given ID) which can be further used by the devices to establish connections with the Cumulocity IoT platform.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_ADMIN) *AND* (is the current tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 The certificates were added to the tenant.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Tenant not found.
	 * * HTTP 409 Duplicate – A certificate with the same fingerprint already exists.
	 * * HTTP 422 Unprocessable Entity – Invalid certificates data.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param addToTrustStore
	 * If set to `true` the certificate is added to the truststore.
	 * 
	 * The truststore contains all trusted certificates. A connection to a device is only established if it connects to Cumulocity IoT with a certificate in the truststore.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@POST("/tenant/tenants/{tenantId}/trusted-certificates/bulk")
	@ReadOnlyProperties("next", "prev", "self", "statistics")
	fun addTrustedCertificates(
		@Body body: UploadedTrustedCertificateCollection, 
		@Path("tenantId") tenantId: String, 
		@Query("addToTrustStore") addToTrustStore: Boolean? = null
	): Call<TrustedCertificateCollection>
	
	/**
	 * Retrieve a stored certificate
	 * 
	 * Retrieve the data of a stored trusted certificate (by a given fingerprint) of a specific tenant (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_ADMIN) *AND* (is the current tenant *OR* is the management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the trusted certificate is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint
	 * Unique identifier of a trusted certificate.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/tenant/tenants/{tenantId}/trusted-certificates/{fingerprint}")
	fun getTrustedCertificate(
		@Path("tenantId") tenantId: String, 
		@Path("fingerprint") fingerprint: String
	): Call<TrustedCertificate>
	
	/**
	 * Update a stored certificate
	 * 
	 * Update the data of a stored trusted certificate (by a given fingerprint) of a specific tenant (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_ADMIN) *AND* (is the current tenant *OR* is the management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The certificate was updated on the tenant.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Certificate not found.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint
	 * Unique identifier of a trusted certificate.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@PUT("/tenant/tenants/{tenantId}/trusted-certificates/{fingerprint}")
	@ReadOnlyProperties("proofOfPossessionValid", "notAfter", "serialNumber", "proofOfPossessionVerificationCodeUsableUntil", "subject", "algorithmName", "version", "issuer", "notBefore", "proofOfPossessionUnsignedVerificationCode", "fingerprint", "self", "certInPemFormat")
	fun updateTrustedCertificate(
		@Body body: TrustedCertificate, 
		@Path("tenantId") tenantId: String, 
		@Path("fingerprint") fingerprint: String
	): Call<TrustedCertificate>
	
	/**
	 * Remove a stored certificate
	 * 
	 * Remove a stored trusted certificate (by a given fingerprint) from a specific tenant (by a given ID).When a trusted certificate is deleted, the established MQTT connection to all devices that are using the corresponding certificate are closed.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_ADMIN) *AND* (is the current tenant *OR* is the management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 The trusted certificate was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Certificate not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint
	 * Unique identifier of a trusted certificate.
	 */
	@Headers("Accept:application/json")
	@DELETE("/tenant/tenants/{tenantId}/trusted-certificates/{fingerprint}")
	fun removeTrustedCertificate(
		@Path("tenantId") tenantId: String, 
		@Path("fingerprint") fingerprint: String
	): Call<ResponseBody>
	
	/**
	 * Provide the proof of possession for an already uploaded certificate
	 * 
	 * Provide the proof of possession for a specific uploaded certificate (by a given fingerprint) for a specific tenant (by a given ID).
	 * 
	 * 
	 * ##### 
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_ADMIN) *AND* is the current tenant 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The provided signed verification code check was successful.
	 * * HTTP 400 The provided signed verification code is not correct.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Trusted certificate not found.
	 * * HTTP 422 Proof of possession for the certificate was not confirmed.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint
	 * Unique identifier of a trusted certificate.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@POST("/tenant/tenants/{tenantId}/trusted-certificates-pop/{fingerprint}/pop")
	fun proveCertificatePossession(
		@Body body: UploadedTrustedCertSignedVerificationCode, 
		@Path("tenantId") tenantId: String, 
		@Path("fingerprint") fingerprint: String
	): Call<TrustedCertificate>
	
	/**
	 * Confirm an already uploaded certificate
	 * 
	 * Confirm an already uploaded certificate (by a given fingerprint) for a specific tenant (by a given ID).
	 * 
	 * 
	 * ##### 
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_ADMIN) *AND* is the management tenant 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The certificate is confirmed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Trusted certificate not found.
	 * * HTTP 422 The verification was not successful. Certificate not confirmed.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint
	 * Unique identifier of a trusted certificate.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@POST("/tenant/tenants/{tenantId}/trusted-certificates-pop/{fingerprint}/confirmed")
	fun confirmCertificate(
		@Path("tenantId") tenantId: String, 
		@Path("fingerprint") fingerprint: String
	): Call<TrustedCertificate>
	
	/**
	 * Generate a verification code for the proof of possession operation for the given certificate
	 * 
	 * Generate a verification code for the proof of possession operation for the certificate (by a given fingerprint).
	 * 
	 * 
	 * ##### 
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_ADMIN) *AND* is the current tenant 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The verification code was generated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Trusted certificate not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param fingerprint
	 * Unique identifier of a trusted certificate.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@POST("/tenant/tenants/{tenantId}/trusted-certificates-pop/{fingerprint}/verification-code")
	fun generateVerificationCode(
		@Path("tenantId") tenantId: String, 
		@Path("fingerprint") fingerprint: String
	): Call<TrustedCertificate>
	
	/**
	 * Verify a certificate chain via file upload
	 * 
	 * Verify a device certificate chain against a specific tenant. Max chain length support is <b>10</b>.The tenant ID is `optional` and this api will be further enhanced to resolve the tenant from the chain in future release.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN) *AND* (is the current tenant *OR* is current management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the validation result is sent in the response.
	 * * HTTP 400 Unable to parse certificate chain.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 The tenant ID does not exist.
	 * 
	 * @param tenantId
	 * @param file
	 * File to be uploaded.
	 */
	@Headers(*["Content-Type:multipart/form-data", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@POST("/tenant/tenants/verify-cert-chain/fileUpload")
	@Multipart
	fun validateChainByFileUpload(
		@Part("tenantId") tenantId: String, 
		@Part("file") file: UByteArray
	): Call<VerifyCertificateChain>
	
	/**
	 * Verify a certificate chain via HTTP header
	 * 
	 * Verify a device certificate chain against a specific tenant. Max chain length support is <b>6</b>.The tenant ID is `optional` and this api will be further enhanced to resolve the tenant from the chain in future release.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN) *AND* (is the current tenant *OR* is current management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the validation result is sent in the response.
	 * * HTTP 400 Unable to parse certificate chain.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 The tenant ID does not exist.
	 * 
	 * @param xCumulocityTenantId
	 * Used to send a tenant ID.
	 * @param xCumulocityClientCertChain
	 * Used to send a certificate chain in the header. Separate the chain with `,` and also each 64 bit block with ` ` (a space character).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@POST("/tenant/tenants/verify-cert-chain")
	fun validateChainByHeader(
		@Header("X-Cumulocity-TenantId") xCumulocityTenantId: String? = null, 
		@Header("X-Cumulocity-Client-Cert-Chain") xCumulocityClientCertChain: String
	): Call<VerifyCertificateChain>
}
