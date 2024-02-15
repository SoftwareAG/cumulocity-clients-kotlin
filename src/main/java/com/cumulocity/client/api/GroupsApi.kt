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
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.Group
import com.cumulocity.client.model.UserGroupCollection
import com.cumulocity.client.model.GroupReferenceCollection

/**
 * API methods to create, retrieve, update and delete user groups.
 * 
 * > **⚠️ Important:** In the Cumulocity IoT user interface, user groups are referred to as "global roles". Global roles are not to be confused with user roles.
 * > **ⓘ Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 */
interface GroupsApi {

	companion object Factory {
		fun create(baseUrl: String): GroupsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): GroupsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(GroupsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all user groups of a specific tenant
	 * 
	 * Retrieve all user groups of a specific tenant (by a given tenant ID).
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
	 * * HTTP 200 The request has succeeded and all user groups are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
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
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.groupcollection+json")
	@GET("/user/{tenantId}/groups")
	fun getTenantUserGroups(
		@Path("tenantId") tenantId: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<UserGroupCollection>
	
	/**
	 * Create a user group for a specific tenant
	 * 
	 * Create a user group for a specific tenant (by a given tenant ID).
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
	 * * HTTP 201 A user group was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 409 Duplicate – Group name already exists.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.group+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json"]) 
	@POST("/user/{tenantId}/groups")
	@ReadOnlyProperties("roles", "self", "id", "devicePermissions", "users", "applications")
	fun createUserGroup(
		@Body body: Group, 
		@Path("tenantId") tenantId: String
	): Call<Group>
	
	/**
	 * Retrieve a specific user group for a specific tenant
	 * 
	 * Retrieve a specific user group (by a given user group ID) for a specific tenant (by a given tenant ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_ADMIN *OR* ROLE_USER_MANAGEMENT_CREATE *AND* is parent of the user *AND* is not the current user 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request succeeded and the user group is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 Group not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId
	 * Unique identifier of the user group.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json")
	@GET("/user/{tenantId}/groups/{groupId}")
	fun getUserGroup(
		@Path("tenantId") tenantId: String, 
		@Path("groupId") groupId: Int
	): Call<Group>
	
	/**
	 * Update a specific user group for a specific tenant
	 * 
	 * Update a specific user group (by a given user group ID) for a specific tenant (by a given tenant ID).
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
	 * * HTTP 200 A user group was updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 Group not found.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId
	 * Unique identifier of the user group.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.group+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json"]) 
	@PUT("/user/{tenantId}/groups/{groupId}")
	@ReadOnlyProperties("roles", "self", "id", "devicePermissions", "users", "applications")
	fun updateUserGroup(
		@Body body: Group, 
		@Path("tenantId") tenantId: String, 
		@Path("groupId") groupId: Int
	): Call<Group>
	
	/**
	 * Delete a specific user group for a specific tenant
	 * 
	 * Delete a specific user group (by a given user group ID) for a specific tenant (by a given tenant ID).
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
	 * * HTTP 204 A user group was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Group not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId
	 * Unique identifier of the user group.
	 */
	@Headers("Accept:application/json")
	@DELETE("/user/{tenantId}/groups/{groupId}")
	fun deleteUserGroup(
		@Path("tenantId") tenantId: String, 
		@Path("groupId") groupId: Int
	): Call<ResponseBody>
	
	/**
	 * Retrieve a user group by group name for a specific tenant
	 * 
	 * Retrieve a user group by group name for a specific tenant (by a given tenant ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_READ *OR* ROLE_USER_MANAGEMENT_CREATE *AND* has access to groups 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request succeeded and the user group is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 Group not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param groupName
	 * The name of the user group.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.group+json")
	@GET("/user/{tenantId}/groupByName/{groupName}")
	fun getUserGroupByName(
		@Path("tenantId") tenantId: String, 
		@Path("groupName") groupName: String
	): Call<Group>
	
	/**
	 * Get all user groups for specific user in a specific tenant
	 * 
	 * Get all user groups for a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_READ *OR* ROLE_USER_MANAGEMENT_CREATE *AND* is parent of the user 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request succeeded and all groups for the user are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 User not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param userId
	 * Unique identifier of the a user.
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.groupreferencecollection+json")
	@GET("/user/{tenantId}/users/{userId}/groups")
	fun getUserGroups(
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<GroupReferenceCollection>
}
