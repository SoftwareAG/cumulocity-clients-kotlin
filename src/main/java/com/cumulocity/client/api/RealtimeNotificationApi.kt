// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import com.cumulocity.client.model.RealtimeNotification

/**
 * # Real-time operations
 * 
 * Real-time notification services of Cumulocity IoT have their own subscription channel name format and URL. The real-time notifications are available for [Alarms](#tag/Alarm-notification-API), [Device control](#tag/Device-control-notification-API), [Events](#tag/Event-notification-API), [Inventory](#tag/Inventory-notification-API) and [Measurements](#tag/Measurement-notification-API).
 * 
 * Note that when using long-polling, all POST requests must contain the Accept header, otherwise an empty response body will be returned.All requests are sent to the <kbd>/notification/realtime</kbd> endpoint.
 * 
 * > **ⓘ Info:** The long-polling interface is designed as a mechanism for custom applications to poll infrequent events from Cumulocity IoT. The long-polling interface is not designed as a mechanism to stream large data volumes (>100kB/sec) or frequent data (>50 events/sec) out of Cumulocity IoT. The usage of long-polling is not supported for such use cases.
 * ## Handshake
 * 
 * A real-time notifications client initiates the connection negotiation by sending a message to the `/meta/handshake` channel. In response, the client receives a `clientId` which identifies a conversation and must be passed in every non-handshake request.
 * 
 * > **ⓘ Info:** The number of parallel connections that can be opened at the same time by a single user is limited. After exceeding this limit when a new connection is created, the oldest one will be closed and the newly created one will be added in its place. This limit is configurable and managed per installation. Its default value is 10 connections per user, subscription channel and server node.
 * When using WebSockets, a property `ext` containing an authentication object must be sent. In case of basic authentication, the token is used with Base64 encoded credentials. In case of OAuth authentication, the request must have the cookie with the authorization name, holding the access token. Moreover, the XSRF token must be forwarded as part of the handshake message.
 * 
 * ### Request example
 * 
 * ```http
 * POST /notification/realtime
 * Authorization: <AUTHORIZATION>
 * Content-Type: application/json
 * 
 * [
 *   {
 *     "channel": "/meta/handshake",
 *     "version": "1.0"
 *   }
 * ]
 * ```
 * ### Response example
 * 
 * A successful response looks like:
 * 
 * ```json
 * [
 *   {
 *     "channel": "/meta/handshake",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp",
 *     "minimumVersion": "1.0",
 *     "successful": true,
 *     "supportedConnectionTypes": [
 *       "long-polling",
 *       "smartrest-long-polling",
 *       "websocket"
 *     ],
 *     "version": "1.0"
 *   }
 * ]
 * ```
 * When an error occurs, the response looks like:
 * 
 * ```json
 * [
 *   {
 *     "channel": "/meta/handshake",
 *     "error": "403::Handshake denied",
 *     "successful": false
 *   }
 * ]
 * ```
 * ## Subscribe
 * 
 * A notification client can send subscribe messages and specify the desired channel to receive output messages from the Cumulocity IoT server. The client will receive the messages in succeeding connect requests.
 * 
 * Each REST API that uses the real-time notification service has its own format for channel names. See [Device control](#tag/Device-control-notification-API) for more details.
 * 
 * ### Request example
 * 
 * ```http
 * POST /notification/realtime
 * Authorization: <AUTHORIZATION>
 * Content-Type: application/json
 * 
 * [
 *   {
 *     "channel": "/meta/subscribe",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp",
 *     "subscription": "/alarms/<DEVICE_ID>"
 *   }
 * ]
 * ```
 * ### Response example
 * 
 * ```json
 * [
 *   {
 *     "channel": "/meta/subscribe",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp",
 *     "subscription": "/alarms/<DEVICE_ID>",
 *     "successful": true
 *   }
 * ]
 * ```
 * ## Unsubscribe
 * 
 * To stop receiving notifications from a channel, send a message to the channel `/meta/unsubscribe` in the same format as used during subscription.
 * 
 * ### Request example
 * 
 * Example Request:
 * 
 * ```http
 * POST /notification/realtime
 * Authorization: <AUTHORIZATION>
 * Content-Type: application/json
 * 
 * [
 *   {
 *     "channel": "/meta/unsubscribe",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp",
 *     "subscription": "/alarms/<DEVICE_ID>"
 *   }
 * ]
 * ```
 * ### Response example
 * 
 * ```json
 * [
 *   {
 *     "channel": "/meta/unsubscribe",
 *     "subscription": "/alarms/<DEVICE_ID>",
 *     "successful": true
 *   }
 * ]
 * ```
 * ## Connect
 * 
 * After a Bayeux client has discovered the server's capabilities with a handshake exchange and subscribed to the desired channels, a connection is established by sending a message to the `/meta/connect` channel. This message may be transported over any of the transports returned by the server in the handshake response. Requests to the connect channel must be immediately repeated after every response to receive the next batch of notifications.
 * 
 * ### Request example
 * 
 * ```http
 * POST /notification/realtime
 * Authorization: <AUTHORIZATION>
 * Content-Type: application/json
 * 
 * [
 *   {
 *     "channel": "/meta/connect",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp",
 *     "connectionType": "long-polling",
 *     "advice": {
 *       "timeout": 5400000,
 *       "interval": 3000
 *     }
 *   }
 * ]
 * ```
 * ### Response example
 * 
 * ```json
 * [
 *   {
 *     "channel": "/meta/connect",
 *     "data": null,
 *     "advice": {
 *       "interval": 3000,
 *       "timeout": 5400000
 *     },
 *     "successful": true
 *   }
 * ]
 * ```
 * ## Disconnect
 * 
 * To stop receiving notifications from all channels and close the conversation, send a message to the `/meta/disconnect` channel.
 * 
 * ### Request example
 * 
 * ```http
 * POST /notification/realtime
 * Authorization: <AUTHORIZATION>
 * Content-Type: application/json
 * 
 * [
 *   {
 *     "channel": "/meta/disconnect",
 *     "clientId": "69wzith4teyensmz6zyk516um4yum0mvp"
 *   }
 * ]
 * ```
 * ### Response example
 * 
 * ```json
 * [
 *   {
 *     "channel": "/meta/disconnect",
 *     "successful": true
 *   }
 * ]
 * ```
 */
interface RealtimeNotificationApi {

	companion object Factory {
		fun create(baseUrl: String): RealtimeNotificationApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): RealtimeNotificationApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(RealtimeNotificationApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Responsive communication
	 * 
	 * The Real-time notification API enables responsive communication from Cumulocity IoT over restricted networks towards clients such as web browser and mobile devices. All clients subscribe to so-called channels to receive messages. These channels are filled by Cumulocity IoT with the output of [Operations](#tag/Operations). In addition, particular system channels are used for the initial handshake with clients, subscription to channels, removal from channels and connection. The [Bayeux protocol](https://docs.cometd.org/current/reference/#_concepts_bayeux_protocol) over HTTPS or WSS is used as communication mechanism.
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The operation was completed and the result is sent in the response.
	 * * HTTP 400 Unprocessable Entity – invalid payload.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@POST("/notification/realtime")
	@ReadOnlyProperties("clientId", "data", "error", "successful")
	fun createRealtimeNotification(
		@Body body: RealtimeNotification, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<RealtimeNotification>
}
