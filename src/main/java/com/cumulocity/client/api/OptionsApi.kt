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
import com.cumulocity.client.model.Option
import com.cumulocity.client.model.CategoryOptions
import com.cumulocity.client.model.CategoryKeyOption
import com.cumulocity.client.model.OptionCollection

/**
 * API methods to retrieve the options configured in the tenant.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all POST/PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
interface OptionsApi {

	companion object Factory {
		fun create(baseUrl: String): OptionsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): OptionsApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(OptionsApi::class.java)
		}
	}

	/**
	 * Retrieve all options </br>
	 * Retrieve all the options available on the tenant.  <section><h5>Required roles</h5> ROLE_OPTION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the options are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param currentPage The current page of the paginated results.
	 * @param pageSize Indicates how many entries of the collection shall be returned. The upper limit for one page is 2,000 objects.
	 * @param withTotalPages When set to `true`, the returned result will contain in the statistics object the total number of pages. Only applicable on [range queries](https://en.wikipedia.org/wiki/Range_query_(database)).
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.optioncollection+json")
	@GET("/tenant/options")
	fun getOptions(
		@Query("currentPage") currentPage: Int? = null, 
		@Query("pageSize") pageSize: Int? = null, 
		@Query("withTotalPages") withTotalPages: Boolean? = null
	): Call<OptionCollection>
	
	/**
	 * Create an option </br>
	 * Create an option on your tenant.  Options are category-key-value tuples which store tenant configurations. Some categories of options allow the creation of new ones, while others are limited to predefined set of keys.  Any option of any tenant can be defined as "non-editable" by the "management" tenant; once done, any PUT or DELETE requests made on that option by the tenant owner will result in a 403 error (Unauthorized).  ### Default option categories  **access.control**  | Key |	Default value |	Predefined | Description | |--|--|--|--| | allow.origin | * | Yes | Comma separated list of domains allowed for execution of CORS. Wildcards are allowed (for example, `*.cumuclocity.com`) |  **alarm.type.mapping**  | Key  |	Predefined | Description | |--|--|--| | &lt;ALARM_TYPE> | No | Overrides the severity and alarm text for the alarm with type &lt;ALARM_TYPE>. The severity and text are specified as `<ALARM_SEVERITY>\|<ALARM_TEXT>`. If either part is empty, the value will not be overridden. If the severity is NONE, the alarm will be suppressed. Example: `"CRITICAL\|temperature too high"`|  ### Encrypted credentials  Adding a "credentials." prefix to the `key` will make the `value` of the option encrypted. When the option is  sent to a microservice, the "credentials." prefix is removed and the `value` is decrypted. For example:  ```json {   "category": "secrets",   "key": "credentials.mykey",   "value": "myvalue" } ```  In that particular example, the request will contain an additional header `"Mykey": "myvalue"`.  <section><h5>Required roles</h5> ROLE_OPTION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An option was created.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity ??? invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.option+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json"]) 
	@POST("/tenant/options")
	@ReadOnlyProperties("self")
	fun createOption(
		@Body body: Option, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Option>
	
	/**
	 * Retrieve all options by category </br>
	 * Retrieve all the options (by a specified category) on your tenant.  <section><h5>Required roles</h5> ROLE_OPTION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the options are sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param category The category of the options.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
	@GET("/tenant/options/{category}")
	fun getOptionsByCategory(
		@Path("category") category: String
	): Call<CategoryOptions>
	
	/**
	 * Update options by category </br>
	 * Update one or more options (by a specified category) on your tenant.  <section><h5>Required roles</h5> ROLE_OPTION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 A collection of options was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity ??? invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param category The category of the options.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json"]) 
	@PUT("/tenant/options/{category}")
	fun updateOptionsByCategory(
		@Body body: CategoryOptions, 
		@Path("category") category: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<CategoryOptions>
	
	/**
	 * Retrieve a specific option </br>
	 * Retrieve a specific option (by a given category and key) on your tenant.  <section><h5>Required roles</h5> ROLE_OPTION_MANAGEMENT_READ </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the option is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Option not found.</li>
	 * </ul>
	 *
	 * @param category The category of the options.
	 * @param key The key of an option.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json")
	@GET("/tenant/options/{category}/{key}")
	fun getOption(
		@Path("category") category: String, 
		@Path("key") key: String
	): Call<Option>
	
	/**
	 * Update a specific option </br>
	 * Update the value of a specific option (by a given category and key) on your tenant.  <section><h5>Required roles</h5> ROLE_OPTION_MANAGEMENT_ADMIN <b>AND</b> the option is editable </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 An option was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Option not found.</li>
	 * <li>422 Unprocessable Entity ??? invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param category The category of the options.
	 * @param key The key of an option.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.option+json"]) 
	@PUT("/tenant/options/{category}/{key}")
	fun updateOption(
		@Body body: CategoryKeyOption, 
		@Path("category") category: String, 
		@Path("key") key: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<Option>
	
	/**
	 * Remove a specific option </br>
	 * Remove a specific option (by a given category and key) on your tenant.  <section><h5>Required roles</h5> ROLE_OPTION_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 An option was removed.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 Option not found.</li>
	 * </ul>
	 *
	 * @param category The category of the options.
	 * @param key The key of an option.
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/json")
	@DELETE("/tenant/options/{category}/{key}")
	fun deleteOption(
		@Path("category") category: String, 
		@Path("key") key: String, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
}
