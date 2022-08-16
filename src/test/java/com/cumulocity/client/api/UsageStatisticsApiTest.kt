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
import okhttp3.ResponseBody
import com.cumulocity.client.model.RangeStatisticsFile
import com.cumulocity.client.model.TenantUsageStatisticsCollection
import com.cumulocity.client.model.SummaryTenantUsageStatistics
import com.cumulocity.client.model.SummaryAllTenantsUsageStatistics
import com.cumulocity.client.model.TenantUsageStatisticsFileCollection
import com.cumulocity.client.model.StatisticsFile

// TODO parameterise servers
class UsageStatisticsApiTest {

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
        val api = UsageStatisticsApi.Factory.create("your tenant")
        Assert.assertNotNull(api)
    }
    
    @Test
    fun testGetTenantUsageStatisticsCollectionResource() {
    	val latch = CountDownLatch(1)
        val api = UsageStatisticsApi.Factory.create("https://iotaccstage2.eu-latest.cumulocity.com/", this.clientBuilder)
    	api.getTenantUsageStatisticsCollectionResource().enqueue(object : Callback<TenantUsageStatisticsCollection> {
    
    		override fun onResponse(call: Call<TenantUsageStatisticsCollection>?, response: Response<TenantUsageStatisticsCollection>?) {
    			println(response?.message())
    			println(response?.body())
    			latch.countDown()
    		}
    
    		override fun onFailure(call: Call<TenantUsageStatisticsCollection>?, t: Throwable?) {
    			println(t)
    			latch.countDown()
    		}
    	})
    	latch.await()
    }
    
    @Test
    fun testGetTenantUsageStatistics() {
    	val latch = CountDownLatch(1)
        val api = UsageStatisticsApi.Factory.create("https://iotaccstage2.eu-latest.cumulocity.com/", this.clientBuilder)
    	api.getTenantUsageStatistics().enqueue(object : Callback<SummaryTenantUsageStatistics> {
    
    		override fun onResponse(call: Call<SummaryTenantUsageStatistics>?, response: Response<SummaryTenantUsageStatistics>?) {
    			println(response?.message())
    			println(response?.body())
    			latch.countDown()
    		}
    
    		override fun onFailure(call: Call<SummaryTenantUsageStatistics>?, t: Throwable?) {
    			println(t)
    			latch.countDown()
    		}
    	})
    	latch.await()
    }
    
    @Test
    fun testGetTenantsUsageStatistics() {
    	val latch = CountDownLatch(1)
        val api = UsageStatisticsApi.Factory.create("https://iotaccstage2.eu-latest.cumulocity.com/", this.clientBuilder)
    	api.getTenantsUsageStatistics().enqueue(object : Callback<Array<SummaryAllTenantsUsageStatistics>> {
    
    		override fun onResponse(call: Call<Array<SummaryAllTenantsUsageStatistics>>?, response: Response<Array<SummaryAllTenantsUsageStatistics>>?) {
    			println(response?.message())
    			println(response?.body())
    			latch.countDown()
    		}
    
    		override fun onFailure(call: Call<Array<SummaryAllTenantsUsageStatistics>>?, t: Throwable?) {
    			println(t)
    			latch.countDown()
    		}
    	})
    	latch.await()
    }
    
    @Test
    fun testGetMetadata() {
    	val latch = CountDownLatch(1)
        val api = UsageStatisticsApi.Factory.create("https://iotaccstage2.eu-latest.cumulocity.com/", this.clientBuilder)
    	api.getMetadata().enqueue(object : Callback<TenantUsageStatisticsFileCollection> {
    
    		override fun onResponse(call: Call<TenantUsageStatisticsFileCollection>?, response: Response<TenantUsageStatisticsFileCollection>?) {
    			println(response?.message())
    			println(response?.body())
    			latch.countDown()
    		}
    
    		override fun onFailure(call: Call<TenantUsageStatisticsFileCollection>?, t: Throwable?) {
    			println(t)
    			latch.countDown()
    		}
    	})
    	latch.await()
    }
}
