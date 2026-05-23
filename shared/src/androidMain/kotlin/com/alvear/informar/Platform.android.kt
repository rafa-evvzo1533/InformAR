package com.alvear.informar

import java.util.UUID

actual fun generateUuid(): String = UUID.randomUUID().toString()
