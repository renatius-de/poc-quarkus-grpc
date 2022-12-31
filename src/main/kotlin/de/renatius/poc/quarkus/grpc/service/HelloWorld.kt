package de.renatius.poc.quarkus.grpc.service

import de.renatius.poc.quarkus.grpc.proto.Greeter
import de.renatius.poc.quarkus.grpc.proto.HelloReply
import de.renatius.poc.quarkus.grpc.proto.HelloRequest
import io.quarkus.grpc.GrpcService
import io.smallrye.mutiny.Uni

@GrpcService
class HelloWorld : Greeter {
    override fun sayHello(request: HelloRequest?): Uni<HelloReply> {
        return Uni
                .createFrom()
                .item {
                    HelloReply
                            .newBuilder()
                            .setMessage("Hello " + request!!.name)
                            .build()
                }
    }
}
