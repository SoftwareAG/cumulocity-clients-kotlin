// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * Information about paging statistics.
 */
class PageStatistics {

	/**
	 * The current page of the paginated results.
	 */
	var currentPage: Int? = null

	/**
	 * Indicates the number of objects that the collection may contain per page. The upper limit for one page is 2,000 objects.
	 */
	var pageSize: Int? = null

	/**
	 * The total number of results (elements).
	 */
	var totalElements: Int? = null

	/**
	 * The total number of paginated results (pages).
	 * 
	 * > **ⓘ Info:** This property is returned by default except when an operation retrieves all records where values are between an upper and lower boundary, for example, querying ranges using `dateFrom`–`dateTo`. In such cases, the query parameter `withTotalPages=true` should be used to include the total number of pages (at the expense of slightly slower performance).
	 */
	var totalPages: Int? = null

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
