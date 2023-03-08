// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import com.cumulocity.client.model.NotificationTokenClaims
import com.cumulocity.client.model.NotificationToken
import com.cumulocity.client.model.NotificationSubscriptionResult

/**
 * In order to receive subscribed notifications, a consumer application or microservice must obtain an authorization token that provides proof that the holder is allowed to receive subscribed notifications.
 */
interface TokensApi {

	companion object Factory {
		fun create(baseUrl: String): TokensApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): TokensApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(TokensApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Create a notification token
	 * 
	 * Create a new JWT (JSON web token) access token which can be used to establish a successful WebSocket connection to read a sequence of notifications.
	 * 
	 * In general, each request to obtain an access token consists of:
	 * 
	 * * The subscriber name which the client wishes to be identified with.
	 * * The subscription name. This value must be associated with a subscription that's already been created and in essence, the obtained token will give the ability to read notifications for the subscription that is specified here.
	 * * The token expiration duration.
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
	 * * HTTP 200 A notification token was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not enough permissions/roles to perform this operation.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@POST("/notification2/token")
	fun createToken(
		@Body body: NotificationTokenClaims, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<NotificationToken>
	
	/**
	 * Unsubscribe a subscriber
	 * 
	 * Unsubscribe a notification subscriber using the notification token.
	 * 
	 * Once a subscription is made, notifications will be kept until they are consumed by all subscribers who have previously connected to the subscription. For non-volatile subscriptions, this can result in notifications remaining in storage if never consumed by the application.They will be deleted if a tenant is deleted. It can take up considerable space in permanent storage for high-frequency notification sources. Therefore, we recommend you to unsubscribe a subscriber that will never run again.
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The notification subscription was deleted or is scheduled for deletion.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param token
	 * Subscriptions associated with this token will be removed.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@POST("/notification2/unsubscribe")
	fun unsubscribeSubscriber(
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null, 
		@Query("token") token: String
	): Call<NotificationSubscriptionResult>
}
