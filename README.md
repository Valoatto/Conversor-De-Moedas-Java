# Conversor-De-Moedas-Java
Conversor de moedas desktop feito em Java com Swing. Projeto desenvolvido como atividade prática durante o curso de Análise e Desenvolvimento de Sistemas.

Criamos uma aplicação simples e visualmente organizada para praticar manipulação de números decimais, eventos de interface e lógica de conversão de valores.

As taxas são fixas no código porque o foco principal era o aprendizado e não a atualização em tempo real das moedas.

---

# Sobre o projeto

Esse projeto foi desenvolvido para praticar melhor a construção de interfaces gráficas no Java e também trabalhar com cálculos simples utilizando `double`.

Durante o desenvolvimento, aproveitei para estudar:

- Interfaces gráficas com Swing
- Uso de `JComboBox`
- Conversão de `String` para `double`
- Tratamento de erros com `try/catch`
- Organização do código em múltiplas classes
- Separação entre lógica da interface e lógica de conversão

---

# Funcionalidades

- Conversão entre moedas
- Seleção de moeda de origem e destino
- Resultado formatado
- Suporte para vírgula e ponto decimal
- Validação de valores inválidos
- Interface simples e organizada

Moedas disponíveis:

- BRL
- USD
- EUR
- GBP
- JPY
- ARS

---

# Tecnologias utilizadas

- Java
- Swing

Nenhuma biblioteca externa foi utilizada.

---

# Estrutura do projeto

```
conversor-moedas-visual/
├── src/
│   ├── Main.java
│   ├── TelaConversor.java
│   ├── Moeda.java
│   └── Conversor.java
├── .gitignore
└── README.md
```

### Organização dos arquivos

| Arquivo | Responsabilidade |
| --- | --- |
| `Main.java` | Inicializa a aplicação |
| `TelaConversor.java` | Interface gráfica e eventos |
| `Moeda.java` | Representa cada moeda |
| `Conversor.java` | Lógica de conversão e taxas |

---

# Como o código foi organizado

Tentei separar cada parte do projeto para deixar o código mais limpo e fácil de entender.

## Moeda.java

Representa cada moeda utilizada no sistema, contendo:

- código
- nome
- taxa

## Conversor.java

Responsável pela lógica da conversão e pelas taxas fixas das moedas.

## TelaConversor.java

Cuida da interface gráfica, dos campos, botões e exibição do resultado.

---

# Como funciona a conversão

A conversão utiliza o real como moeda intermediária.

Exemplo:

1. O valor é convertido para BRL
2. Depois é convertido para a moeda de destino

Isso evitou criar uma fórmula separada para cada combinação de moedas.

---

# O que aprendi com esse projeto

- Uso de `JComboBox`
- Conversão de valores com `Double.parseDouble`
- Tratamento de erros com `try/catch`
- Organização de interfaces gráficas
- Manipulação de números decimais
- Separação entre lógica visual e lógica de negócio

Também aproveitei para praticar uma estrutura mais organizada para projetos no GitHub.

---

# Melhorias futuras

- Buscar taxas reais de uma API
- Histórico de conversões
- Botão para inverter moedas
- Tema escuro
- Melhorias visuais
