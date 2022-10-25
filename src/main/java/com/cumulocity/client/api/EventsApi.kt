// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.Event
import com.cumulocity.client.model.EventCollection

/**
 * Events are used to pass real-time information through Cumulocity IoT and they come in three types: base events  when something in the sensor network happens, alarms requiring manual actions, and audit logs to store events that are security-relevant.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
interface EventsApi {

	companion object Factory {
		fun create(baseUrl: String): EventsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): EventsApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(EventsApi::class.java)
		}
	}

	/**
	 * Retrieve all events </br>
	 * Retrieve all events on your tenant.  In case of executing [range queries](https://en.wikipedia.org/wiki/Range_query_(database)) between an upper and lower boundary, for example, querying using `dateFrom`–`dateTo` or `createdFrom`–`createdTo`, the newest registered events are returned first. It is possible to change the order using the query parameter `revert=true`.  <section><h5>Required roles</h5> ROLE_EVENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all events are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param createdFrom Start date or date and time of the event's creation (set by the platform during creation).
	 * @param createdTo End date or date and time of the event's creation (set by the platform during creation).
	 * @param currentPage The current page of the paginated results.
	 * @param dateFrom Start date or date and time of the event occurrence (provided by the device).
	 * @param dateTo End date or date and time of the event occurrence (provided by the device).
	 * @param fragmentType A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.
	 * @param fragmentValue Allows filtering events by the fragment's value, but only when provided together with `fragmentType`.  > **⚠️ Important:** Only fragments with a string value are supported. 
	 * @param lastUpdatedFrom Start date or date and time of the last update made.
	 * @param lastUpdatedTo End date or date and time of the last update made.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param revert If you are using a range query (that is, at least one of the `dateFrom` or `dateTo` parameters is included in the request), then setting `revert=true` will sort the results by the oldest events first. By default, the results are sorted by the newest events first. 
	 * @param source The managed object ID to which the event is associated.
	 * @param type The type of event to search for.
	 * @param withSourceAssets When set to `true` also events for related source assets will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withSourceDevices When set to `true` also events for related source devices will be included in the request. When this parameter is provided a `source` must be specified.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.eventcollection+json")
	@GET("/event/events")
	fun getEvents(
		@Query("createdFrom") createdFrom: String? = null, 
		@Query("createdTo") createdTo: String? = null, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("fragmentType") fragmentType: String? = null, 
		@Query("fragmentValue") fragmentValue: String? = null, 
		@Query("lastUpdatedFrom") lastUpdatedFrom: String? = null, 
		@Query("lastUpdatedTo") lastUpdatedTo: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("revert") revert: Boolean? = null, 
		@Query("source") source: String? = null, 
		@Query("type") type: String? = null, 
		@Query("withSourceAssets") withSourceAssets: Boolean? = null, 
		@Query("withSourceDevices") withSourceDevices: Boolean? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<EventCollection>
	
	/**
	 * Create an event </br>
	 * An event must be associated with a source (managed object) identified by an ID.<br> In general, each event consists of:  *  A type to identify the nature of the event. *  A time stamp to indicate when the event was last updated. *  A description of the event. *  The managed object which originated the event.  <section><h5>Required roles</h5> ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An event was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.event+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.event+json"]) 
	@POST("/event/events")
	@ReadOnlyProperties("lastUpdated", "creationTime", "self", "id", "self")
	fun createEvent(
		@Body body: Event
	): Call<Event>
	
	/**
	 * Remove event collections </br>
	 * Remove event collections specified by query parameters.  DELETE requests are not synchronous. The response could be returned before the delete request has been completed. This may happen especially when the deleted event has a lot of associated data. After sending the request, the platform starts deleting the associated data in an asynchronous way. Finally, the requested event is deleted after all associated data has been deleted.  > **⚠️ Important:** Note that it is possible to call this endpoint without providing any parameter - it will result in deleting all events and it is not recommended.  <section><h5>Required roles</h5> ROLE_EVENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A collection of events was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * </ul>
	 *
	 * @param createdFrom Start date or date and time of the event's creation (set by the platform during creation).
	 * @param createdTo End date or date and time of the event's creation (set by the platform during creation).
	 * @param dateFrom Start date or date and time of the event occurrence (provided by the device).
	 * @param dateTo End date or date and time of the event occurrence (provided by the device).
	 * @param fragmentType A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.
	 * @param source The managed object ID to which the event is associated.
	 * @param type The type of event to search for.
	 */
	@Headers("Accept:application/json")
	@DELETE("/event/events")
	fun deleteEvents(
		@Query("createdFrom") createdFrom: String? = null, 
		@Query("createdTo") createdTo: String? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("fragmentType") fragmentType: String? = null, 
		@Query("source") source: String? = null, 
		@Query("type") type: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve a specific event </br>
	 * Retrieve a specific event by a given ID.  <section><h5>Required roles</h5> ROLE_EVENT_READ <b>OR</b> owner of the source <b>OR</b> EVENT_READ permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the event is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Event not found.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the event.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.event+json")
	@GET("/event/events/{id}")
	fun getEvent(
		@Path("id") id: String
	): Call<Event>
	
	/**
	 * Update a specific event </br>
	 * Update a specific event by a given ID. Only its text description and custom fragments can be updated.  <section><h5>Required roles</h5> ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An event was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Event not found.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param id Unique identifier of the event.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.event+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.event+json"]) 
	@PUT("/event/events/{id}")
	@ReadOnlyProperties("lastUpdated", "creationTime", "self", "id", "source", "time", "type")
	fun updateEvent(
		@Body body: Event, 
		@Path("id") id: String
	): Call<Event>
	
	/**
	 * Remove a specific event </br>
	 * Remove a specific event by a given ID.  <section><h5>Required roles</h5> ROLE_EVENT_ADMIN <b>OR</b> owner of the source <b>OR</b> EVENT_ADMIN permission on the source </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 An event was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Event not found.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the event.
	 */
	@Headers("Accept:application/json")
	@DELETE("/event/events/{id}")
	fun deleteEvent(
		@Path("id") id: String
	): Call<ResponseBody>
}
