# MicroserviceSpring

## Description
Projeto para estudo de microserviços com Spring Boot

## Funcionamento do RabbitMQ

1. Vamos ter os Produtores, o que vai enviar a mensagem para o RabbitMQ
2. Vamos ter os Consumidores, o que vai receber a mensagem do RabbitMQ
3. Essas mensagem ficam guardadas no rabbitMQ que é o nosso servidor de mensageria

## Funcionamento da filas
Onde as mensagem são armazenadas, que é um buffer de mensagens que pode ser configuravel dentro do RabbitMQ, onde essa configuração é definida como duravel ou não duravel
isso quer dizer, que elas se forem duraveis são persistidas no disco, e se não forem duraveis, elas são armazenadas na memoria.

Caso a escolha seja não duravel, e o servidor cair, as mensagens são perdidas.
Caso seja duravel, as mensagens são persistidas no disco e não são perdidas.
## Funcionamento do Exchange
Todas as mensagens que são enviadas para o RabbitMQ, são enviadas para um Exchange, que é um roteador de mensagens, que vai direcionar as mensagens para as filas corretas.
Mais informações sobre Exchange clique [aqui](https://www.rabbitmq.com/tutorials/amqp-concepts.html#exchanges) ou [Blog RabbitMQ](https://www.cloudamqp.com/blog/part4-rabbitmq-for-beginners-exchanges-routing-keys-bindings.html?gad_source=1&gclid=Cj0KCQjw8pKxBhD_ARIsAPrG45nKZxmVlkxKAQHh-5jZYcF6oCcn6J-VakEt7XFtBJYr9RGIf2qJt7MaAi7hEALw_wcB)

Existe 5 tipos de Exchange:
1. Direct
   - Envia a mensagem para a fila que tem o mesmo nome da routing key
2. Fanout
   - Envia a mensagem para todas as filas que estão ligadas a ele
3. Topic
    - Envia a mensagem para todas as filas que tem a routing key que corresponde a routing key da mensagem
4. Headers
    - Envia a mensagem para todas as filas que tem os headers que correspondem aos headers da mensagem
5. Default
    - Envia a mensagem para a fila default

Qual o motivo de saber os 5 tipos de Exchange?
- Cada mensagem enviado, você precisa expecificar qual argumentos o cabeçalho da mensagem vai ter, e qual Exchange vai ser utilizado.
- Qual o nome da routing key (que é o nome da fila) ?
  - routing key é o nome da fila que a mensagem vai ser enviada
  - Que funciona da seguinte maneira
  ```
  Uma troca direta entrega mensagens em filas com base em uma chave de roteamento de mensagens. 
  A chave de roteamento é um atributo da mensagem adicionado ao cabeçalho da mensagem pelo produtor.
  Pense na chave de roteamento como um “endereço” que a central está usando para decidir como encaminhar a mensagem.
  Uma mensagem vai para a(s) fila(s) com a chave de ligação que corresponde exatamente à chave de roteamento da mensagem.
  ```
  - Qual Exchange vai ser utilizado?
    - payload fanout
      ```
         # Envia a mensagem para todas as filas que estão ligadas a ele, ou os consumidores que estão ligados a ele
         exchange: fanout
         routing key: ""
         body: "message"
      ```
    - payload direct
    ```
         # Envia a mensagem para a fila que tem o mesmo nome da routing key
         exchange: direct
         routing key: "fila1"
         body: "message"
    ```
     - payload topic
      ```
           # Envia a mensagem para todas as filas que tem a routing key que corresponde a routing key da mensagem
           # A diferença entre o direct e o topic, é entre a chave de roteamento, na direct você pode colocar exatamente a chave da fila
           # e no topic você pode colocar um coringa, que é o * que significa qualquer coisa 
           exchange: topic
           routing key: "fila1.*"
           body: "message"
      ```
    - payload headers
       ```
            # Envia a mensagem para todas as filas que tem os headers que correspondem aos headers da mensagem
            # O headers é muito utilizado parar envio de arquivos grandes ou pequenos, pois temos que definir.
            exchange: headers
            routing key: ""
            body: "message"
            headers: 
            key1: value1
            key2: value2
       ```
    - payload default
        ```
             # Envia a mensagem para a fila default
             exchange: default
             routing key: ""
             body: "message"
        ```
- Qual o tipo de Exchange vai ser utilizado?
  - Depende muito do que vai precisar cada tipo de payload que vai ser enviado, se é para todas as filas, se é para uma fila especifica, se é para todas as filas que tem a routing key que corresponde a routing key da mensagem, se é para todas as filas que tem os headers que correspondem aos headers da mensagem, ou se é para a fila default.
O rabbitMQ possui um banlanceador de carga, distribuindo de forma igual, ele utiliza o algoritmo round-robin, que é um algoritmo de escalonamento de processos, que distribui de forma igualitaria as mensagens para os consumidores.

## Implementação do RabbitMQ

Para acessar o rabbitMQ local execute o seguinte comando 
```
docker-compose up -d
```
Ele vai abrir o [rabbitMQ](http://localhost:15672) na porta 15672, com o usuário e senha 

## Como é criado a estrutura de pasta para microserviços ?
A estrutura de pasta é dividida em 3 partes, que são:
1. Config
   - Onde fica as configurações do projeto
2. Controller
   - Onde fica os controllers do projeto
3. Service
   - Onde fica os serviços do projeto
Primeiro precisamos criar uma arquivo chamada connection que vai ficar dentro da pasta config, que vai ser responsavel por criar a conexão com o rabbitMQ
```