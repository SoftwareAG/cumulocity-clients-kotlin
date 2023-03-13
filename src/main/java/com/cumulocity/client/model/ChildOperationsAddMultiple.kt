// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.	

package com.cumulocity.client.model
import com.google.gson.Gson

data class ChildOperationsAddMultiple(var references: Array<References>?) {
	constructor() : this(references = null)

	class References {
	
		var managedObject: ManagedObject? = null
	
		data class ManagedObject(var id: String?) {
			constructor() : this(id = null)
		
			override fun toString(): String {
				return Gson().toJson(this).toString()
			}
		}
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
