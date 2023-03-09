// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.supplementary.SeparatedQueryParameter
import com.cumulocity.client.model.Alarm
import com.cumulocity.client.model.AlarmCollection

/**
 * An alarm represents an event that requires manual action, for example, when the temperature of a fridge increases above a particular threshold.
 * 
 * > **ⓘ Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 */
interface AlarmsApi {

	companion object Factory {
		fun create(baseUrl: String): AlarmsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): AlarmsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(AlarmsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all alarms
	 * 
	 * Retrieve all alarms on your tenant, or a specific subset based on queries. The results are sorted by the newest alarms first.
	 * 
	 * #### Query parameters
	 * 
	 * The query parameter `withTotalPages` only works when the user has the ROLE_ALARM_READ role, otherwise it is ignored.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  The role ROLE_ALARM_READ is not required, but if a user has this role, all the alarms on the tenant are returned. If a user has access to alarms through inventory roles, only those alarms are returned. 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and all alarms are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param createdFrom
	 * Start date or date and time of the alarm creation.
	 * @param createdTo
	 * End date or date and time of the alarm creation.
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param dateFrom
	 * Start date or date and time of the alarm occurrence.
	 * @param dateTo
	 * End date or date and time of the alarm occurrence.
	 * @param lastUpdatedFrom
	 * Start date or date and time of the last update made.
	 * @param lastUpdatedTo
	 * End date or date and time of the last update made.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param resolved
	 * When set to `true` only alarms with status CLEARED will be fetched, whereas `false` will fetch all alarms with status ACTIVE or ACKNOWLEDGED.
	 * @param severity
	 * The severity of the alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm severities at once, comma-separate the values.
	 * @param source
	 * The managed object ID to which the alarm is associated.
	 * @param status
	 * The status of the alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm statuses at once, comma-separate the values.
	 * @param type
	 * The types of alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm types at once, comma-separate the values. Space characters in alarm types must be escaped.
	 * @param withSourceAssets
	 * When set to `true` also alarms for related source assets will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withSourceDevices
	 * When set to `true` also alarms for related source devices will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarmcollection+json")
	@GET("/alarm/alarms")
	fun getAlarms(
		@Query("createdFrom") createdFrom: String? = null, 
		@Query("createdTo") createdTo: String? = null, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("lastUpdatedFrom") lastUpdatedFrom: String? = null, 
		@Query("lastUpdatedTo") lastUpdatedTo: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("resolved") resolved: Boolean? = null, 
		@Query("severity") severity: SeparatedQueryParameter<String>? = null, 
		@Query("source") source: String? = null, 
		@Query("status") status: SeparatedQueryParameter<String>? = null, 
		@Query("type") type: SeparatedQueryParameter<String>? = null, 
		@Query("withSourceAssets") withSourceAssets: Boolean? = null, 
		@Query("withSourceDevices") withSourceDevices: Boolean? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<AlarmCollection>
	
	/**
	 * Update alarm collections
	 * 
	 * Update alarm collections specified by query parameters. At least one query parameter is required to avoid accidentally updating all existing alarms.
	 * Currently, only the status of alarms can be modified.
	 * 
	 * > **ⓘ Info:** Since this operation can take considerable time, the request returns after maximum 0.5 seconds of processing, and the update operation continues as a background process in the platform.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_ALARM_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 An alarm collection was updated.
	 * * HTTP 202 An alarm collection is being updated in background.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param createdFrom
	 * Start date or date and time of the alarm creation.
	 * @param createdTo
	 * End date or date and time of the alarm creation.
	 * @param dateFrom
	 * Start date or date and time of the alarm occurrence.
	 * @param dateTo
	 * End date or date and time of the alarm occurrence.
	 * @param resolved
	 * When set to `true` only alarms with status CLEARED will be fetched, whereas `false` will fetch all alarms with status ACTIVE or ACKNOWLEDGED.
	 * @param severity
	 * The severity of the alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm severities at once, comma-separate the values.
	 * @param source
	 * The managed object ID to which the alarm is associated.
	 * @param status
	 * The status of the alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm statuses at once, comma-separate the values.
	 * @param withSourceAssets
	 * When set to `true` also alarms for related source assets will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withSourceDevices
	 * When set to `true` also alarms for related source devices will be included in the request. When this parameter is provided a `source` must be specified.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.alarm+json", "Accept:application/json"]) 
	@PUT("/alarm/alarms")
	@ReadOnlyProperties("firstOccurrenceTime", "severity", "lastUpdated", "creationTime", "count", "self", "id", "source", "text", "time", "type")
	fun updateAlarms(
		@Body body: Alarm, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null, 
		@Query("createdFrom") createdFrom: String? = null, 
		@Query("createdTo") createdTo: String? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("resolved") resolved: Boolean? = null, 
		@Query("severity") severity: SeparatedQueryParameter<String>? = null, 
		@Query("source") source: String? = null, 
		@Query("status") status: SeparatedQueryParameter<String>? = null, 
		@Query("withSourceAssets") withSourceAssets: Boolean? = null, 
		@Query("withSourceDevices") withSourceDevices: Boolean? = null
	): Call<ResponseBody>
	
	/**
	 * Create an alarm
	 * 
	 * An alarm must be associated with a source (managed object) identified by ID.
	 * In general, each alarm may consist of:
	 * 
	 * * A status showing whether the alarm is ACTIVE, ACKNOWLEDGED or CLEARED.
	 * * A time stamp to indicate when the alarm was last updated.
	 * * The severity of the alarm: CRITICAL, MAJOR, MINOR or WARNING.
	 * * A history of changes to the event in form of audit logs.
	 * 
	 * ### Alarm suppression
	 * 
	 * If the source device is in maintenance mode, the alarm is not created and not reported to the Cumulocity IoT event processing engine. When sending a POST request to create a new alarm and if the source device is in maintenance mode, the self link of the alarm will be:
	 * 
	 * ```json
	 * "self": "https://<TENANT_DOMAIN>/alarm/alarms/null"
	 * ```
	 * ### Alarm de-duplication
	 * 
	 * If an ACTIVE or ACKNOWLEDGED alarm with the same source and type exists, no new alarm is created.Instead, the existing alarm is updated by incrementing the `count` property; the `time` property is also updated.Any other changes are ignored, and the alarm history is not updated. Alarms with status CLEARED are not de-duplicated.The first occurrence of the alarm is recorded in the `firstOccurrenceTime` property.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_ALARM_ADMIN *OR* owner of the source *OR* ALARM_ADMIN permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 An alarm was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.alarm+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarm+json"]) 
	@POST("/alarm/alarms")
	@ReadOnlyProperties("firstOccurrenceTime", "lastUpdated", "creationTime", "count", "self", "id", "self")
	fun createAlarm(
		@Body body: Alarm, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Alarm>
	
	/**
	 * Remove alarm collections
	 * 
	 * Remove alarm collections specified by query parameters.
	 * 
	 * > **⚠️ Important:** Note that it is possible to call this endpoint without providing any parameter - it will result in deleting all alarms and it is not recommended.Also note that DELETE requests are not synchronous. The response could be returned before the delete request has been completed.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_ALARM_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A collection of alarms was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * 
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param createdFrom
	 * Start date or date and time of the alarm creation.
	 * @param createdTo
	 * End date or date and time of the alarm creation.
	 * @param dateFrom
	 * Start date or date and time of the alarm occurrence.
	 * @param dateTo
	 * End date or date and time of the alarm occurrence.
	 * @param resolved
	 * When set to `true` only alarms with status CLEARED will be fetched, whereas `false` will fetch all alarms with status ACTIVE or ACKNOWLEDGED.
	 * @param severity
	 * The severity of the alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm severities at once, comma-separate the values.
	 * @param source
	 * The managed object ID to which the alarm is associated.
	 * @param status
	 * The status of the alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm statuses at once, comma-separate the values.
	 * @param type
	 * The types of alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm types at once, comma-separate the values. Space characters in alarm types must be escaped.
	 * @param withSourceAssets
	 * When set to `true` also alarms for related source assets will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withSourceDevices
	 * When set to `true` also alarms for related source devices will be included in the request. When this parameter is provided a `source` must be specified.
	 */
	@Headers("Accept:application/json")
	@DELETE("/alarm/alarms")
	fun deleteAlarms(
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null, 
		@Query("createdFrom") createdFrom: String? = null, 
		@Query("createdTo") createdTo: String? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("resolved") resolved: Boolean? = null, 
		@Query("severity") severity: SeparatedQueryParameter<String>? = null, 
		@Query("source") source: String? = null, 
		@Query("status") status: SeparatedQueryParameter<String>? = null, 
		@Query("type") type: SeparatedQueryParameter<String>? = null, 
		@Query("withSourceAssets") withSourceAssets: Boolean? = null, 
		@Query("withSourceDevices") withSourceDevices: Boolean? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve a specific alarm
	 * 
	 * Retrieve a specific alarm by a given ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_ALARM_READ *OR* owner of the source *OR* ALARM_READ permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the alarm is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Alarm not found.
	 * 
	 * @param id
	 * Unique identifier of the alarm.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarm+json")
	@GET("/alarm/alarms/{id}")
	fun getAlarm(
		@Path("id") id: String
	): Call<Alarm>
	
	/**
	 * Update a specific alarm
	 * 
	 * Update a specific alarm by a given ID.Only text, status, severity and custom properties can be modified. A request will be rejected when non-modifiable properties are provided in the request body.
	 * 
	 * > **ⓘ Info:** Changes to alarms will generate a new audit record. The audit record will include the username and application that triggered the update, if applicable. If the update operation doesn’t change anything (that is, the request body contains data that is identical to the already present in the database), there will be no audit record added and no notifications will be sent.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_ALARM_ADMIN *OR* owner of the source *OR* ALARM_ADMIN permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 An alarm was updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Alarm not found.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the alarm.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.alarm+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.alarm+json"]) 
	@PUT("/alarm/alarms/{id}")
	@ReadOnlyProperties("firstOccurrenceTime", "lastUpdated", "creationTime", "count", "self", "id", "source", "time", "type")
	fun updateAlarm(
		@Body body: Alarm, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Alarm>
	
	/**
	 * Retrieve the total number of alarms
	 * 
	 * Count the total number of active alarms on your tenant.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  The role ROLE_ALARM_READ is not required, but if a user has this role, all the alarms on the tenant are counted. Otherwise, inventory role permissions are used to count the alarms and the limit is 100. 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the number of active alarms is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param dateFrom
	 * Start date or date and time of the alarm occurrence.
	 * @param dateTo
	 * End date or date and time of the alarm occurrence.
	 * @param resolved
	 * When set to `true` only alarms with status CLEARED will be fetched, whereas `false` will fetch all alarms with status ACTIVE or ACKNOWLEDGED.
	 * @param severity
	 * The severity of the alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm severities at once, comma-separate the values.
	 * @param source
	 * The managed object ID to which the alarm is associated.
	 * @param status
	 * The status of the alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm statuses at once, comma-separate the values.
	 * @param type
	 * The types of alarm to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple alarm types at once, comma-separate the values. Space characters in alarm types must be escaped.
	 * @param withSourceAssets
	 * When set to `true` also alarms for related source assets will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withSourceDevices
	 * When set to `true` also alarms for related source devices will be included in the request. When this parameter is provided a `source` must be specified.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, text/plain, application/json")
	@GET("/alarm/alarms/count")
	fun getNumberOfAlarms(
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("resolved") resolved: Boolean? = null, 
		@Query("severity") severity: SeparatedQueryParameter<String>? = null, 
		@Query("source") source: String? = null, 
		@Query("status") status: SeparatedQueryParameter<String>? = null, 
		@Query("type") type: SeparatedQueryParameter<String>? = null, 
		@Query("withSourceAssets") withSourceAssets: Boolean? = null, 
		@Query("withSourceDevices") withSourceDevices: Boolean? = null
	): Call<Int>
}
