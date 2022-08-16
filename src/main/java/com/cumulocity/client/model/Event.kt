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

@JsonAdapter(Event.JsonAdapter::class)
class Event {
	
	companion object Serialization {
	
		@Transient
		var additionalPropertyClasses: MutableMap<String, Class<*>> = HashMap()
	
		fun registerAdditionalProperty(typeName: String, type: Class<*>) {
			additionalPropertyClasses[typeName] = type
		}
	}

	/**
	 * The date and time when the event was created.
	 */
	var creationTime: String? = null

	/**
	 * The date and time when the event was last updated.
	 */
	var lastUpdated: String? = null

	/**
	 * Unique identifier of the event.
	 */
	var id: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The managed object to which the event is associated.
	 */
	var source: Source? = null

	/**
	 * Description of the event.
	 */
	var text: String? = null

	/**
	 * The date and time when the event is updated.
	 */
	var time: String? = null

	/**
	 * Identifies the type of this event.
	 */
	var type: String? = null

	/**
	 * It is possible to add an arbitrary number of additional properties as a list of key-value pairs, for example, `"property1": {}`, `"property2": "value"`. These properties are known as custom fragments and can be of any type, for example, object or string. Each custom fragment is identified by a unique name.
	 * 
	 * Review the [Naming conventions of fragments](https://cumulocity.com/guides/concepts/domain-model/#naming-conventions-of-fragments) as there are characters that can not be used when naming custom fragments.
	 * 
	 */
	var customFragments: MutableMap<String, Any>? = null

	/**
	 * The managed object to which the event is associated.
	 */
	class Source {
	
		/**
		 * Unique identifier of the object.
		 */
		var id: String? = null
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	class JsonAdapter: JsonDeserializer<Event>, JsonSerializer<Event> {
	
		override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): Event {
			val event = Event()
			json?.let {
				json.asJsonObject.entrySet().forEach { (key, value) ->
					try {
						val field = event.javaClass.getDeclaredField(key)
						field.isAccessible = true
						val item = context?.deserialize<Any>(value, field.type)
						field.set(event, item)
					} catch (e: NoSuchFieldException) {
						additionalPropertyClasses[key]?.let {
							val item = context?.deserialize<Any>(value, it)
							if (event.customFragments == null) {
								event.customFragments = HashMap()
							}
							item?.let { event.customFragments?.put(key, item) }
						}
					}
				}
			}
			return event
		}
		
		override fun serialize(src: Event?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
			val jsonTree = JsonObject()
			src?.creationTime?.let { it -> jsonTree.add("creationTime", context?.serialize(it)) }
			src?.lastUpdated?.let { it -> jsonTree.add("lastUpdated", context?.serialize(it)) }
			src?.id?.let { it -> jsonTree.add("id", context?.serialize(it)) }
			src?.self?.let { it -> jsonTree.add("self", context?.serialize(it)) }
			src?.source?.let { it -> jsonTree.add("source", context?.serialize(it)) }
			src?.text?.let { it -> jsonTree.add("text", context?.serialize(it)) }
			src?.time?.let { it -> jsonTree.add("time", context?.serialize(it)) }
			src?.type?.let { it -> jsonTree.add("type", context?.serialize(it)) }
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
