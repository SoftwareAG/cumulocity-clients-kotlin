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
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.ApplicationVersionTag
import com.cumulocity.client.model.ApplicationVersion
import com.cumulocity.client.model.ApplicationVersionCollection

/**
 * API methods to retrieve, create, update and delete application versions. </br>
 * 
 */ 
interface ApplicationVersionsApi {

	companion object Factory {
		fun create(baseUrl: String): ApplicationVersionsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): ApplicationVersionsApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(ApplicationVersionsApi::class.java)
		}
	}

	/**
	 * Retrieve a specific version of an application </br>
	 * Retrieve the selected version of an application in your tenant. To select the version, use only the version or only the tag query parameter. <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section>
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the application version is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application not found.</li>
	 * <li>422 both parameters (version and tag) are present.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the application.
	 * @param version The version field of the application version.
	 * @param tag The tag of the application version.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersion+json")
	@GET("/application/applications/{id}/versions?version=1.0")
	fun getApplicationVersion(
		@Path("id") id: String, 
		@Query("version") version: String? = null, 
		@Query("tag") tag: String? = null
	): Call<ApplicationVersion>
	
	/**
	 * Retrieve all versions of an application </br>
	 * Retrieve all versions of an application in your tenant.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the list of application versions is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application version not found.</li>
	 * <li>422 This application doesn't support versioning.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the application.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersionCollection+json")
	@GET("/application/applications/{id}/versions")
	fun getApplicationVersions(
		@Path("id") id: String
	): Call<ApplicationVersionCollection>
	
	/**
	 * Create an application version </br>
	 * Create an application version in your tenant.  Uploaded version and tags can only contain upper and lower case letters, integers and `.`,` + `,` -`. Other characters are prohibited.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An application version was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application version not found.</li>
	 * <li>409 Duplicate version/tag or versions limit exceeded.</li>
	 * <li>422 tag or version contains unacceptable characters.</li>
	 * </ul>
	 *
	 * @param applicationBinary The ZIP file to be uploaded.
	 * @param applicationVersion The JSON file with version information.
	 * @param id Unique identifier of the application.
	 */
	@Headers(*["Content-Type:multipart/form-data", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersion+json"]) 
	@POST("/application/applications/{id}/versions")
	@Multipart
	fun createApplicationVersion(
		@Part("applicationBinary") applicationBinary: UByteArray, 
		@Part("applicationVersion") applicationVersion: String, 
		@Path("id") id: String
	): Call<ApplicationVersion>
	
	/**
	 * Delete a specific version of an application </br>
	 * Delete a specific version of an application in your tenant, by a given tag or version.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 A version was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application version not found.</li>
	 * <li>409 Version with tag latest cannot be removed.</li>
	 * <li>422 both parameters (version and tag) are present.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the application.
	 * @param version The version field of the application version.
	 * @param tag The tag of the application version.
	 */
	@Headers("Accept:application/json")
	@DELETE("/application/applications/{id}/versions")
	fun deleteApplicationVersion(
		@Path("id") id: String, 
		@Query("version") version: String? = null, 
		@Query("tag") tag: String? = null
	): Call<ResponseBody>
	
	/**
	 * Replace an application version's tags </br>
	 * Replaces the tags of a given application version in your tenant.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An application version was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application version not found.</li>
	 * <li>409 Duplicate version/tag or versions limit exceeded.</li>
	 * <li>422 tag contains unacceptable characters.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param id Unique identifier of the application.
	 * @param version Version of the application.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationVersion+json"]) 
	@PUT("/application/applications/{id}/versions/{version}")
	fun updateApplicationVersion(
		@Body body: ApplicationVersionTag, 
		@Path("id") id: String, 
		@Path("version") version: String
	): Call<ApplicationVersion>
}
