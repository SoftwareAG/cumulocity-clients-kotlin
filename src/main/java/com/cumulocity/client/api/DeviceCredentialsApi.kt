// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import com.cumulocity.client.model.DeviceCredentials
import com.cumulocity.client.model.BulkNewDeviceRequest

/**
 * API methods to create device credentials in Cumulocity IoT.
 * 
 * Device credentials can be enquired by devices that do not have credentials for accessing a tenant yet.Since the device does not have credentials yet, a set of fixed credentials is used for this API.The credentials can be obtained by [contacting support](https://cumulocity.com/guides/about-doc/contacting-support/).
 * 
 * > **⚠️ Important:** Do not use your tenant credentials with this API.
 * > **ⓘ Info:** The Accept header should be provided in all POST requests, otherwise an empty response body will be returned.
 */
interface DeviceCredentialsApi {

	companion object Factory {
		fun create(baseUrl: String): DeviceCredentialsApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): DeviceCredentialsApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(DeviceCredentialsApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Create device credentials
	 * 
	 * Create device credentials.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_DEVICE_BOOTSTRAP 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 Device credentials were created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param body
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.devicecredentials+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.devicecredentials+json"]) 
	@POST("/devicecontrol/deviceCredentials")
	@ReadOnlyProperties("password", "tenantId", "self", "username")
	fun createDeviceCredentials(
		@Body body: DeviceCredentials, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<DeviceCredentials>
	
	/**
	 * Create a bulk device credentials request
	 * 
	 * Create a bulk device credentials request.
	 * 
	 * Device credentials and basic device representation can be provided within a CSV file which must be UTF-8 or ANSI encoded. The CSV file must have two sections.
	 * 
	 * The first section is the first line of the CSV file. This line contains column names (headers):
	 * 
	 * |Name|Mandatory|Description||--- |--- |--- ||ID|Yes|The external ID of a device.||CREDENTIALS|Yes*|Password for the device's user. Mandatory, unless AUTH_TYPE is "CERTIFICATES", then CREDENTIALS can be skipped.||AUTH_TYPE|No|Required authentication type for the device's user. If the device uses credentials, this can be skipped or filled with "BASIC". Devices that use certificates must set "CERTIFICATES".||TENANT|No|The ID of the tenant for which the registration is executed (only allowed for the management tenant).||TYPE|No|The type of the device representation.||NAME|No|The name of the device representation.||ICCID|No|The ICCID of the device (SIM card number). If the ICCID appears in file, the import adds a fragment `c8y_Mobile.iccid`. The ICCID value is not mandatory for each row, see the example for an HTTP request below.||IDTYPE|No|The type of the external ID. If IDTYPE doesn't appear in the file, the default value is used. The default value is `c8y_Serial`. The IDTYPE value is not mandatory for each row, see the example for an HTTP request below.||PATH|No|The path in the groups hierarchy where the device is added. PATH contains the name of each group separated by `/`, that is: `main_group/sub_group/.../last_sub_group`. If a group does not exist, the import creates the group.||SHELL|No|If this column contains a value of 1, the import adds the SHELL feature to the device (specifically the `c8y_SupportedOperations` fragment). The SHELL value is not mandatory for each row, see the example for an HTTP request below.|
	 * 
	 * Section two is the rest of the CSV file. Section two contains the device information. The order and quantity of the values must be the same as of the headers.
	 * 
	 * A separator is automatically obtained from the CSV file. Valid separator values are: `\t` (tabulation mark), `;` (semicolon) and `,` (comma).
	 * 
	 * > **⚠️ Important:** The CSV file needs the "com_cumulocity_model_Agent.active" header with a value of "true" to be added to the request.
	 * > **ⓘ Info:** A bulk registration creates an elementary representation of the device. Then, the device needs to update it to a full representation with its own status. The device is ready to use only after it is updated to the full representation. Also see [credentials upload](https://cumulocity.com/guides/users-guide/device-management/#creds-upload) and [device integration](https://cumulocity.com/guides/device-sdk/rest/#device-integration).
	 * A CSV file can appear in many forms (with regard to the optional tenant column and the occurrence of device information):
	 * 
	 * * If a user is logged in as the management tenant, then the columns ID, CREDENTIALS and TENANT are mandatory, and the device credentials will be created for the tenant mentioned in the TENANT column.
	 * * If a user is logged in as a different tenant, for example, as `sample_tenant`, then the columns ID and CREDENTIALS are mandatory (if the file contains the TENANT column, it is ignored and the device credentials will be created for the tenant that is logged in).
	 * * If a user wants to add information about the device, the columns TYPE and NAME must appear in the CSV file.
	 * * If a user wants to add information about a SIM card number, the columns TYPE, NAME and ICCID must appear in the CSV file.
	 * * If a user wants to change the type of external ID, the columns TYPE, NAME and IDTYPE must appear in the CSV file.
	 * * If a user wants to add a device to a group, the columns TYPE, NAME and PATH must appear in the CSV file.
	 * * If a user wants to add the SHELL feature, the columns TYPE, NAME and SHELL must appear in the CSV file and the column SHELL must contain a value of 1.
	 * 
	 * It is possible to define a custom [external ID](#tag/External-IDs) mapping and some custom device properties which are added to newly created devices during registration:
	 * 
	 * * To add a custom external ID mapping, enter the external ID type as the header of the last column with the prefix "external-", for example, to add an external ID mapping of type `c8y_Imei`, enter `external-c8y_Imei` in the last column header. The value of this external ID type should be set in the corresponding column of the data rows.
	 * * To add a custom property to a registered device, enter the custom property name as a header, for example, "myCustomProperty", and the value would be in the rows below.
	 * 
	 * The custom device properties mapping has the following limitations:
	 * 
	 * * Braces '{}' used in data rows will be interpreted as strings of "{}". The system will interpret the value as an object when some custom property is added, for example, put `com_cumulocity_model_Agent.active` into the headers row and `true` into the data row to create an object `"com_cumulocity_model_Agent": {"active": "true"}"`.
	 * * It is not possible to add array values via bulk registration.
	 * 
	 * Example file:
	 * 
	 * ```csv
	 * ID;CREDENTIALS;TYPE;NAME;ICCID;IDTYPE;PATH;SHELL
	 * id_101;AbcD1234!1234AbcD;type_of_device;Device 101;111111111;;csv device/subgroup0;1
	 * id_102;AbcD1234!1234AbcD;type_of_device;Device 102;222222222;;csv device/subgroup0;0
	 * id_111;AbcD1234!1234AbcD;type_of_device;Device 111;333333333;c8y_Imei;csv device1/subgroup1;0
	 * id_112;AbcD1234!1234AbcD;type_of_device;Device 112;444444444;;csv device1/subgroup1;1
	 * id_121;AbcD1234!1234AbcD;type_of_device;Device 121;555555555;;csv device1/subgroup2;1
	 * id_122;AbcD1234!1234AbcD;type_of_device;Device 122;;;csv device1/subgroup2;
	 * id_131;AbcD1234!1234AbcD;type_of_device;Device 131;;;csv device1/subgroup3;
	 * ```
	 * There is also a simple registration method that creates all registration requests at once, then each one needs to go through regular acceptance.This simple registration only makes use of the ID and PATH fields from the list above.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_DEVICE_CONTROL_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 201 A bulk of new device requests was created.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * 
	 * @param file
	 * The CSV file to be uploaded.
	 * @param xCumulocityProcessingMode
	 * Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:multipart/form-data", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.bulknewdevicerequest+json"]) 
	@POST("/devicecontrol/bulkNewDeviceRequests")
	@Multipart
	fun createBulkDeviceCredentials(
		@Part("file") file: UByteArray, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<BulkNewDeviceRequest>
}
