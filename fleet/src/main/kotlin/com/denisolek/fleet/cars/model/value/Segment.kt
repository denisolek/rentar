package com.denisolek.fleet.cars.model.value

import com.denisolek.fleet.cars.infrastructure.CarExceptions.InvalidSegmentException

class Segment private constructor(
    val type: SegmentType
) {
    enum class SegmentType {
        A,
        A1,
        B,
        B2,
        C,
        C1,
        D2,
        E,
        E1,
        E2,
        F1,
        G1,
        G3
    }

    companion object {
        operator fun invoke(value: String): Segment {
            val type = when (value.toUpperCase()) {
                "A" -> SegmentType.A
                "A1" -> SegmentType.A1
                "B" -> SegmentType.B
                "B2" -> SegmentType.B2
                "C" -> SegmentType.C
                "C1" -> SegmentType.C1
                "D2" -> SegmentType.D2
                "E" -> SegmentType.E
                "E1" -> SegmentType.E1
                "E2" -> SegmentType.E2
                "F1" -> SegmentType.F1
                "G1" -> SegmentType.G1
                "G3" -> SegmentType.G3
                else -> throw InvalidSegmentException()
            }
            return Segment(type)
        }
    }
}