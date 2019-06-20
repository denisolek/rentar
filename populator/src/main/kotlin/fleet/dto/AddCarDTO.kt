package fleet.dto

class AddCarDTO(
    val manufacturer: String,
    val model: String,
    val segment: String,
    val productionYear: Int,
    val mileage: Int,
    val transmission: String,
    val fuel: String,
    val registrationNumber: String,
    val dailyPrice: Int
) {
    companion object {
        operator fun invoke(): AddCarDTO {
            val template = CarGenerator.templates.random()

            return AddCarDTO(
                manufacturer = template.manufacturer,
                model = template.model,
                segment = template.segment,
                productionYear = CarGenerator.productionYear,
                mileage = CarGenerator.mileage,
                transmission = template.transmission,
                fuel = CarGenerator.fuel,
                registrationNumber = CarGenerator.registrationNumber,
                dailyPrice = CarGenerator.dailyPrice
            )
        }
    }
}
