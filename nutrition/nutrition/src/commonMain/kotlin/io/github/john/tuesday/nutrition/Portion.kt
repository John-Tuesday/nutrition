package io.github.john.tuesday.nutrition

import io.github.john.tuesday.measurement.*


/**
 * Portion of food, serving size
 *
 * measured in [Mass] or [Volume] (exclusive)
 */
public sealed class Portion {
    /**
     * Portion in terms of mass when not null.
     *
     * This value is mutually exclusive with [volume]. If [mass] is `null`, then [volume] must not be `null` and vice versa.
     */
    public abstract val mass: Mass?

    /**
     * Portion in terms of volume when not null.
     *
     * This value is mutually exclusive with [mass]. If [volume] is `null`, then [mass] must not be `null` and vice versa.
     */
    public abstract val volume: Volume?

    /**
     * Return this a string representation of this object
     */
    override fun toString(): String = "Portion(mass=$mass, volume=$volume)"

    /**
     * Returns true if and only if [other] is of type [Portion] and has equal [mass] and [volume]
     */
    override fun equals(other: Any?): Boolean = other is Portion && mass == other.mass && volume == other.volume

    /**
     * Returns the hashcode of the object
     */
    override fun hashCode(): Int {
        var result = mass?.hashCode() ?: 0
        result = 31 * result + (volume?.hashCode() ?: 0)
        return result
    }

    /**
     * Companion object for extensibility
     */
    public companion object
}

/**
 * Create a new [Portion] with [mass]
 */
public fun Portion(mass: Mass): Portion = MassPortion(mass)

/**
 * Create a new [Portion] with [volume]
 */
public fun Portion(volume: Volume): Portion = VolumePortion(volume)

/**
 * Fold `this` to a single value [T] by calling [onMassPortion] if `this` is [MassPortion]
 * and [onVolumePortion] if `this` is [VolumePortion]
 */
public inline fun <T> Portion.fold(onMassPortion: (Mass) -> T, onVolumePortion: (Volume) -> T): T = when (this) {
    is MassPortion -> onMassPortion(mass)
    is VolumePortion -> onVolumePortion(volume)
}

/**
 * Portion of food measured in [Mass]
 *
 * @see [Portion]
 */
public sealed class MassPortion : Portion(), Comparable<MassPortion> {
    final override val volume: Volume? = null
    abstract override val mass: Mass

    /**
     * Compares this [mass] to the [mass] of [other]
     */
    override fun compareTo(other: MassPortion): Int = mass.compareTo(other.mass)

    /**
     * Companion object for extensibility
     */
    public companion object
}

/**
 * Create a [MassPortion] with [mass]
 */
public fun MassPortion(mass: Mass = 0.grams): MassPortion = MassPortionImpl(mass = mass)

/**
 * Portion of food measured in [Volume]
 *
 * @see [Portion]
 */
public sealed class VolumePortion : Portion(), Comparable<VolumePortion> {
    final override val mass: Mass? = null
    abstract override val volume: Volume

    /**
     * Compares this [volume] to the [volume] of [other]
     */
    override fun compareTo(other: VolumePortion): Int = volume.compareTo(other.volume)

    /**
     * Companion object for extensibility
     */
    public companion object
}

/**
 * Create a [VolumePortion] with [volume]
 */
public fun VolumePortion(volume: Volume = 0.liters): VolumePortion = VolumePortionImpl(volume = volume)

/**
 * Basic implementation of [MassPortion]
 */
internal class MassPortionImpl(
    override val mass: Mass,
) : MassPortion()

/**
 * Basic implementation of [VolumePortion]
 */
internal class VolumePortionImpl(
    override val volume: Volume,
) : VolumePortion()

/**
 * Create a [MassPortion] whose [MassPortion.mass] is equal to the sum of mass in `this` and [other]
 */
public operator fun MassPortion.plus(other: MassPortion): MassPortion = MassPortion(mass = mass + other.mass)

/**
 * Create a [VolumePortion] with [VolumePortion.volume] equal to the sum of volume in `this` and [other]
 */
public operator fun VolumePortion.plus(other: VolumePortion): VolumePortion =
    VolumePortion(volume = volume + other.volume)

/**
 * Create a [Portion] whose volume or mass is equal to the sum of `this` and [other]. `this` and [other] should both be
 * defined in the same measurement type; specifically, both are [MassPortion] or both are [VolumePortion]
 *
 * @throws [MismatchPortionError] if `this` and [other] are not the same type, i.e. [MassPortion] or [VolumePortion]
 */
