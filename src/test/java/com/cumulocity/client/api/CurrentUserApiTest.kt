// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.api
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Headers
import org.junit.Assert
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import okhttp3.ResponseBody
import com.cumulocity.client.model.CurrentUser
import com.cumulocity.client.model.PasswordChange
import com.cumulocity.client.model.CurrentUserTotpSecretActivity
import com.cumulocity.client.model.CurrentUserTotpCode
import com.cumulocity.client.model.CurrentUserTotpSecret

// TODO parameterise servers
class CurrentUserApiTest {

	private var clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
	
	init {
		val authToken = Credentials.basic("userName", "password")
		val headerAuthorizationInterceptor = Interceptor { chain ->
			var request: Request = chain.request()
			val headers: Headers = request.headers().newBuilder().add("Authorization", authToken).build()
			request = request.newBuilder().headers(headers).build()
			chain.proceed(request)
	   	}
		this.clientBuilder.addInterceptor(headerAuthorizationInterceptor)
	}	

    @Test
    fun testFactoryCreatesObject() {
        val api = CurrentUserApi.Factory.create("your tenant")
        Assert.assertNotNull(api)
    }
    
    @Test
    fun testGetCurrentUser() {
    	val latch = CountDownLatch(1)
        val api = CurrentUserApi.Factory.create("tenant", this.clientBuilder)
    	api.getCurrentUser().enqueue(object : Callback<CurrentUser> {
    
    		override fun onResponse(call: Call<CurrentUser>?, response: Response<CurrentUser>?) {
    			println(response?.message())
    			println(response?.body())
    			latch.countDown()
    		}
    
    		override fun onFailure(call: Call<CurrentUser>?, t: Throwable?) {
    			println(t)
    			latch.countDown()
    		}
    	})
    	latch.await()
    }
    
    @Test
    fun testGetTfaState() {
    	val latch = CountDownLatch(1)
        val api = CurrentUserApi.Factory.create("tenant", this.clientBuilder)
    	api.getTfaState().enqueue(object : Callback<CurrentUserTotpSecretActivity> {
    
    		override fun onResponse(call: Call<CurrentUserTotpSecretActivity>?, response: Response<CurrentUserTotpSecretActivity>?) {
    			println(response?.message())
    			println(response?.body())
    			latch.countDown()
    		}
    
    		override fun onFailure(call: Call<CurrentUserTotpSecretActivity>?, t: Throwable?) {
    			println(t)
    			latch.countDown()
    		}
    	})
    	latch.await()
    }
}
