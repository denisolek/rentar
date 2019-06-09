package fleet.dto

class ValidateAddCarDTO(val registrationNumber: String) {
    constructor(dto: AddCarDTO) :this(
        registrationNumber = dto.registrationNumber
    )
}

