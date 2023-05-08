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

@JsonAdapter(CategoryOptions.JsonAdapter::class)
class CategoryOptions {

	companion object Serialization {
	
		@Transient
		var additionalPropertyClasses: MutableMap<String, Class<*>> = HashMap()
	
		fun registerAdditionalProperty(typeName: String, type: Class<*>) {
			additionalPropertyClasses[typeName] = type
		}
	}

	/**
	 * It is possible to specify an arbitrary number of existing options as a list of key-value pairs, for example, `"key1": "value1"`, `"key2": "value2"`.
	 */
	var keyValuePairs: MutableMap<String, Any> = hashMapOf()
	
	operator fun get(key: String): Any? = keyValuePairs[key]
	operator fun set(key: String, value: Any): Any? = keyValuePairs.put(key, value)

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}

	class JsonAdapter: JsonDeserializer<CategoryOptions>, JsonSerializer<CategoryOptions> {
	
		override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): CategoryOptions {
			val categoryOptions = CategoryOptions()
			json?.let {
				json.asJsonObject.entrySet().forEach { (key, value) ->
					try {
						val field = categoryOptions.javaClass.getDeclaredField(key)
						field.isAccessible = true
						val item = context?.deserialize<Any>(value, field.type)
						field.set(categoryOptions, item)
					} catch (e: NoSuchFieldException) {
						additionalPropertyClasses[key]?.let {
							val item = context?.deserialize<Any>(value, it)
							item?.let { categoryOptions.keyValuePairs.put(key, item) }
						}
					}
				}
			}
			return categoryOptions
		}

		override fun serialize(src: CategoryOptions?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
			val jsonTree = JsonObject()
			src?.keyValuePairs?.let { it ->
				it.forEach { (s, any) -> jsonTree.add(s, context?.serialize(any))}
			}
			return jsonTree
		}
	}
}
