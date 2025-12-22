package com.events;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.context.annotation.Context;
import io.micronaut.rabbitmq.connect.ChannelInitializer;

import java.io.IOException;

@Context
public class QueueBindingCreator extends ChannelInitializer {

    @Override
    public void initialize(Channel channel, String name) throws IOException {
        channel.exchangeDeclare("event", BuiltinExchangeType.TOPIC);
        channel.queueDeclare("events.main", true, false, false, null);
        channel.queueBind("events.main", "event", "events.main");
    }
}
