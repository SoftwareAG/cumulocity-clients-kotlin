// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.api
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.gson.ExtendedGsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import okhttp3.OkHttpClient
import retrofit2.converter.gson.ReadOnlyProperties
import okhttp3.ResponseBody
import com.cumulocity.client.model.CurrentUser
import com.cumulocity.client.model.PasswordChange
import com.cumulocity.client.model.CurrentUserTotpSecretActivity
import com.cumulocity.client.model.CurrentUserTotpCode
import com.cumulocity.client.model.CurrentUserTotpSecret

/**
 * The current user is the user that is currently authenticated with Cumulocity IoT for the API calls.
 * 
 * > **&#9432; Info:** The Accept header should be provided in all PUT requests, otherwise an empty response body will be returned.
 *  </br>
 * 
 */ 
interface CurrentUserApi {

	companion object Factory {
		fun create(baseUrl: String): CurrentUserApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): CurrentUserApi {
			val retrofitBuilder = Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(CurrentUserApi::class.java)
		}
	}

	/**
	 * Retrieve the current user </br>
	 * Retrieve the user reference of the current user.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the current user is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currentuser+json")
	@GET("/user/currentUser")
	fun getCurrentUser(
	): Call<CurrentUser>
	
	/**
	 * Update the current user </br>
	 * Update the current user.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The current user was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity ??? invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.currentuser+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currentuser+json"]) 
	@PUT("/user/currentUser")
	@ReadOnlyProperties("self", "effectiveRoles", "shouldResetPassword", "id", "lastPasswordChange", "twoFactorAuthenticationEnabled", "devicePermissions")
	fun updateCurrentUser(
		@Body body: CurrentUser, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<CurrentUser>
	
	/**
	 * Update the current user's password </br>
	 * Update the current user's  password.  > **?????? Important:** If the tenant uses OAI-Secure authentication, the current user will not be logged out. Instead, a new cookie will be set with a new token, and the previous token will expire within a minute.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_ADMIN </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The current user password was updated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>422 Unprocessable Entity ??? invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/json"]) 
	@PUT("/user/currentUser/password")
	fun updateCurrentUserPassword(
		@Body body: PasswordChange, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Generate secret to set up TFA </br>
	 * Generate a secret code to create a QR code to set up the two-factor authentication functionality using a TFA app/service.  For more information about the feature, see [User Guide > Administration > Two-factor authentication](https://cumulocity.com/guides/users-guide/administration/#tfa) in the *Cumulocity IoT documentation*.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 The request has succeeded and the secret is sent in the response.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * </ul>
	 *
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@POST("/user/currentUser/totpSecret")
	fun generateTfaSecret(
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<CurrentUserTotpSecret>
	
	/**
	 * Returns the activation state of the two-factor authentication feature. </br>
	 * Returns the activation state of the two-factor authentication feature for the current user.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>200 Returns the activation state.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 *
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/user/currentUser/totpSecret/activity")
	fun getTfaState(
	): Call<CurrentUserTotpSecretActivity>
	
	/**
	 * Activates or deactivates the two-factor authentication feature </br>
	 * Activates or deactivates the two-factor authentication feature for the current user.  For more information about the feature, see [User Guide > Administration > Two-factor authentication](https://cumulocity.com/guides/users-guide/administration/#tfa) in the *Cumulocity IoT documentation*.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 The two-factor authentication was activated or deactivated.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Cannot deactivate TOTP setup.</li>
	 * <li>404 User not found.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/json"]) 
	@POST("/user/currentUser/totpSecret/activity")
	fun setTfaState(
		@Body body: CurrentUserTotpSecretActivity, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
	
	/**
	 * Verify TFA code </br>
	 * Verifies the authentication code that the current user received from a TFA app/service and uploaded to the platform to gain access or enable the two-factor authentication feature.  <section><h5>Required roles</h5> ROLE_USER_MANAGEMENT_OWN_READ <b>OR</b> ROLE_SYSTEM </section> 
	 *
	 * <br>The following table gives an overview of the possible response codes and their meanings:</br>
	 * <ul>
	 * <li>204 The sent code was correct and the access can be granted.</li>
	 * <li>401 Authentication information is missing or invalid.</li>
	 * <li>403 Invalid verification code.</li>
	 * <li>404 Cannot validate TFA TOTP code - user's TFA TOTP secret does not exist.</li>
	 * <li>422 Unprocessable Entity ??? invalid payload.</li>
	 * </ul>
	 *
	 * @param body 
	 * @param xCumulocityProcessingMode Used to explicitly control the processing mode of the request. See [Processing mode](#processing-mode) for more details.
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/json"]) 
	@POST("/user/currentUser/totpSecret/verify")
	fun verifyTfaCode(
		@Body body: CurrentUserTotpCode, 
		@Header("X-Cumulocity-Processing-Mode") xCumulocityProcessingMode: String? = null
	): Call<ResponseBody>
}
