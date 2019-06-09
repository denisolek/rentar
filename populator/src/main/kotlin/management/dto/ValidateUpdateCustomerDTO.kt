package management.dto

class ValidateUpdateCustomerDTO(
    val email: String,
    val drivingLicence: String,
    val passport: String
) {
    constructor(dto: UpdateCustomerDTO) : this(
        email = dto.email,
        drivingLicence = dto.drivingLicence,
        passport = dto.passport
    )
}