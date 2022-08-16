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
import retrofit2.http.Query
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.BinaryInfo
import com.cumulocity.client.model.BinaryCollection
import com.cumulocity.client.model.Binary

/**
 * Managed objects can perform operations to store, retrieve and delete binaries. One binary can store only one file. Together with the binary, a managed object is created which acts as a metadata information for the binary.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
interface BinariesApi {

	companion object Factory {
		fun create(baseUrl: String): BinariesApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): BinariesApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(BinariesApi::class.java)
		}
	}

	/**
	 * Retrieve the stored files </br>
	 * Retrieve the stored files as a collections of managed objects. 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the managed objects are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param childAdditionId Search for a specific child addition and list all the groups to which it belongs.
	 * @param childAssetId Search for a specific child asset and list all the groups to which it belongs.
	 * @param childDeviceId Search for a specific child device and list all the groups to which it belongs.
	 * @param currentPage The current page of the paginated results.
	 * @param ids The managed object IDs to search for (comma separated).
	 * @param owner Username of the owner of the managed objects.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param text Search for managed objects where any property value is equal to the given one. Only string values are supported.
	 * @param type The type of managed object to search for.
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectcollection+json")
	@GET("/inventory/binaries")
	fun getBinaries(
		@Query("childAdditionId") childAdditionId: String? = null, 
		@Query("childAssetId") childAssetId: String? = null, 
		@Query("childDeviceId") childDeviceId: String? = null, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("ids") ids: Array<String>? = null, 
		@Query("owner") owner: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("text") text: String? = null, 
		@Query("type") type: String? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<BinaryCollection>
	
	/**
	 * Upload a file </br>
	 * Uploading a file (binary) requires providing the following properties:  * `object` – In JSON format, it contains information about the file. * `file` – Contains the file to be uploaded.  After the file has been uploaded, the corresponding managed object will contain the fragment `c8y_IsBinary`.  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_INVENTORY_CREATE </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A file was uploaded.</li>
	 * <li>400 Unprocessable Entity – invalid payload.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * </ul>
	 *
	 * @param pObject 
	 * @param file Path of the file to be uploaded.
	 */
	@Headers(*["Content-Type:multipart/form-data", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json"]) 
	@POST("/inventory/binaries")
	@Multipart
	fun uploadBinary(
		@Part("object") pObject: BinaryInfo, 
		@Part("file") file: UByteArray
	): Call<Binary>
	
	/**
	 * Retrieve a stored file </br>
	 * Retrieve a stored file (managed object) by a given ID.  <section><h5>Required roles</h5> ROLE_INVENTORY_READ <b>OR</b> owner of the resource <b>OR</b> MANAGE_OBJECT_READ permission on the resource </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the file is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the managed object.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/octet-stream")
	@GET("/inventory/binaries/{id}")
	fun getBinary(
		@Path("id") id: String
	): Call<ResponseBody>
	
	/**
	 * Replace a file </br>
	 * Upload and replace the attached file (binary) of a specific managed object by a given ID.<br>  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGE_OBJECT_ADMIN permission on the resource </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 A file was uploaded.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 */
	@Headers(*["Content-Type:text/plain", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json"]) 
	@PUT("/inventory/binaries/{id}")
	fun replaceBinary(
		@Body body: UByteArray, 
		@Path("id") id: String
	): Call<Binary>
	
	/**
	 * Remove a stored file </br>
	 * Remove a managed object and its stored file by a given ID.  <section><h5>Required roles</h5> ROLE_INVENTORY_ADMIN <b>OR</b> owner of the resource <b>OR</b> MANAGE_OBJECT_ADMIN permission on the resource </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A managed object and its stored file was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the managed object.
	 */
	@Headers("Accept:application/json")
	@DELETE("/inventory/binaries/{id}")
	fun removeBinary(
		@Path("id") id: String
	): Call<ResponseBody>
}
