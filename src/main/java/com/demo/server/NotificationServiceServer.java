package com.demo.server;

import com.semi.NotificationServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import static com.semi.NotificationServ.*;

@Slf4j
public class NotificationServiceServer extends NotificationServiceGrpc.NotificationServiceImplBase {

    @Override
    public void sendNotification(Notification request, StreamObserver<NotificationResponse> responseObserver) {
        log.info("got new notification: {}", request);
        responseObserver.onNext(NotificationResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
