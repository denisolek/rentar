import fleet.FleetFacade
import management.ManagementFacade
import rental.RentalFacade

object Setup {
    const val apiHost = "https://api.rentar.eu"
    const val requestInterval = 0
    const val customersCount = 150
    const val carCount = 200
    const val rentalsCount = 400
}

fun main() {
    val customers = ManagementFacade().addCustomers(Setup.customersCount)
    ManagementFacade().updateCustomers(customers)
    val cars = FleetFacade().addCars(Setup.carCount)
    FleetFacade().updateCars(cars)
    val rentals = RentalFacade().createRentals(Setup.rentalsCount, customers.map { it.id })
    RentalFacade().cancelRentals(rentals)
}