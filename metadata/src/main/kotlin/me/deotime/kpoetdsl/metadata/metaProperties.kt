package me.deotime.kpoetdsl.metadata

import kotlinx.metadata.KmProperty
import me.deotime.kpoetdsl.Cozy.Initializer.Simple.Companion.invoke
import me.deotime.kpoetdsl.ExperimentalKotlinPoetDSL
import me.deotime.kpoetdsl.PropertyBuilder

fun KmProperty.toSpec() = let { km ->
    PropertyBuilder {
        name(km.name)
        type(km.returnType.asTypeName())
        modifiers(km.flags.toPropertyModifiers())
    }
}
