import java.util.Random;

public class Exam {
    public static void main(String[] args) {
        System.out.println("---             Start game             ---");
        System.out.print("Predict the points number (2..12):");
        int userNumber = Integer.valueOf(System.console().readLine());
        System.out.println("User rolss the dice:");
        int firstDice = rollTheDice(1,6);
        int secondDice = rollTheDice(1,6);
        printTheDice(firstDice);
        printTheDice(secondDice);
        int points = firstDice+secondDice;
        int resultPoints = points-Math.abs(points-userNumber)*2;
        System.out.println("On the dice fell "+ points + " points.");
        System.out.println("Result is " + points+"-abs("+points + "-" + userNumber + "*2):" + resultPoints + "points");
        int botNumber = rollTheDice(2,12);
        System.out.println("Computer predicted " + botNumber +" points.");
        System.out.println("Computer rolls the dice:");
        int botFirstDice = rollTheDice(1,6);
        int botSecondDice = rollTheDice(1,6);
        printTheDice(botFirstDice);
        printTheDice(botSecondDice);
        int pointsBot = botFirstDice+botSecondDice;
        int resultPointsBot = pointsBot-Math.abs(pointsBot-botNumber)*2;
        System.out.println("On the dice fell "+ pointsBot + " points.");
        System.out.println("Result is " + pointsBot+"-abs("+pointsBot + "-" + botNumber + "*2):" + resultPointsBot + " points");
        if (resultPoints > resultPointsBot) {
            int winPoints = resultPoints-resultPointsBot;
            System.out.println("User win "+ winPoints+" points more.");
        }
        else{
            int winPoints = resultPointsBot - resultPoints;
            System.out.println("Computer win " + winPoints + " points more.");
        }
        
    }
    public static int rollTheDice(int a, int b) {
        Random random = new Random();
        return random.nextInt(b - a + 1)+a;
    }
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    public static void printTheDice(int number) {
        System.out.println("---------");
        switch (number) {
            case 1:
            System.out.print("|       |\n|   #   |\n|       |\n");
            break;
            case 2:
            System.out.print("|   #   |\n|       |\n|   #   |\n");
            break;
            case 3:
            System.out.print("|   #   |\n|   #   |\n|   #   |\n");
            break;
            case 4:
            System.out.print("| #   # |\n|       |\n| #   # |\n");
            break;
            case 5:
            System.out.print("| #   # |\n|   #   |\n| #   # |\n");
            break;
            case 6:
            System.out.print("| #   # |\n| #   # |\n| #   # |\n");
            break;
        }
        System.out.println("---------");
    }
    
}