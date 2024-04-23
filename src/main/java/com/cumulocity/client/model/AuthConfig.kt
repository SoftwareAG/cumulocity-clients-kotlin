// Copyright (c) 2014-2023 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors.
// Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG.

package com.cumulocity.client.model
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Parameters determining the authentication process.
 */
data class AuthConfig(var providerName: String?, var type: Type?) {
	constructor() : this(providerName = null, type = null)

	/**
	 * SSO specific. Describes the fields in the access token from the external server containing user information.
	 */
	var accessTokenToUserDataMapping: AccessTokenToUserDataMapping? = null

	/**
	 * SSO specific. Token audience.
	 */
	var audience: String? = null

	var authorizationRequest: RequestRepresentation? = null

	/**
	 * For basic authentication case only.
	 */
	var authenticationRestrictions: BasicAuthenticationRestrictions? = null

	/**
	 * SSO specific. Information for the UI about the name displayed on the external server login button.
	 */
	var buttonName: String? = null

	/**
	 * SSO specific. The identifier of the Cumulocity IoT tenant on the external authorization server.
	 */
	var clientId: String? = null

	/**
	 * The authentication configuration grant type identifier.
	 */
	var grantType: GrantType? = null

	/**
	 * Unique identifier of this login option.
	 */
	var id: String? = null

	/**
	 * SSO specific. External token issuer.
	 */
	var issuer: String? = null

	var logoutRequest: RequestRepresentation? = null

	/**
	 * Indicates whether the configuration is only accessible to the management tenant.
	 */
	var onlyManagementTenantAccess: Boolean? = null

	/**
	 * SSO specific. Describes the process of internal user creation during login with the external authorization server.
	 */
	var onNewUser: OnNewUser? = null

	/**
	 * SSO specific. URL used for redirecting to the Cumulocity IoT platform.
	 */
	var redirectToPlatform: String? = null

	var refreshRequest: RequestRepresentation? = null

	/**
	 * A URL linking to this resource.
	 */
	var self: String? = null

	/**
	 * The session configuration properties are only available for OAuth internal. See [Changing settings > OAuth internal](https://cumulocity.com/guides/users-guide/administration/#oauth-internal) for more details.
	 */
	var sessionConfiguration: OAuthSessionConfiguration? = null

	/**
	 * SSO specific and authorization server dependent. Describes the method of access token signature verification on the Cumulocity IoT platform.
	 */
	var signatureVerificationConfig: SignatureVerificationConfig? = null

	/**
	 * SSO specific. Template name used by the UI.
	 */
	var template: String? = null

	var tokenRequest: RequestRepresentation? = null

	/**
	 * SSO specific. Points to the field in the obtained JWT access token that should be used as the username in the Cumulocity IoT platform.
	 */
	var userIdConfig: UserIdConfig? = null

	/**
	 * Indicates whether user data are managed internally by the Cumulocity IoT platform or by an external server. Note that the value is case insensitive.
	 */
	var userManagementSource: UserManagementSource? = null

	/**
	 * Information for the UI if the respective authentication form should be visible for the user.
	 */
	var visibleOnLoginPage: Boolean? = null

	/**
	 * A configuration for authentication with an access token from the authorization server.
	 */
	var externalTokenConfig: ExternalTokenConfig? = null

	/**
	 * The authentication configuration grant type identifier.
	 */
	enum class GrantType(val value: String) {
		@SerializedName(value = "AUTHORIZATION_CODE")
		AUTHORIZATIONCODE("AUTHORIZATION_CODE"),
		@SerializedName(value = "PASSWORD")
		PASSWORD("PASSWORD")
	}

	/**
	 * The authentication configuration type. Note that the value is case insensitive.
	 */
	enum class Type(val value: String) {
		@SerializedName(value = "BASIC")
		BASIC("BASIC"),
		@SerializedName(value = "OAUTH2")
		OAUTH2("OAUTH2"),
		@SerializedName(value = "OAUTH2_INTERNAL")
		OAUTH2INTERNAL("OAUTH2_INTERNAL")
	}

	/**
	 * Indicates whether user data are managed internally by the Cumulocity IoT platform or by an external server. Note that the value is case insensitive.
	 */
	enum class UserManagementSource(val value: String) {
		@SerializedName(value = "INTERNAL")
		INTERNAL("INTERNAL"),
		@SerializedName(value = "REMOTE")
		REMOTE("REMOTE")
	}

