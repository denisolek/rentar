import fleet.FleetFacade
import management.ManagementFacade
import rental.RentalFacade

object Setup {
    const val managementHost = "http://localhost:9000"
    const val fleetHost = "http://localhost:9001"
    const val rentalHost = "http://localhost:9002"
    const val requestInterval = 0
    const val customersCount = 100
    const val carCount = 25
    const val rentalsCount = 300
}

fun main() {
    val customers = ManagementFacade().addCustomers(Setup.customersCount)
    ManagementFacade().updateCustomers(customers)
    val cars = FleetFacade().addCars(Setup.carCount)
    FleetFacade().updateCars(cars)
    val rentals = RentalFacade().createRentals(Setup.rentalsCount, customers.map { it.id })
    RentalFacade().cancelRentals(rentals)
}