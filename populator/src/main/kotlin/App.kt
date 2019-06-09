import management.ManagementFacade

object Setup {
    const val managementHost = "http://localhost:8080"
    const val requestInterval = 0
}
fun main(){
    println("***** Adding customers *****")
    val customers = ManagementFacade().addCustomers(2000)
    val updatedCustomers = ManagementFacade().updateCustomers(customers)
}