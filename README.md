# wekateste

* Projeto esta separado em dois pacotes (client e server)

* para testar o sistema se faz necessário a criação de um banco de dados,
o arquivo de configuração do mesmo esta em "\weka-test\src\main\resources\application.properties"

* No servidor há três métodos passíveis de acesso

1º setNumOfClusters -> acesso por "/api/num_clusters/{numOfClusters}" (GET)
 apenas recebe a quantidade de clusters para facilitar a configuração do algoritmo SimpleKMeans
caso o mesmo não tiver valor atribuido o algoritmo ira deixar o valor padrão (2).

2º findAll -> acesso por /api/all" (GET)
Retorna todos os registros de eventos existentes no banco de dados

3º  saveAndProcess -> acesso por "/api/add_sensor_record/" (POST)
**(Usar metodo "ClientMain" para consumit esse serviço)**
Persiste novo evento no banco de dados,
Quando existir 1000 ou mais eventos os mesmos são processados pelo algoritmo SimpleKmeans disponível na API Weka
Após processar os dados é adicionado um objeto de resultado, o mesmo é transformado em um arquivo JSON e retornado ao cliente
contedo os clusters e o número de eventos vinculados a cada um. 

Qualquer dúvida entrar em contato através do e-mail
gutogorgen@gmail.com
