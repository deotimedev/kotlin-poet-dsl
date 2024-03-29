package me.deotime.kpoetdsl

import com.squareup.kotlinpoet.ParameterSpec
import me.deotime.kpoetdsl.Cozy.Initializer.Simple.Companion.cozy
import me.deotime.kpoetdsl.utils.Assembler
import me.deotime.kpoetdsl.utils.Required
import me.deotime.kpoetdsl.Attributes.Buildable.Companion.buildWith
import me.deotime.kpoetdsl.utils.requiredHolder
import me.deotime.kpoetdsl.utils.withRequired

@KotlinPoetDsl
class ParameterBuilder private constructor(
    private val cozy: Cozy<ParameterBuilder>,
) :
    Attributes.Sourced<ParameterSpec.Builder>,
    Attributes.Buildable<ParameterSpec> by Attributes.buildWith(cozy, ParameterSpec.Builder::build),
    Attributes.Has.Type by Attributes.typeHolder(cozy),
    Attributes.Has.Documentation by Attributes.documentationVisitor(cozy, ParameterSpec.Builder::addKdoc),
    Attributes.Property by Attributes.property(
        cozy = cozy,
        modifiers = ParameterSpec.Builder::modifiers,
        annotations = ParameterSpec.Builder::annotations,
    ),
    Required.Holder by requiredHolder(),
    Maybe<ParameterSpec.Builder> by maybe() {

    override val source by withRequired { ParameterSpec.builder(name, type) }

    @KotlinPoetDsl
    fun default(assembler: Assembler<CodeBuilder>) {
        source.defaultValue(CodeBuilder.cozy().buildWith(assembler))
    }

    companion object Initializer :
        Cozy.Initializer.Simple<ParameterBuilder> by cozied(::ParameterBuilder),
        Crumple<ParameterSpec, ParameterBuilder> by unstableMaybeCozyCrumple({ Initializer }, ParameterSpec::toBuilder)
}