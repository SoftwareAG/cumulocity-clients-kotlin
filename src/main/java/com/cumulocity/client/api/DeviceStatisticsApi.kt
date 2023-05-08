// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import com.cumulocity.client.model.DeviceStatisticsCollection

/**
 * Device statistics are collected for each inventory object with at least one measurement, event or alarm. There are no additional checks if the inventory object is marked as device using the `c8y_IsDevice` fragment. When the first measurement, event or alarm is created for a specific inventory object, Cumulocity IoT is always considering this as a device and starts counting.
 * 
 * Device statistics are counted with daily and monthy rate. All requests are considered when counting device statistics, no matter which processing mode is used.
 * 
 * The following requests are counted:
 * 
 * * Alarm creation and update
 * * Event creation and update
 * * Measurement creation
 * * Bulk measurement creation is counted as multiple requests
 * * Bulk alarm status update is counted as multiple requests
 * * MQTT and SmartREST requests with multiple rows are counted as multiple requests
 * 
 * ### Frequently asked questions
 * 
 * #### Are operations on device firmware counted?
 * 
 * **No**, device configuration and firmware update operate on inventory objects, hence they are not counted.
 * 
 * #### Are requests done by the UI applications, for example, when browsing device details, counted?
 * 
 * **No**, viewing device details performs only GET requests which are not counted.
 * 
 * #### Is the clear alarm operation done from the UI counted?
 * 
 * **Yes**, a clear alarm operation in fact performs an alarm update operation and it will be counted as device request.
 * 
 * #### Is there any operation performed on the device which is counted?
 * 
 * **Yes**, retrieving device logs requires from the device to create an event and attach a binary with logs to it. Those are two separate requests and both are counted.
 * 
 * #### When I have a device with children are the requests counted always to the root device or separately for each child?
 * 
 * Separately for each child.
 */
interface DeviceStatisticsApi {

	companion object Factory {
		fun create(baseUrl: String): DeviceStatisticsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): DeviceStatisticsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(DeviceStatisticsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve monthly device statistics
	 * 
	 * Retrieve monthly device statistics from a specific tenant (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_STATISTICS_READ 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the devices statistics are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param date
	 * Date (format YYYY-MM-dd) of the queried month (the day value is ignored).
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param deviceId
	 * The ID of the device to search for.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/tenant/statistics/device/{tenantId}/monthly/{date}")
	fun getMonthlyDeviceStatistics(
		@Path("tenantId") tenantId: String, 
		@Path("date") date: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("deviceId") deviceId: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<DeviceStatisticsCollection>
	
	/**
	 * Retrieve daily device statistics
	 * 
	 * Retrieve daily device statistics from a specific tenant (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_STATISTICS_READ 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the devices statistics are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * 
	 * @param tenantId
	 * Unique identifier of a Cumulocity IoT tenant.
	 * @param date
	 * Date (format YYYY-MM-dd) of the queried day.
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param deviceId
	 * The ID of the device to search for.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/tenant/statistics/device/{tenantId}/daily/{date}")
	fun getDailyDeviceStatistics(
		@Path("tenantId") tenantId: String, 
		@Path("date") date: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("deviceId") deviceId: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<DeviceStatisticsCollection>
}
