package com.demo.client;

import com.demo.EchoServiceGrpc;
import com.demo.GrpcEcho;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoService {

    private final EchoServiceGrpc.EchoServiceBlockingStub echoServiceBlockingStub;

    public EchoService(ManagedChannel managedChannel) {
        this.echoServiceBlockingStub = EchoServiceGrpc.newBlockingStub(managedChannel);
    }

    public void echo () {
        log.debug("started echo service");

        GrpcEcho.EchoRequest echoRequest = GrpcEcho.EchoRequest.newBuilder()
                .setMessage("echo message")
                .build();
        log.debug("creating request: {}", echoRequest);

        GrpcEcho.EchoResponse echoResponse = echoServiceBlockingStub.getEcho(echoRequest);
        log.debug("echo response: {}", echoResponse);
    }

}
