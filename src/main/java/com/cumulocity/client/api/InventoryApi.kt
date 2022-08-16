// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api

import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import com.cumulocity.client.model.InventoryApiResource

/**
 * The inventory stores all master data related to devices, their configuration and their connections. It also contains all related assets (for example, vehicles, machines, buildings) and their structure. The inventory API resource returns URIs and URI templates to collections of managed objects. </br>
 * 
 */ 
interface InventoryApi {

	companion object Factory {
		fun create(baseUrl: String): InventoryApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): InventoryApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(InventoryApi::class.java)
		}
	}

	/**
	 * Retrieve URIs to collections of managed objects </br>
	 * Retrieve URIs and URI templates to collections of managed objects.  <section><h5>Required roles</h5> ROLE_INVENTORY_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the URIs are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Not authorized to perform this operation.</li>
	 * </ul>
	 *
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.inventoryapi+json")
	@GET("/inventory")
	fun getInventoryApiResource(
	): Call<InventoryApiResource>
}