public operator fun Portion.plus(other: Portion): Portion = when {
    this is MassPortion && other is MassPortion -> MassPortion(mass = mass + other.mass)
    this is VolumePortion && other is VolumePortion -> VolumePortion(volume = volume + other.volume)
    else -> throw (MismatchPortionError(this, other))
}

/**
 * Create a [MassPortion] with mass equal to `this.mass - other.mass`
 */
public operator fun MassPortion.minus(other: MassPortion): MassPortion = MassPortion(mass = mass - other.mass)

/**
 * Create a [VolumePortion] with volume equal to `this.volume - other.volume`
 */
public operator fun VolumePortion.minus(other: VolumePortion): VolumePortion =
    VolumePortion(volume = volume - other.volume)

/**
 * Create a [Portion] whose volume or mass is equal to `this` subtracted by [other]. `this` and [other] should both be
 * defined in the same measurement type; specifically, both are [MassPortion] or both are [VolumePortion]
 *
 * @throws [MismatchPortionError] if `this` and [other] are not the same type, i.e. [MassPortion] or [VolumePortion]
 */
public operator fun Portion.minus(other: Portion): Portion = when {
    this is MassPortion && other is MassPortion -> MassPortion(mass = mass - other.mass)
    this is VolumePortion && other is VolumePortion -> VolumePortion(volume = volume - other.volume)
    else -> throw (MismatchPortionError(this, other))
}

/**
 * Negate `this`
 */
public operator fun MassPortion.unaryMinus(): MassPortion = MassPortion(mass = -mass)

/**
 * Negate `this`
 */
public operator fun VolumePortion.unaryMinus(): VolumePortion = VolumePortion(volume = -volume)

/**
 * Negate `this`
 */
public operator fun Portion.unaryMinus(): Portion = when (this) {
    is MassPortion -> unaryMinus()
    is VolumePortion -> unaryMinus()
}

/**
 * Return [MassPortion] with positive [MassPortion.mass]
 */
public val MassPortion.absoluteValue: MassPortion get() = MassPortion(mass.absoluteValue)

/**
 * Return [VolumePortion] with positive [VolumePortion.volume]
 */
public val VolumePortion.absoluteValue: VolumePortion get() = VolumePortion(volume.absoluteValue)

/**
 * Return [Portion] with positive [Portion.mass] or [Portion.volume]. The other stays `null`
 */
public val Portion.absoluteValue: Portion
    get() = when (this) {
        is MassPortion -> absoluteValue
        is VolumePortion -> absoluteValue
    }

/**
 * Find the ratio between `this` to [other]
 */
public operator fun MassPortion.div(other: MassPortion): Double = mass / other.mass

/**
 * Find the ratio between `this` to [other]
 */
public operator fun VolumePortion.div(other: VolumePortion): Double = volume / other.volume

/**
 * Find the ratio between `this` to [other]
 */
public operator fun Portion.div(other: Portion): Double = when {
    this is MassPortion && other is MassPortion -> div(other)
    this is VolumePortion && other is VolumePortion -> div(other)
    else -> throw (MismatchPortionError(this, other))
}

/**
 * Scales `this` by [number]
 */
public operator fun MassPortion.times(number: Number): MassPortion = MassPortion(mass = mass * number)

/**
 * Scales `this` by [number]
 */
public operator fun VolumePortion.times(number: Number): VolumePortion = VolumePortion(volume * number)

/**
 * Scales `this` by [number]
 */
public operator fun Portion.times(number: Number): Portion = when (this) {
    is MassPortion -> times(number)
    is VolumePortion -> times(number)
}

/**
 * Scales [portion] by `this`
 */
public operator fun Number.times(portion: MassPortion): MassPortion = portion * this

/**
 * Scales [portion] by `this`
 */
public operator fun Number.times(portion: VolumePortion): VolumePortion = portion * this

/**
 * Scales [portion] by `this`
 */
public operator fun Number.times(portion: Portion): Portion = portion * this

/**
 * Shrinks `this` by [number]
 */
public operator fun MassPortion.div(number: Number): MassPortion = MassPortion(mass = mass / number)

/**
 * Shrinks `this` by [number]
 */
public operator fun VolumePortion.div(number: Number): VolumePortion = VolumePortion(volume / number)

/**
 * Shrinks `this` by [number]
 */
public operator fun Portion.div(number: Number): Portion = when (this) {
    is MassPortion -> div(number)
    is VolumePortion -> div(number)
}
