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
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.Tenant
import com.cumulocity.client.model.TenantCollection
import com.cumulocity.client.model.CurrentTenant
import com.cumulocity.client.model.TenantTfaData

/**
 * Tenants are physically separated data spaces with a separate URL, with own users, a separate application management and no sharing of data by default. Users in a single tenant by default share the same URL and the same data space.
 * 
 * ### Tenant ID and tenant domain
 * 
 * The **tenant ID** is a unique identifier across all tenants in Cumulocity IoT and it follows the format t<number>, for example, t07007007. It is possible to specify the tenant ID while creating a subtenant, but the ID cannot be changed after creation. If the ID is not specified (recommended), it gets auto-generated for all tenant types.
 * 
 * The location where a tenant can be accessed is called **tenant domain**, for example, *mytenant.cumulocity.com*. It needs to be unique across all tenants and it can be changed after tenant creation.The tenant domain may contain only lowercase letters, digits and hyphens. It must start with a lowercase letter, hyphens are only allowed in the middle, and the minimum length is 2 characters. Note that the usage of underscore characters is deprecated but still possible for backward compatibility reasons.
 * 
 * In general, the tenant domain should be used for communication if it is known.
 * 
 * > **⚠️ Important:** For support user access, the tenant ID must be used and not the tenant domain.
 * See [Tenant > Current tenant](#operation/getCurrentTenantResource) for information on how to retrieve the tenant ID and domain of the current tenant via the API.
 * 
 * In the UI, the tenant ID is displayed in the user dropdown menu, see [Getting started > Get familiar with the UI > User options and settings](https://cumulocity.com/docs/get-familiar-with-the-ui/user-settings/) in the Cumulocity IoT user documentation.
 * 
 * ### Access rights and permissions
 * 
 * There are two types of roles in Cumulocity IoT – global and inventory. Global roles are applied at the tenant level. In a Role Based Access Control (RBAC) approach you must use the inventory roles in order to have the correct level of separation. Apart from some global permissions (like "own user management") customer users will not be assigned any roles. Inventory roles must be created, or the default roles used, and then assigned to the user in combination with the assets the roles apply to. This needs to be done at least once for each customer.
 * 
 * In a multi-tenancy approach, as the tenant is completely separated from all other customers you do not necessarily need to be involved in setting up the access rights of the customer. If customers are given administration rights for their tenants, they can set up permissions on their own. It is not possible for customers to have any sight or knowledge of other customers.
 * 
 * In the RBAC approach, managing access is the most complicated part because a misconfiguration can potentially give customers access to data that they must not see, like other customers' data. The inventory roles allow you to granularly define access for only certain parts of data, but they don't protect you from accidental misconfigurations. A limitation here is that customers won't be able to create their own roles.
 * 
 * For more details, see [RBAC versus multi-tenancy approach](https://cumulocity.com/docs/concepts/tenant-hierarchy/#comparison-of-various-use-cases).
 * 
 * > **ⓘ Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 */
interface TenantsApi {

	companion object Factory {
		fun create(baseUrl: String): TenantsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): TenantsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(TenantsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all subtenants
	 * 
	 * Retrieve all subtenants of the current tenant.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_READ 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the subtenants are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param company
	 * Company name associated with the Cumulocity IoT tenant.
	 * @param domain
	 * Domain name of the Cumulocity IoT tenant.
	 * @param parent
	 * Identifier of the Cumulocity IoT tenant's parent.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantcollection+json")
	@GET("/tenant/tenants")
	fun getTenants(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null, 
		@Query("company") company: String? = null, 
		@Query("domain") domain: String? = null, 
		@Query("parent") parent: String? = null
	): Call<TenantCollection>
	
	/**
	 * Create a tenant
	 * 
	 * Create a subtenant for the current tenant.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_MANAGEMENT_CREATE) *AND* the current tenant is allowed to create subtenants 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A tenant was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 409 Conflict – The tenant domain/ID already exists.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.tenant+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenant+json"]) 
	@POST("/tenant/tenants")
	@ReadOnlyProperties("allowCreateTenants", "parent", "creationTime", "self", "id", "ownedApplications", "applications", "status")
	fun createTenant(
		@Body body: Tenant
	): Call<Tenant>
	
	/**
	 * Retrieve the current tenant
	 * 
	 * Retrieve information about the current tenant.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_OWN_READ *OR* ROLE_SYSTEM 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the information is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param withParent
	 * When set to `true`, the returned result will contain the parent of the current tenant.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currenttenant+json")
	@GET("/tenant/currentTenant")
	fun getCurrentTenant(
		@Query("withParent") withParent: Boolean? = null
	): Call<CurrentTenant>
	
	/**
	 * Retrieve a specific tenant
	 * 
	 * Retrieve a specific tenant by a given ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_READ *AND* (the current tenant is its parent *OR* is the management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the tenant is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Tenant not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenant+json")
	@GET("/tenant/tenants/{tenantId}")
	fun getTenant(
		@Path("tenantId") tenantId: String
	): Call<Tenant>
	
	/**
	 * Update a specific tenant
	 * 
	 * Update a specific tenant by a given ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  (ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_MANAGEMENT_UPDATE) *AND*
	 *  ((the current tenant is its parent *AND* the current tenant is allowed to create subtenants) *OR* is the management tenant) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 A tenant was updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Tenant not found.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.tenant+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenant+json"]) 
	@PUT("/tenant/tenants/{tenantId}")
	@ReadOnlyProperties("adminName", "allowCreateTenants", "parent", "creationTime", "self", "id", "ownedApplications", "applications", "status")
	fun updateTenant(
		@Body body: Tenant, 
		@Path("tenantId") tenantId: String
	): Call<Tenant>
	
	/**
	 * Remove a specific tenant
	 * 
	 * Remove a specific tenant by a given ID.
	 * 
	 * > **⚠️ Important:** Deleting a subtenant cannot be reverted. For security reasons, it is therefore only available in the management tenant. You cannot delete tenants from any tenant but the management tenant.
	 * Administrators in Enterprise Tenants are only allowed to suspend active subtenants, but not to delete them.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN *AND* is the management tenant 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A tenant was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Tenant not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers("Accept:application/json")
	@DELETE("/tenant/tenants/{tenantId}")
	fun deleteTenant(
		@Path("tenantId") tenantId: String
	): Call<ResponseBody>
	
	/**
	 * Retrieve TFA settings of a specific tenant
	 * 
	 * Retrieve the two-factor authentication settings of a specific tenant by a given tenant ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ((ROLE_TENANT_MANAGEMENT_READ *OR* ROLE_USER_MANAGEMENT_READ) *AND* (the current tenant is its parent *OR* is the management tenant *OR* the current user belongs to the tenant)) *OR* (the user belongs to the tenant *AND* ROLE_USER_MANAGEMENT_OWN_READ) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the TFA settings are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Tenant not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/tenant/tenants/{tenantId}/tfa")
	fun getTenantTfaSettings(
		@Path("tenantId") tenantId: String
	): Call<TenantTfaData>
}
