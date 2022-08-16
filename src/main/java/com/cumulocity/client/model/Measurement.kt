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

@JsonAdapter(Measurement.JsonAdapter::class)
data class Measurement(var source: Source?, var time: String?, var type: String?) {
	
	companion object Serialization {
	
		@Transient
		var additionalPropertyClasses: MutableMap<String, Class<*>> = HashMap()
	
		fun registerAdditionalProperty(typeName: String, type: Class<*>) {
			additionalPropertyClasses[typeName] = type
		}
	}
	constructor() : this(source = null, time = null, type = null)

	/**
	 * Unique identifier of the measurement.
	 */
	var id: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * A type of measurement fragment.
	 */
	@SerializedName(value = "c8y_Steam")
	var c8ySteam: C8ySteam? = null

	/**
	 * It is possible to add an arbitrary number of additional properties as a list of key-value pairs, for example, `"property1": {}`, `"property2": "value"`. These properties are known as custom fragments and can be of any type, for example, object or string. Each custom fragment is identified by a unique name.
	 * 
	 * Review the [Naming conventions of fragments](https://cumulocity.com/guides/concepts/domain-model/#naming-conventions-of-fragments) as there are characters that can not be used when naming custom fragments.
	 * 
	 */
	var customFragments: MutableMap<String, Any>? = null

	/**
	 * The managed object to which the measurement is associated.
	 */
	data class Source(var id: String?) {
		constructor() : this(id = null)
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	class JsonAdapter: JsonDeserializer<Measurement>, JsonSerializer<Measurement> {
	
		override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): Measurement {
			val measurement = Measurement()
			json?.let {
				json.asJsonObject.entrySet().forEach { (key, value) ->
					try {
						val field = measurement.javaClass.getDeclaredField(key)
						field.isAccessible = true
						val item = context?.deserialize<Any>(value, field.type)
						field.set(measurement, item)
					} catch (e: NoSuchFieldException) {
						additionalPropertyClasses[key]?.let {
							val item = context?.deserialize<Any>(value, it)
							if (measurement.customFragments == null) {
								measurement.customFragments = HashMap()
							}
							item?.let { measurement.customFragments?.put(key, item) }
						}
					}
				}
			}
			return measurement
		}
		
		override fun serialize(src: Measurement?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
			val jsonTree = JsonObject()
			src?.id?.let { it -> jsonTree.add("id", context?.serialize(it)) }
			src?.self?.let { it -> jsonTree.add("self", context?.serialize(it)) }
			src?.source?.let { it -> jsonTree.add("source", context?.serialize(it)) }
			src?.time?.let { it -> jsonTree.add("time", context?.serialize(it)) }
			src?.type?.let { it -> jsonTree.add("type", context?.serialize(it)) }
			src?.c8ySteam?.let { it -> jsonTree.add("c8y_Steam", context?.serialize(it)) }
			src?.customFragments?.let { it ->
				it.forEach { (s, any) -> jsonTree.add(s, context?.serialize(any))}
			}
			return jsonTree
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
