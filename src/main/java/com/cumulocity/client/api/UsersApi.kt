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
import retrofit2.http.Header
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.User
import com.cumulocity.client.model.PasswordChange
import com.cumulocity.client.model.SubscribedUser
import com.cumulocity.client.model.UserCollection
import com.cumulocity.client.model.UserTfaData
import com.cumulocity.client.model.UserReferenceCollection
import com.cumulocity.client.model.UserReference

/**
 * API methods to create, retrieve, update and delete users in Cumulocity IoT.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
interface UsersApi {

	companion object Factory {
		fun create(baseUrl: String): UsersApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): UsersApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(UsersApi::class.java)
		}
	}

	/**
	 * Retrieve all users for a specific tenant </br>
	 * Retrieve all users for a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all users are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param currentPage The current page of the paginated results.
	 * @param groups Numeric group identifiers separated by commas. The response will contain only users which belong to at least one of the specified groups.
	 * @param onlyDevices If set to `true`, the response will only contain users created during bootstrap process (starting with “device_”). If the flag is absent or `false` the result will not contain “device_” users. 
	 * @param owner Exact username of the owner of the user
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param username Prefix or full username
	 * @param withSubusersCount If set to `true`, then each of returned user will contain an additional field “subusersCount”. It is the number of direct subusers (users with corresponding “owner”). 
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.usercollection+json")
	@GET("/user/{tenantId}/users")
	fun getUsers(
		@Path("tenantId") tenantId: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("groups") groups: Array<String>? = null, 
		@Query("onlyDevices") onlyDevices: Boolean? = null, 
		@Query("owner") owner: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("username") username: String? = null, 
		@Query("withSubusersCount") withSubusersCount: Boolean? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<UserCollection>
	
	/**
	 * Create a user for a specific tenant </br>
	 * Create a user for a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to roles, groups, device permissions and applications </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A user was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>409 Duplicate – The userName or alias already exists.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.user+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json"]) 
	@POST("/user/{tenantId}/users")
	@ReadOnlyProperties("owner", "passwordStrength", "roles", "groups", "self", "shouldResetPassword", "id", "lastPasswordChange", "twoFactorAuthenticationEnabled", "devicePermissions", "applications")
	fun createUser(
		@Body body: User, 
		@Path("tenantId") tenantId: String
	): Call<User>
	
	/**
	 * Retrieve a specific user for a specific tenant </br>
	 * Retrieve a specific user (by a given user ID) for a specific tenant (by a given tenant ID).  Users in the response are sorted by username in ascending order. Only objects which the user is allowed to see are returned to the user. The user password is never returned in a GET response. Authentication mechanism is provided by another interface.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the user is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
	@GET("/user/{tenantId}/users/{userId}")
	fun getUser(
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String
	): Call<User>
	
	/**
	 * Update a specific user for a specific tenant </br>
	 * Update a specific user (by a given user ID) for a specific tenant (by a given tenant ID).  Any change in user's roles, device permissions and groups creates corresponding audit records with type "User" and activity "User updated" with information which properties have been changed.  When the user is updated with changed permissions or groups, a corresponding audit record is created with type "User" and activity "User updated".  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN to update root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/> ROLE_USER_MANAGEMENT_ADMIN to update non-root users in a user hierarchy <b>AND</b> whose parents have access to roles, groups, device permissions and applications being assigned<br/> ROLE_USER_MANAGEMENT_CREATE to update descendants of the current user in a user hierarchy <b>AND</b> whose parents have access to roles, groups, device permissions and applications being assigned </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A user was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.user+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json"]) 
	@PUT("/user/{tenantId}/users/{userId}")
	@ReadOnlyProperties("owner", "passwordStrength", "roles", "groups", "self", "shouldResetPassword", "id", "lastPasswordChange", "userName", "twoFactorAuthenticationEnabled", "devicePermissions", "applications")
	fun updateUser(
		@Body body: User, 
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String
	): Call<User>
	
	/**
	 * Delete a specific user for a specific tenant </br>
	 * Delete a specific user (by a given user ID) for a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> not the current user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A user was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 */
	@Headers("Accept:application/json")
	@DELETE("/user/{tenantId}/users/{userId}")
	fun deleteUser(
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String
	): Call<ResponseBody>
	
	/**
	 * Update a specific user's password of a specific tenant </br>
	 * Update a specific user's password (by a given user ID) of a specific tenant (by a given tenant ID).  Changing the user's password creates a corresponding audit record of type "User" and activity "User updated", and specifying that the password has been changed.  > **⚠️ Important:** If the tenant uses OAI-Secure authentication, the target user will be logged out.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN to update root users in a user hierarchy <b>OR</b> users that are not in any hierarchy<br/> ROLE_USER_MANAGEMENT_ADMIN to update non-root users in a user hierarchy <b>AND</b> whose parents have access to assigned roles, groups, device permissions and applications<br/> ROLE_USER_MANAGEMENT_CREATE to update descendants of the current user in a user hierarchy <b>AND</b> whose parents have access to assigned roles, groups, device permissions and applications </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A user was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/json"]) 
	@PUT("/user/{tenantId}/users/{userId}/password")
	fun updateUserPassword(
		@Body body: PasswordChange, 
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String
	): Call<ResponseBody>
	
	/**
	 * Retrieve the TFA settings of a specific user </br>
	 * Retrieve the two-factor authentication settings for the specified user.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> (ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user) <b>OR</b> is the current user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the TFA settings are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param userId Unique identifier of the a user.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/user/{tenantId}/users/{userId}/tfa")
	fun getUserTfaSettings(
		@Path("tenantId") tenantId: String, 
		@Path("userId") userId: String
	): Call<UserTfaData>
	
	/**
	 * Retrieve a user by username in a specific tenant </br>
	 * Retrieve a user by username in a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the user is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param username The username of the a user.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.user+json")
	@GET("/user/{tenantId}/userByName/{username}")
	fun getUserByUsername(
		@Path("tenantId") tenantId: String, 
		@Path("username") username: String
	): Call<User>
	
	/**
	 * Retrieve the users of a specific user group of a specific tenant </br>
	 * Retrieve the users of a specific user group (by a given user group ID) of a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_READ <b>OR</b> (ROLE_USER_MANAGEMENT_CREATE <b>AND</b> has access to the user group) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the users are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Group not found.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.userreferencecollection+json")
	@GET("/user/{tenantId}/groups/{groupId}/users")
	fun getUsersFromUserGroup(
		@Path("tenantId") tenantId: String, 
		@Path("groupId") groupId: Int, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null
	): Call<UserReferenceCollection>
	
	/**
	 * Add a user to a specific user group of a specific tenant </br>
	 * Add a user to a specific user group (by a given user group ID) of a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN to assign root users in a user hierarchy <b>OR</b> users that are not in any hierarchy to any group<br/> ROLE_USER_MANAGEMENT_ADMIN to assign non-root users in a user hierarchy to groups accessible by the parent of assigned user<br/> ROLE_USER_MANAGEMENT_CREATE to assign descendants of the current user in a user hierarchy to groups accessible by current user <b>AND</b> accessible by the parent of assigned user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 The user was added to the group.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Group not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.userreference+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.userreference+json"]) 
	@POST("/user/{tenantId}/groups/{groupId}/users")
	fun assignUserToUserGroup(
		@Body body: SubscribedUser, 
		@Path("tenantId") tenantId: String, 
		@Path("groupId") groupId: Int
	): Call<UserReference>
	
	/**
	 * Remove a specific user from a specific user group of a specific tenant </br>
	 * Remove a specific user (by a given user ID) from a specific user group (by a given user group ID) of a specific tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN <b>OR</b> ROLE_USER_MANAGEMENT_CREATE <b>AND</b> is parent of the user <b>AND</b> is not the current user </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A user was removed from a group.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param groupId Unique identifier of the user group.
	 * @param userId Unique identifier of the a user.
	 */
	@Headers("Accept:application/json")
	@DELETE("/user/{tenantId}/groups/{groupId}/users/{userId}")
	fun removeUserFromUserGroup(
		@Path("tenantId") tenantId: String, 
		@Path("groupId") groupId: Int, 
		@Path("userId") userId: String
	): Call<ResponseBody>
	
	/**
	 * Terminate a user's session </br>
	 * After logging out, a user has to enter valid credentials again to get access to the platform.  The request is responsible for removing cookies from the browser and invalidating internal platform access tokens. 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the user is logged out.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param cookie The authorization cookie storing the access token of the user. This parameter is specific to OAI-Secure authentication.
	 * @param xXSRFTOKEN Prevents XRSF attack of the authenticated user. This parameter is specific to OAI-Secure authentication.
	 */
	@Headers("Accept:application/json")
	@POST("/user/logout")
	fun logout(
		@Header("Cookie") cookie: String? = null, 
		@Header("X-XSRF-TOKEN") xXSRFTOKEN: String? = null
	): Call<ResponseBody>
}
