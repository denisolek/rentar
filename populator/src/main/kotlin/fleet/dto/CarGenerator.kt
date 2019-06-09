package fleet.dto

import org.apache.commons.lang3.RandomStringUtils
import kotlin.random.Random

object CarGenerator {
    val productionYear: Int
        get() = (2010..2019).random()

    val mileage: Int
        get() = (10000..140000).random()

    val fuel: String
        get() = listOf("PETROL", "DIESEL", "ELECTRICITY").random()

    val registrationNumber: String
        get() = "PO ${RandomStringUtils.randomNumeric(2)}${RandomStringUtils.randomAlphanumeric(3).toUpperCase()}"

    class CarTemplate(val manufacturer: String, val model: String, val segment: String, val transmission: String)

    val templates = listOf(
        CarTemplate("Fiat", "500", "A", "MANUAL"),
        CarTemplate("Fiat", "Panda", "A1", "MANUAL"),
        CarTemplate("Ford", "Firsta", "B", "MANUAL"),
        CarTemplate("Nissan", "Micra", "B", "MANUAL"),
        CarTemplate("Opel", "Corsa", "B", "MANUAL"),
        CarTemplate("Volkswagen", "Polo", "B", "MANUAL"),
        CarTemplate("Fiat", "Punto", "B", "MANUAL"),
        CarTemplate("Citroen", "C3", "B", "MANUAL"),
        CarTemplate("Lancia", "Ypsilon", "B", "MANUAL"),
        CarTemplate("Toyota", "Yaris", "B2", "MANUAL"),
        CarTemplate("Ford", "Focus", "C", "MANUAL"),
        CarTemplate("Fiat", "Tipo", "C", "MANUAL"),
        CarTemplate("Citroen", "C4", "C", "MANUAL"),
        CarTemplate("Volkswagen", "Golf", "C1", "MANUAL"),
        CarTemplate("Peugeot", "108", "D2", "AUTOMATIC"),
        CarTemplate("Fiat", "500L", "E", "MANUAL"),
        CarTemplate("Fiat", "Tipo", "E", "MANUAL"),
        CarTemplate("Nissan", "Pulsar", "E", "MANUAL"),
        CarTemplate("Kia", "Ceed", "E", "MANUAL"),
        CarTemplate("Peugeot", "308", "E", "MANUAL"),
        CarTemplate("Alfa Romeo", "Giulietta", "E", "MANUAL"),
        CarTemplate("Fiat", "Doblo", "E1", "MANUAL"),
        CarTemplate("Nissan", "Juka", "E2", "MANUAL"),
        CarTemplate("Peugeot", "2008", "E2", "MANUAL"),
        CarTemplate("Opel", "Mokka", "E2", "MANUAL"),
        CarTemplate("Fiat", "500 X", "E2", "MANUAL"),
        CarTemplate("Ford", "Ecosport", "E2", "MANUAL"),
        CarTemplate("Ford", "Focus", "F1", "AUTOMATIC"),
        CarTemplate("Peugeot", "508", "G1", "AUTOMATIC"),
        CarTemplate("Audi", "A3 Cabrio", "G3", "AUTOMATIC")
    )
}