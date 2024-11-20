package com.new2310.preproject.service;

import com.new2310.preproject.dto.Client;
import com.new2310.preproject.repositiry.ClientRepository2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    @Autowired
    private ClientRepository2 clientRepository2;

    @Value("${topic.first.name}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> msgKafkaTemplate;

    public void sendMessageToTopic() {
        Properties prop = new Properties();
        try (InputStream input = KafkaProducerService.class
                .getClassLoader().getResourceAsStream("discount.properties")) {
            if (input == null) {
                System.out.println("не нашел конфиг");
                return;
            }
            prop.load(input);
            String discount = prop.getProperty("discount");     //настроил значение скидки

            List<Client> list = clientRepository2.findAll();
            for (Client client : list) {
                String msg = "phone: " + client.getPhone() + "\n" +
                        "message: " + client.getFullName() + ", в этом месяце для вас действует скидка " +
                        discount + " %";
                if (client.isMessageSend() == false) {
                    msgKafkaTemplate.send(topic, msg);
                    client.setMessageSend(true);            //меняю на тру в messageSend
                    clientRepository2.save(client);         //сохраняю изменения в бд

                    System.out.println(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
