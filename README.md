# viridis-actives-manager
Serviço restfull simplificado para o gerenciamento de ativos industriais

Siga os passos abaixo para executar a aplicação:

1) Clone ou faça download do repositório
2) Crie o banco de dados com o nome "actives_manager"
3) Retire o comentário das linhas de de número 1 até 11 do arquivo application.properties e a preencha-as com o caminho do banco recem criado, username e password
Obs: 
* A propriedade "spring.jpa.hibernate.ddl-auto" está setada como "create-drop" para finalidade de testes. Caso deseje não apagar o banco a cada restart da aplicação altere o valor de "create-drop" para "update"

*O projeto possui scripts sql que podem ser importados através da ferramenta pgAdmin do postgresql ou pelo terminal

4) Utilize alguma ferramenta capaz de executar requisições http para executar os endpoints da aplicação
Obs: * Recomendo fortemente o uso do Postman

5) Uma documentação mais detalhada do serviço pode ser encontrada no endereço http://localhost:8080/swagger-ui.html#/ 
Obs: * No endereço os formato das requisiçoes Json que devem ser enviadas para os endpoints é apresentado

Endereços disponpiveis:

--Equipamento

GET http://localhost8080/equipment/get

GET http://localhost8080/equipment/get/{id}

POST http://localhost8080/equipment/new

--Ordem de Manutenção

GET http://localhost8080/maintenance/get

GET http://localhost8080/maintenance/get/{id}

POST http://localhost8080/maintenance/new

PUT http://localhost8080/maintenance/edit/{id}



