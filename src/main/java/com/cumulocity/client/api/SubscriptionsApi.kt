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
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.NotificationSubscription
import com.cumulocity.client.model.NotificationSubscriptionCollection

/**
 * Methods to create, retrieve and delete notification subscriptions.
 */
interface SubscriptionsApi {

	companion object Factory {
		fun create(baseUrl: String): SubscriptionsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): SubscriptionsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(SubscriptionsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all subscriptions
	 * 
	 * Retrieve all subscriptions on your tenant, or a specific subset based on queries.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_NOTIFICATION_2_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and all subscriptions are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * 
	 * @param context
	 * The context to which the subscription is associated.
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param source
	 * The managed object ID to which the subscription is associated.
	 * @param subscription
	 * The subscription name by which filtering will be done.
	 * @param typeFilter
	 * The type used to filter subscriptions. This will check the subscription's `subscriptionFilter.typeFilter` field.
	 * 
	 * **ⓘ Info:** Filtering by `typeFilter` may affect paging. Additional post filtering may be performed if OData-like expressions are used in the subscriptions.
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscriptioncollection+json")
	@GET("/notification2/subscriptions")
	fun getSubscriptions(
		@Query("context") context: String? = null, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("source") source: String? = null, 
		@Query("subscription") subscription: String? = null, 
		@Query("typeFilter") typeFilter: String? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<NotificationSubscriptionCollection>
	
	/**
	 * Create a subscription
	 * 
	 * Create a new subscription, for example, a subscription that forwards measurements and events of a specific type for a given device.
	 * 
	 * In general, each subscription may consist of:
	 * 
	 * * The managed object to which the subscription is associated.
	 * * The context under which the subscription is to be processed.
	 * * The name of the subscription.
	 * * The applicable filter criteria.
	 * * The option to only include specific custom fragments in the forwarded data.
	 * * The option to use persistent or non-persistent message storage.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_NOTIFICATION_2_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A notification subscription was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 409 Duplicated subscription.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.subscription+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscription+json"]) 
	@POST("/notification2/subscriptions")
	@ReadOnlyProperties("self", "id", "self")
	fun createSubscription(
		@Body body: NotificationSubscription, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<NotificationSubscription>
	
	/**
	 * Remove subscriptions by source
	 * 
	 * Remove subscriptions by source and context.
	 * 
	 * > **ⓘ Info:** The request will result in an error if there are no query parameters. The `source` parameter is optional only if the `context` parameter equals `tenant`.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_NOTIFICATION_2_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A collection of subscriptions was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 422 Unprocessable Entity – error in query parameters
	 * 
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param context
	 * The context to which the subscription is associated.
	 * 
	 * **ⓘ Info:** If the value is `mo`, then `source` must also be provided in the query.
	 * @param source
	 * The managed object ID to which the subscription is associated.
	 */
	@Headers("Accept:application/json")
	@DELETE("/notification2/subscriptions")
	fun deleteSubscriptions(
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null, 
		@Query("context") context: String? = null, 
		@Query("source") source: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve a specific subscription
	 * 
	 * Retrieve a specific subscription by a given ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_NOTIFICATION_2_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the subscription is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 Subscription not found.
	 * 
	 * @param id
	 * Unique identifier of the notification subscription.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscription+json")
	@GET("/notification2/subscriptions/{id}")
	fun getSubscription(
		@Path("id") id: String
	): Call<NotificationSubscription>
	
	/**
	 * Remove a specific subscription
	 * 
	 * Remove a specific subscription by a given ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_NOTIFICATION_2_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A subscription was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 404 Subscription not found.
	 * 
	 * @param id
	 * Unique identifier of the notification subscription.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/json")
	@DELETE("/notification2/subscriptions/{id}")
	fun deleteSubscription(
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
}
