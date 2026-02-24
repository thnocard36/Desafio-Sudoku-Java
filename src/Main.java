import br.com.thnocard.dio.util.BoardTemplate;
import br.com.thnocard.dio.util.model.OnBoard;
import br.com.thnocard.dio.util.model.Spaces;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

    // Algumas variaveis
    private final static Scanner scanner = new Scanner(System.in);
    private static OnBoard onBoard;
    private final static int BOARD_LIMIT = 9;


    public static void main(String[] args) {

        // Passando valores do '[] args
        final var positionsInf = Stream.of(args)
                .collect(toMap(kaps -> kaps.split(";")[0],
                        volt -> volt.split(";")[1]
                ));
        /* final var positionsInf :Map<String, String> = Stream.of(args)
                .collect(toMap(
                        kaps -> kaps.split(";")[0],
                        volt -> volt.split(";")[1]
                )); */

        System.out.println("********************************* SUDOKU 2026 *********************************");
        System.out.println();

        var option = -1;
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

            option = scanner.nextInt();

            switch (scanner.nextLine()) { // Alguns metódos
                case "1" -> startMatch(positionsInf);
                case "2" -> inputNumber();
                case "3" -> removeNumber();
                case "4" -> showCurrentGame();
                case "5" -> showGameStatus();
                case "6" -> clearMatch();
                case "7" -> finishGame();
                case "8" -> {
                    System.out.println("Obrigado por jogar!");
                    System.exit(0);
                }
                    default -> {
                        System.out.println("Opção inválida.");
                        System.out.println("Selecione uma das opções do menú!");
                    }
            }

        }

    }

    private static void startMatch(final Map<String, String> positionsInf) {
        if(nonNull(onBoard)) {
            System.out.println("O Jogo já foi iniciado!");
            return;
        }

        List<List<Spaces>> spaces = new ArrayList<>();
        for(int delta = 0; delta < BOARD_LIMIT; delta++) {
            spaces.add(new ArrayList<>());

            for(int jow = 0; jow < BOARD_LIMIT; jow++) {
                var positionConfig = positionsInf.get("%s, %s".formatted(delta, jow));
                var expectedBalance = Integer.parseInt(positionConfig.split(",")[0]);
                var fixedBoard = Boolean.parseBoolean(positionConfig.split(",")[1]);
                var currentSpace = new Spaces(expectedBalance, fixedBoard);
                spaces.get(jow).add(currentSpace);
            }
        }

        onBoard = new OnBoard(spaces);
        System.out.println("O Jogo está pronto para começar!");

    }

    private static void inputNumber() {
        /* if(isNull(boardOn)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        } */
        if(isNull(onBoard)) {
            System.out.println("O Jogo ainda não iniciou!");
            return;
        }

        System.out.println("Informe a coluna em que o número será inserido!");
        var col = runUntilGetNumberValid(0,8);
        System.out.println("Informe a linha em que o número será inserido!");
        var row = runUntilGetNumberValid(0,8);

        System.out.printf("Informe o número que entra-rá na posição [%s, %s]: ", col, row);
        var value = runUntilGetNumberValid(1,9);
    }

    private static void removeNumber() {
    }

    private static void clearMatch() {
    }

    private static void showGameStatus() {
    }

    private static void showCurrentGame() {
    }

    private static void finishGame() {
    }

    public static int runUntilGetNumberValid(final int mini, final int maxin) {
        var currentNumber = scanner.nextInt();
        while(currentNumber < mini || currentNumber > maxin) {
            System.out.printf("Informe um número entre %s e %s", mini, maxin);
            System.out.println();
            currentNumber = scanner.nextInt();
        }
        return currentNumber;
    }






}