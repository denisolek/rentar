import fleet.FleetFacade
import management.ManagementFacade

object Setup {
    const val managementHost = "http://localhost:9000"
    const val fleetHost = "http://localhost:9001"
    const val requestInterval = 0
    const val customersCount = 200
    const val carCount = 100
}

fun main() {
    println("***** Adding customers *****")
    val customers = ManagementFacade().addCustomers(Setup.customersCount)
    val updatedCustomers = ManagementFacade().updateCustomers(customers)
    val cars = FleetFacade().addCars(Setup.carCount)
    val updatedCars = FleetFacade().updateCars(cars)
}