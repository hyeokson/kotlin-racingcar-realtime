package study.domain

class Car(val name: String, var position: Int = 0) {
    fun move() {
        position++
        println("$name: ${"-".repeat(position)} (${position})")
    }

    fun isWinner(distance: Int): Boolean = position >= distance

}