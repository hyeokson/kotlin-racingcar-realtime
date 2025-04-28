package study.view

object InputView {
    fun readCarNames(): List<String> {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
        val input = readlnOrNull() ?: ""
        return input.split(",")
    }

    fun readTargetDistance(): Int {
        println("목표 거리를 입력하세요.")
        return readlnOrNull()?.toInt() ?: 0
    }

}
