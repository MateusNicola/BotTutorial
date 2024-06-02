# Controle Financeiro Bot

Este é um bot de Telegram desenvolvido em Java, projetado para ajudar os usuários a acompanhar seus gastos financeiros. O bot permite adicionar valores, verificar o total acumulado e limpar os valores armazenados.

## Funcionalidades

- **/start**: Inicia a interação com o bot e configura o total acumulado como zero.
- **/total**: Exibe o total acumulado dos valores adicionados.
- **/limpar**: Limpa os valores acumulados, redefinindo o total para zero.
- Adiciona valores numéricos enviados pelos usuários ao total acumulado.
- Valida se as mensagens enviadas são números válidos.

## Pré-requisitos

- [Java 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/)
- [Telegram Bot API](https://github.com/rubenlagus/TelegramBots)
- [dotenv-java](https://github.com/cdimascio/dotenv-java) para gerenciar variáveis de ambiente

## Configuração

1. Crie um bot no Telegram e obtenha o token da API do bot. Consulte [aqui](https://core.telegram.org/bots#6-botfather) para instruções detalhadas.
2. Crie um arquivo `.env` no diretório raiz do projeto e adicione a seguinte linha com o token do seu bot:

    ```env
    TOKEN=seu_token_do_telegram_aqui
    ```

## Como executar

1. Clone o repositório:

    ```sh
    git clone https://github.com/seu_usuario/seu_repositorio.git
    cd seu_repositorio
    ```

2. Instale as dependências com Maven:

    ```sh
    mvn clean install
    ```

3. Execute o bot:

    ```sh
    mvn exec:java -Dexec.mainClass="tutorial.Bot"
    ```

## Estrutura do Código

- **getBotUsername**: Retorna o nome do bot.
- **getBotToken**: Carrega o token do bot a partir do arquivo `.env`.
- **onUpdateReceived**: Trata as mensagens recebidas pelo bot.
  - `/start`: Inicia a interação e configura o total acumulado para zero.
  - `/total`: Exibe o total acumulado.
  - `/limpar`: Limpa os valores acumulados.
  - Caso contrário, tenta adicionar o valor numérico enviado à soma acumulada.
- **isNumeric**: Verifica se uma string é um número válido.
- **sendText**: Envia uma mensagem de texto para o usuário.

## Contato

Para qualquer dúvida ou sugestão, entre em contato com [Mateus Nicola](mailto:mateus.nicola@hotmail.com).

---

Esperamos que este bot seja útil para gerenciar suas finanças pessoais! Se tiver qualquer problema ou sugestão, sinta-se à vontade para abrir uma issue ou enviar um pull request.
