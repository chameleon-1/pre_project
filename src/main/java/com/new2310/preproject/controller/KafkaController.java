package com.new2310.preproject.controller;

import com.new2310.preproject.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {

   private final KafkaProducerService kafkaProducerService;

    @Scheduled(cron = "${task.cron.expression}", zone = "${task.cron.time.zone}")
    public void sendMessageToTopic() {
        kafkaProducerService.sendMessageToTopic();
    }
}
