package study

import study.service.RacingGameService
import study.view.InputView

fun main() {
    val racingGameService = RacingGameService()
    racingGameService.gameStart(InputView.readCarNames(), InputView.readTargetDistance())
}
