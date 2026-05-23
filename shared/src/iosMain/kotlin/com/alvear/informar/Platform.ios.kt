package com.alvear.informar

import platform.Foundation.NSUUID

actual fun generateUuid(): String = NSUUID().UUIDString()
