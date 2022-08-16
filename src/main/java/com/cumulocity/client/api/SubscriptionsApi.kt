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
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.NotificationSubscription
import com.cumulocity.client.model.NotificationSubscriptionCollection

/**
 * Methods to create, retrieve and delete notification subscriptions. </br>
 * 
 */ 
interface SubscriptionsApi {

	companion object Factory {
		fun create(baseUrl: String): SubscriptionsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): SubscriptionsApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(SubscriptionsApi::class.java)
		}
	}

	/**
	 * Retrieve all subscriptions </br>
	 * Retrieve all subscriptions on your tenant, or a specific subset based on queries.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and all subscriptions are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * </ul>
	 *
	 * @param context The context to which the subscription is associated.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param source The managed object ID to which the subscription is associated.
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscriptioncollection+json")
	@GET("/notification2/subscriptions")
	fun getSubscriptions(
		@Query("context") context: String? = null, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("source") source: String? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<NotificationSubscriptionCollection>
	
	/**
	 * Create a subscription </br>
	 * Create a new subscription, for example, a subscription that forwards measurements and events of a specific type for a given device.  In general, each subscription may consist of:  *  The managed object to which the subscription is associated. *  The context under which the subscription is to be processed. *  The name of the subscription. *  The applicable filter criteria. *  The option to only include specific custom fragments in the forwarded data.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A notification subscription was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Managed object not found.</li>
	 * <li>409 Duplicated subscription.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.subscription+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscription+json"]) 
	@POST("/notification2/subscriptions")
	@ReadOnlyProperties("self", "id", "self")
	fun createSubscription(
		@Body body: NotificationSubscription
	): Call<NotificationSubscription>
	
	/**
	 * Remove subscriptions by source </br>
	 * Remove subscriptions by source and context.  >**&#9432; Info:** The request will result in an error if there are no query parameters. The `source` parameter is optional only if the `context` parameter equals `tenant`.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A collection of subscriptions was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>422 Unprocessable Entity – error in query parameters</li>
	 * </ul>
	 *
	 * @param context The context to which the subscription is associated. > **&#9432; Info:** If the value is `mo`, then `source` must also be provided in the query. 
	 * @param source The managed object ID to which the subscription is associated.
	 */
	@Headers("Accept:application/json")
	@DELETE("/notification2/subscriptions")
	fun deleteSubscriptions(
		@Query("context") context: String? = null, 
		@Query("source") source: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve a specific subscription </br>
	 * Retrieve a specific subscription by a given ID.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the subscription is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Subscription not found.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the notification subscription.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.subscription+json")
	@GET("/notification2/subscriptions/{id}")
	fun getSubscription(
		@Path("id") id: String
	): Call<NotificationSubscription>
	
	/**
	 * Remove a specific subscription </br>
	 * Remove a specific subscription by a given ID.  <section><h5>Required roles</h5> ROLE_NOTIFICATION_2_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A subscription was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not enough permissions/roles to perform this operation.</li>
	 * <li>404 Subscription not found.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the notification subscription.
	 */
	@Headers("Accept:application/json")
	@DELETE("/notification2/subscriptions/{id}")
	fun deleteSubscription(
		@Path("id") id: String
	): Call<ResponseBody>
}
