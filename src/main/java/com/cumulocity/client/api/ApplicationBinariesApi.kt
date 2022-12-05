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
import retrofit2.http.Path
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import com.cumulocity.client.model.ApplicationBinaries
import com.cumulocity.client.model.Application

/**
 * An API method to upload an application binary. It is a deployable microservice or web application. </br>
 * 
 */ 
interface ApplicationBinariesApi {

	companion object Factory {
		fun create(baseUrl: String): ApplicationBinariesApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): ApplicationBinariesApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(ApplicationBinariesApi::class.java)
		}
	}

	/**
	 * Retrieve all application attachments </br>
	 * Retrieve all application attachments. This method is not supported by microservice applications.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the application attachments are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application not found.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the application.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.applicationbinaries+json, application/vnd.com.nsn.cumulocity.error+json")
	@GET("/application/applications/{id}/binaries")
	fun getApplicationAttachments(
		@Path("id") id: String
	): Call<ApplicationBinaries>
	
	/**
	 * Upload an application attachment </br>
	 * Upload an application attachment (by a given application ID).  For the applications of type “microservice” and “web application” to be available for Cumulocity IoT platform users, an attachment ZIP file must be uploaded.  For a microservice application, the ZIP file must consist of:  * cumulocity.json - file describing the deployment * image.tar - executable Docker image  For a web application, the ZIP file must include an index.html file in the root directory.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 The application attachments have been uploaded.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param file The ZIP file to be uploaded.
	 * @param id Unique identifier of the application.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:multipart/form-data", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json"]) 
	@POST("/application/applications/{id}/binaries")
	@Multipart
	fun uploadApplicationAttachment(
		@Part("file") file: UByteArray, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Application>
	
	/**
	 * Retrieve a specific application attachment </br>
	 * Retrieve a specific application attachment (by a given application ID and a given binary ID). This method is not supported by microservice applications.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the application attachment is sent as a ZIP file in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the application.
	 * @param binaryId Unique identifier of the binary.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/zip")
	@GET("/application/applications/{id}/binaries/{binaryId}")
	fun getApplicationAttachment(
		@Path("id") id: String, 
		@Path("binaryId") binaryId: String
	): Call<ResponseBody>
	
	/**
	 * Delete a specific application attachment </br>
	 * Delete  a specific application attachment (by a given application ID and a given binary ID). This method is not supported by microservice applications.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 An application binary was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the application.
	 * @param binaryId Unique identifier of the binary.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/json")
	@DELETE("/application/applications/{id}/binaries/{binaryId}")
	fun deleteApplicationAttachment(
		@Path("id") id: String, 
		@Path("binaryId") binaryId: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
}
