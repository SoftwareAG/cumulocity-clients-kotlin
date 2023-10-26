// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.RangeStatisticsFile
import com.cumulocity.client.model.TenantUsageStatisticsCollection
import com.cumulocity.client.model.SummaryTenantUsageStatistics
import com.cumulocity.client.model.SummaryAllTenantsUsageStatistics
import com.cumulocity.client.model.TenantUsageStatisticsFileCollection
import com.cumulocity.client.model.StatisticsFile

/**
 * Days are counted according to server timezone, so be aware that the tenant usage statistics displaying/filtering may not work correctly when the client is not in the same timezone as the server. However, it is possible to send a request with a time range (using the query parameters `dateFrom` and `dateTo`) in zoned format (for example, `2020-10-26T03:00:00%2B01:00`). Statistics from past days are stored with daily aggregations, which means that for a specific day you get either the statistics for the whole day or none at all.
 * 
 * ### Request counting in SmartREST and MQTT
 * 
 * * SmartREST: Each row in a SmartREST request is transformed into a separate HTTP request. For example, if one SmartREST request contains 10 rows, then 10 separate calls are executed, meaning that request count is increased by 10.
 * * MQTT: Each row/line counts as a separate request. Creating custom template counts as a single request.
 * 
 * ### REST specific counting details
 * 
 * * All counters increase also when the request is invalid, for example, wrong payload or missing permissions.
 * * Bulk measurements creation and bulk alarm status update are counted as a single "requestCount"/"deviceRequestCount" and multiple inbound data transfer count.
 * 
 * ### SmartREST 1.0 specific counting details
 * 
 * * Invalid SmartREST requests are not counted, for example, when the template doesn't exist.
 * * A new template registration is treated as two separate requests. Create a new inventory object which increases "requestCount", "deviceRequestCount" and "inventoriesCreatedCount". There is also a second request which binds the template with X-ID, this increases "requestCount" and "deviceRequestCount".
 * * Each row in a SmartREST request is transformed into a separate HTTP request. For example, if one SmartREST request contains 10 rows, then 10 separate calls are executed, meaning that both "requestCount" and "deviceRequestCount" are increased by 10.
 * 
 * ### MQTT specific counting details
 * 
 * * Invalid requests are counted, for example, when sending a message with a wrong template ID.
 * * Device creation request and automatic device creation are counted.
 * * Each row/line counts as a separate request.
 * * Creating a custom template counts as a single request, no matter how many rows are sent in the request.
 * * There is one special SmartREST 2.0 template (402 Create location update event with device update) which is doing two things in one call, that is, create a new location event and update the location of the device. It is counted as two separate requests.
 * 
 * ### JSON via MQTT specific counting details
 * 
 * * Invalid requests are counted, for example, when the message payload is invalid.
 * * Bulk creation requests are counted as a single "requestCount"/"deviceRequestCount" and multiple inbound data transfer count.
 * * Bulk creation requests with a wrong payload are not counted for inbound data transfer count.
 * 
 * ### Total inbound data transfer
 * 
 * Inbound data transfer refers to the total number of inbound requests performed to transfer data into the Cumulocity IoT platform. This includes sensor readings, alarms, events, commands and alike that are transferred between devices and the Cumulocity IoT platform using the REST and/or MQTT interfaces. Such an inbound request could also originate from a custom microservice, website or any other client.
 * 
 * See the table below for more information on how the counters are increased. Additionally, it shows how inbound data transfers are handled for both MQTT and REST:
 * 
 * |Type of transfer|MQTT counter information|REST counter information||:---------------|:-----------------------|:-----------------------||Creation of an **alarm** in one request|One alarm creation is counted.|One alarm creation is counted via REST.||Update of an **alarm** (for example, status change)|One alarm update is counted.|One alarm update is counted via REST.||Creation of **multiple alarms** in one request|Each alarm creation in a single MQTT request will be counted.|Not supported by C8Y (REST does not support creating multiple alarms in one call).||Update of **multiple alarms** (for example, status change) in one request|Each alarm update in a single MQTT request will be counted.|Each alarm that matches the filter is counted as an alarm update (causing multiple updates).||Creation of an **event** in one request|One event creation is counted.|One event creation is counted.||Update of an **event** (for example, text change)|One event update is counted.|One event update is counted.||Creation of **multiple events** in one request|Each event creation in a single MQTT request will be counted.|Not supported by C8Y (REST does not support creating multiple events in one call).||Update of **multiple events** (for example, text change) in one request|Each event update in a single MQTT request will be counted.|Not supported by C8Y (REST does not support updating multiple events in one call).||Creation of a **measurement** in one request|One measurement creation is counted. |One measurement creation is counted.||Creation of **multiple measurements** in one request|Each measurement creation in a single MQTT request will be counted. Example: If MQTT is used to report 5 measurements, the measurementCreated counter will be incremented by five.|REST allows multiple measurements to be created by sending multiple measurements in one call. In this case, each measurement sent via REST is counted individually. The call itself is not counted. For example, if somebody sends 5 measurements via REST in one call, the corresponding counter will be increased by 5. Measurements with multiple series are counted as a singular measurement.||Creation of a **managed object** in one request|One managed object creation is counted.|One managed object creation is counted.||Update of one **managed object** (for example, status change)|One managed object update is counted.|One managed object update is counted.||Update of **multiple managed objects** in one request|Each managed object update in a single MQTT request will be counted.|Not supported by C8Y (REST does not support updating multiple managed objects in one call).||Creation/update of **multiple alarms/measurements/events/inventories** mixed in a single call.|Each MQTT line is processed separately. If it is a creation/update of an event/alarm/measurement/inventory, the corresponding counter is increased by one.|Not supported by the REST API.||Assign/unassign of **child devices and child assets** in one request|One managed object update is counted.|One managed object update is counted.|
 * 
 * ### Microservice usage statistics
 * 
 * The microservice usage statistics gathers information on the resource usage for tenants for each subscribed application which are collected on a daily base.
 * 
 * The microservice usage's information is stored in the `resources` object.
 * 
 * ### Frequently asked questions
 * 
 * #### Which requests are counted as general "requestCount"?
 * 
 * All requests which the platform receives are counted, including,for example, UI requests, microservices requests, device requests and agents requests. Only a few internal endpoints are not counted:
 * 
 * * `/health` (and all endpoints including this URI fragment, like `/tenant/health`)
 * * `/application/currentApplication` (and all subresources, like `/application/currentApplication/subscriptions`)
 * * `/tenant/limit`
 * * `/devicecontrol/deviceCredentials`
 * * `/inventory/templates` (and all subresources)
 * 
 * #### My devices are not sending any data, but "requestCount" is increasing, and the total number is really big. Why is this happening?
 * 
 * Not only device requests are counted. Every user interaction with UI applications generates some requests to the backend API. Additionally you may have subscribed standard or custom microservices, which also regularly send requests to the platform.
 * 
 * Example: If you have four microservices and each microservice sends five requests per minute, this setup creates `4 * 5 * 60 * 24 = 28800` requests per day. Similar numbers arise if there are multiple users working with the given tenant UI concurrently.
 * 
 * #### Which requests are counted as "deviceRequestCount"?
 * 
 * All requests from "requestCount" except the following:
 * 
 * * Tenant API requests
 * * Application API requests
 * * User API requests
 * * Requests with the proper HTTP header `X-Cumulocity-Application-Key`, matching the application key of one of the applications used by a particular tenant
 * 
 * The exclusion of the APIs in the list above means that requests to endpoints which start with the mentioned API prefixes are not counted. For example, for the Tenant API the following endpoints are not counted (the list is incomplete):
 * 
 * * `/tenant/tenants`
 * * `/tenant/currentTenant`
 * * `/tenant/statistics`
 * * `/tenant/options`
 * 
 * > **ⓘ Info:** Each microservice and web application must include the `X-Cumulocity-Application-Key` header in all requests.Otherwise such requests are counted as device requests which incorrectly affects the "deviceRequestCount" usage metric.
 */
