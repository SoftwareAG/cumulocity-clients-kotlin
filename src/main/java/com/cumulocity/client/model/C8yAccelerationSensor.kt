// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson

/**
 * An acceleration sensor, or accelerometer, is a device that measures acceleration along an axis. This sensor model does not define the direction of that axis. The units for this sensor type are metres per second per second (m/s2). In a managed object, an acceleration sensor is modeled as a simple empty fragment.
 */
class C8yAccelerationSensor {

	override fun toString(): String {
		return Gson().toJson(this).toString()
	}
}
