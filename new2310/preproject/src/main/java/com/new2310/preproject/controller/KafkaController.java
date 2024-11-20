package com.new2310.preproject.controller;

import com.new2310.preproject.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Scheduled(cron = "${task.cron.expression}", zone = "${task.cron.time.zone}")
    @GetMapping("/send")
    public void sendMessageToTopicFirst() {
        kafkaProducerService.sendMessageToTopic();
    }
}
