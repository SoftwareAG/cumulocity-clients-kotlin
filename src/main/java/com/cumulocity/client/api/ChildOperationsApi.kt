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
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.ChildOperationsAddOne
import com.cumulocity.client.model.ChildOperationsAddMultiple
import com.cumulocity.client.model.ManagedObject
import com.cumulocity.client.model.ManagedObjectReferenceCollection
import com.cumulocity.client.model.ManagedObjectReference

/**
 * Managed objects can contain collections of references to child devices, additions and assets.
 * 
 * > **ⓘ Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 */
interface ChildOperationsApi {

	companion object Factory {
		fun create(baseUrl: String): ChildOperationsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): ChildOperationsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(ChildOperationsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all child additions of a specific managed object
	 * 
	 * Retrieve all child additions of a specific managed object by a given ID, or a subset based on queries.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_READ *OR* ROLE_MANAGED_OBJECT_READ *OR* owner of the source *OR* MANAGE_OBJECT_READ permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and all child additions are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Invalid data was sent.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param query
	 * Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in [Query language](#tag/Query-language).
	 * @param withChildren
	 * Determines if children with ID and name should be returned when fetching the managed object. Set it to `false` to improve query performance.
	 * @param withChildrenCount
	 * When set to `true`, the returned result will contain the total number of children in the respective objects (`childAdditions`, `childAssets` and `childDevices`).
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
	@GET("/inventory/managedObjects/{id}/childAdditions")
	fun getChildAdditions(
		@Path("id") id: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("query") query: String? = null, 
		@Query("withChildren") withChildren: Boolean? = null, 
		@Query("withChildrenCount") withChildrenCount: Boolean? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<ManagedObjectReferenceCollection>
	
	/**
	 * Assign a managed object as child addition
	 * 
	 * The possible ways to assign child objects are:
	 * 
	 * * Assign an existing managed object (by a given child ID) as child addition of another managed object (by a given ID).
	 * * Assign multiple existing managed objects (by given child IDs) as child additions of another managed object (by a given ID).
	 * * Create a managed object in the inventory and assign it as a child addition to another managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* ((owner of the source *OR* MANAGE_OBJECT_ADMIN permission on the source) *AND* (owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the child)) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A managed object was assigned as child addition.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobjectreference+json", "Accept:application/json"]) 
	@POST("/inventory/managedObjects/{id}/childAdditions")
	fun assignAsChildAddition(
		@Body body: ChildOperationsAddOne, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Assign a managed object as child addition
	 * 
	 * The possible ways to assign child objects are:
	 * 
	 * * Assign an existing managed object (by a given child ID) as child addition of another managed object (by a given ID).
	 * * Assign multiple existing managed objects (by given child IDs) as child additions of another managed object (by a given ID).
	 * * Create a managed object in the inventory and assign it as a child addition to another managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* ((owner of the source *OR* MANAGE_OBJECT_ADMIN permission on the source) *AND* (owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the child)) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A managed object was assigned as child addition.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json", "Accept:application/json"]) 
	@POST("/inventory/managedObjects/{id}/childAdditions")
	fun assignAsChildAddition(
		@Body body: ChildOperationsAddMultiple, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Assign a managed object as child addition
	 * 
	 * The possible ways to assign child objects are:
	 * 
	 * * Assign an existing managed object (by a given child ID) as child addition of another managed object (by a given ID).
	 * * Assign multiple existing managed objects (by given child IDs) as child additions of another managed object (by a given ID).
	 * * Create a managed object in the inventory and assign it as a child addition to another managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* ((owner of the source *OR* MANAGE_OBJECT_ADMIN permission on the source) *AND* (owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the child)) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A managed object was assigned as child addition.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobject+json", "Accept:application/json"]) 
	@POST("/inventory/managedObjects/{id}/childAdditions")
	@ReadOnlyProperties("owner", "additionParents", "lastUpdated", "childDevices", "childAssets", "creationTime", "childAdditions", "self", "assetParents", "deviceParents", "id")
	fun assignAsChildAddition(
		@Body body: ManagedObject, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Remove specific child additions from its parent
	 * 
	 * Remove specific child additions (by given child IDs) from its parent (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* owner of the source (parent) *OR* owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the source (parent) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 Child additions were removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json", "Accept:application/json"]) 
	@DELETE("/inventory/managedObjects/{id}/childAdditions")
	fun unassignChildAdditions(
		@Body body: ChildOperationsAddMultiple, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve a specific child addition of a specific managed object
	 * 
	 * Retrieve a specific child addition (by a given child ID) of a specific managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_READ *OR* ROLE_MANAGED_OBJECT_READ *OR* MANAGE_OBJECT_READ permission on the source (parent) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the child addition is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Invalid data was sent.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 * @param childId
	 * Unique identifier of the child object.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.managedobjectreference+json, application/vnd.com.nsn.cumulocity.error+json")
	@GET("/inventory/managedObjects/{id}/childAdditions/{childId}")
	fun getChildAddition(
		@Path("id") id: String, 
		@Path("childId") childId: String
	): Call<ManagedObjectReference>
	
	/**
	 * Remove a specific child addition from its parent
	 * 
	 * Remove a specific child addition (by a given child ID) from its parent (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* owner of the source (parent) *OR* owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the source (parent) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A child addition was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Invalid data was sent.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 * @param childId
	 * Unique identifier of the child object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/json")
	@DELETE("/inventory/managedObjects/{id}/childAdditions/{childId}")
	fun unassignChildAddition(
		@Path("id") id: String, 
		@Path("childId") childId: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve all child assets of a specific managed object
	 * 
	 * Retrieve all child assets of a specific managed object by a given ID, or a subset based on queries.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_READ *OR* ROLE_MANAGED_OBJECT_READ *OR* owner of the source *OR* MANAGE_OBJECT_READ permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and all child assets are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Invalid data was sent.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param query
	 * Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in [Query language](#tag/Query-language).
	 * @param withChildren
	 * Determines if children with ID and name should be returned when fetching the managed object. Set it to `false` to improve query performance.
	 * @param withChildrenCount
	 * When set to `true`, the returned result will contain the total number of children in the respective objects (`childAdditions`, `childAssets` and `childDevices`).
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
	@GET("/inventory/managedObjects/{id}/childAssets")
	fun getChildAssets(
		@Path("id") id: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("query") query: String? = null, 
		@Query("withChildren") withChildren: Boolean? = null, 
		@Query("withChildrenCount") withChildrenCount: Boolean? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<ManagedObjectReferenceCollection>
	
	/**
	 * Assign a managed object as child asset
	 * 
	 * The possible ways to assign child objects are:
	 * 
	 * * Assign an existing managed object (by a given child ID) as child asset of another managed object (by a given ID).
	 * * Assign multiple existing managed objects (by given child IDs) as child assets of another managed object (by a given ID).
	 * * Create a managed object in the inventory and assign it as a child asset to another managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* ((owner of the source *OR* MANAGE_OBJECT_ADMIN permission on the source) *AND* (owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the child)) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A managed object was assigned as child asset.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobjectreference+json", "Accept:application/json"]) 
	@POST("/inventory/managedObjects/{id}/childAssets")
	fun assignAsChildAsset(
		@Body body: ChildOperationsAddOne, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Assign a managed object as child asset
	 * 
	 * The possible ways to assign child objects are:
	 * 
	 * * Assign an existing managed object (by a given child ID) as child asset of another managed object (by a given ID).
	 * * Assign multiple existing managed objects (by given child IDs) as child assets of another managed object (by a given ID).
	 * * Create a managed object in the inventory and assign it as a child asset to another managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* ((owner of the source *OR* MANAGE_OBJECT_ADMIN permission on the source) *AND* (owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the child)) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A managed object was assigned as child asset.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json", "Accept:application/json"]) 
	@POST("/inventory/managedObjects/{id}/childAssets")
	fun assignAsChildAsset(
		@Body body: ChildOperationsAddMultiple, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Assign a managed object as child asset
	 * 
	 * The possible ways to assign child objects are:
	 * 
	 * * Assign an existing managed object (by a given child ID) as child asset of another managed object (by a given ID).
	 * * Assign multiple existing managed objects (by given child IDs) as child assets of another managed object (by a given ID).
	 * * Create a managed object in the inventory and assign it as a child asset to another managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* ((owner of the source *OR* MANAGE_OBJECT_ADMIN permission on the source) *AND* (owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the child)) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A managed object was assigned as child asset.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobject+json", "Accept:application/json"]) 
	@POST("/inventory/managedObjects/{id}/childAssets")
	@ReadOnlyProperties("owner", "additionParents", "lastUpdated", "childDevices", "childAssets", "creationTime", "childAdditions", "self", "assetParents", "deviceParents", "id")
	fun assignAsChildAsset(
		@Body body: ManagedObject, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Remove specific child assets from its parent
	 * 
	 * Remove specific child assets (by given child IDs) from its parent (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* owner of the source (parent) *OR* owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the source (parent) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 Child assets were removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json", "Accept:application/json"]) 
	@DELETE("/inventory/managedObjects/{id}/childAssets")
	fun unassignChildAssets(
		@Body body: ChildOperationsAddMultiple, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve a specific child asset of a specific managed object
	 * 
	 * Retrieve a specific child asset (by a given child ID) of a specific managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_READ *OR* ROLE_MANAGED_OBJECT_READ *OR* MANAGE_OBJECT_READ permission on the source (parent) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the child asset is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Invalid data was sent.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 * @param childId
	 * Unique identifier of the child object.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.managedobjectreference+json, application/vnd.com.nsn.cumulocity.error+json")
	@GET("/inventory/managedObjects/{id}/childAssets/{childId}")
	fun getChildAsset(
		@Path("id") id: String, 
		@Path("childId") childId: String
	): Call<ManagedObjectReference>
	
	/**
	 * Remove a specific child asset from its parent
	 * 
	 * Remove a specific child asset (by a given child ID) from its parent (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* owner of the source (parent) *OR* owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the source (parent) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A child asset was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Invalid data was sent.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 * @param childId
	 * Unique identifier of the child object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/json")
	@DELETE("/inventory/managedObjects/{id}/childAssets/{childId}")
	fun unassignChildAsset(
		@Path("id") id: String, 
		@Path("childId") childId: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve all child devices of a specific managed object
	 * 
	 * Retrieve all child devices of a specific managed object by a given ID, or a subset based on queries.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_READ *OR* ROLE_MANAGED_OBJECT_READ *OR* owner of the source *OR* MANAGE_OBJECT_READ permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and all child devices are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Invalid data was sent.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param query
	 * Use query language to perform operations and/or filter the results. Details about the properties and supported operations can be found in [Query language](#tag/Query-language).
	 * @param withChildren
	 * Determines if children with ID and name should be returned when fetching the managed object. Set it to `false` to improve query performance.
	 * @param withChildrenCount
	 * When set to `true`, the returned result will contain the total number of children in the respective objects (`childAdditions`, `childAssets` and `childDevices`).
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json")
	@GET("/inventory/managedObjects/{id}/childDevices")
	fun getChildDevices(
		@Path("id") id: String, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("query") query: String? = null, 
		@Query("withChildren") withChildren: Boolean? = null, 
		@Query("withChildrenCount") withChildrenCount: Boolean? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<ManagedObjectReferenceCollection>
	
	/**
	 * Assign a managed object as child device
	 * 
	 * The possible ways to assign child objects are:
	 * 
	 * * Assign an existing managed object (by a given child ID) as child device of another managed object (by a given ID).
	 * * Assign multiple existing managed objects (by given child IDs) as child devices of another managed object (by a given ID).
	 * * Create a managed object in the inventory and assign it as a child device to another managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* ((owner of the source *OR* MANAGE_OBJECT_ADMIN permission on the source) *AND* (owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the child)) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A managed object was assigned as child device.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobjectreference+json", "Accept:application/json"]) 
	@POST("/inventory/managedObjects/{id}/childDevices")
	fun assignAsChildDevice(
		@Body body: ChildOperationsAddOne, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Assign a managed object as child device
	 * 
	 * The possible ways to assign child objects are:
	 * 
	 * * Assign an existing managed object (by a given child ID) as child device of another managed object (by a given ID).
	 * * Assign multiple existing managed objects (by given child IDs) as child devices of another managed object (by a given ID).
	 * * Create a managed object in the inventory and assign it as a child device to another managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* ((owner of the source *OR* MANAGE_OBJECT_ADMIN permission on the source) *AND* (owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the child)) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A managed object was assigned as child device.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json", "Accept:application/json"]) 
	@POST("/inventory/managedObjects/{id}/childDevices")
	fun assignAsChildDevice(
		@Body body: ChildOperationsAddMultiple, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Assign a managed object as child device
	 * 
	 * The possible ways to assign child objects are:
	 * 
	 * * Assign an existing managed object (by a given child ID) as child device of another managed object (by a given ID).
	 * * Assign multiple existing managed objects (by given child IDs) as child devices of another managed object (by a given ID).
	 * * Create a managed object in the inventory and assign it as a child device to another managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* ((owner of the source *OR* MANAGE_OBJECT_ADMIN permission on the source) *AND* (owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the child)) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A managed object was assigned as child device.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobject+json", "Accept:application/json"]) 
	@POST("/inventory/managedObjects/{id}/childDevices")
	@ReadOnlyProperties("owner", "additionParents", "lastUpdated", "childDevices", "childAssets", "creationTime", "childAdditions", "self", "assetParents", "deviceParents", "id")
	fun assignAsChildDevice(
		@Body body: ManagedObject, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Remove specific child devices from its parent
	 * 
	 * Remove specific child devices (by given child IDs) from its parent (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* owner of the source (parent) *OR* owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the source (parent) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 Child devices were removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param id
	 * Unique identifier of the managed object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.managedobjectreferencecollection+json", "Accept:application/json"]) 
	@DELETE("/inventory/managedObjects/{id}/childDevices")
	fun unassignChildDevices(
		@Body body: ChildOperationsAddMultiple, 
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve a specific child device of a specific managed object
	 * 
	 * Retrieve a specific child device (by a given child ID) of a specific managed object (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_READ *OR* ROLE_MANAGED_OBJECT_READ *OR* MANAGE_OBJECT_READ permission on the source (parent) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the child device is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Invalid data was sent.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 * @param childId
	 * Unique identifier of the child object.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.managedobjectreference+json, application/vnd.com.nsn.cumulocity.error+json")
	@GET("/inventory/managedObjects/{id}/childDevices/{childId}")
	fun getChildDevice(
		@Path("id") id: String, 
		@Path("childId") childId: String
	): Call<ManagedObjectReference>
	
	/**
	 * Remove a specific child device from its parent
	 * 
	 * Remove a specific child device (by a given child ID) from its parent (by a given ID).
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_INVENTORY_ADMIN *OR* ROLE_MANAGED_OBJECT_ADMIN *OR* owner of the source (parent) *OR* owner of the child *OR* MANAGE_OBJECT_ADMIN permission on the source (parent) 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A child device was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Managed object not found.
	 * * HTTP 422 Invalid data was sent.
	 * 
	 * @param id
	 * Unique identifier of the managed object.
	 * @param childId
	 * Unique identifier of the child object.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/json")
	@DELETE("/inventory/managedObjects/{id}/childDevices/{childId}")
	fun unassignChildDevice(
		@Path("id") id: String, 
		@Path("childId") childId: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
}
