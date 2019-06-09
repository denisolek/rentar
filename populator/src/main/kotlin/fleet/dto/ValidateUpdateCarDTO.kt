package fleet.dto

class ValidateUpdateCarDTO(val registrationNumber: String) {
    constructor(dto: UpdateCarDTO) : this(
        registrationNumber = dto.registrationNumber
    )
}
