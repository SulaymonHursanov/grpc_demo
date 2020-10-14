package com.demo.client;

import com.semi.NotificationServiceGrpc;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import static com.semi.NotificationServ.*;


@Slf4j
public class NotificationService {

    private final NotificationServiceGrpc.NotificationServiceBlockingStub notificationServiceClient;

    public NotificationService(ManagedChannel channel) {
        this.notificationServiceClient = NotificationServiceGrpc.newBlockingStub(channel);
    }

    public void sendNotification (String message) {
        log.debug("started notification service");
        int id = new Random().nextInt();

        Notification notificationRequest = Notification
                .newBuilder()
                .setId(id)
                .setMsg(message)
                .build();
        log.debug("creating request: {}", notificationRequest);
        NotificationResponse notificationResponse = notificationServiceClient.sendNotification(notificationRequest);
        log.info("got response of sending notification: {}", notificationResponse);
    }
}
