// Copyright (c) 2014-2022 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonSerializer
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

@JsonAdapter(Operation.JsonAdapter::class)
class Operation {

	companion object Serialization {
	
		@Transient
		var additionalPropertyClasses: MutableMap<String, Class<*>> = HashMap()
	
		fun registerAdditionalProperty(typeName: String, type: Class<*>) {
			additionalPropertyClasses[typeName] = type
		}
	}

	/**
	 * Reference to a bulk operation ID if this operation was scheduled from a bulk operation.
	 */
	var bulkOperationId: String? = null

	/**
	 * Date and time when the operation was created in the database.
	 */
	var creationTime: String? = null

	/**
	 * Identifier of the target device where the operation should be performed.
	 */
	var deviceId: String? = null

	var deviceExternalIDs: ExternalIds? = null

	/**
	 * Reason for the failure.
	 */
	var failureReason: String? = null

	/**
	 * Unique identifier of this operation.
	 */
	var id: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The status of the operation.
	 */
	var status: Status? = null

	/**
	 * It is possible to add an arbitrary number of additional properties as a list of key-value pairs, for example, `"property1": {}`, `"property2": "value"`. These properties are known as custom fragments and can be of any type, for example, object or string. Each custom fragment is identified by a unique name.
	 * 
	 * Review the [Naming conventions of fragments](https://cumulocity.com/guides/concepts/domain-model/#naming-conventions-of-fragments) as there are characters that can not be used when naming custom fragments.
	 * 
	 */
	var customFragments: MutableMap<String, Any> = hashMapOf()
	
	operator fun get(key: String): Any? = customFragments[key]
	operator fun set(key: String, value: Any): Any? = customFragments.put(key, value)

	/**
	 * The status of the operation.
	 * [SUCCESSFUL, FAILED, EXECUTING, PENDING]
	 */
	enum class Status(val value: String) {
		@SerializedName(value = "SUCCESSFUL")
		SUCCESSFUL("SUCCESSFUL"),
		@SerializedName(value = "FAILED")
		FAILED("FAILED"),
		@SerializedName(value = "EXECUTING")
		EXECUTING("EXECUTING"),
		@SerializedName(value = "PENDING")
		PENDING("PENDING")
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}

	class JsonAdapter: JsonDeserializer<Operation>, JsonSerializer<Operation> {
	
		override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): Operation {
			val operation = Operation()
			json?.let {
				json.asJsonObject.entrySet().forEach { (key, value) ->
					try {
						val field = operation.javaClass.getDeclaredField(key)
						field.isAccessible = true
						val item = context?.deserialize<Any>(value, field.type)
						field.set(operation, item)
					} catch (e: NoSuchFieldException) {
						additionalPropertyClasses[key]?.let {
							val item = context?.deserialize<Any>(value, it)
							item?.let { operation.customFragments.put(key, item) }
						}
					}
				}
			}
			return operation
		}

		override fun serialize(src: Operation?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
			val jsonTree = JsonObject()
			src?.bulkOperationId?.let { it -> jsonTree.add("bulkOperationId", context?.serialize(it)) }
			src?.creationTime?.let { it -> jsonTree.add("creationTime", context?.serialize(it)) }
			src?.deviceId?.let { it -> jsonTree.add("deviceId", context?.serialize(it)) }
			src?.deviceExternalIDs?.let { it -> jsonTree.add("deviceExternalIDs", context?.serialize(it)) }
			src?.failureReason?.let { it -> jsonTree.add("failureReason", context?.serialize(it)) }
			src?.id?.let { it -> jsonTree.add("id", context?.serialize(it)) }
			src?.self?.let { it -> jsonTree.add("self", context?.serialize(it)) }
			src?.status?.let { it -> jsonTree.add("status", context?.serialize(it)) }
			src?.customFragments?.let { it ->
				it.forEach { (s, any) -> jsonTree.add(s, context?.serialize(any))}
			}
			return jsonTree
		}
	}
}
