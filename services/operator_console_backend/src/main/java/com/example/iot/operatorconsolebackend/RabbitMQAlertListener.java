package com.example.iot.operatorconsolebackend;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQAlertListener {

    private final OperatorWebSocketHandler webSocketHandler;
    private final PendingAlertStore pendingAlertStore;

    public RabbitMQAlertListener(
            OperatorWebSocketHandler webSocketHandler,
            PendingAlertStore pendingAlertStore) {

        this.webSocketHandler = webSocketHandler;
        this.pendingAlertStore = pendingAlertStore;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "operator_alerts_queue", durable = "true"),
            exchange = @Exchange(value = "human_alerts", type = "fanout", durable = "true")))
    public void receiveAlert(String message) {

        System.out.println(
                "[RABBITMQ] Alerta recibida: " + message
        );

        // Guardar la alerta temporalmente
        pendingAlertStore.addAlert(message);

        // Enviar la alerta a los operadores conectados
        webSocketHandler.broadcastAlert(message);
    }
}
