package management.dto

import java.util.*

class BaseCustomer(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val drivingLicence: String
)