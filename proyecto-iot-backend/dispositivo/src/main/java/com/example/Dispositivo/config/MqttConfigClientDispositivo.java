package com.example.Dispositivo.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfigClientDispositivo {


    @Bean
    public MqttClient mqttClient() throws MqttException {
        String brokerUrl = "tcp://broker.hivemq.com:1883"; // URL del broker MQTT
        String clientId = MqttClient.generateClientId();   // Genera un ID único para el cliente
        //String username = "tu_usuario";                   // Reemplaza con tu usuario del broker
        //String password = "tu_contraseña";                // Reemplaza con tu contraseña del broker

        MqttClient client = new MqttClient(brokerUrl, clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true); // Crea una nueva sesión en cada conexión
        options.setAutomaticReconnect(true); // Reconecta automáticamente en caso de desconexión
        options.setConnectionTimeout(10); // Tiempo de espera de la conexión (en segundos)

        // Configuración de credenciales
        //options.setUserName(username);
        //options.setPassword(password.toCharArray());

        client.connect(options); // Establece la conexión

        return client;
    }
}
