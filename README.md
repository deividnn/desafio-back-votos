# desafio-back-votos

Tecnologias Usadas:

Spring Boot<br/>
Lombok<br/>
PostgreSQL<br/>
Hibernate<br/>
Apache Kafka<br/>
Jcache<br/>
Swagger<br/>
Actuator<br/>
Maven<br/>
Junit<br/>


Configurações do PostgreSQL:<br/>

docker pull postgres<br/>
docker run -p 5432:5432 --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=desafio-voto -d postgres<br/>



Configurações do Kafka<br/>

docker pull spotify/kafka<br/>
docker run -d -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 --name kafka spotify/kafka<br/>
docker exec kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic desafio-voto<br/>
docker exec kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --list --zookeeper localhost:2181<br/>


Acesso ao Swagger<br/>
http://localhost:8080/api-desafio-voto/swagger-ui/<br/>


Acesso ao Actuator<br/>
http://localhost:8080/api-desafio-voto/actuator<br/>




1-Integração com sistemas externos<br/>
ConsultaCpfService consulta um cpf e verifca se esta apto a votar ou não.<br/>

2-Mensageria e filas<br/>
TarefaService verifica a cada 10segundos se existe sessoes em abertos e valida suas durações, caso a sessão esteja finalizada é enviado uma mensagem para o Kafka com o resultado de votos da pauta.<br/>

3-Performance<br/>
Foi configurado o cache nas entidades Pauta e Voto para minimizar o acesso direto ao banco de dados.<br/>

4-Versionamento da API<br/>
Foi definido novas versoes(v2) das operacoes nos controllers marcando as versoes antigas(v1) como depreciadas<br/>