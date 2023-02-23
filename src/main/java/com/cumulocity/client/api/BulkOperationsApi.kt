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
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.BulkOperation
import com.cumulocity.client.model.BulkOperationCollection

/**
 * The bulk operations API allows to schedule an operation on a group of devices to be executed at a specified time.
 * It is required to specify the delay between the creation of subsequent operations.
 * When the bulk operation is created, it has the status ACTIVE.
 * When all operations are created, the bulk operation has the status COMPLETED.
 * It is also possible to cancel an already created bulk operation by deleting it.
 * 
 * When you create a bulk operation, you can run it in two modes:
 * 
 * * If `groupId` is passed, it works the standard way, that means, it takes devices from a group and schedules operations on them.
 * * If `failedParentId` is passed, it takes the already processed bulk operation by that ID, and schedules operations on devices for which the previous operations failed.
 * 
 * Note that passing both `groupId` and `failedParentId` will not work, and a bulk operation works with groups of type `static` and `dynamic`.
 * 
 * > **&#9432; Info:** The bulk operations API requires different roles than the rest of the device control API: `BULK_OPERATION_READ` and `BULK_OPERATION_ADMIN`.
 * >
 * > The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
interface BulkOperationsApi {

	companion object Factory {
		fun create(baseUrl: String): BulkOperationsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): BulkOperationsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(BulkOperationsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve a list of bulk operations
	 * Retrieve a list of bulk operations.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_BULK_OPERATION_READ
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the list of bulk operations sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.bulkoperationcollection+json, application/vnd.com.nsn.cumulocity.error+json")
	@GET("/devicecontrol/bulkoperations")
	fun getBulkOperations(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null
	): Call<BulkOperationCollection>
	
	/**
	 * Create a bulk operation
	 * Create a bulk operation.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_BULK_OPERATION_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A bulk operation was created.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.bulkoperation+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulkoperation+json"]) 
	@POST("/devicecontrol/bulkoperations")
	@ReadOnlyProperties("generalStatus", "self", "progress", "id", "status")
	fun createBulkOperation(
		@Body body: BulkOperation, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<BulkOperation>
	
	/**
	 * Retrieve a specific bulk operation
	 * Retrieve a specific bulk operation (by a given ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_BULK_OPERATION_READ
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the bulk operation is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Bulk operation not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the bulk operation.
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulkoperation+json")
	@GET("/devicecontrol/bulkoperations/{id}")
	fun getBulkOperation(
		@Path("id") id: String
	): Call<BulkOperation>
	
	/**
	 * Update a specific bulk operation
	 * Update a specific bulk operation (by a given ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_BULK_OPERATION_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - A bulk operation was updated.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Bulk operation not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @param id Unique identifier of the bulk operation.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.bulkoperation+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulkoperation+json"]) 
	@PUT("/devicecontrol/bulkoperations/{id}")
	@ReadOnlyProperties("generalStatus", "self", "progress", "id", "status")
	fun updateBulkOperation(
		@Body body: BulkOperation, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<BulkOperation>
	
	/**
	 * Delete a specific bulk operation
	 * Delete a specific bulk operation (by a given ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_BULK_OPERATION_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A bulk operation was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Bulk operation not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the bulk operation.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/json")
	@DELETE("/devicecontrol/bulkoperations/{id}")
	fun deleteBulkOperation(
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
}
