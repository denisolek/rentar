import fleet.FleetFacade
import management.ManagementFacade
import rental.RentalFacade
import java.util.*
import kotlin.concurrent.schedule

object Setup {
    const val apiHost = "https://api.rentar.eu"
    const val requestInterval = 0
    const val customersCount = 200
    const val carCount = 200
    const val rentalsCount = 200
}

fun main() {
    generateCustomers()
    generateCars()
    generateRentals()
}

private fun generateCustomers() {
    Thread {
        val createdCustomers = ManagementFacade().addCustomers(Setup.customersCount)
        ManagementFacade().updateCustomers(createdCustomers)
    }.start()
}

private fun generateCars() {
    Thread {
        val cars = FleetFacade().addCars(Setup.carCount)
        FleetFacade().updateCars(cars)
    }.start()
}

private fun generateRentals() {
    Timer().schedule(10000) {
        val allCustomers = ManagementFacade().getAll()
        val rentals = RentalFacade().createRentals(Setup.rentalsCount, allCustomers)
        RentalFacade().cancelRentals(rentals)
    }
}