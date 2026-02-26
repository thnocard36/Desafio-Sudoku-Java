import br.com.thnocard.dio.util.model.OnBoard;
import br.com.thnocard.dio.util.model.Spaces;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static br.com.thnocard.dio.util.BoardTemplate.BOARD_SUDOKU;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

    // Algumas variaveis
    private final static Scanner scanner = new Scanner(System.in);
    private static OnBoard onBoard;
    private final static int BOARD_LIMIT = 9;


    public static void main(String[] args) {

        System.out.println("********************************* SUDOKU 2026 *********************************");
        System.out.println();

        // Passando valores do '[] args
        final var positionInf = Stream.of(args)
                .collect(toMap(
                        caps -> caps.split(";")[0],
                        volt -> volt.split(";")[1]
                ));

        var choose = -1;
        while(true) {
            System.out.println("Selecione uma das opções abaixo");
            System.out.println("1. Iniciar uma nova partida");
            System.out.println("2. Adicionar um novo número");
            System.out.println("3. Remover um número");
            System.out.println("4. Visualizar jogo atual");
            System.out.println("5. Verificar status do jogo");
            System.out.println("6. Limpar Jogo");
            System.out.println("7. Finalizar Jogo");
            System.out.println("8. Sair");

            choose = scanner.nextInt();

            // switch (scanner.nextLine()) { // Alguns metódos
            switch(choose) {
                    case 1 -> startMatch(positionInf);
                    case 2 -> inputNumber();
                    case 3 -> removeNumber();
                    case 4 -> showCurrentGame();
                    case 5 -> showGameStatus();
                    case 6 -> clearMatch();
                    case 7 -> finishGame();
                    case 8 -> {
                        System.out.println();
                        System.out.println("Obrigado por jogar!");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println();
                        System.out.println("Opção inválida.");
                        System.out.println("Selecione uma das opções do menú!");
                        System.out.println();
                    }
            }

        }

    }

    private static void startMatch(final Map<String, String> positionsInf) {
        if(nonNull(onBoard)) {
            System.out.println("O Jogo já foi iniciado!");
            System.out.println();
            return;
        }

        List<List<Spaces>> spaces = new ArrayList<>();
        for(int iggy = 0; iggy < BOARD_LIMIT; iggy++) {
            spaces.add(new ArrayList<>());

            for(int jow = 0; jow < BOARD_LIMIT; jow++) {
                var positionConfig = positionsInf.get("%s,%s".formatted(iggy, jow));
                var expectedBalance = Integer.parseInt(positionConfig.split(",")[0]);
                var fixedBoard = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Spaces(expectedBalance, fixedBoard);
                spaces.get(iggy).add(currentSpace);
            }
        }

        onBoard = new OnBoard(spaces);
        System.out.println("O Jogo está pronto para começar!");
        System.out.println();

    }

    private static void inputNumber() {
        if(isNull(onBoard)) {
            System.out.println("O Jogo ainda não iniciou!");
            System.out.println();
            return;
        }

        System.out.println();
        System.out.println("Informe a coluna em que o número será inserido: ");
        var col = runUntilGetNumberValid(0,8);

        System.out.println();
        System.out.println("Informe a linha em que o número será inserido: ");
        var row = runUntilGetNumberValid(0,8);

        System.out.println();
        System.out.printf("Informe o número que entra-ra na posição [%s, %s]: ", col, row);
        var value = runUntilGetNumberValid(1,9);
        System.out.println();

        if(!onBoard.changeValues(col, row, value)) {
            System.out.printf("A posição [%s, %s] tem um valor fixo.", col, row);
        }
    }

    private static void showCurrentGame() {
        if(isNull(onBoard)) {
            System.out.println("O Jogo ainda não iniciou!");
            System.out.println();
            return;
        }

        var args = new Object[81];
        var argsPosg = 0;

        for(int yellow = 0; yellow < BOARD_LIMIT; yellow++) {
            for(var col: onBoard.getSpacesList()) {
                args[argsPosg ++] = " " + ((isNull(col.get(yellow).getActualStage())) ? " " :
                        col.get(yellow).getActualStage());
            }
        }

        System.out.println();
        System.out.println("Seu jogo se encontra assim... \n\n");
        System.out.printf((BOARD_SUDOKU) + "%n", args);
        System.out.println();

    }

    private static void showGameStatus() {
        if(isNull(onBoard)) {
            System.out.println("O Jogo ainda não iniciou!");
            System.out.println();
            return;
        }

        System.out.println("O Jogo se encontra atualmente assim... \n");
        System.out.printf("%s", onBoard.getStatusMatch().getLables());
        System.out.println();

        if(onBoard.hasErrors()) {
            System.out.println("O Jogo possui erros!");
        } else {
            System.out.println("O Jogo não possui erros!");
        }
    }

    private static void removeNumber() {
        if(isNull(onBoard)) {
            System.out.println("O Jogo ainda não iniciou!");
            System.out.println();
            return;
        }

        System.out.println();
        System.out.println("Informe a coluna em que o número será inserido: ");
        var col = runUntilGetNumberValid(0,8);

        System.out.println();
        System.out.println("Informe a linha em que o número será inserido: ");
        var row = runUntilGetNumberValid(0,8);

        System.out.println();
        System.out.printf("Informe o número que entra-ra na posição [%s, %s]: ", col, row);
        System.out.println();

        if(!onBoard.clearValues(col, row)) {
            System.out.printf("A posição [%s, %s] tem um valor fixo.", col, row);
        }

    }

    private static void clearMatch() {
        if(isNull(onBoard)) {
            System.out.println("O Jogo ainda não iniciou!");
            System.out.println();
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso?");
        var confirmQuestion = scanner.nextLine();

        while(!confirmQuestion.equalsIgnoreCase("sim") && !confirmQuestion.equalsIgnoreCase("nao")) {
            System.out.println("Informe 'sim' ou 'nao' ");
            confirmQuestion = scanner.nextLine();
        }

        if(confirmQuestion.equalsIgnoreCase("sim")) {
            onBoard.resetSpace();
        }
    }

    private static void finishGame() {
        if(isNull(onBoard)) {
            System.out.println("O Jogo ainda não iniciou!");
            return;
        }

        if(onBoard.gameFinishIs()) {
            System.out.println("Parabéns!");
            System.out.println("Você concluiu o jogo.");
            showCurrentGame();
            onBoard = null;
        } else if(onBoard.hasErrors()) {
            System.out.println();
            System.out.println("O Jogo possui erros, por favor verique seu 'board' e o-ajuste!");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Você ainda precisa preencher algum espaço!");
            System.out.println();
        }

    }

    // Metodo utilitário para 'pegar' numeros válidos!
    public static int runUntilGetNumberValid(final int mini, final int maxin) {
        var currentNumber = scanner.nextInt();

        while(currentNumber < mini || currentNumber > maxin) {
            System.out.printf("Informe um número entre %s e %s", mini, maxin);
            currentNumber = scanner.nextInt();
            System.out.println();
        }
        return currentNumber;
    }
}