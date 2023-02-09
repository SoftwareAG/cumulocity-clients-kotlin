// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.supplementary

/**
 * Global helper function to create a [SeparatedQueryParameter].
 *
 * Example usage:
 * ```
 * separatedQueryParameterOf(1, 2, 3)
 * ```
 */
fun <T : Any> separatedQueryParameterOf(vararg elements: T): SeparatedQueryParameter<T> {
	return SeparatedQueryParameter(elements)
}

/**
 * A wrapper class for any sort of *Arrays* used to implement comma separated query parameters. Any *null* values will be removed.
 *
 * Use [separatedQueryParameterOf] to construct an instance.
 *
 * @param elements
 */
class SeparatedQueryParameter<T>(var elements: Array<out T>) {

	override fun toString(): String {
		return elements.filterNotNull().joinToString(",")
	}
}
