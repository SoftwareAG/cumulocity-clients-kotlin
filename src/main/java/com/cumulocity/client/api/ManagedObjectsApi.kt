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
import com.cumulocity.client.supplementary.SeparatedQueryParameter
import com.cumulocity.client.model.ManagedObject
import com.cumulocity.client.model.ManagedObjectUser
import com.cumulocity.client.model.ManagedObjectCollection
import com.cumulocity.client.model.SupportedMeasurements
import com.cumulocity.client.model.SupportedSeries

/**
 * The inventory stores devices and other assets relevant to your IoT solution. We refer to them as managed objects and such can be “smart objects”, for example, smart electricity meters, home automation gateways or GPS devices.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
interface ManagedObjectsApi {

	companion object Factory {
		fun create(baseUrl: String): ManagedObjectsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): ManagedObjectsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(ManagedObjectsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all managed objects
	 * Retrieve all managed objects (for example, devices, assets, etc.) registered in your tenant, or a subset based on queries.
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the collection of objects is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Invalid data was sent., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param childAdditionId Search for a specific child addition and list all the groups to which it belongs.
	 * @param childAssetId Search for a specific child asset and list all the groups to which it belongs.
	 * @param childDeviceId Search for a specific child device and list all the groups to which it belongs.
	 * @param currentPage The current page of the paginated results.
	 * @param fragmentType A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.
	 * @param ids The managed object IDs to search for. >**&#9432; Info:** If you query for multiple IDs at once, comma-separate the values. 
	 * @param onlyRoots When set to `true` it returns managed objects which don't have any parent. If the current user doesn't have access to the parent, this is also root for the user.
	 * @param owner Username of the owner of the managed objects.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param q Similar to the parameter `query`, but it assumes that this is a device query request and it adds automatically the search criteria `fragmentType=c8y_IsDevice`.
	 * @param query Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in [Query language](#tag/Query-language).
	 * @param skipChildrenNames When set to `true`, the returned references of child devices won't contain their names.
	 * @param text Search for managed objects where any property value is equal to the given one. Only string values are supported.
	 * @param type The type of managed object to search for.
	 * @param withChildren Determines if children with ID and name should be returned when fetching the managed object. Set it to `false` to improve query performance.
	 * @param withChildrenCount When set to `true`, the returned result will contain the total number of children in the respective objects (`childAdditions`, `childAssets` and `childDevices`).
	 * @param withGroups When set to `true` it returns additional information about the groups to which the searched managed object belongs. This results in setting the `assetParents` property with additional information about the groups.
	 * @param withParents When set to `true`, the returned references of child parents will return the device's parents (if any). Otherwise, it will be an empty array.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectcollection+json")
	@GET("/inventory/managedObjects")
	fun getManagedObjects(
		@Query("childAdditionId") childAdditionId: String? = null, 
		@Query("childAssetId") childAssetId: String? = null, 
		@Query("childDeviceId") childDeviceId: String? = null, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("fragmentType") fragmentType: String? = null, 
		@Query("ids") ids: SeparatedQueryParameter<String>? = null, 
		@Query("onlyRoots") onlyRoots: Boolean? = null, 
		@Query("owner") owner: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("q") q: String? = null, 
		@Query("query") query: String? = null, 
		@Query("skipChildrenNames") skipChildrenNames: Boolean? = null, 
		@Query("text") text: String? = null, 
		@Query("type") type: String? = null, 
		@Query("withChildren") withChildren: Boolean? = null, 
		@Query("withChildrenCount") withChildrenCount: Boolean? = null, 
		@Query("withGroups") withGroups: Boolean? = null, 
		@Query("withParents") withParents: Boolean? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<ManagedObjectCollection>
	
	/**
	 * Create a managed object
	 * Create a managed object, for example, a device with temperature measurements support or a binary switch.<br>
	 * In general, each managed object may consist of:
	 * 
	 * *  A unique identifier that references the object.
	 * *  The name of the object.
	 * *  The most specific type of the managed object.
	 * *  A time stamp showing the last update.
	 * *  Fragments with specific meanings, for example, `c8y_IsDevice`, `c8y_SupportedOperations`.
	 * *  Any additional custom fragments.
	 * 
	 * Imagine, for example, that you want to describe electric meters from different vendors. Depending on the make of the meter, one may have a relay and one may be capable to measure a single phase or three phases (for example, a three-phase electricity sensor). A fragment `c8y_ThreePhaseElectricitySensor` would identify such an electric meter. Devices' characteristics are identified by storing fragments for each of them.
	 * 
	 * > **&#9432; Info:** For more details about fragments with specific meanings, review the sections [Device management library](#section/Device-management-library) and [Sensor library](#section/Sensor-library).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> ROLE_INVENTORY_CREATE
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A managed object was created.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobject+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json"]) 
	@POST("/inventory/managedObjects")
	@ReadOnlyProperties("owner", "additionParents", "lastUpdated", "childDevices", "childAssets", "creationTime", "childAdditions", "self", "assetParents", "deviceParents", "id")
	fun createManagedObject(
		@Body body: ManagedObject, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ManagedObject>
	
	/**
	 * Retrieve the total number of managed objects
	 * Retrieve the total number of managed objects (for example, devices, assets, etc.) registered in your tenant, or a subset based on queries.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ is not required, but if the current user doesn't have this role, the response will contain the number of inventory objects accessible for the user.
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the number of managed objects is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param childAdditionId Search for a specific child addition and list all the groups to which it belongs.
	 * @param childAssetId Search for a specific child asset and list all the groups to which it belongs.
	 * @param childDeviceId Search for a specific child device and list all the groups to which it belongs.
	 * @param fragmentType A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.
	 * @param ids The managed object IDs to search for. >**&#9432; Info:** If you query for multiple IDs at once, comma-separate the values. 
	 * @param owner Username of the owner of the managed objects.
	 * @param text Search for managed objects where any property value is equal to the given one. Only string values are supported.
	 * @param type The type of managed object to search for.
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, text/plain,application/json")
	@GET("/inventory/managedObjects/count")
	fun getNumberOfManagedObjects(
		@Query("childAdditionId") childAdditionId: String? = null, 
		@Query("childAssetId") childAssetId: String? = null, 
		@Query("childDeviceId") childDeviceId: String? = null, 
		@Query("fragmentType") fragmentType: String? = null, 
		@Query("ids") ids: SeparatedQueryParameter<String>? = null, 
		@Query("owner") owner: String? = null, 
		@Query("text") text: String? = null, 
		@Query("type") type: String? = null
	): Call<Int>
	
	/**
	 * Retrieve a specific managed object
	 * Retrieve a specific managed object (for example, device, group, template) by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the object is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Managed object not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the managed object.
	 * @param skipChildrenNames When set to `true`, the returned references of child devices won't contain their names.
	 * @param withChildren Determines if children with ID and name should be returned when fetching the managed object. Set it to `false` to improve query performance.
	 * @param withChildrenCount When set to `true`, the returned result will contain the total number of children in the respective objects (`childAdditions`, `childAssets` and `childDevices`).
	 * @param withParents When set to `true`, the returned references of child parents will return the device's parents (if any). Otherwise, it will be an empty array.
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json")
	@GET("/inventory/managedObjects/{id}")
	fun getManagedObject(
		@Path("id") id: String, 
		@Query("skipChildrenNames") skipChildrenNames: Boolean? = null, 
		@Query("withChildren") withChildren: Boolean? = null, 
		@Query("withChildrenCount") withChildrenCount: Boolean? = null, 
		@Query("withParents") withParents: Boolean? = null
	): Call<ManagedObject>
	
	/**
	 * Update a specific managed object
	 * Update a specific managed object (for example, device) by a given ID.
	 * 
	 * For example, if you want to specify that your managed object is a device, you must add the fragment `c8y_IsDevice`.
	 * 
	 * 
	 * The endpoint can also be used as a device availability heartbeat. 
	 * If you only specifiy the `id`, it updates the date when the last message was received and no other property. 
	 * The response then only contains the `id` instead of the full managed object.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - A managed object was updated.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Managed object not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobject+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobject+json"]) 
	@PUT("/inventory/managedObjects/{id}")
	@ReadOnlyProperties("owner", "additionParents", "lastUpdated", "childDevices", "childAssets", "creationTime", "childAdditions", "self", "assetParents", "deviceParents", "id")
	fun updateManagedObject(
		@Body body: ManagedObject, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ManagedObject>
	
	/**
	 * Remove a specific managed object
	 * Remove a specific managed object (for example, device) by a given ID.
	 * 
	 * > **&#9432; Info:** Inventory DELETE requests are not synchronous. The response could be returned before the delete request has been completed. This may happen especially when the deleted managed object has a lot of associated data. After sending the request, the platform starts deleting the associated data in an asynchronous way. Finally, the requested managed object is deleted after all associated data has been deleted.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A managed object was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Managed object not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 409 - Conflict – The managed object is associated to other objects, for example child devices., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param cascade When set to `true` and the managed object is a device or group, all the hierarchy will be deleted.
	 * @param forceCascade When set to `true` all the hierarchy will be deleted without checking the type of managed object. It takes precedence over the parameter `cascade`.
	 * @param withDeviceUser When set to `true` and the managed object is a device, it deletes the associated device user (credentials).
	 */
	@Headers("Accept:application/json")
	@DELETE("/inventory/managedObjects/{id}")
	fun deleteManagedObject(
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null, 
		@Query("cascade") cascade: Boolean? = null, 
		@Query("forceCascade") forceCascade: Boolean? = null, 
		@Query("withDeviceUser") withDeviceUser: Boolean? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve the latest availability date of a specific managed object
	 * Retrieve the date when a specific managed object (by a given ID) sent the last message to Cumulocity IoT.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the date is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Managed object not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, text/plain, application/json")
	@GET("/inventory/managedObjects/{id}/availability")
	fun getLatestAvailability(
		@Path("id") id: String
	): Call<String>
	
	/**
	 * Retrieve all supported measurement fragments of a specific managed object
	 * Retrieve all measurement types of a specific managed object by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and all measurement types are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Managed object not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/inventory/managedObjects/{id}/supportedMeasurements")
	fun getSupportedMeasurements(
		@Path("id") id: String
	): Call<SupportedMeasurements>
	
	/**
	 * Retrieve all supported measurement fragments and series of a specific managed object
	 * Retrieve all supported measurement fragments and series of a specific managed object by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and all supported measurement series are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Managed object not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/inventory/managedObjects/{id}/supportedSeries")
	fun getSupportedSeries(
		@Path("id") id: String
	): Call<SupportedSeries>
	
	/**
	 * Retrieve the username and state of a specific managed object
	 * Retrieve the device owner's username and state (enabled or disabled) of a specific managed object (by a given ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_READ <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_READ permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the username and state are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Managed object not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the managed object.
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.managedobjectuser+json, application/vnd.com.nsn.cumulocity.error+json")
	@GET("/inventory/managedObjects/{id}/user")
	fun getManagedObjectUser(
		@Path("id") id: String
	): Call<ManagedObjectUser>
	
	/**
	 * Update the user's details of a specific managed object
	 * Update the device owner's state (enabled or disabled) of a specific managed object (by a given ID).
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_INVENTORY_ADMIN <b>OR</b> owner of the source <b>OR</b> MANAGE_OBJECT_ADMIN permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The user's details of a specific managed object were updated.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 404 - Managed object not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @param id Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @return
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobjectuser+json", "Accept:application/vnd.com.nsn.cumulocity.managedobjectuser+json, application/vnd.com.nsn.cumulocity.error+json"]) 
	@PUT("/inventory/managedObjects/{id}/user")
	@ReadOnlyProperties("self", "userName")
	fun updateManagedObjectUser(
		@Body body: ManagedObjectUser, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ManagedObjectUser>
}
