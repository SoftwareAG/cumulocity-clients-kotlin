// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Device capability to either display or display and manage the WAN, LAN, and DHCP settings.
 */
class C8yNetwork {

	/**
	 * Local network information.
	 */
	@SerializedName(value = "c8y_LAN")
	var c8yLAN: C8yLAN? = null

	/**
	 * Mobile internet connectivity interface status.
	 */
	@SerializedName(value = "c8y_WAN")
	var c8yWAN: C8yWAN? = null

	/**
	 * Information for DHCP server status.
	 */
	@SerializedName(value = "c8y_DHCP")
	var c8yDHCP: C8yDHCP? = null

	/**
	 * Local network information.
	 */
	class C8yLAN {
	
		/**
		 * Subnet mask configured for the network interface.
		 */
		var netmask: String? = null
	
		/**
		 * IP address configured for the network interface.
		 */
		var ip: String? = null
	
		/**
		 * Identifier for the network interface.
		 */
		var name: String? = null
	
		/**
		 * Indicator showing if the interface is enabled.
		 */
		var enabled: Int? = null
	
		/**
		 * MAC address of the network interface.
		 */
		var mac: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	/**
	 * Mobile internet connectivity interface status.
	 */
	class C8yWAN {
	
		/**
		 * SIM connectivity password.
		 */
		var password: String? = null
	
		/**
		 * SIM connection status.
		 */
		var simStatus: String? = null
	
		/**
		 * Authentication type used by the SIM connectivity.
		 */
		var authType: String? = null
	
		/**
		 * APN used for internet access.
		 */
		var apn: String? = null
	
		/**
		 * SIM connectivity username.
		 */
		var username: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	/**
	 * Information for DHCP server status.
	 */
	class C8yDHCP {
	
		/**
		 * First configured DNS server.
		 */
		var dns1: String? = null
	
		/**
		 * Second configured DNS server.
		 */
		var dns2: String? = null
	
		/**
		 * Domain name configured for the device.
		 */
		var domainName: String? = null
	
		/**
		 * IP address range.
		 */
		var addressRange: AddressRange? = null
	
		/**
		 * Indicator showing if the DHCP server is enabled.
		 */
		var enabled: Int? = null
	
		/**
		 * IP address range.
		 */
		class AddressRange {
		
			/**
			 * Start of address range assigned to DHCP clients.
			 */
			var start: String? = null
		
			/**
			 * End of address range assigned to DHCP clients.
			 */
			var end: String? = null
		
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
