# Boa Entrega
Trabalho de Conclusão de Curso (TCC) do curso de especialização em Arquitetura de Software Distribuído pela PUC Minas.

## O que é?
Esta aplicação se trata da PoC do Módulo de Informações Cadastrais, implementando os seguintes casos de uso:
- **Cliente** -> Atualizar detalhes de seu endereço para futuras entregas
- **Fornecedor** -> Gerar API Key para ser utilizada em integrações
- **Fornecedor** -> Gerir cadastro de depósitos

O sistema está implementado com a seguinte divisão:
- Customer microservice: Serviço de gestão de dados de clientes, implementado em **Java + PostgresSQL**.
- Supplier microservice: Serviço de gestão de dados de fornecedores, implementado em **Java + PostgresSQL**.
- Auth service: Gestão de acesso as aplicações, utilizando **Keycloak**.
- API Gateway: Gerenciador de API, utilizando **Kong**.

## Como executar?

### Requisitos:
- [Docker](https://www.docker.com)
- [Docker Compose](https://docs.docker.com/compose/)
- [Postman](https://www.postman.com)

Adicionalmente, caso haja necessidade de executar a aplicação Java na máquina, é necessário ter o SDKMAN instalado, para fazer a gestão da versão do Java na máquina. 
Para aplicar a versão do Java/Gradle, basta entrar no caminho [`/backend/`](./backend) e digitar `sdk env`.

Além disso, são utilizadas as seguintes portas:
- 5432: PostgreSQL
- 8000: API Gateway Kong
- 8001: Admin API Kong
- 8002: Admin GUI Kong
- 8080: Keycloak
- 8081: Customer microservice
- 8082: Supplier microservice

### Execução

Primeiramente, importe o arquivo [BoaEntrega.postman_collection.json](./BoaEntrega.postman_collection.json) para seu Postman. Todas as variáveis de ambiente já estão configuradas na raiz da collection. Caso tenha dúvidas de como importar, consulte o [guia oficial](https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman).

Para executar a aplicação, digite o seguinte comando no terminal:

```shell
make
```

Ou, caso prefira:

```shell
docker compose up --build --detach
```

A execução do comando pode levar alguns minutos, o tempo de realizar download de todas as imagens e de executar build de todos Dockerfiles.

Pronto! A aplicação já está em execução.

As rotas são as seguintes:
- **API Gateway (Kong):** http://localhost:8000
  - **Customer:** 
    - **APIs:** /api/customer/*
    - **Swagger:** [/customer/swagger-ui.html](http://localhost:8000/customer/swagger-ui.html)
  - **Supplier:**
      - **APIs:** /api/supplier/*
      - **Swagger:** [/supplier/swagger-ui.html](http://localhost:8000/supplier/swagger-ui.html)
  - **Auth (Keycloak):** /realm/master/*
- **Admin Kong:** http://localhost:8002/default/dashboard
- **Admin Keycloak:** http://localhost:8080/admin/master/console/#/realms/master

Por motivos de brevidade, os usuários a serem utilizados já estão pré-criados no serviço de autenticação (Keycloak). São os seguintes (formato "user / password"):
- Perfil cliente:
  - cliente1 / 123456
  - cliente2 / 123456
- Perfil fornecedor 
  - fornecedor1 / 123456 
  - fornecedor2 / 123456
- Admin Keycloak 
  - admin / admin

Com exceção das GUI administrativas e do Swagger, todo o fluxo de requisições da aplicação está mapeado no arquivo do Postman, com o uso bem simplificado.

Para parar a execução da aplicação, digite:

```shell
make down
```

Ou, caso prefira:

```shell
docker compose down  --remove-orphans --volumes
```

### Problemas conhecidos
>Valid top-level sections for this Compose file are: version, services, networks, volumes, and extensions starting with "x-".

Caso a aplicação apresente esse erro ao executar o Docker Compose, atualize a versão para, no mínimo, v1.27.4, como pode ser visto [aqui](https://stackoverflow.com/a/65018993).
