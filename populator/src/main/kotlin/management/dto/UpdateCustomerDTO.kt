package management.dto

import com.google.gson.annotations.SerializedName

class UpdateCustomerDTO(
    val firstName: String,
    val lastName: String,
    @SerializedName("email")
    val email: String,
    val birthDate: String,
    val phoneNumber: String,
    val drivingLicence: String,
    val passport: String
) {
    companion object {
        operator fun invoke(): UpdateCustomerDTO {
            val firstName = CustomerGenerators.firstName
            val lastName = CustomerGenerators.lastName
            val email = CustomerGenerators.email(firstName, lastName)
            val phoneNumber = CustomerGenerators.phoneNumber
            val birthDate = CustomerGenerators.birthDate
            val drivingLicence = CustomerGenerators.drivingLicence
            val passport = CustomerGenerators.passport

            return UpdateCustomerDTO(
                firstName = firstName,
                lastName = lastName,
                email = email,
                birthDate = birthDate,
                phoneNumber = phoneNumber,
                drivingLicence = drivingLicence,
                passport = passport
            )
        }
    }
}