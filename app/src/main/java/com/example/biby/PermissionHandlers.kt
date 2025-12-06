package com.example.biby

import android.provider.Settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.myapplication.MyAccessibilityService

class permissionHandler
private fun isServiceEnabled(): Boolean {
    // Find the full component name of our service
    val expectedComponentName = componentName.packageName + "/" + MyAccessibilityService::class.java.name

    // Retrieve the list of currently enabled accessibility services from the system settings
    val enabledServices = Settings.Secure.getString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)

    // Return true if our service's name is found in the list of enabled services
    return enabledServices?.contains(expectedComponentName) == true
}