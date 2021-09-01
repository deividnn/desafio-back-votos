# desafio-back-votos

Tecnologias Usadas:

Spring Boot
Lombok
PostgreSQL
Hibernate
Apache Kafka
Jcache
Swagger
Actuator
Maven
Junit


Configurações do PostgreSQL:

docker pull postgres
docker run -p 5432:5432 --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=desafio-voto -d postgres



Configurações do Kafka

docker pull spotify/kafka
docker run -d -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 --name kafka spotify/kafka
docker exec kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic desafio-voto
docker exec kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --list --zookeeper localhost:2181


Acesso ao Swagger
http://localhost:8080/api-desafio-voto/swagger-ui/


Acesso ao Actuator
http://localhost:8080/api-desafio-voto/actuator




1-Integração com sistemas externos
ConsultaCpfService consulta um cpf e verifca se esta apto a votar ou não.

2-Mensageria e filas
TarefaService verifica a cada 10segundos se existe sessoes em abertos e valida suas durações, caso a sessão esteja finalizada é enviado uma mensagem para o Kafka com o resultado de votos da pauta.

3-Performance
Foi configurado o cache nas entidades Pauta e Voto para minimizar o acesso direto ao banco de dados.

4-Versionamento da API
Foi definido novas versoes(v2) das operacoes nos controllers marcando as versoes antigas(v1) como depreciadas