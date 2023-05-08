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

@JsonAdapter(ManagedObject.JsonAdapter::class)
class ManagedObject {

	companion object Serialization {
	
		@Transient
		var additionalPropertyClasses: MutableMap<String, Class<*>> = HashMap()
	
		fun registerAdditionalProperty(typeName: String, type: Class<*>) {
			additionalPropertyClasses[typeName] = type
		}
	}

	/**
	 * The date and time when the object was created.
	 */
	var creationTime: String? = null

	/**
	 * Unique identifier of the object.
	 */
	var id: String? = null

	/**
	 * The date and time when the object was updated for the last time.
	 */
	var lastUpdated: String? = null

	/**
	 * Human-readable name that is used for representing the object in user interfaces.
	 */
	var name: String? = null

	/**
	 * Username of the device's owner.
	 */
	var owner: String? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The fragment type can be interpreted as *device class*, this means, devices with the same type can receive the same types of configuration, software, firmware and operations. The type value is indexed and is therefore used for queries.
	 */
	var type: String? = null

	/**
	 * A collection of references to child additions.
	 */
	var childAdditions: ObjectChildAdditions? = null

	/**
	 * A collection of references to child assets.
	 */
	var childAssets: ObjectChildAssets? = null

	/**
	 * A collection of references to child devices.
	 */
	var childDevices: ObjectChildDevices? = null

	/**
	 * A collection of references to addition parent objects.
	 */
	var additionParents: ObjectAdditionParents? = null

	/**
	 * A collection of references to asset parent objects.
	 */
	var assetParents: ObjectAssetParents? = null

	/**
	 * A collection of references to device parent objects.
	 */
	var deviceParents: ObjectDeviceParents? = null

	/**
	 * A fragment which identifies this managed object as a device.
	 */
	@SerializedName(value = "c8y_IsDevice")
	var c8yIsDevice: C8yIsDevice? = null

	/**
	 * This fragment must be added in order to publish sample commands for a subset of devices sharing the same device type. If the fragment is present, the list of sample commands for a device type will be extended with the sample commands for the `c8y_DeviceTypes`. New sample commands created from the user interface will be created in the context of the `c8y_DeviceTypes`.
	 */
	@SerializedName(value = "c8y_DeviceTypes")
	var c8yDeviceTypes: Array<String>? = null

	/**
	 * Lists the operations that are available for a particular device, so that applications can trigger the operations.
	 */
	@SerializedName(value = "c8y_SupportedOperations")
	var c8ySupportedOperations: Array<String>? = null

	/**
	 * It is possible to add an arbitrary number of additional properties as a list of key-value pairs, for example, `"property1": {}`, `"property2": "value"`. These properties are known as custom fragments and can be of any type, for example, object or string. Each custom fragment is identified by a unique name.
	 * 
	 * Review the [Naming conventions of fragments](https://cumulocity.com/guides/concepts/domain-model/#naming-conventions-of-fragments) as there are characters that can not be used when naming custom fragments.
	 */
	var customFragments: MutableMap<String, Any> = hashMapOf()
	
	operator fun get(key: String): Any? = customFragments[key]
	operator fun set(key: String, value: Any): Any? = customFragments.put(key, value)

	/**
	 * A fragment which identifies this managed object as a device.
	 */
	class C8yIsDevice {
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}

	class JsonAdapter: JsonDeserializer<ManagedObject>, JsonSerializer<ManagedObject> {
	
		override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): ManagedObject {
			val managedObject = ManagedObject()
			json?.let {
				json.asJsonObject.entrySet().forEach { (key, value) ->
					try {
						val field = managedObject.javaClass.getDeclaredField(key)
						field.isAccessible = true
						val item = context?.deserialize<Any>(value, field.type)
						field.set(managedObject, item)
					} catch (e: NoSuchFieldException) {
						additionalPropertyClasses[key]?.let {
							val item = context?.deserialize<Any>(value, it)
							item?.let { managedObject.customFragments.put(key, item) }
						}
					}
				}
			}
			return managedObject
		}

		override fun serialize(src: ManagedObject?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
			val jsonTree = JsonObject()
			src?.creationTime?.let { it -> jsonTree.add("creationTime", context?.serialize(it)) }
			src?.id?.let { it -> jsonTree.add("id", context?.serialize(it)) }
			src?.lastUpdated?.let { it -> jsonTree.add("lastUpdated", context?.serialize(it)) }
			src?.name?.let { it -> jsonTree.add("name", context?.serialize(it)) }
			src?.owner?.let { it -> jsonTree.add("owner", context?.serialize(it)) }
			src?.self?.let { it -> jsonTree.add("self", context?.serialize(it)) }
			src?.type?.let { it -> jsonTree.add("type", context?.serialize(it)) }
			src?.childAdditions?.let { it -> jsonTree.add("childAdditions", context?.serialize(it)) }
			src?.childAssets?.let { it -> jsonTree.add("childAssets", context?.serialize(it)) }
			src?.childDevices?.let { it -> jsonTree.add("childDevices", context?.serialize(it)) }
			src?.additionParents?.let { it -> jsonTree.add("additionParents", context?.serialize(it)) }
			src?.assetParents?.let { it -> jsonTree.add("assetParents", context?.serialize(it)) }
			src?.deviceParents?.let { it -> jsonTree.add("deviceParents", context?.serialize(it)) }
			src?.c8yIsDevice?.let { it -> jsonTree.add("c8y_IsDevice", context?.serialize(it)) }
			src?.c8yDeviceTypes?.let { it -> jsonTree.add("c8y_DeviceTypes", context?.serialize(it)) }
			src?.c8ySupportedOperations?.let { it -> jsonTree.add("c8y_SupportedOperations", context?.serialize(it)) }
			src?.customFragments?.let { it ->
				it.forEach { (s, any) -> jsonTree.add(s, context?.serialize(any))}
			}
			return jsonTree
		}
	}
}
