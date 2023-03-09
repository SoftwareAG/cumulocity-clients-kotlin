// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
 * > **ⓘ Info:** The Accept header should be provided in all PUT requests, otherwise an empty response body will be returned.
 */
interface CurrentUserApi {

	companion object Factory {
		fun create(baseUrl: String): CurrentUserApi {
			return create(baseUrl, null)
		}

		fun create(baseUrl: String, clientBuilder: OkHttpClient.Builder?): CurrentUserApi {
			val retrofitBuilder = retrofit().baseUrl(baseUrl)
			if (clientBuilder != null) {
				retrofitBuilder.client(clientBuilder.build())
			}
			return retrofitBuilder.build().create(CurrentUserApi::class.java)
		}

		fun retrofit(): Retrofit.Builder{
			return Retrofit.Builder()
				.addConverterFactory(ExtendedGsonConverterFactory())
				.addConverterFactory(ScalarsConverterFactory.create())
		}
	}

	/**
	 * Retrieve the current user
	 * 
	 * Retrieve the user reference of the current user.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_OWN_READ *OR* ROLE_SYSTEM 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the current user is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currentuser+json")
	@GET("/user/currentUser")
	fun getCurrentUser(
	): Call<CurrentUser>
	
	/**
	 * Update the current user
	 * 
	 * Update the current user.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_OWN_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The current user was updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 */
	@Headers(*["Content-Type:application/vnd.com.nsn.cumulocity.currentuser+json", "Accept:application/vnd.com.nsn.cumulocity.error+json, application/vnd.com.nsn.cumulocity.currentuser+json"]) 
	@PUT("/user/currentUser")
	@ReadOnlyProperties("self", "effectiveRoles", "shouldResetPassword", "id", "lastPasswordChange", "twoFactorAuthenticationEnabled", "devicePermissions")
	fun updateCurrentUser(
		@Body body: CurrentUser
	): Call<CurrentUser>
	
	/**
	 * Update the current user's password
	 * 
	 * Update the current user's  password.
	 * 
	 * > **⚠️ Important:** If the tenant uses OAI-Secure authentication, the current user will not be logged out. Instead, a new cookie will be set with a new token, and the previous token will expire within a minute.
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_OWN_ADMIN 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The current user password was updated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/json"]) 
	@PUT("/user/currentUser/password")
	fun updateCurrentUserPassword(
		@Body body: PasswordChange
	): Call<ResponseBody>
	
	/**
	 * Generate secret to set up TFA
	 * 
	 * Generate a secret code to create a QR code to set up the two-factor authentication functionality using a TFA app/service.
	 * 
	 * For more information about the feature, see [User Guide > Administration > Two-factor authentication](https://cumulocity.com/guides/users-guide/administration/#tfa) in the *Cumulocity IoT documentation*.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_OWN_READ *OR* ROLE_SYSTEM 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 The request has succeeded and the secret is sent in the response.
	 * * HTTP 401 Authentication information is missing or invalid.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@POST("/user/currentUser/totpSecret")
	fun generateTfaSecret(
	): Call<CurrentUserTotpSecret>
	
	/**
	 * Returns the activation state of the two-factor authentication feature.
	 * 
	 * Returns the activation state of the two-factor authentication feature for the current user.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_OWN_READ *OR* ROLE_SYSTEM 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 200 Returns the activation state.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 404 User not found.
	 */
	@Headers("Accept:application/vnd.com.nsn.cumulocity.error+json, application/json")
	@GET("/user/currentUser/totpSecret/activity")
	fun getTfaState(
	): Call<CurrentUserTotpSecretActivity>
	
	/**
	 * Activates or deactivates the two-factor authentication feature
	 * 
	 * Activates or deactivates the two-factor authentication feature for the current user.
	 * 
	 * For more information about the feature, see [User Guide > Administration > Two-factor authentication](https://cumulocity.com/guides/users-guide/administration/#tfa) in the *Cumulocity IoT documentation*.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_OWN_READ *OR* ROLE_SYSTEM 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 The two-factor authentication was activated or deactivated.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Cannot deactivate TOTP setup.
	 * * HTTP 404 User not found.
	 * 
	 * @param body
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/json"]) 
	@POST("/user/currentUser/totpSecret/activity")
	fun setTfaState(
		@Body body: CurrentUserTotpSecretActivity
	): Call<ResponseBody>
	
	/**
	 * Verify TFA code
	 * 
	 * Verifies the authentication code that the current user received from a TFA app/service and uploaded to the platform to gain access or enable the two-factor authentication feature.
	 * 
	 * 
	 * ##### Required roles
	 * 
	 *  ROLE_USER_MANAGEMENT_OWN_READ *OR* ROLE_SYSTEM 
	 * 
	 * ##### Response Codes
	 * 
	 * The following table gives an overview of the possible response codes and their meanings:
	 * 
	 * * HTTP 204 The sent code was correct and the access can be granted.
	 * * HTTP 401 Authentication information is missing or invalid.
	 * * HTTP 403 Invalid verification code.
	 * * HTTP 404 Cannot validate TFA TOTP code - user's TFA TOTP secret does not exist.
	 * * HTTP 422 Unprocessable Entity – invalid payload.
	 * 
	 * @param body
	 */
	@Headers(*["Content-Type:application/json", "Accept:application/json"]) 
	@POST("/user/currentUser/totpSecret/verify")
	fun verifyTfaCode(
		@Body body: CurrentUserTotpCode
	): Call<ResponseBody>
}
