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
import com.google.gson.annotations.SerializedName

@JsonAdapter(Alarm.JsonAdapter::class)
class Alarm {

	companion object Serialization {
	
		@Transient
		var additionalPropertyClasses: MutableMap<String, Class<*>> = HashMap()
	
		fun registerAdditionalProperty(typeName: String, type: Class<*>) {
			additionalPropertyClasses[typeName] = type
		}
	}

	/**
	 * Number of times that this alarm has been triggered.
	 */
	var count: Int? = null

	/**
	 * The date and time when the alarm was created.
	 */
	var creationTime: String? = null

	/**
	 * The time at which the alarm occurred for the first time. Only present when `count` is greater than 1.
	 */
	var firstOccurrenceTime: String? = null

	/**
	 * Unique identifier of the alarm.
	 */
	var id: String? = null

	/**
	 * The date and time when the alarm was last updated.
	 */
	var lastUpdated: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The severity of the alarm.
	 */
	var severity: Severity? = null

	/**
	 * The managed object to which the alarm is associated.
	 */
	var source: Source? = null

	/**
	 * The status of the alarm. If not specified, a new alarm will be created as ACTIVE.
	 */
	var status: Status? = null

	/**
	 * Description of the alarm.
	 */
	var text: String? = null

	/**
	 * The date and time when the alarm is triggered.
	 */
	var time: String? = null

	/**
	 * Identifies the type of this alarm.
	 */
	var type: String? = null

	/**
	 * It is possible to add an arbitrary number of additional properties as a list of key-value pairs, for example, `"property1": {}`, `"property2": "value"`. These properties are known as custom fragments and can be of any type, for example, object or string. Each custom fragment is identified by a unique name.
	 * 
	 * Review the [Naming conventions of fragments](https://cumulocity.com/guides/concepts/domain-model/#naming-conventions-of-fragments) as there are characters that can not be used when naming custom fragments.
	 */
	var customFragments: MutableMap<String, Any> = hashMapOf()
	
	operator fun get(key: String): Any? = customFragments[key]
	operator fun set(key: String, value: Any): Any? = customFragments.put(key, value)

	/**
	 * The severity of the alarm.
	 */
	enum class Severity(val value: String) {
		@SerializedName(value = "CRITICAL")
		CRITICAL("CRITICAL"),
		@SerializedName(value = "MAJOR")
		MAJOR("MAJOR"),
		@SerializedName(value = "MINOR")
		MINOR("MINOR"),
		@SerializedName(value = "WARNING")
		WARNING("WARNING")
	}

	/**
	 * The status of the alarm. If not specified, a new alarm will be created as ACTIVE.
	 */
	enum class Status(val value: String) {
		@SerializedName(value = "ACTIVE")
		ACTIVE("ACTIVE"),
		@SerializedName(value = "ACKNOWLEDGED")
		ACKNOWLEDGED("ACKNOWLEDGED"),
		@SerializedName(value = "CLEARED")
		CLEARED("CLEARED")
	}


	/**
	 * The managed object to which the alarm is associated.
	 */
	class Source {
	
		/**
		 * Unique identifier of the object.
		 */
		var id: String? = null
	
		/**
		 * Human-readable name that is used for representing the object in user interfaces.
		 */
		var name: String? = null
	
		/**
		 * A URL linking to this resource.
		 */
		var self: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}


	override fun toString(): String {
		return Gson().toJson(this).toString()
	}

	class JsonAdapter: JsonDeserializer<Alarm>, JsonSerializer<Alarm> {
	
		override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): Alarm {
			val alarm = Alarm()
			json?.let {
				json.asJsonObject.entrySet().forEach { (key, value) ->
					try {
						val field = alarm.javaClass.getDeclaredField(key)
						field.isAccessible = true
						val item = context?.deserialize<Any>(value, field.type)
						field.set(alarm, item)
					} catch (e: NoSuchFieldException) {
						additionalPropertyClasses[key]?.let {
							val item = context?.deserialize<Any>(value, it)
							item?.let { alarm.customFragments.put(key, item) }
						}
					}
				}
			}
			return alarm
		}

		override fun serialize(src: Alarm?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
			val jsonTree = JsonObject()
			src?.count?.let { it -> jsonTree.add("count", context?.serialize(it)) }
			src?.creationTime?.let { it -> jsonTree.add("creationTime", context?.serialize(it)) }
			src?.firstOccurrenceTime?.let { it -> jsonTree.add("firstOccurrenceTime", context?.serialize(it)) }
			src?.id?.let { it -> jsonTree.add("id", context?.serialize(it)) }
			src?.lastUpdated?.let { it -> jsonTree.add("lastUpdated", context?.serialize(it)) }
			src?.self?.let { it -> jsonTree.add("self", context?.serialize(it)) }
			src?.severity?.let { it -> jsonTree.add("severity", context?.serialize(it)) }
			src?.source?.let { it -> jsonTree.add("source", context?.serialize(it)) }
			src?.status?.let { it -> jsonTree.add("status", context?.serialize(it)) }
			src?.text?.let { it -> jsonTree.add("text", context?.serialize(it)) }
			src?.time?.let { it -> jsonTree.add("time", context?.serialize(it)) }
			src?.type?.let { it -> jsonTree.add("type", context?.serialize(it)) }
			src?.customFragments?.let { it ->
				it.forEach { (s, any) -> jsonTree.add(s, context?.serialize(any))}
			}
			return jsonTree
		}
	}
}
