// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Query
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.SubscribedRole
import com.cumulocity.client.model.UserRoleCollection
import com.cumulocity.client.model.Role
import com.cumulocity.client.model.RoleReferenceCollection
import com.cumulocity.client.model.RoleReference

/**
 * API methods to create, retrieve, update and delete user roles.
 * 
 * > **ⓘ Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 */
interface RolesApi {

	companion object Factory {
		fun create(baseUrl: String): RolesApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): RolesApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(RolesApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all user roles
	 * 
	 * Retrieve all user roles.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_READ *OR* ROLE_USER_MANAGEMENT_CREATE *AND* has access to the user role 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and all user roles are sent in the response.
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
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolecollection+json")
	@GET("/user/roles")
	fun getUserRoles(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<UserRoleCollection>
	
	/**
	 * Retrieve a user role by name
	 * 
	 * Retrieve a user role by name.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_READ *OR* ROLE_USER_MANAGEMENT_CREATE *AND* current user has access to the role with this name 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the user role is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Role not found.
	 * 
	 * @param name
	 * The name of the user role.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.role+json")
	@GET("/user/roles/{name}")
	fun getUserRole(
		@Path("name") name: String
	): Call<Role>
	
	/**
	 * Retrieve all roles assigned to a specific user group in a specific tenant
	 * 
	 * Retrieve all roles assigned to a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_READ 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request succeeded and the roles are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 Group not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId
	 * Unique identifier of the user group.
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereferencecollection+json")
	@GET("/user/{tenantId}/groups/{groupId}/roles")
	fun getGroupRoles(
		@Path("tenantId") tenantId: String, 
		@Path("groupId") groupId: Int, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null
	): Call<RoleReferenceCollection>
	
	/**
	 * Assign a role to a specific user group in a specific tenant
	 * 
	 * Assign a role to a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).
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
	 * * HTTP 201 A user role was assigned to a user group.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Group not found.
	 * * HTTP 409 Conflict – Role already assigned to the user group.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId
	 * Unique identifier of the user group.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.rolereference+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereference+json"]) 
	@POST("/user/{tenantId}/groups/{groupId}/roles")
	fun assignGroupRole(
		@Body body: SubscribedRole, 
		@Path("tenantId") tenantId: String, 
		@Path("groupId") groupId: Int
	): Call<RoleReference>
	
	/**
	 * Unassign a specific role for a specific user group in a specific tenant
	 * 
	 * Unassign a specific role (given by a role ID) for a specific user group (by a given user group ID) in a specific tenant (by a given tenant ID).
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
	 * * HTTP 204 A role was unassigned from a user group.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Role not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId
	 * Unique identifier of the user group.
	 * @param roleId
	 * Unique identifier of the user role.
	 */
	@Headers("Accept:application/json")
	@DELETE("/user/{tenantId}/groups/{groupId}/roles/{roleId}")
	fun unassignGroupRole(
		@Path("tenantId") tenantId: String, 
		@Path("groupId") groupId: Int, 
		@Path("roleId") roleId: String
	): Call<ResponseBody>
	
	/**
	 * Assign a role to specific user in a specific tenant
	 * 
	 * Assign a role to a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * When a role is assigned to a user, a corresponding audit record is created with type "User" and activity "User updated".
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_ADMIN to assign any role to root users in a user hierarchy *OR* users that are not in any hierarchy
	 *  ROLE_USER_MANAGEMENT_ADMIN to assign roles accessible by the parent of assigned user to non-root users in a user hierarchy
	 *  ROLE_USER_MANAGEMENT_CREATE to assign roles accessible by the current user *AND* accessible by the parent of the assigned user to the descendants of the current user in a user hierarchy 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A user role was assigned to a user.
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
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.rolereference+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.rolereference+json"]) 
	@POST("/user/{tenantId}/users/{userId}/roles")
	fun assignUserRole(
		@Body body: SubscribedRole, 
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String
	): Call<RoleReference>
	
	/**
	 * Unassign a specific role from a specific user in a specific tenant
	 * 
	 * Unassign a specific role (by a given role ID) from a specific user (by a given user ID) in a specific tenant (by a given tenant ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_READ *OR* ROLE_USER_MANAGEMENT_CREATE *AND* is parent of the user *AND* has access to roles 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A user role was unassigned from a user.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 User not found.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param userId
	 * Unique identifier of the a user.
	 * @param roleId
	 * Unique identifier of the user role.
	 */
	@Headers("Accept:application/json")
	@DELETE("/user/{tenantId}/users/{userId}/roles/{roleId}")
	fun unassignUserRole(
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String, 
		@Path("roleId") roleId: String
	): Call<ResponseBody>
}
