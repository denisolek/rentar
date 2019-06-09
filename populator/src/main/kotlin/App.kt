import management.ManagementFacade

object Setup {
    const val managementHost = "http://localhost:8080"
    const val requestInterval = 15
}
fun main(){
    println("***** Adding customers *****")
    ManagementFacade().addCustomers(1000)
}