package management.dto

import com.google.i18n.phonenumbers.PhoneNumberUtil
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.StringUtils
import java.time.LocalDate
import java.util.concurrent.ThreadLocalRandom

object CustomerGenerator {
    val firstName: String
        get() = firstNames.random()

    val lastName: String
        get() = lastNames.random()

    fun email(firstName: String, lastName: String): String =
        "${StringUtils.stripAccents(firstName.toLowerCase())}.${StringUtils.stripAccents(lastName.toLowerCase())}@${emails.random()}"

    val birthDate: String
        get() = LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(date["min"]!!, date["max"]!!)).toString()

    val phoneNumber: String
        get() = generatePhoneNumber()

    val drivingLicence: String
        get() = "${numeric(5)}/${numeric(2)}/${numeric(4)}"

    val passport: String
        get() = "${alphabetic(2).toUpperCase()}${numeric(7)}"

    private fun generatePhoneNumber(): String {
        var isValid = false
        val phoneUtil = PhoneNumberUtil.getInstance()
        var candidate: String
        var number = ""
        while (!isValid) {
            candidate = "+48${(500..800).random()}${(500..600).random()}${(100..999).random()}"
            val numberProto = phoneUtil.parse(candidate, "PL")
            if (phoneUtil.isValidNumber(numberProto)) {
                isValid = true
                number = "+${numberProto.countryCode}${numberProto.nationalNumber}"
            }
        }
        return number
    }

    private val date = hashMapOf(
        "min" to LocalDate.of(1960, 1, 1).toEpochDay(),
        "max" to LocalDate.of(2000, 12, 31).toEpochDay()
    )

    private fun numeric(count: Int) = RandomStringUtils.randomNumeric(count)
    private fun alphabetic(count: Int) = RandomStringUtils.randomAlphabetic(count)

    private val firstNames = listOf(
        "Anna",
        "Piotr",
        "Maria",
        "Krzysztof",
        "Katarzyna",
        "Andrzej",
        "Małgorzata",
        "Jan",
        "Agnieszka",
        "Stanisław",
        "Barbara",
        "Tomasz",
        "Krystyna",
        "Paweł",
        "Ewa",
        "Marcin",
        "Elżbieta",
        "Michał",
        "Zofia",
        "Marek",
        "Teresa",
        "Grzegorz",
        "Magdalena",
        "Józef",
        "Joanna",
        "Łukasz",
        "Janina",
        "Adam",
        "Monika",
        "Zbigniew",
        "Danuta",
        "Jerzy",
        "Jadwiga",
        "Tadeusz",
        "Aleksandra",
        "Mateusz",
        "Halina",
        "Dariusz",
        "Irena",
        "Mariusz",
        "Beata",
        "Wojciech",
        "Marta",
        "Ryszard",
        "Dorota",
        "Jakub",
        "Helena",
        "Henryk",
        "Karolina",
        "Robert",
        "Grażyna",
        "Rafał",
        "Jolanta",
        "Kazimierz",
        "Iwona",
        "Jacek"
    )

    private val lastNames = listOf(
        "Nowak",
        "Kowalski",
        "Wiśniewski",
        "Wójcik",
        "Kowalczyk",
        "Kamiński",
        "Lewandowski",
        "Zieliński",
        "Szymański",
        "Woźniak",
        "Dąbrowski",
        "Kozłowski",
        "Jankowski",
        "Mazur",
        "Wojciechowski",
        "Kwiatkowski",
        "Krawczyk",
        "Kaczmarek",
        "Piotrowski",
        "Grabowski",
        "Zając",
        "Pawłowski",
        "Michalski",
        "Król",
        "Wieczorek",
        "Jabłoński",
        "Wróbel",
        "Nowakowski",
        "Majewski",
        "Olszewski"
    )

    private val emails = listOf("wp.pl", "gmail.com", "yahoo.com", "onet.pl")
}