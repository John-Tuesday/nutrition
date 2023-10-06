package io.github.john.tuesday.nutrition

sealed class NutritionError(override val message: String? = null, override val cause: Throwable? = null) : Throwable()
class MismatchPortionError private constructor(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : NutritionError() {
    constructor(a: Portion, b: Portion) : this(message = "Type ${a::class.qualifiedName} is incompatible with ${b::class.qualifiedName}")
    constructor() : this(null, null)
}
