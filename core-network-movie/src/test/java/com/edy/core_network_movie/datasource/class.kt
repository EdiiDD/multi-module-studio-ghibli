package com.edy.core_network.utils

import java.lang.reflect.Modifier

val Any.nonStaticFieldsCount: Int
    get() = this::class.java.declaredFields.count { Modifier.isStatic(it.modifiers).not() }