	/**
	 * SSO specific. Describes the fields in the access token from the external server containing user information.
	 */
	class AccessTokenToUserDataMapping {
	
		/**
		 * The name of the field containing the user's email.
		 */
		var emailClaimName: String? = null
	
		/**
		 * The name of the field containing the user's first name.
		 */
		var firstNameClaimName: String? = null
	
		/**
		 * The name of the field containing the user's last name.
		 */
		var lastNameClaimName: String? = null
	
		/**
		 * The name of the field containing the user's phone number.
		 */
		var phoneNumberClaimName: String? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}


	/**
	 * SSO specific. Describes the process of internal user creation during login with the external authorization server.
	 */
	class OnNewUser {
	
		/**
		 * Modern version of configuration of default groups and applications. This ensures backward compatibility.
		 */
		var dynamicMapping: DynamicMapping? = null
	
		/**
		 * Modern version of configuration of default groups and applications. This ensures backward compatibility.
		 */
		class DynamicMapping {
		
			/**
			 * Configuration of the mapping.
			 */
			var configuration: Configuration? = null
		
			/**
			 * Represents rules used to assign groups and applications.
			 */
			var mappings: Array<Mappings>? = null
		
			/**
			 * Represents rules used to assign inventory roles.
			 */
			var inventoryMappings: Array<InventoryMappings>? = null
		
			/**
			 * Configuration of the mapping.
			 */
			class Configuration {
			
				/**
				 * Indicates whether the mapping should be evaluated always or only during the first external login when the internal user is created.
				 */
				var mapRolesOnlyForNewUser: Boolean? = null
			
				/**
				 * If set to `true`, dynamic access mapping is only managed for global roles, applications and inventory roles which are listed in the configuration. Others remain unchanged.
				 */
				var manageRolesOnlyFromAccessMapping: Boolean? = null
			
				override fun toString(): String {
					return Gson().toJson(this).toString()
				}
			}
		
			/**
			 * Represents information of mapping access to groups and applications.
			 */
			class Mappings {
			
				/**
				 * Represents a predicate for verification. It acts as a condition which is necessary to assign a user to the given groups, permit access to the specified applications or to assign specific inventory roles to device groups.
				 */
				@SerializedName(value = "when")
				var pWhen: JSONPredicateRepresentation? = null
			
				/**
				 * List of the applications' identifiers.
				 */
				var thenApplications: Array<Int>? = null
			
				/**
				 * List of the groups' identifiers.
				 */
				var thenGroups: Array<Int>? = null
			
				override fun toString(): String {
					return Gson().toJson(this).toString()
				}
			}
		
			/**
			 * Represents information of mapping access to inventory roles.
			 */
			class InventoryMappings {
			
				/**
				 * Represents a predicate for verification. It acts as a condition which is necessary to assign a user to the given groups, permit access to the specified applications or to assign specific inventory roles to device groups.
				 */
				@SerializedName(value = "when")
				var pWhen: JSONPredicateRepresentation? = null
			
				/**
				 * List of the OAuth inventory assignments.
				 */
				var thenInventoryRoles: Array<ThenInventoryRoles>? = null
			
				/**
				 * Represents inventory roles for a specific device group.
				 */
				class ThenInventoryRoles {
				
					/**
					 * A unique identifier for the managed object for which the roles are assigned.
					 */
					var managedObject: String? = null
				
					/**
					 * List of the inventory roles' identifiers.
					 */
					var roleIds: Array<Int>? = null
				
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
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}

	/**
	 * SSO specific and authorization server dependent. Describes the method of access token signature verification on the Cumulocity IoT platform.
	 */
	class SignatureVerificationConfig {
	
		/**
		 * AAD signature verification configuration.
		 */
		var aad: Aad? = null
	
		/**
		 * ADFS manifest signature verification configuration.
		 */
		var adfsManifest: AdfsManifest? = null
	
		/**
		 * The address of the endpoint which is used to retrieve the public key used to verify the JWT access token signature.
		 */
		var jwks: Jwks? = null
	
		/**
		 * Describes the process of verification of JWT access token with the public keys embedded in the provided X.509 certificates.
		 */
		var manual: Manual? = null
	
		/**
		 * AAD signature verification configuration.
		 */
		class Aad {
		
			/**
			 * URL used to retrieve the public key used for signature verification.
			 */
			var publicKeyDiscoveryUrl: String? = null
		
