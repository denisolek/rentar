package fleet.dto

class UpdateCarDTO(
    val manufacturer: String,
    val model: String,
    val segment: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    val registrationNumber: String
) {
    companion object {
        operator fun invoke(): UpdateCarDTO {
            val template = CarGenerator.templates.random()

            return UpdateCarDTO(
                manufacturer = template.manufacturer,
                model = template.model,
                segment = template.segment,
                productionYear = CarGenerator.productionYear,
                mileage = CarGenerator.mileage,
                transmission = template.transmission,
                fuel = CarGenerator.fuel,
                registrationNumber = CarGenerator.registrationNumber
            )
        }
    }
}
