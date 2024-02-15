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
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.BinaryInfo
import com.cumulocity.client.model.EventBinary

/**
 * It is possible to store, retrieve and delete binaries for events. Each event can have only one binary attached.
 */
interface AttachmentsApi {

	companion object Factory {
		fun create(baseUrl: String): AttachmentsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): AttachmentsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(AttachmentsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve the attached file of a specific event
	 * 
	 * Retrieve the attached file (binary) of a specific event by a given ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_EVENT_READ *OR* EVENT_READ permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the file is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Attachment not found.
	 * 
	 * @param id
	 * Unique identifier of the event.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/octet-stream")
	@GET("/event/events/{id}/binaries")
	fun getEventAttachment(
		@Path("id") id: String
	): Call<ResponseBody>
	
	/**
	 * Replace the attached file of a specific event
	 * 
	 * Upload and replace the attached file (binary) of a specific event by a given ID.
	 * The size of the attachment is configurable, and the default size is 50 MiB. The default chunk size is 5MiB.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_EVENT_ADMIN *OR* owner of the source *OR* EVENT_ADMIN permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A file was uploaded.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Event not found.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the event.
	 */
	@Headers(*["Content-Type:text/plain", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@PUT("/event/events/{id}/binaries")
	fun replaceEventAttachment(
		@Body body: UByteArray, 
		@Path("id") id: String
	): Call<EventBinary>
	
	/**
	 * Attach a file to a specific event
	 * 
	 * Upload a file (binary) as an attachment of a specific event by a given ID.The size of the attachment is configurable, and the default size is 50 MiB. The default chunk size is 5MiB.
	 * 
	 * > **ⓘ Info:** If there is a binary already attached to the event, the POST request results in a 409 error.
	 * When the file has been uploaded, the corresponding event contains the fragment `c8y_IsBinary` similar to:
	 * 
	 * ```json
	 * "c8y_IsBinary": {
	 *     "name": "hello.txt",
	 *     "length": 365,
	 *     "type": "text/plain"
	 * }
	 * ```
	 * There are two request body schemas you can use for your POST requests.`text/plain` is preselected (see below).If you set it to `multipart/form-data` each value is sent as a block of data (body part), with a user agent-defined delimiter (`boundary`) separating each part.The keys are given in the `Content-Disposition` header of each part.
	 * 
	 * ```http
	 * POST /event/events/{id}/binaries
	 * Host: https://<TENANT_DOMAIN>
	 * Authorization: <AUTHORIZATION>
	 * Accept: application/json
	 * Content-Type: multipart/form-data;boundary="boundary"
	 * 
	 * --boundary
	 * Content-Disposition: form-data; name="object"
	 * 
	 * { "name": "hello.txt", "type": "text/plain" }
	 * --boundary
	 * Content-Disposition: form-data; name="file"; filename="hello.txt"
	 * Content-Type: text/plain
	 * 
	 * <FILE_CONTENTS>
	 * --boundary--
	 * ```
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_EVENT_ADMIN *OR* owner of the source *OR* EVENT_ADMIN permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A file was uploaded.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Event not found.
	 * * HTTP 409 An attachment exists already.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the event.
	 */
	@Headers(*["Content-Type:text/plain", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@POST("/event/events/{id}/binaries")
	fun uploadEventAttachment(
		@Body body: UByteArray, 
		@Path("id") id: String
	): Call<EventBinary>
	
	/**
	 * Attach a file to a specific event
	 * 
	 * Upload a file (binary) as an attachment of a specific event by a given ID.The size of the attachment is configurable, and the default size is 50 MiB. The default chunk size is 5MiB.
	 * 
	 * > **ⓘ Info:** If there is a binary already attached to the event, the POST request results in a 409 error.
	 * When the file has been uploaded, the corresponding event contains the fragment `c8y_IsBinary` similar to:
	 * 
	 * ```json
	 * "c8y_IsBinary": {
	 *     "name": "hello.txt",
	 *     "length": 365,
	 *     "type": "text/plain"
	 * }
	 * ```
	 * There are two request body schemas you can use for your POST requests.`text/plain` is preselected (see below).If you set it to `multipart/form-data` each value is sent as a block of data (body part), with a user agent-defined delimiter (`boundary`) separating each part.The keys are given in the `Content-Disposition` header of each part.
	 * 
	 * ```http
	 * POST /event/events/{id}/binaries
	 * Host: https://<TENANT_DOMAIN>
	 * Authorization: <AUTHORIZATION>
	 * Accept: application/json
	 * Content-Type: multipart/form-data;boundary="boundary"
	 * 
	 * --boundary
	 * Content-Disposition: form-data; name="object"
	 * 
	 * { "name": "hello.txt", "type": "text/plain" }
	 * --boundary
	 * Content-Disposition: form-data; name="file"; filename="hello.txt"
	 * Content-Type: text/plain
	 * 
	 * <FILE_CONTENTS>
	 * --boundary--
	 * ```
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_EVENT_ADMIN *OR* owner of the source *OR* EVENT_ADMIN permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A file was uploaded.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Event not found.
	 * * HTTP 409 An attachment exists already.
	 * 
	 * @param pObject
	 * @param file
	 * Path of the file to be uploaded.
	 * @param id
	 * Unique identifier of the event.
	 */
	@Headers(*["Content-Type:multipart/form-data", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/json"]) 
	@POST("/event/events/{id}/binaries")
	@Multipart
	fun uploadEventAttachment(
		@Part("object") pObject: BinaryInfo, 
		@Part("file") file: UByteArray, 
		@Path("id") id: String
	): Call<EventBinary>
	
	/**
	 * Remove the attached file from a specific event
	 * 
	 * Remove the attached file (binary) from a specific event by a given ID.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_EVENT_ADMIN *OR* owner of the source *OR* EVENT_ADMIN permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A file was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Event not found.
	 * 
	 * @param id
	 * Unique identifier of the event.
	 */
	@Headers("Accept:application/json")
	@DELETE("/event/events/{id}/binaries")
	fun deleteEventAttachment(
		@Path("id") id: String
	): Call<ResponseBody>
}
