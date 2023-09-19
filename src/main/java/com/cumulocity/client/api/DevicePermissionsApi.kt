// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.UpdatedDevicePermissions
import com.cumulocity.client.model.DevicePermissionOwners

/**
 * API methods to retrieve and update device permissions assignments.
 * 
 * Device permissions enable users to access and manipulate devices.
 * 
 * The device permission structure is **[API:fragment_name:permission]** where:
 * 
 * * **API** is one of the following values: OPERATION, ALARM, AUDIT, EVENT, MANAGED_OBJECT, MEASUREMENT or "*"
 * * **fragment_name** can be the name of any fragment, for example, "c8y_Restart" or "*"
 * * **permission** is ADMIN, READ or "*"
 * 
 * Required permission per HTTP method:
 * 
 * * GET - READ or "*"
 * * PUT - ADMIN or "*"
 * 
 * The wildcard "*" enables you to access every API and stored object regardless of the fragments that are inside it.
 * 
 * > **⚠️ Important:** If there is no fragment in an object, for example, to read the object, you must use the wildcard "*" for the **fragment_name** part of the device permission (see the structure above). For example: `"10200":["MEASUREMENT:*:READ"]`.
 */
interface DevicePermissionsApi {

	companion object Factory {
		fun create(baseUrl: String): DevicePermissionsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): DevicePermissionsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(DevicePermissionsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Returns all device permissions assignments
	 * 
	 * Returns all device permissions assignments if the current user has READ permission.
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
	 * * HTTP 200 The request has succeeded and the device permissions assignments are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/user/devicePermissions/{id}")
	fun getDevicePermissionAssignments(
		@Path("id") id: String
	): Call<DevicePermissionOwners>
	
	/**
	 * Updates the device permissions assignments
	 * 
	 * Updates the device permissions assignments if the current user has ADMIN permission or CREATE permission and also has all device permissions.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_ADMIN *OR* ROLE_USER_MANAGEMENT_CREATE 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The device permissions were successfully updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/json"]) 
	@PUT("/user/devicePermissions/{id}")
	fun updateDevicePermissionAssignments(
		@Body body: UpdatedDevicePermissions, 
		@Path("id") id: String
	): Call<ResponseBody>
}
