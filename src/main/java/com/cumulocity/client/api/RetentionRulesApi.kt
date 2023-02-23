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
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.RetentionRule
import com.cumulocity.client.model.RetentionRuleCollection

/**
 * It is possible to define rules that make the platform remove certain data. A retention rule shows which data will be deleted. For example, a retention rule with `dataType=EVENT` and `maximumAge=30` removes from the system all events older than 30 days.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
interface RetentionRulesApi {

	companion object Factory {
		fun create(baseUrl: String): RetentionRulesApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): RetentionRulesApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(RetentionRulesApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all retention rules
	 * Retrieve all retention rules on your tenant.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_RETENTION_RULE_READ
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and all retention rules are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 * </ul>
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.retentionrulecollection+json")
	@GET("/retention/retentions")
	fun getRetentionRules(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<RetentionRuleCollection>
	
	/**
	 * Create a retention rule
	 * Create a retention rule on your tenant.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_RETENTION_RULE_ADMIN
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - A retention rule was created.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @return
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.retentionrule+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.retentionrule+json"]) 
	@POST("/retention/retentions")
	@ReadOnlyProperties("self", "id")
	fun createRetentionRule(
		@Body body: RetentionRule
	): Call<RetentionRule>
	
	/**
	 * Retrieve a retention rule
	 * Retrieve a specific retention rule by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_RETENTION_RULE_READ
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the retention rule is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Retention rule not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the retention rule.
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.retentionrule+json")
	@GET("/retention/retentions/{id}")
	fun getRetentionRule(
		@Path("id") id: String
	): Call<RetentionRule>
	
	/**
	 * Update a retention rule
	 * Update a specific retention rule by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_RETENTION_RULE_ADMIN <b>AND</b> (the rule is editable <b>OR</b> it's the tenant management)
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - A retention rule was updated.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Retention rule not found., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 422 - Unprocessable Entity – invalid payload.</li>
	 * </ul>
	 * @param body 
	 * @param id Unique identifier of the retention rule.
	 * @return
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.retentionrule+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.retentionrule+json"]) 
	@PUT("/retention/retentions/{id}")
	@ReadOnlyProperties("self", "id")
	fun updateRetentionRule(
		@Body body: RetentionRule, 
		@Path("id") id: String
	): Call<RetentionRule>
	
	/**
	 * Remove a retention rule
	 * Remove a specific retention rule by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_RETENTION_RULE_ADMIN <b>AND</b> the rule is editable
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 204 - A retention rule was removed.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 *     <li>HTTP 403 - Not authorized to perform this operation.</li>
	 *     <li>HTTP 404 - Retention rule not found., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the retention rule.
	 */
	@Headers("Accept:application/json")
	@DELETE("/retention/retentions/{id}")
	fun deleteRetentionRule(
		@Path("id") id: String
	): Call<ResponseBody>
}
