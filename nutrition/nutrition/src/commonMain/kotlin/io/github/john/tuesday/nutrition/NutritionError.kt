package io.github.john.tuesday.nutrition

/**
 * Base error type for all [io.github.john.tuesday.nutrition]
 */
public sealed class NutritionError(override val message: String? = null, override val cause: Throwable? = null) : Throwable()

/**
 * Logical error when trying to perform an operation with two [Portion] of incompatible type;
 * that is to say, attempting to perform an operation with a [MassPortion] and a [VolumePortion]
 */
public class MismatchPortionError private constructor(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : NutritionError() {
    public constructor(a: Portion, b: Portion) : this(message = "Type ${a::class.qualifiedName} is incompatible with ${b::class.qualifiedName}")

    public constructor() : this(null, null)
}
