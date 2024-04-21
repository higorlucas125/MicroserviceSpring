package com.microservice.learning.config.enums;


public enum Exchanges {
        EXCHANGE_DIRECT("amq.direct"),
        EXCHANGE_TOPIC("amq.topic"),
        EXCHANGE_FANOUT("amq.fanout"),
        EXCHANGE_HEADERS("amq.headers"),
        EXCHANGE_MATCH("amq.match"),
        EXCHANGE_RABBITMQ("amq.rabbitmq.trace"),
        EXCHANGE_DEFAULT("");

        private final String value;

        Exchanges(String value) {
                this.value = value;
        }

        public String getValue() {
                return value;
        }
}
