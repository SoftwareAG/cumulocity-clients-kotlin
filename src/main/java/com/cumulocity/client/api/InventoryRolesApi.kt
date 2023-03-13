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
import com.cumulocity.client.model.InventoryRole
import com.cumulocity.client.model.InventoryAssignment
import com.cumulocity.client.model.InventoryAssignmentReference
import com.cumulocity.client.model.InventoryRoleCollection
import com.cumulocity.client.model.InventoryAssignmentCollection

/**
 * API methods to create, retrieve, update and delete inventory roles.
 * 
 * > **ⓘ Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 */
interface InventoryRolesApi {

	companion object Factory {
		fun create(baseUrl: String): InventoryRolesApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): InventoryRolesApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(InventoryRolesApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all inventory roles
	 * 
	 * Retrieve all inventory roles.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_READ *OR* ROLE_USER_MANAGEMENT_CREATE 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request succeeded and all inventory roles are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryrolecollection+json")
	@GET("/user/inventoryroles")
	fun getInventoryRoles(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null
	): Call<InventoryRoleCollection>
	
	/**
	 * Create an inventory role
	 * 
	 * Create an inventory role.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 An inventory role was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 409 Duplicate – The inventory role already exists.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.inventoryrole+json", "Accept:application/vnd.com.nsn.cumulocity.inventoryrole+json, application/vnd.com.nsn.cumulocity.error+json"]) 
	@POST("/user/inventoryroles")
	@ReadOnlyProperties("self", "id")
	fun createInventoryRole(
		@Body body: InventoryRole
	): Call<InventoryRole>
	
	/**
	 * Retrieve a specific inventory role
	 * 
	 * Retrieve a specific inventory role (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_READ *OR* ROLE_USER_MANAGEMENT_CREATE *AND* has access to the inventory role 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request succeeded and the inventory role is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Role not found.
	 * 
	 * @param id
	 * Unique identifier of the inventory role.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.inventoryrole+json, application/vnd.com.nsn.cumulocity.error+json")
	@GET("/user/inventoryroles/{id}")
	fun getInventoryRole(
		@Path("id") id: Int
	): Call<InventoryRole>
	
	/**
	 * Update a specific inventory role
	 * 
	 * Update a specific inventory role (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 An inventory role was updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Role not found.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the inventory role.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.inventoryrole+json", "Accept:application/vnd.com.nsn.cumulocity.inventoryrole+json, application/vnd.com.nsn.cumulocity.error+json"]) 
	@PUT("/user/inventoryroles/{id}")
	@ReadOnlyProperties("self", "id")
	fun updateInventoryRole(
		@Body body: InventoryRole, 
		@Path("id") id: Int
	): Call<InventoryRole>
	
	/**
	 * Remove a specific inventory role
	 * 
	 * Remove a specific inventory role (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 An inventory role was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Role not found.
	 * 
	 * @param id
	 * Unique identifier of the inventory role.
	 */
	@Headers("Accept:application/json")
	@DELETE("/user/inventoryroles/{id}")
	fun deleteInventoryRole(
		@Path("id") id: Int
	): Call<ResponseBody>
	
	/**
	 * Retrieve all inventory roles assigned to a user
	 * 
	 * Retrieve all inventory roles assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_READ *OR* ROLE_USER_MANAGEMENT_CREATE *AND* is the parent of the user 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the inventory roles are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 User not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param userId
	 * Unique identifier of the a user.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignmentcollection+json")
	@GET("/user/{tenantId}/users/{userId}/roles/inventory")
	fun getUserInventoryRoles(
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String
	): Call<InventoryAssignmentCollection>
	
	/**
	 * Assign an inventory role to a user
	 * 
	 * Assign an existing inventory role to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_ADMIN to assign any inventory role to root users in a user hierarchy *OR* users that are not in any hierarchy
	 *  ROLE_USER_MANAGEMENT_ADMIN to assign inventory roles accessible by the parent of the assigned user to non-root users in a user hierarchy
	 *  ROLE_USER_MANAGEMENT_CREATE to assign inventory roles accessible by the current user *AND* accessible by the parent of the assigned user to the descendants of the current user in a user hierarchy 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 An inventory role was assigned to a user.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 User not found.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param userId
	 * Unique identifier of the a user.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.inventoryassignment+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignment+json"]) 
	@POST("/user/{tenantId}/users/{userId}/roles/inventory")
	@ReadOnlyProperties("self", "id")
	fun assignUserInventoryRole(
		@Body body: InventoryAssignment, 
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String
	): Call<InventoryAssignment>
	
	/**
	 * Retrieve a specific inventory role assigned to a user
	 * 
	 * Retrieve a specific inventory role (by a given ID) assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_READ *OR* ROLE_USER_MANAGEMENT_CREATE *AND* is the parent of the user 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the inventory role is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 Role not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param userId
	 * Unique identifier of the a user.
	 * @param id
	 * Unique identifier of the inventory assignment.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignment+json")
	@GET("/user/{tenantId}/users/{userId}/roles/inventory/{id}")
	fun getUserInventoryRole(
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String, 
		@Path("id") id: Int
	): Call<InventoryAssignment>
	
	/**
	 * Update a specific inventory role assigned to a user
	 * 
	 * Update a specific inventory role (by a given ID) assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_ADMIN to update the assignment of any inventory roles to root users in a user hierarchy *OR* users that are not in any hierarchy
	 *  ROLE_USER_MANAGEMENT_ADMIN to update the assignment of inventory roles accessible by the assigned user parent, to non-root users in a user hierarchy
	 *  ROLE_USER_MANAGEMENT_CREATE to update the assignment of inventory roles accessible by the current user *AND* the parent of the assigned user to the descendants of the current user in the user hierarchy 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 An inventory assignment was updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 Role not found.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param userId
	 * Unique identifier of the a user.
	 * @param id
	 * Unique identifier of the inventory assignment.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.inventoryassignment+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryassignment+json"]) 
	@PUT("/user/{tenantId}/users/{userId}/roles/inventory/{id}")
	fun updateUserInventoryRole(
		@Body body: InventoryAssignmentReference, 
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String, 
		@Path("id") id: Int
	): Call<InventoryAssignment>
	
	/**
	 * Remove a specific inventory role assigned to a user
	 * 
	 * Remove a specific inventory role (by a given ID) assigned to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_ADMIN *AND* (is not in user hierarchy *OR* is root in the user hierarchy) *OR* ROLE_USER_MANAGEMENT_ADMIN *AND* is in user hiararchy *AND* has parent access to inventory assignments *OR* ROLE_USER_MANAGEMENT_CREATE *AND* is parent of the user *AND* is not the current user *AND* has current user access to inventory assignments *AND* has parent access to inventory assignments 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 An inventory assignment was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Role not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param userId
	 * Unique identifier of the a user.
	 * @param id
	 * Unique identifier of the inventory assignment.
	 */
	@Headers("Accept:application/json")
	@DELETE("/user/{tenantId}/users/{userId}/roles/inventory/{id}")
	fun unassignUserInventoryRole(
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String, 
		@Path("id") id: Int
	): Call<ResponseBody>
}
