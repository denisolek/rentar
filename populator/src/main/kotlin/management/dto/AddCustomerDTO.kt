package management.dto

import com.google.gson.annotations.SerializedName

class AddCustomerDTO(
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
        operator fun invoke(): AddCustomerDTO {
            val firstName = CustomerGenerator.firstName
            val lastName = CustomerGenerator.lastName
            val email = CustomerGenerator.email(firstName, lastName)
            val phoneNumber = CustomerGenerator.phoneNumber
            val birthDate = CustomerGenerator.birthDate
            val drivingLicence = CustomerGenerator.drivingLicence
            val passport = CustomerGenerator.passport

            return AddCustomerDTO(
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