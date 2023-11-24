package io.github.john.tuesday.nutrition

/**
 * Base error type for all [io.github.john.tuesday.nutrition]
 */
public sealed class NutritionError(
    /**
     * The detail message string.
     */
    override val message: String? = null,

    /**
     * The cause of this [Throwable]
     */
    override val cause: Throwable? = null,
) : Throwable()

/**
 * Logical error when trying to perform an operation with two [Portion] of incompatible type;
 * that is to say, attempting to perform an operation with a [MassPortion] and a [VolumePortion]
 */
public class MismatchPortionError private constructor(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : NutritionError() {
    /**
     * Auto-generate [message] detailing [a] and [b] as mismatched types
     */
    public constructor(
        a: Portion,
        b: Portion,
    ) : this(message = "Type ${a::class.qualifiedName} is incompatible with ${b::class.qualifiedName}")

    /**
     * [MismatchPortionError] with `null` [message] and [cause]
     */
    public constructor() : this(null, null)
}
