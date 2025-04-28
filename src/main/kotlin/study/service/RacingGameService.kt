package study.service

import kotlinx.coroutines.*
import study.domain.Car
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.random.Random

class RacingGameService {
    private val positions = ConcurrentHashMap<String, Car>()
    private val state = AtomicBoolean(true)

    fun gameStart(
        cars: List<String>,
        distance: Int,
    ) = runBlocking {

        println("\n실행 결과")

        val jobs = arrayListOf<Job>()
        for (car in cars) {
            jobs +=
                launch {
                    while (state.get()) {
                        val randomValue = Random.nextLong(501)
                        delay(randomValue)

                        if (state.get()) {
                            positions.putIfAbsent(car, Car(car))
                            val myCar = positions.get(car) ?: throw CancellationException()
                            myCar.move()

                            if (myCar.isWinner(distance)) {
                                state.set(false)
                            }
                        }
                    }
                }
        }

        jobs.joinAll()
        printWinners(distance)

    }

    private fun printWinners(distance: Int) {

        val result = positions.values.filter {
            it.isWinner(distance)
        }.map {
            it.name
        }.toList().joinToString {","}

        println()
        println("${result}가 최종 우승했습니다.")
    }
}
