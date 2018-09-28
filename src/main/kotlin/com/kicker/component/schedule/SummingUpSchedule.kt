//package com.kicker.component.schedule
//
//import com.kicker.service.AwardService
//import com.kicker.service.DashboardRatingService
//import com.kicker.service.PlayerService
//import org.springframework.scheduling.annotation.Scheduled
//import org.springframework.stereotype.Component
//import org.springframework.transaction.annotation.Transactional
//
///**
// * @author Yauheni Efimenko
// */
//@Component
//class SummingUpSchedule(
//        private val playerService: PlayerService,
//        private val dashboardRatingService: DashboardRatingService,
//        private val awardService: AwardService
//) {
//
//    @Scheduled(cron = "0 0 0 * * MON")
//    @Transactional
//    fun summingUpForWeek() {
//        awardService.doAwardMaxRatingForWeek()
//
//        val players = playerService.getAll()
//        players.forEach { dashboardRatingService.recalculate(it) }
//
//        awardService.doAwardMaxDeltaRatingForWeek()
//    }
//
//}