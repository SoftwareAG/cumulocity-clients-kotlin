// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import com.cumulocity.client.model.AuditRecord
import com.cumulocity.client.model.AuditRecordCollection

/**
 * An audit log stores events that are security-relevant and should be stored for auditing. For example, an audit log should be generated when a user logs into a gateway.
 * 
 * An audit log extends an event through:
 * 
 * * A username of the user that carried out the activity.
 * * An application that was used to carry out the activity.
 * * The actual activity.
 * * A severity.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */
interface AuditsApi {

	companion object Factory {
		fun create(baseUrl: String): AuditsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): AuditsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(AuditsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all audit records
	 * Retrieve all audit records registered on your tenant, or a specific subset based on queries.
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and all audit records are sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param application Name of the application from which the audit was carried out.
	 * @param currentPage The current page of the paginated results.
	 * @param dateFrom Start date or date and time of the audit record.
	 * @param dateTo End date or date and time of the audit record.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param source The platform component ID to which the audit is associated.
	 * @param type The type of audit record to search for.
	 * @param user The username to search for.
	 * @param withTotalElements When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.auditrecordcollection+json")
	@GET("/audit/auditRecords")
	fun getAuditRecords(
		@Query("application") application: String? = null, 
		@Query("currentPage") currentPage: Int? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("source") source: String? = null, 
		@Query("type") type: String? = null, 
		@Query("user") user: String? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<AuditRecordCollection>
	
	/**
	 * Create an audit record
	 * Create an audit record.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_AUDIT_ADMIN <b>OR</b> ROLE_SYSTEM <b>OR</b> AUDIT_ADMIN permission on the resource
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 201 - An audit record was created.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param body 
	 * @return
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.auditrecord+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.auditrecord+json"]) 
	@POST("/audit/auditRecords")
	@ReadOnlyProperties("severity", "application", "creationTime", "c8y_Metadata", "changes", "self", "id", "self")
	fun createAuditRecord(
		@Body body: AuditRecord
	): Call<AuditRecord>
	
	/**
	 * Retrieve a specific audit record
	 * Retrieve a specific audit record by a given ID.
	 * 
	 * <section><h5>Required roles</h5>
	 * ROLE_AUDIT_READ <b>OR</b> AUDIT_READ permission on the source
	 * </section>
	 * 
	 *
	 * The following table gives an overview of the possible response codes and their meanings:
	 * <ul>
	 *     <li>HTTP 200 - The request has succeeded and the audit record is sent in the response.</li>
	 *     <li>HTTP 401 - Authentication information is missing or invalid., @{link com.cumulocity.client.model.Error}</li>
	 * </ul>
	 * @param id Unique identifier of the audit record.
	 * @return
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.auditrecord+json")
	@GET("/audit/auditRecords/{id}")
	fun getAuditRecord(
		@Path("id") id: String
	): Call<AuditRecord>
}
