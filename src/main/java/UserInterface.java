import java.util.Scanner;

public class UserInterface {

    String userInput = "a";
    String turn = "0";

    FieldRepository fieldRepository = new FieldRepository();

    AlgoRepository algoRepository = new AlgoRepository();

    Scanner scanner = new Scanner(System.in);

    public void runGame() {
        System.out.println("Welcome to Tic Tac Toe");
        System.out.println("Write the number of a field to place a piece on that field");
        System.out.println("O starts");
        System.out.println("Type e to end the game");
        System.out.println("Type r to restart the game");
        System.out.println("Type n to see the numbers of the fields");

        while (!userInput.equalsIgnoreCase("e")) {
            System.out.println(" ");
            System.out.println("Type 1 to play against another human being or type 2 to play against the computer:");
            userInput = scanner.next();
            if (userInput.equals("1")) {
                playAginstPlayer();
            } else if (userInput.equals("2")) {
                playAgainstAI();
            } else System.out.println("This is not a valid number");
        }
    }

    public void playAgainstAI() {
        System.out.println("You are now playing against the computer");
        System.out.println("Do you want to be X or O? (type X or O):");
        while (!userInput.equalsIgnoreCase("X") && !userInput.equalsIgnoreCase("O")) {
            userInput = scanner.next();
        }
        turn = userInput.toUpperCase();



        if (userInput.equalsIgnoreCase("X")) {
            algoRepository.setAlgoColor();
            algoRepository.startAlphaBeta(fieldRepository);
        }

        while (!userInput.equalsIgnoreCase("e")) {

            if (userInput.equalsIgnoreCase("r")) {
                fieldRepository.cleanFields();
                System.out.println("The game has been restarted");
                if (turn.equalsIgnoreCase("X")){
                    algoRepository.startAlphaBeta(fieldRepository);;

                }
            }

            fieldRepository.printFields();
            if (userInput.equalsIgnoreCase("n")) {
                fieldRepository.printFieldsWithNumbers();
                System.out.println(algoRepository.getValue(fieldRepository.getFields()));
            }



            System.out.println("Where do you want to place the next piece?");
            userInput = scanner.next();

            if (!userInput.equalsIgnoreCase("r") && !userInput.equalsIgnoreCase("n")
                    && !userInput.equalsIgnoreCase("e")) {
                if (fieldRepository.checkNumber(userInput) && fieldRepository.checkPieces(userInput)) {
                    fieldRepository.placePiece(turn, userInput);
                    algoRepository.startAlphaBeta(fieldRepository);;
                }

                if (fieldRepository.checkWinnerX()) {
                    fieldRepository.printFields();
                    System.out.println("X WINS");
                    userInput = "e";
                    continue;
                }
                if (fieldRepository.checkWinnerO()) {
                    fieldRepository.printFields();
                    System.out.println("O WINS");
                    userInput = "e";
                }
                if (!fieldRepository.checkWinnerX() && !fieldRepository.checkWinnerO() && fieldRepository.checkDraw()) {
                    fieldRepository.printFields();
                    System.out.println("THE GAME IS A DRAW");
                }
            }
        }
        System.out.println("The game is now shutting down");
        System.out.println("Thank you for playing Tic Tac Toe");

    }


    public void playAginstPlayer(){
        System.out.println("You are now playing against another human player");
        String turn = "O";

            while (!userInput.equalsIgnoreCase("e")){

                if (userInput.equalsIgnoreCase("r")) {
                    fieldRepository.cleanFields();
                    System.out.println("The game has been restarted");
                    turn = "O";
                }
                fieldRepository.printFields();
                if (userInput.equalsIgnoreCase("n")) {
                    fieldRepository.printFieldsWithNumbers();
                }

                System.out.println(turn + " to Play");
                System.out.println("Where do you want to place the next piece?");
                userInput = scanner.next();

                if (!userInput.equalsIgnoreCase("r")&&!userInput.equalsIgnoreCase("n")
                        &&!userInput.equalsIgnoreCase("e")){
                    if (fieldRepository.checkNumber(userInput) && fieldRepository.checkPieces(userInput)) {
                        fieldRepository.placePiece(turn, userInput);
                        switch (turn) {
                            case "O" -> turn = "X";
                            case "X" -> turn = "O";
                        }
                    }
                    if (fieldRepository.checkWinnerX()){
                        fieldRepository.printFields();
                        System.out.println("X WINS");
                        userInput = "e";
                    }
                    if (fieldRepository.checkWinnerO()){
                        fieldRepository.printFields();
                        System.out.println("O WINS");
                        userInput = "e";
                    }
                    if (!fieldRepository.checkWinnerX()&&!fieldRepository.checkWinnerO()&&fieldRepository.checkDraw()){
                        fieldRepository.printFields();
                        System.out.println("THE GAME IS A DRAW");
                        userInput = "e";
                    }
                }
        }
        System.out.println("The game is now shutting down");
        System.out.println("Thank you for playing Tic Tac Toe");
    }
}