			override fun toString(): String {
				return Gson().toJson(this).toString()
			}
		}
	
		/**
		 * ADFS manifest signature verification configuration.
		 */
		class AdfsManifest {
		
			/**
			 * The URI to the manifest resource.
			 */
			var manifestUrl: String? = null
		
			override fun toString(): String {
				return Gson().toJson(this).toString()
			}
		}
	
		/**
		 * The address of the endpoint which is used to retrieve the public key used to verify the JWT access token signature.
		 */
		class Jwks {
		
			/**
			 * The URI to the public key resource.
			 */
			var jwksUrl: String? = null
		
			override fun toString(): String {
				return Gson().toJson(this).toString()
			}
		}
	
		/**
		 * Describes the process of verification of JWT access token with the public keys embedded in the provided X.509 certificates.
		 */
		class Manual {
		
			/**
			 * The name of the field in the JWT access token containing the certificate identifier.
			 */
			var certIdField: String? = null
		
			/**
			 * Indicates whether the certificate identifier should be read from the JWT access token.
			 */
			var certIdFromField: Boolean? = null
		
			/**
			 * Details of the certificates.
			 */
			var certificates: Certificates? = null
		
			/**
			 * Details of the certificates.
			 */
			class Certificates {
			
				/**
				 * The signing algorithm of the JWT access token.
				 */
				var alg: Alg? = null
			
				/**
				 * The public key certificate.
				 */
				var publicKey: String? = null
			
				/**
				 * The validity start date of the certificate.
				 */
				var validFrom: String? = null
			
				/**
				 * The expiry date of the certificate.
				 */
				var validTill: String? = null
			
				/**
				 * The signing algorithm of the JWT access token.
				 */
				enum class Alg(val value: String) {
					@SerializedName(value = "RSA")
					RSA("RSA"),
					@SerializedName(value = "PCKS")
					PCKS("PCKS")
				}
			
			
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


	/**
	 * SSO specific. Points to the field in the obtained JWT access token that should be used as the username in the Cumulocity IoT platform.
	 */
	class UserIdConfig {
	
		/**
		 * Used only when `useConstantValue` is set to `true`.
		 */
		var constantValue: String? = null
	
		/**
		 * The name of the field containing the JWT.
		 */
		var jwtField: String? = null
	
		/**
		 * Not recommended. If set to `true`, all SSO users will share one account in the Cumulocity IoT platform.
		 */
		var useConstantValue: Boolean? = null
	
		override fun toString(): String {
			return Gson().toJson(this).toString()
		}
	}


	/**
	 * A configuration for authentication with an access token from the authorization server.
	 */
	class ExternalTokenConfig {
	
		/**
		 * Indicates whether authentication is enabled or disabled.
		 */
		var enabled: Boolean? = null
	
		/**
		 * Points to the claim of the access token from the authorization server that must be used as the username in the Cumulocity IoT platform.
		 */
		var userOrAppIdConfig: UserOrAppIdConfig? = null
	
		/**
		 * If set to `true`, the access token is validated against the authorization server by way of introspection or user info request.
		 */
		var validationRequired: Boolean? = null
	
		/**
		 * The method of validation of the access token.
		 */
		var validationMethod: ValidationMethod? = null
	
		var tokenValidationRequest: RequestRepresentation? = null
	
		/**
		 * The frequency (in Minutes) in which Cumulocity sends a validation request to authorization server. The recommended frequency is 1 minute.
		 */
		var accessTokenValidityCheckIntervalInMinutes: Int? = null
	
		/**
		 * The method of validation of the access token.
		 */
		enum class ValidationMethod(val value: String) {
			@SerializedName(value = "INTROSPECTION")
			INTROSPECTION("INTROSPECTION"),
			@SerializedName(value = "USERINFO")
			USERINFO("USERINFO")
		}
	
		/**
		 * Points to the claim of the access token from the authorization server that must be used as the username in the Cumulocity IoT platform.
		 */
		class UserOrAppIdConfig {
		
			/**
			 * Used only if `useConstantValue` is set to `true`.
			 */
			var constantValue: String? = null
		
			/**
			 * The name of the field containing the JWT.
			 */
			var jwtField: String? = null
		
			/**
			 * Not recommended. If set to `true`, all users share a single account in the Cumulocity IoT platform.
			 */
			var useConstantValue: Boolean? = null
		
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
