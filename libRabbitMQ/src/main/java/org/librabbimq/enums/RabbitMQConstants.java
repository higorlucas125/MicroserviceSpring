package org.librabbimq.enums;


public enum RabbitMQConstants {
        QUEUE_STOCK("ESTOQUE"),
        QUEUE_PRICE("PRECO");

        private final String value;

        RabbitMQConstants(String value) {
                this.value = value;
        }

        public String getValue() {
                return value;
        }
}
