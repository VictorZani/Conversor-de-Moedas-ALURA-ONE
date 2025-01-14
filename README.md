# Conversor de Moedas Alura One

Este projeto é um conversor de moedas desenvolvido em Java como parte de um desafio de programação. Ele permite converter valores entre várias moedas utilizando taxas de câmbio atualizadas de uma API externa.

## 📌 Funcionalidades

- **Conversão de Reais para outras moedas**:
  - Dólar (USD)
  - Euro (EUR)
  - Libras Esterlinas (GBP)
  - Peso Argentino (ARS)
  - Peso Chileno (CLP)

- **Conversão de outras moedas para Reais**:
  - USD → BRL
  - EUR → BRL
  - GBP → BRL
  - ARS → BRL
  - CLP → BRL

- **Interface baseada no console**:
  - Menu interativo para selecionar opções de conversão.
  - Entrada de valores diretamente pelo usuário.

## 🛠️ Tecnologias Utilizadas

- **Java 11**: Linguagem de programação principal.
- **HttpClient**: Para realizar requisições HTTP à API de taxas de câmbio.
- **Gson**: Para manipular o JSON retornado pela API.
- **Scanner**: Para capturar entradas do usuário via console.

## 📂 Estrutura do Projeto

O projeto possui a seguinte estrutura de arquivos:

```
├── src
│   ├── CurrencyConverter.java  # Arquivo principal do projeto
└── README.md                    # Documentação do projeto
```

## 🚀 Como Executar o Projeto

1. **Pré-requisitos**:
   - JDK 11 ou superior.
   - Configurar o arquivo `CurrencyConverter.java` com a sua chave de API.

2. **Configuração da Chave de API**:
   Substitua `YOUR_API_KEY` no código pelo valor da sua chave API, fornecida pelo [Exchangerate API](https://app.exchangerate-api.com/keys):

   ```java
   private static final String API_URL = "https://v6.exchangerate-api.com/v6/YOUR_API_KEY/latest/";
   ```

3. **Compilação e Execução**:
   - Compile o programa:
     ```
     javac CurrencyConverter.java
     ```
   - Execute o programa:
     ```
     java CurrencyConverter
     ```

## 🎯 Próximos Desafios

Aqui estão algumas sugestões para expandir o projeto:

1. **Histórico de Conversões**:
   - Armazene as últimas conversões realizadas e permita ao usuário visualizá-las.

2. **Suporte para Mais Moedas**:
   - Expanda a lista de moedas disponíveis para conversão.

3. **Registros de Logs**:
   - Utilize a biblioteca `java.time` para registrar logs com a data e hora das conversões realizadas.

4. **Interface Gráfica (GUI)**:
   - Desenvolva uma interface gráfica com bibliotecas como `JavaFX` ou `Swing` para facilitar a interação com o usuário.

## 🧪 Testes

Testes foram realizados para garantir o funcionamento correto do programa. Os seguintes cenários foram cobertos:

- Conversão de valores entre Reais e outras moedas.
- Validação de entradas inválidas (ex.: valores negativos, moedas inexistentes).
- Erros de conexão com a API.

## 📜 Licença

Este projeto foi desenvolvido como parte do **Programa ONE (Oracle Next Education)** e está disponível para fins educacionais e experimentais.

---

Feito com 💻 e ☕ por [Seu Nome].

