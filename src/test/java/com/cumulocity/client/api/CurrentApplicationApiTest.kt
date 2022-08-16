// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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
import com.cumulocity.client.model.Application
import com.cumulocity.client.model.ApplicationSettings
import com.cumulocity.client.model.ApplicationUserCollection

// TODO parameterise servers
class CurrentApplicationApiTest {

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
        val api = CurrentApplicationApi.Factory.create("your tenant")
        Assert.assertNotNull(api)
    }
    
    @Test
    fun testGetCurrentApplication() {
    	val latch = CountDownLatch(1)
        val api = CurrentApplicationApi.Factory.create("https://iotaccstage2.eu-latest.cumulocity.com/", this.clientBuilder)
    	api.getCurrentApplication().enqueue(object : Callback<Application> {
    
    		override fun onResponse(call: Call<Application>?, response: Response<Application>?) {
    			println(response?.message())
    			println(response?.body())
    			latch.countDown()
    		}
    
    		override fun onFailure(call: Call<Application>?, t: Throwable?) {
    			println(t)
    			latch.countDown()
    		}
    	})
    	latch.await()
    }
    
    @Test
    fun testGetCurrentApplicationSettings() {
    	val latch = CountDownLatch(1)
        val api = CurrentApplicationApi.Factory.create("https://iotaccstage2.eu-latest.cumulocity.com/", this.clientBuilder)
    	api.getCurrentApplicationSettings().enqueue(object : Callback<Array<ApplicationSettings>> {
    
    		override fun onResponse(call: Call<Array<ApplicationSettings>>?, response: Response<Array<ApplicationSettings>>?) {
    			println(response?.message())
    			println(response?.body())
    			latch.countDown()
    		}
    
    		override fun onFailure(call: Call<Array<ApplicationSettings>>?, t: Throwable?) {
    			println(t)
    			latch.countDown()
    		}
    	})
    	latch.await()
    }
    
    @Test
    fun testGetSubscribedUsers() {
    	val latch = CountDownLatch(1)
        val api = CurrentApplicationApi.Factory.create("https://iotaccstage2.eu-latest.cumulocity.com/", this.clientBuilder)
    	api.getSubscribedUsers().enqueue(object : Callback<ApplicationUserCollection> {
    
    		override fun onResponse(call: Call<ApplicationUserCollection>?, response: Response<ApplicationUserCollection>?) {
    			println(response?.message())
    			println(response?.body())
    			latch.countDown()
    		}
    
    		override fun onFailure(call: Call<ApplicationUserCollection>?, t: Throwable?) {
    			println(t)
    			latch.countDown()
    		}
    	})
    	latch.await()
    }
}
