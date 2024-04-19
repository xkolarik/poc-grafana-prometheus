# POC de Observabilidade Grafana [Metricas, Logs e Traces]

## Arquitetura da Aplicação

![Arquitetura da Aplicação](/arquitetura.png "Arquitetura da Aplicação")

Descrição dos componentes:
* **Employee Service**: Aplicação em Spring-Boot com banco em memória que expõe os endpoints `/employees` e `/employees/{id}`. Cada resultado de employee é enriquecido com as informaçãoes do serviço Child Service.
* **Child Service**: Aplicação em Spring-Boot que retorna um número randômico de dependentes, sempre entre 0 e 5. O serviço expõe o endpoints `/employees/{id}/children`.
* **OTel Agent**: Agente que roda juntamente com os serviços acima para coletar dados de temeletria e enviar ao coletor.
* **OTel Collector**: Coletor de dados do Open Telemetry de métricas, logs e traces a partir dos serviços descritos acima.
* **Grafana Loki**, Promtail**: Serviço backend responsável por armazenar dados de logs.
* **Grafana Prometheus**: Serviço backend responsável por armazenar dados de métricas, (CPU, Memória, Largura de banda, etc.) e as envia para o grafana.
* **Jaeger**: Serviço backend responsável por armazenar dados de tracing.
* **Grafana**: UI utilizada para realizar consultas e agregações nos dados dos serviços de tracing, logs e métricas.

## Instruções de Execução

Para executar a aplicação será necessário ter instalado na máquina local as seguintes ferramentas:
* Java, na versão 11 ou superior
* Maven
* Docker / Podman
* Podman-compose (no caso de estar usando Podman)

### Passo 1: Gerar jars dos serviços Spring-Boot

```bash
$ cd ./employee-service
$ mvn clean package install
$ cd ..
```

```bash
$ cd ./child-service
$ mvn clean package install
$ cd ..
```

Os comandos devem mostrar que executaram com sucesso e devem gerar dentro da pasta `./target/` de cada serviço o jar correspondente.

### Passo 2: Subir a stack localmente com Compose

* Caso esteja usando Docker:

```bash
$ docker compose up
```

### Passo 3: Realizar requests

Sugiro utilizar alguma ferramenta para realizar requests http, como `curl` ou `Postman`. O serviço Employee Service está rodando em `http://localhost:8080`. Segue um exemplo de duas requisições usando `curl`:

```bash
$ curl --location 'http://localhost:8080/employees?page=50'
$ curl --location 'http://localhost:8080/employees/8734'
```

Após isso, basta acessar o Grafana no browser utilizando a url `http://localhost:3000` e acessar os dados de métricas, logs e traces.

## Linguagens, frameworks e ferramentas

* [Java 11](https://openjdk.org/projects/jdk/11/)
* [Spring-Boot](https://docs.spring.io/spring-boot/docs/2.7.12/reference/html/index.html)
* [Maven](https://maven.apache.org/)
* [Open Telemetry](https://opentelemetry.io/)
* [Jaeger](https://www.jaegertracing.io/)
* [Loki](https://grafana.com/oss/loki/)
* [Promtail](https://grafana.com/docs/loki/latest/send-data/promtail/configuration/)
* [Prometheus](https://prometheus.io/)
* [Grafana](https://grafana.com/)
* [Docker](https://www.docker.com/)

