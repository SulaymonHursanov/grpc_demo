package com.demo.server;

import com.demo.EchoServiceGrpc;
import com.demo.GrpcEcho;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class AppServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 5003;
        Server server = ServerBuilder
                .forPort(port)
                .addService(new HelloServiceImpl())
                .addService(new NotificationServiceServer())
                .build();
        server.start();
        System.out.println("Started Server on port: " + port);
        server.awaitTermination();
    }
}

class HelloServiceImpl extends EchoServiceGrpc.EchoServiceImplBase {
    @Override
    public void getEcho(GrpcEcho.EchoRequest request, StreamObserver<GrpcEcho.EchoResponse> responseObserver) {
        String message = request.getMessage();
        System.out.println("Consumed message: "+ message );
        GrpcEcho.EchoResponse build = GrpcEcho.EchoResponse
                .newBuilder()
                .setMessage(message)
                .build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }
}

