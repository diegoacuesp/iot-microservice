# IOT-Backend Microservice
Este repoitorio es la primera versión del desarrollo realizado para un cliente con el cual se desplego un sistema IOT, cumpliendo la 
necesidad de desarrollo backend IOT para su sistema.

# Topicos y tecnologias aplicadas
- Spring boot
- POO Java
- API Rest y endpoints
- Microservicios
- Spring Cloud - API Gateway
- JPA + Hibernate
- WebFlux
- Eureka
- Base de datos SQL PostgreSQL
- Postman
- Git
- MQTT
- Node Red

# Arquitectura
En esta primera version se tienen los servicios dispositivo y facturacion.

# dispositivo
Contiene el controlador que va a recibir información directamente por un metodo POST según los siguientes campos utilizando JSON
- nombreVariable
- numeroSerie
- estampaTiempo
- valorVariable

Luego de ello se procesa y almacena en una base Postgres.

Contiene el cocomponent que recibe información mediante el protocolo MQTT Asincrono utilizado para transmitir información por topicos en formato JSON, este protocolo es capas de
 enviar millones de datos para millones de dispositivos de forma asincrona y eficiente. Se tienen los campos mencionados anteriormente.
       
Luego de ello se procesa y almacena en una base Postgres.

Contiene el controlador que va a enviar información utilizando WebFlux hacia el servicio Facturación de forma asincrona no bloqueante.
Los campos que se reciben son:
- String nombreVariable,
- Long findUltimoIdDispositivo

La data que se envia tiene los siguientes campos
- idDispositivo,
- nombreVariable,
- numeroSerie,
- estampaTiempo,
- valorVariable

# facturacion
Se encarga de solicitar cada cierto tiempo la data nueva que es recolectada por el servicio dispositivo, filtrando y solicitando solo las variables necesarias para facturación del sistema,
almacenandolas en una base de datos Postgres y luego de ello se realizan filtros y procesamiento formando tablas por variables separadas.

- Se tiene un Servicio que solicita la información tulizando WebFlux y Streams para colectar la información desde el servicio dispositivo.
- Se tiene un Scheduler para programar la ejecución de la recopilación según demanda programada, por ejemplo cuando exista menor congestión del ancho de banda.
- Se tiene un Servicio para la formación de tablas dinámicas en la base de datos Postgres.

Por ejemplo solo se recolectaran voltaje y corriente de dispositivo. Luego en facturación se formaron tablas dinámicas independientes y filtradas para su uso por el cliente.
