import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // write your code here
        char[][] grid = makeGame();
        displayGame(grid);
        int turn = 1;
        while (gameOver(grid)) {
            if (turn % 2 == 0) {
                userInput(grid, 'O');
            } else {
                userInput(grid, 'X');
            }
            turn++;
        }
        if (draw(grid)) {
            System.out.println("Draw");
        } else {
            gameWinner(grid);
        }
    }

    static char[][] makeGame(){
        char[][] grid = new char[3][3];
        int index = 0;
        for (int i = 0; i<3; i++){
            for (int j = 0; j < 3; j++){
                grid[i][j] = ' ';
                index++;
            }
        }
        return grid;
    }

    static void displayGame(char[][] grid){
        System.out.println("---------");
        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static void userInput(char[][] array, char mark){
        int x, y;
        boolean loop = true;
        while (loop) {
            try {
                System.out.print("Enter the coordinates: ");
                x = scanner.nextInt();
                y = scanner.nextInt();
                if (array[x - 1][y - 1] == ' ') {
                    array[x - 1][y - 1] = mark;
                    displayGame(array);
                    loop = false;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
            catch (InputMismatchException e){
                System.out.println("You should enter numbers!");
            }
        }

    }

    static boolean gameOver(char[][] array){
        boolean value = true;
        for (int i = 0; i < array.length; i++){
            if ((array[i][0] != ' ') && (array[i][1] != ' ') && (array[i][2]) != ' ') {
                if ((array[i][0] == array[i][1]) && (array[i][1] == array[i][2])) {
                    value = false;
                }
            }
            if ((array[0][i] != ' ') && (array[1][i] != ' ') && (array[2][i]) != ' ') {
                if ((array[0][i] == array[1][i]) && (array[1][i] == array[2][i])) {
                    value = false;
                }
            }
            if (((array[i][0] == ' ') || (array[i][1] == ' ') || (array[i][2] == ' ')) && (value)){
                value = true;
            }
        }
        boolean diagonals  = checkDiagonals(array, false);
        if (diagonals){
            value = false;
        }
        if (draw(array)){
            value = false;
        }
        return value;
    }

    static boolean draw(char[][] array){
        boolean value = true;
        for (int i = 0; i < array.length; i++){
            if ((array[i][0] == array[i][1]) && (array[i][1] == array[i][2])){
                value = false;
            }
            if ((array[0][i] == array[1][i]) && (array[1][i] == array[2][i])){
                value = false;
            }
            if ((array[i][0] == ' ') || (array[i][1] == ' ') || (array[i][2] == ' ')){
                value = false;
            }
        }
        boolean diagonals  = checkDiagonals(array, false);
        if (diagonals){
            value = false;
        }
        return value;
    }

    static void gameWinner(char[][] array){
        for (int i = 0; i < array.length; i++){
            if (array[i][0] + array[i][1] + array[i][2] == 264){
                System.out.println("X wins");
            }
            else if (array[i][0] + array[i][1] + array[i][2] == 237){
                System.out.println("O wins");
            }

            if (array[0][i] + array[1][i] + array[2][i] == 264){
                System.out.println("X wins");
            }
            else if (array[0][i] + array[1][i] + array[2][i] == 237){
                System.out.println("O wins");
            }
        }
        boolean value = checkDiagonals(array, true);
    }

    static boolean checkDiagonals(char[][] array, boolean print){
        boolean value = false;
        if (array[0][0] + array[1][1] + array[2][2] == 264){
            value = true;
            if (print){
                System.out.println("X wins");
            }
        }
        else if (array[0][0] + array[1][1] + array[2][2] == 237){
            value = true;
            if (print){
                System.out.println("O wins");
            }
        }

        if (array[2][0] + array[1][1] + array[0][2] == 264){
            value = true;
            if (print){
                System.out.println("X wins");
            }
        }
        else if (array[2][0] + array[1][1] + array[2][2] == 237){
            value = true;
            if (print){
                System.out.println("O wins");
            }
        }
        return value;
    }
}

