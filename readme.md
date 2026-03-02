# 🧩 Desafio: Jogo Sudoku em Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)
![Java Study](https://img.shields.io/badge/Estudo-Java_Core-yellow?style=for-the-badge&logo=java)
![DIO](https://img.shields.io/badge/Origem-Digital_Innovation_One-EE2E5D?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Em_Estudo-green?style=for-the-badge)

Um simulador de Sudoku robusto desenvolvido em Java, que oferece duas formas de interação: via **Console (CLI)** e via **Interface Gráfica (GUI)** utilizando a biblioteca Swing. O projeto foi construído focado em lógica de validação de matrizes e organização de código orientada a objetos.

---

| **Categoria** | **Detalhes** |
| ----------------- | :----------:|
| **Autor** | [Thiago Cardoso](https://github.com/thnocard36) |
| **Professor / Instrutor** | [Prof° José Junior](https://github.com/juniorjrjl) |

---

## 🚀 Funcionalidades

* **Dual Mode:** Jogue diretamente no terminal ou abra uma janela interativa.
* **Validação em Tempo Real:** O sistema verifica se os números inseridos estão corretos de acordo com o gabarito.
* **Gerenciamento de Estado:** Identifica se a partida está *Não Iniciada*, *Incompleta* ou *Finalizada*.
* **Trava de Células Fixas:** Números pré-definidos no tabuleiro não podem ser alterados pelo jogador.
* **Limitação de Entrada:** No modo gráfico, filtros (DocumentListeners) impedem a digitação de caracteres inválidos.

---

## 🏗️ Estrutura do Projeto

O projeto segue o padrão de separação de responsabilidades:

* `model`: Entidades principais (`OnBoard`, `Spaces`) e Enums de status.
* `services`: Regras de negócio e inicialização do tabuleiro.
* `ui`: Componentes visuais customizados e telas (Frames, Panels, Buttons).
* `util`: Templates de exibição ASCII e classes `Main` / `UIMain`.

---

## 🧠 Objetivos de Aprendizado
* Uso de Generics em coleções Java.
* Tratamento de exceções personalizadas.
* Manipulação de entrada de dados com Scanner.

---

## 🛠️ Como Executar
O jogo utiliza argumentos de linha de comando (`args`) para carregar a configuração inicial do tabuleiro no formato `coordenada;valor,fixo`.

### Exemplo de Argumento:
Para definir que na posição (coluna 0, linha 0) o valor correto é 5 e a célula deve vir travada (fixa):
`0,0;5,true`

### Executando via Terminal:
Substitua os `...` pelas 81 posições do tabuleiro:


``` bash
# Modo Gráfico (Swing)
java br.com.thnocard.dio.util.UIMain "0,0;5,true" "0,1;3,false" ...

# Modo Console (Scanner)
java br.com.thnocard.dio.util.Main "0,0;5,true" "0,1;3,false" ...
```

---

## ⚖️ Licença

Este projeto está sob a licença MIT. Isso significa que você pode copiar, modificar e distribuir o código, desde que inclua o aviso de copyright original.