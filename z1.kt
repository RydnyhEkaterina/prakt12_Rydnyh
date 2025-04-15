import kotlinx.coroutines.*
import java.util.*
suspend fun getData(taskIndex: Int): String {
    delay(800L)
    return "Полученные данные от задания $taskIndex"
}
fun main() {
    println("Введите количество задач (m):")
    val userInput = readLine()
    if (userInput == null || userInput.isEmpty() || userInput.toIntOrNull() == null || userInput.toInt() <= 0) {
        println("Неверный ввод. Введите положительное число.")
        return
    }
    val taskCount = userInput.toInt()
    println("Запуск $taskCount корутин...")
    val outputData = Array(taskCount) { "" }
    runBlocking {
        for (index in 1..taskCount) {
            launch {
                val data = getData(index)
                outputData[index - 1] = data
            }
        }
    }
    println("Результаты выполнения:")
    for (data in outputData) {
        println(data)
    }
}