interface UsageStatisticsApi {

	companion object Factory {
		fun create(baseUrl: String): UsageStatisticsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): UsageStatisticsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(UsageStatisticsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve statistics of the current tenant
	 * 
	 * Retrieve usage statistics of the current tenant.
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
	 * * HTTP 200 The request has succeeded and the tenant statistics are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param dateFrom
	 * Start date or date and time of the statistics.
	 * @param dateTo
	 * End date or date and time of the statistics.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantusagestatisticscollection+json")
	@GET("/tenant/statistics")
	fun getTenantUsageStatisticsCollectionResource(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<TenantUsageStatisticsCollection>
	
	/**
	 * Retrieve a usage statistics summary
	 * 
	 * Retrieve a usage statistics summary of a tenant.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_STATISTICS_READ *OR* ROLE_INVENTORY_READ 
	 *  If the `tenant` request parameter is specified, then the current tenant must be the management tenant *OR* the parent of the requested `tenant`. 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the usage statistics summary is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Tenant not found.
	 * 
	 * @param dateFrom
	 * Start date or date and time of the statistics.
	 * @param dateTo
	 * End date or date and time of the statistics.
	 * @param tenant
	 * Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantusagestatisticssummary+json")
	@GET("/tenant/statistics/summary")
	fun getTenantUsageStatistics(
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("tenant") tenant: String? = null
	): Call<SummaryTenantUsageStatistics>
	
	/**
	 * Retrieve a summary of all usage statistics
	 * 
	 * Retrieve a summary of all tenants usage statistics.
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
	 * * HTTP 200 The request has succeeded and the usage statistics summary is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param dateFrom
	 * Start date or date and time of the statistics.
	 * @param dateTo
	 * End date or date and time of the statistics.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/tenant/statistics/allTenantsSummary")
	fun getTenantsUsageStatistics(
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null
	): Call<Array<SummaryAllTenantsUsageStatistics>>
	
	/**
	 * Retrieve usage statistics files metadata
	 * 
	 * Retrieve usage statistics summary files report metadata.
	 * 
	 * > **ⓘ Info:** This is only accessible by the Management tenant.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the tenant statistics are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param dateFrom
	 * Start date or date and time of the statistics file generation.
	 * @param dateTo
	 * End date or date and time of the statistics file generation.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantStatisticsfilecollection+json")
	@GET("/tenant/statistics/files")
	fun getMetadata(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<TenantUsageStatisticsFileCollection>
	
	/**
	 * Generate a statistics file report
	 * 
	 * Generate a TEST statistics file report for a given time range.
	 * 
	 * There are two types of statistics files:
	 * 
	 * * REAL - generated by the system on the first day of the month and including statistics from the previous month.
	 * * TEST - generated by the user with a time range specified in the query parameters (`dateFrom`, `dateTo`).
	 * 
	 * > **ⓘ Info:** This is only accessible by the Management tenant.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN *OR* ROLE_TENANT_MANAGEMENT_CREATE 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A statistics file was generated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.tenantstatisticsdate+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.tenantstatisticsfile+json"]) 
	@POST("/tenant/statistics/files")
	fun generateStatisticsFile(
		@Body body: RangeStatisticsFile
	): Call<StatisticsFile>
	
	/**
	 * Retrieve a usage statistics file
	 * 
	 * Retrieve a specific usage statistics file (by a given ID).
	 * 
	 * > **ⓘ Info:** This is only accessible by the Management tenant.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the file is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Statistics file not found.
	 * 
	 * @param id
	 * Unique identifier of the statistics file.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/octet-stream")
	@GET("/tenant/statistics/files/{id}")
	fun getStatisticsFile(
		@Path("id") id: String
	): Call<ResponseBody>
	
	/**
	 * Retrieve the latest usage statistics file
	 * 
	 * Retrieve the latest usage statistics file with REAL data for a given month.
	 * 
	 * There are two types of statistics files:
	 * 
	 * * REAL - generated by the system on the first day of the month and includes statistics for the previous month.
	 * * TEST - generated by the user with a time range specified in the query parameters (`dateFrom`, `dateTo`).
	 * 
	 * > **ⓘ Info:** This is only accessible by the Management tenant.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_TENANT_MANAGEMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the file is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param month
	 * Date (format YYYY-MM-dd) specifying the month for which the statistics file will be downloaded (the day value is ignored).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/octet-stream")
	@GET("/tenant/statistics/files/latest/{month}")
	fun getLatestStatisticsFile(
		@Path("month") month: String
	): Call<ResponseBody>
}
