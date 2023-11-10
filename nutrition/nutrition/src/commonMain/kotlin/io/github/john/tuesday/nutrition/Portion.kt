package io.github.john.tuesday.nutrition

import io.github.john.tuesday.measurement.*


/**
 * Portion of food, serving size
 *
 * measured in [Mass] or [Volume] (exclusive)
 */
public sealed interface Portion {
    public val mass: Mass?
    public val volume: Volume?

    public companion object {
        public operator fun invoke(mass: Mass): MassPortion = MassPortion(mass = mass)

        public operator fun invoke(volume: Volume): VolumePortion = VolumePortion(volume = volume)
    }
}

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
public sealed class MassPortion(override val mass: Mass) : Portion {
    final override val volume: Volume? = null

    public companion object {
        public operator fun invoke(mass: Mass = 0.grams): MassPortion = MassPortionImpl(mass = mass)
    }
}

/**
 * Portion of food measured in [Volume]
 *
 * @see [Portion]
 */
public sealed class VolumePortion(override val volume: Volume) : Portion {
    final override val mass: Mass? = null

    public companion object {
        public operator fun invoke(volume: Volume = 0.milliliters): VolumePortion = VolumePortionImpl(volume = volume)
    }
}

internal data class MassPortionImpl(
    override val mass: Mass,
) : MassPortion(mass = mass)

internal data class VolumePortionImpl(
    override val volume: Volume,
) : VolumePortion(volume = volume)

public operator fun MassPortion.plus(other: MassPortion): MassPortion = MassPortion(mass = mass + other.mass)
public operator fun VolumePortion.plus(other: VolumePortion): VolumePortion = VolumePortion(volume = volume + other.volume)

/**
 * Attempts to add `this` and [other]
 *
 * @throws [MismatchPortionError] if `this` and [other] are not the same type, i.e. [MassPortion] or [VolumePortion]
 */
public operator fun Portion.plus(other: Portion): Portion = when {
    this is MassPortion && other is MassPortion -> MassPortion(mass = mass + other.mass)
    this is VolumePortion && other is VolumePortion -> VolumePortion(volume = volume + other.volume)
    else -> throw (MismatchPortionError(this, other))
}

public operator fun MassPortion.minus(other: MassPortion): MassPortion = MassPortion(mass = mass - other.mass)

public operator fun VolumePortion.minus(other: VolumePortion): VolumePortion = VolumePortion(volume = volume - other.volume)

/**
 * Attempts to subtract `this` by [other]
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

public val MassPortion.absoluteValue: MassPortion get() = MassPortion(mass.absoluteValue)
public val VolumePortion.absoluteValue: VolumePortion get() = VolumePortion(volume.absoluteValue)
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
public operator fun Portion.times(number: Number): Portion = when(this) {
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
public operator fun Portion.div(number: Number): Portion = when(this) {
    is MassPortion -> div(number)
    is VolumePortion -> div(number)
}
