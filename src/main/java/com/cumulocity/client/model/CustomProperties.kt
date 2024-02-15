// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
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

/**
 * An object with a list of custom properties.
 */
@JsonAdapter(CustomProperties.JsonAdapter::class)
class CustomProperties {

	companion object Serialization {
	
		@Transient
		var additionalPropertyClasses: MutableMap<String, Class<*>> = HashMap()
	
		fun registerAdditionalProperty(typeName: String, type: Class<*>) {
			additionalPropertyClasses[typeName] = type
		}
	}

	/**
	 * The preferred language to be used in the platform.
	 */
	var language: String? = null

	/**
	 * It is possible to add an arbitrary number of custom properties as a list of key-value pairs, for example, `"property": "value"`.
	 */
	var customProperties: MutableMap<String, Any> = hashMapOf()
	
	operator fun get(key: String): Any? = customProperties[key]
	operator fun set(key: String, value: Any): Any? = customProperties.put(key, value)

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}

	class JsonAdapter: JsonDeserializer<CustomProperties>, JsonSerializer<CustomProperties> {
	
		override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): CustomProperties {
			val customProperties = CustomProperties()
			json?.let {
				json.asJsonObject.entrySet().forEach { (key, value) ->
					try {
						val field = customProperties.javaClass.getDeclaredField(key)
						field.isAccessible = true
						val item = context?.deserialize<Any>(value, field.type)
						field.set(customProperties, item)
					} catch (e: NoSuchFieldException) {
						additionalPropertyClasses[key]?.let {
							val item = context?.deserialize<Any>(value, it)
							item?.let { customProperties.customProperties.put(key, item) }
						}
					}
				}
			}
			return customProperties
		}

		override fun serialize(src: CustomProperties?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
			val jsonTree = JsonObject()
			src?.language?.let { it -> jsonTree.add("language", context?.serialize(it)) }
			src?.customProperties?.let { it ->
				it.forEach { (s, any) -> jsonTree.add(s, context?.serialize(any))}
			}
			return jsonTree
		}
	}
}
