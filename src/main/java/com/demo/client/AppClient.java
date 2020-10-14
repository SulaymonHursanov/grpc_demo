package com.demo.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class AppClient {

    public static void main(String[] args) {

        int port = 5003;
        String domain = "localhost";
        ManagedChannel managedChannel = getManagedChannel(domain, port);

        NotificationService notificationService = new NotificationService(managedChannel);
        notificationService.sendNotification("test");

        EchoService echoService = new EchoService(managedChannel);
        echoService.echo();
    }

    private static ManagedChannel getManagedChannel (String host, int port) {
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
    }


}
