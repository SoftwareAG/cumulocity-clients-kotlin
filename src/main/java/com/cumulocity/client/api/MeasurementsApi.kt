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
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.supplementary.SeparatedQueryParameter
import com.cumulocity.client.model.Measurement
import com.cumulocity.client.model.MeasurementCollection
import com.cumulocity.client.model.MeasurementSeries

/**
 * Measurements are produced by reading sensor values. In some cases, this data is read in static intervals and sent to the platform (for example, temperature sensors or electrical meters). In other cases, the data is read on demand or at irregular intervals (for example, health devices such as weight scales). Regardless what kind of protocol the device supports, the agent is responsible for converting it into a "push" protocol by uploading data to Cumulocity IoT.
 * 
 * > **ⓘ Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 */
interface MeasurementsApi {

	companion object Factory {
		fun create(baseUrl: String): MeasurementsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): MeasurementsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(MeasurementsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve all measurements
	 * 
	 * Retrieve all measurements on your tenant, or a specific subset based on queries.
	 * 
	 * In case of executing [range queries](https://en.wikipedia.org/wiki/Range_query_(database)) between an upper and lower boundary, for example, querying using `dateFrom`–`dateTo`, the oldest registered measurements are returned first. It is possible to change the order using the query parameter `revert=true`.
	 * 
	 * For large measurement collections, querying older records without filters can be slow as the server needs to scan from the beginning of the input results set before beginning to return the results. For cases when older measurements should be retrieved, it is recommended to narrow the scope by using range queries based on the time stamp reported by a device. The scope of query can also be reduced significantly when a source device is provided.
	 * 
	 * Review [Measurements Specifics](#tag/Measurements-specifics) for details about data streaming and response formats.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_MEASUREMENT_READ 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and all measurements are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param currentPage
	 * The current page of the paginated results.
	 * @param dateFrom
	 * Start date or date and time of the measurement.
	 * @param dateTo
	 * End date or date and time of the measurement.
	 * @param pageSize
	 * Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param revert
	 * If you are using a range query (that is, at least one of the `dateFrom` or `dateTo` parameters is included in the request), then setting `revert=true` will sort the results by the newest measurements first.By default, the results are sorted by the oldest measurements first.
	 * @param source
	 * The managed object ID to which the measurement is associated.
	 * @param type
	 * The type of measurement to search for.
	 * @param valueFragmentSeries
	 * The specific series to search for.
	 * @param valueFragmentType
	 * A characteristic which identifies the measurement.
	 * @param withTotalElements
	 * When set to `true`, the returned result will contain in the statistics object the total number of elements. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 * @param withTotalPages
	 * When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.measurementcollection+json")
	@GET("/measurement/measurements")
	fun getMeasurements(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("revert") revert: Boolean? = null, 
		@Query("source") source: String? = null, 
		@Query("type") type: String? = null, 
		@Query("valueFragmentSeries") valueFragmentSeries: String? = null, 
		@Query("valueFragmentType") valueFragmentType: String? = null, 
		@Query("withTotalElements") withTotalElements: Boolean? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<MeasurementCollection>
	
	/**
	 * Create a measurement
	 * 
	 * A measurement must be associated with a source (managed object) identified by ID, and must specify the type of measurement and the time when it was measured by the device (for example, a thermometer).
	 * 
	 * Each measurement fragment is an object (for example, `c8y_Steam`) containing the actual measurements as properties. The property name represents the name of the measurement (for example, `Temperature`) and it contains two properties:
	 * 
	 * * `value` - The value of the individual measurement. The maximum precision for floating point numbers is 64-bit IEEE 754. For integers it's a 64-bit two's complement integer. The `value` is mandatory for a fragment.
	 * * `unit` - The unit of the measurements.
	 * 
	 * Review the [System of units](#section/System-of-units) section for details about the conversions of units. Also review the [Naming conventions of fragments](https://cumulocity.com/guides/concepts/domain-model/#naming-conventions-of-fragments) in the Concepts guide.
	 * 
	 * The example below uses `c8y_Steam` in the request body to illustrate a fragment for recording temperature measurements.
	 * 
	 * > **⚠️ Important:** Property names used for fragment and series must not contain whitespaces nor the special characters `. , * [ ] ( ) @ $`. This is required to ensure a correct processing and visualization of measurement series on UI graphs.
	 * ### Create multiple measurements
	 * 
	 * It is also possible to create multiple measurements at once by sending a `measurements` array containing all the measurements to be created. The content type must be `application/vnd.com.nsn.cumulocity.measurementcollection+json`.
	 * 
	 * > **ⓘ Info:** For more details about fragments with specific meanings, review the sections [Device management library](#section/Device-management-library) and [Sensor library](#section/Sensor-library).
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_MEASUREMENT_ADMIN *OR* owner of the source *OR* MEASUREMENT_ADMIN permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A measurement was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.measurement+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.measurement+json, application/vnd.com.nsn.cumulocity.measurementcollection+json"]) 
	@POST("/measurement/measurements")
	@ReadOnlyProperties("self", "id", "self")
	fun createMeasurement(
		@Body body: Measurement, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Measurement>
	
	/**
	 * Create a measurement
	 * 
	 * A measurement must be associated with a source (managed object) identified by ID, and must specify the type of measurement and the time when it was measured by the device (for example, a thermometer).
	 * 
	 * Each measurement fragment is an object (for example, `c8y_Steam`) containing the actual measurements as properties. The property name represents the name of the measurement (for example, `Temperature`) and it contains two properties:
	 * 
	 * * `value` - The value of the individual measurement. The maximum precision for floating point numbers is 64-bit IEEE 754. For integers it's a 64-bit two's complement integer. The `value` is mandatory for a fragment.
	 * * `unit` - The unit of the measurements.
	 * 
	 * Review the [System of units](#section/System-of-units) section for details about the conversions of units. Also review the [Naming conventions of fragments](https://cumulocity.com/guides/concepts/domain-model/#naming-conventions-of-fragments) in the Concepts guide.
	 * 
	 * The example below uses `c8y_Steam` in the request body to illustrate a fragment for recording temperature measurements.
	 * 
	 * > **⚠️ Important:** Property names used for fragment and series must not contain whitespaces nor the special characters `. , * [ ] ( ) @ $`. This is required to ensure a correct processing and visualization of measurement series on UI graphs.
	 * ### Create multiple measurements
	 * 
	 * It is also possible to create multiple measurements at once by sending a `measurements` array containing all the measurements to be created. The content type must be `application/vnd.com.nsn.cumulocity.measurementcollection+json`.
	 * 
	 * > **ⓘ Info:** For more details about fragments with specific meanings, review the sections [Device management library](#section/Device-management-library) and [Sensor library](#section/Sensor-library).
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_MEASUREMENT_ADMIN *OR* owner of the source *OR* MEASUREMENT_ADMIN permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A measurement was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.measurementcollection+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.measurement+json, application/vnd.com.nsn.cumulocity.measurementcollection+json"]) 
	@POST("/measurement/measurements")
	@ReadOnlyProperties("next", "prev", "self", "statistics")
	fun createMeasurement(
		@Body body: MeasurementCollection, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<MeasurementCollection>
	
	/**
	 * Remove measurement collections
	 * 
	 * Remove measurement collections specified by query parameters.
	 * 
	 * DELETE requests are not synchronous. The response could be returned before the delete request has been completed. This may happen especially when there are a lot of measurements to be deleted.
	 * 
	 * > **⚠️ Important:** Note that it is possible to call this endpoint without providing any parameter - it may result in deleting all measurements and it is not recommended.
	 * In case of enhanced time series measurements, both `dateFrom` and `dateTo` parameters must be truncated to full hours (for example, 2022-08-19T14:00:00.000Z), otherwise an error will be returned.The `fragmentType` parameter allows to delete measurements only by a measurement fragment when enhanced time series measurements are used.It's not possible to delete by a custom (non-measurement) fragment.
	 * 
	 * Example for a valid measurement value fragment:
	 * 
	 * ```
	 * "c8y_TemperatureMeasurement": {
	 *     "T": {
	 *       "value": 28,
	 *       "unit": "C"
	 *     }
	 * }
	 * ```
	 * In the example above `c8y_TemperatureMeasurement` is called fragment and `T` is called series.
	 * 
	 * Example for a non-measurement fragment:
	 * 
	 * ```
	 * "c8y_TemperatureMeasurement": 28
	 * ```
	 * Enhanced Time series measurements will not allow to delete by fragment specific like above.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_MEASUREMENT_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A collection of measurements was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 * @param dateFrom
	 * Start date or date and time of the measurement.
	 * @param dateTo
	 * End date or date and time of the measurement.
	 * @param fragmentType
	 * A characteristic which identifies a managed object or event, for example, geolocation, electricity sensor, relay state.
	 * @param source
	 * The managed object ID to which the measurement is associated.
	 * @param type
	 * The type of measurement to search for.
	 */
	@Headers("Accept:application/json")
	@DELETE("/measurement/measurements")
	fun deleteMeasurements(
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null, 
		@Query("dateFrom") dateFrom: String? = null, 
		@Query("dateTo") dateTo: String? = null, 
		@Query("fragmentType") fragmentType: String? = null, 
		@Query("source") source: String? = null, 
		@Query("type") type: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve a specific measurement
	 * 
	 * Retrieve a specific measurement by a given ID.Note that you cannot retrieve time series measurements by ID.Instead you can search for such measurements via query parameters.No behavior changes for tenants which do not have time series enabled.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_MEASUREMENT_READ *OR* owner of the source *OR* MEASUREMENT_READ permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the measurement is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 Measurement not found.
	 * 
	 * @param id
	 * Unique identifier of the measurement.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.measurement+json")
	@GET("/measurement/measurements/{id}")
	fun getMeasurement(
		@Path("id") id: String
	): Call<Measurement>
	
	/**
	 * Remove a specific measurement
	 * 
	 * Remove a specific measurement by a given ID.Note that you cannot delete time series measurements by ID.Instead, you can delete by query or use the retention rules to remove expired measurements data from the Operational Store.No behavior changes for tenants which do not have time series enabled.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_MEASUREMENT_ADMIN *OR* owner of the source *OR* MEASUREMENT_ADMIN permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 A measurement was removed.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Not authorized to perform this operation.
	 * * HTTP 404 Measurement not found.
	 * 
	 * @param id
	 * Unique identifier of the measurement.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/json")
	@DELETE("/measurement/measurements/{id}")
	fun deleteMeasurement(
		@Path("id") id: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Retrieve a list of series and their values
	 * 
	 * Retrieve a list of series (all or only those matching the specified names) and their values within a given period of a specific managed object (source).
	 * A series is any fragment in measurement that contains a `value` property.
	 * 
	 * It is possible to fetch aggregated results using the `aggregationType` parameter. If the aggregation is not specified, the result will contain no more than 5000 values.
	 * 
	 * > **⚠️ Important:** For the aggregation to be done correctly, a device shall always use the same time zone when it sends dates.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_MEASUREMENT_READ *OR* owner of the source *OR* MEASUREMENT_READ permission on the source 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the series are sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param aggregationType
	 * Fetch aggregated results as specified.
	 * @param dateFrom
	 * Start date or date and time of the measurement.
	 * @param dateTo
	 * End date or date and time of the measurement.
	 * @param revert
	 * If you are using a range query (that is, at least one of the `dateFrom` or `dateTo` parameters is included in the request), then setting `revert=true` will sort the results by the newest measurements first.By default, the results are sorted by the oldest measurements first.
	 * @param series
	 * The specific series to search for.
	 * 
	 * **ⓘ Info:** If you query for multiple series at once, comma-separate the values.
	 * @param source
	 * The managed object ID to which the measurement is associated.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/measurement/measurements/series")
	fun getMeasurementSeries(
		@Query("aggregationType") aggregationType: String? = null, 
		@Query("dateFrom") dateFrom: String, 
		@Query("dateTo") dateTo: String, 
		@Query("revert") revert: Boolean? = null, 
		@Query("series") series: SeparatedQueryParameter<String>? = null, 
		@Query("source") source: String
	): Call<MeasurementSeries>
}
