package management.dto

class ValidateAddCustomerDTO(
    val email: String,
    val drivingLicence: String,
    val passport: String
) {
    constructor(dto: AddCustomerDTO) : this(
        email = dto.email,
        drivingLicence = dto.drivingLicence,
        passport = dto.passport
    )
}