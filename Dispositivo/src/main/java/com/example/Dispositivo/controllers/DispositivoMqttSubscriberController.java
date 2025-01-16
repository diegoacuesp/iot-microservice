package com.example.Dispositivo.controllers;
import com.example.Dispositivo.model.DispositivoMapper;
import com.example.Dispositivo.model.DispositivoRequestDto;
import com.example.Dispositivo.services.DispositivoService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor

public class DispositivoMqttSubscriberController {
    private final DispositivoMapper dispositivoMapper;
    private final DispositivoService dispositivoService; // Inyectamos el servicio
    private final MqttClient mqttClient;


    public DispositivoMqttSubscriberController(MqttClient mqttClient, DispositivoMapper dispositivoMapper, DispositivoService dispositivoService) {
        this.dispositivoMapper = dispositivoMapper;
        this.dispositivoService = dispositivoService;
        this.mqttClient = mqttClient;
        try {
            String topic = "iot/sensors4387t5"; // Tópico al que deseas suscribirte

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Conexión perdida: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {

                    System.out.println("Mensaje recibido del tópico " + topic + ": " + new String(message.getPayload()));
                    // Convertir el mensaje recibido en JSON a DispositivoRequestDto

                    DispositivoRequestDto dispositivoRequestDto =
                            dispositivoMapper.jsonToDispositivoRequestDto(new String(message.getPayload()));

                    // Guardar el dispositivo en la base de datos
                    dispositivoService.guardarDispositivo(dispositivoRequestDto);

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Este metodo no se usa para clientes suscriptores
                }
            });

            mqttClient.subscribe(topic); // Suscribirse al tópico
            System.out.println("Suscrito al tópico: " + topic);

        } catch (Exception e) {
            System.err.println("Error al suscribirse al tópico: " + e.getMessage());
        }
    }

}
