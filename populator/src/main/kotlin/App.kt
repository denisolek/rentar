import fleet.FleetFacade
import management.ManagementFacade
import rental.RentalFacade

object Setup {
    const val apiHost = "https://api.rentar.eu"
    const val requestInterval = 0
    const val customersCount = 0
    const val carCount = 0
    const val rentalsCount = 100
}

fun main() {
    val createdCustomers = ManagementFacade().addCustomers(Setup.customersCount)
    ManagementFacade().updateCustomers(createdCustomers)
    val cars = FleetFacade().addCars(Setup.carCount)
    FleetFacade().updateCars(cars)
    val allCustomers = ManagementFacade().getAll()
    val rentals = RentalFacade().createRentals(Setup.rentalsCount, allCustomers)
    RentalFacade().cancelRentals(rentals)
}