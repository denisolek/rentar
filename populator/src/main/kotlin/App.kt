import management.ManagementFacade

object Setup {
    const val managementHost = "http://localhost:8080"
}
fun main(){
    ManagementFacade().addCustomers(1000)
}