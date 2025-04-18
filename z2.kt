import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    while (true) {
        println("Введите имя организации (или 'БЛОКИРОВКА' для выхода):")
        val orgName = readLine() ?: break
        if (orgName.uppercase() == "БЛОКИРОВКА") break

        println("Введите количество репозиториев:")
        val repoCount = readLine()?.toIntOrNull() ?: 0
        val repos = List(repoCount) { index ->
            println("Введите владельца репозитория ${index + 1}:")
            val owner = readLine() ?: "Неизвестный"
            Repo(Owner(owner))
        }
        val counts = repos.groupingBy { it.owner.login }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }

        println("\nУчастники и количество их репозиториев:")
        counts.forEach { (owner, count) -> println("$owner: $count") }
        println()
    }
    println("Выход из программы.")
}
data class Repo(val owner: Owner)
data class Owner(val login: String)