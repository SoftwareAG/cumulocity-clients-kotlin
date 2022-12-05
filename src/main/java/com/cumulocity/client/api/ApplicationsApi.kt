// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.Application
import com.cumulocity.client.model.ApplicationCollection

/**
 * API methods to retrieve, create, update and delete applications.
 * 
 * ### Application names
 * 
 * For each tenant, Cumulocity IoT manages the subscribed applications and provides a number of applications of various types.
 * In case you want to subscribe a tenant to an application using an API, you must use the application name in the argument (as name).
 * 
 * Refer to the tables in [Administration > Managing applications](https://cumulocity.com/guides/10.7.0/users-guide/administration#managing-applications) in the User guide for the respective application name to be used.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
interface ApplicationsApi {

	companion object Factory {
		fun create(baseUrl: String): ApplicationsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): ApplicationsApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(ApplicationsApi::class.java)
		}
	}

	/**
	 * Retrieve all applications </br>
	 * Retrieve all applications on your tenant.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the list of applications is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param currentPage The current page of the paginated results.
	 * @param name The name of the application.
	 * @param owner The ID of the tenant that owns the applications.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param providedFor The ID of a tenant that is subscribed to the applications but doesn't own them.
	 * @param subscriber The ID of a tenant that is subscribed to the applications.
	 * @param tenant The ID of a tenant that either owns the application or is subscribed to the applications.
	 * @param type The type of the application.
	 * @param user The ID of a user that has access to the applications.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
	@GET("/application/applications")
	fun getApplications(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("name") name: String? = null, 
		@Query("owner") owner: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("providedFor") providedFor: String? = null, 
		@Query("subscriber") subscriber: String? = null, 
		@Query("tenant") tenant: String? = null, 
		@Query("type") type: String? = null, 
		@Query("user") user: String? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<ApplicationCollection>
	
	/**
	 * Create an application </br>
	 * Create an application on your tenant.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An application was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>409 Duplicate key/name.</li>
	 * <li>422 Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.application+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json"]) 
	@POST("/application/applications")
	@ReadOnlyProperties("owner", "activeVersionId", "self", "id", "resourcesUrl")
	fun createApplication(
		@Body body: Application, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Application>
	
	/**
	 * Retrieve a specific application </br>
	 * Retrieve a specific application (by a given ID).  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ <b>OR</b> current user has explicit access to the application </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the application is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application not found.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the application.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
	@GET("/application/applications/{id}")
	fun getApplication(
		@Path("id") id: String
	): Call<Application>
	
	/**
	 * Update a specific application </br>
	 * Update a specific application (by a given ID).  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An application was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Application not found.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param id Unique identifier of the application.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.application+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json"]) 
	@PUT("/application/applications/{id}")
	@ReadOnlyProperties("owner", "activeVersionId", "self", "id", "type", "resourcesUrl")
	fun updateApplication(
		@Body body: Application, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Application>
	
	/**
	 * Delete an application </br>
	 * Delete an application (by a given ID). This method is not supported by microservice applications.  > **&#9432; Info:** With regards to a hosted application, there is a caching mechanism in place that keeps the information about the placement of application files (html, javascript, css, fonts, etc.). Removing a hosted application, in normal circumstances, will cause the subsequent requests for application files to fail with an HTTP 404 error because the application is removed synchronously, its files are immediately removed on the node serving the request and at the same time the information is propagated to other nodes – but in rare cases there might be a delay with this propagation. In such situations, the files of the removed application can be served from those nodes up until the aforementioned cache expires. For the same reason, the cache can also cause HTTP 404 errors when the application is updated as it will keep the path to the files of the old version of the application. The cache is filled on demand, so there should not be issues if application files were not accessed prior to the delete request. The expiration delay of the cache can differ, but should not take more than one minute.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN <b>AND</b> tenant is the owner of the application </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 An application was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * <li>404 Application not found.</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the application.
	 * @param force Force deletion by unsubscribing all tenants from the application first and then deleting the application itself.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/json")
	@DELETE("/application/applications/{id}")
	fun deleteApplication(
		@Path("id") id: String, 
		@Query("force") force: Boolean? = null, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Copy an application </br>
	 * Copy an application (by a given ID).  This method is not supported by microservice applications.  A request to the "clone" resource creates a new application based on an already existing one.  The properties are copied to the newly created application and the prefix "clone" is added to the properties `name`, `key` and `contextPath` in order to be unique.  If the target application is hosted and has an active version, the new application will have the active version with the same content. <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>201 An application was copied.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity – method not supported</li>
	 * </ul>
	 *
	 * @param id Unique identifier of the application.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.application+json")
	@POST("/application/applications/{id}/clone")
	fun copyApplication(
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Application>
	
	/**
	 * Retrieve applications by name </br>
	 * Retrieve applications by name.  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the applications are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param name The name of the application.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
	@GET("/application/applicationsByName/{name}")
	fun getApplicationsByName(
		@Path("name") name: String
	): Call<ApplicationCollection>
	
	/**
	 * Retrieve applications by tenant </br>
	 * Retrieve applications subscribed or owned by a particular tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the applications are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
	@GET("/application/applicationsByTenant/{tenantId}")
	fun getApplicationsByTenant(
		@Path("tenantId") tenantId: String
	): Call<ApplicationCollection>
	
	/**
	 * Retrieve applications by owner </br>
	 * Retrieve all applications owned by a particular tenant (by a given tenant ID).  <section><h5>Required roles</h5> ROLE_APPLICATION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the applications are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param tenantId Unique identifier of a Cumulocity IoT tenant.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
	@GET("/application/applicationsByOwner/{tenantId}")
	fun getApplicationsByOwner(
		@Path("tenantId") tenantId: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<ApplicationCollection>
	
	/**
	 * Retrieve applications by user </br>
	 * Retrieve all applications for a particular user (by a given username).  <section><h5>Required roles</h5> (ROLE_USER_MANAGEMENT_OWN_READ <b>AND</b> is the current user) <b>OR</b> (ROLE_USER_MANAGEMENT_READ <b>AND</b> ROLE_APPLICATION_MANAGEMENT_READ) </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the applications are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param username The username of the a user.
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.applicationcollection+json")
	@GET("/application/applicationsByUser/{username}")
	fun getApplicationsByUser(
		@Path("username") username: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<ApplicationCollection>
}
