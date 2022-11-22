public class Exam2 {
    public static void main(String[] args) {
        int[] userResultList = new int[3];
        int[] userPredict = new int[3];
        int[] userDice = new int[3];
        int[] botResultList = new int[3];
        int[] botPredict = new int[3];
        int[] botDice = new int[3];
        
        System.out.println("---             Start game             ---");
        
        for (int i = 0; i < 3; i++) {
            System.out.print("Predict the points number (2..12):");
            int userNumber = Integer.valueOf(System.console().readLine());
            System.out.println("User rolss the dice:");
            int firstDice = Exam.rollTheDice(1,6);
            int secondDice = Exam.rollTheDice(1,6);
            Exam.printTheDice(firstDice);
            Exam.printTheDice(secondDice);
            int points = firstDice+secondDice;
            int resultPoints = points-Math.abs(points-userNumber)*2;
            System.out.println("On the dice fell "+ points + " points.");
            System.out.println("Result is " + points+"-abs("+points + "-" + userNumber + "*2):" + resultPoints + " points\n");
            
            
            int botNumber = Exam.rollTheDice(2,12);
            System.out.println("Computer predicted " + botNumber +" points.");
            System.out.println("Computer rolls the dice:");
            int botFirstDice = Exam.rollTheDice(1,6);
            int botSecondDice = Exam.rollTheDice(1,6);
            Exam.printTheDice(botFirstDice);
            Exam.printTheDice(botSecondDice);
            int pointsBot = botFirstDice+botSecondDice;
            int resultPointsBot = pointsBot-Math.abs(pointsBot-botNumber)*2;
            System.out.println("On the dice fell "+ pointsBot + " points.");
            System.out.println("Result is " + pointsBot+"-abs("+pointsBot + "-" + botNumber + "*2):" + resultPointsBot + " points");
            
            
            System.out.println("----------Current score----------");
            int userWinPoints = resultPoints - resultPointsBot;
            int botWinPoints = resultPointsBot - resultPoints;
            System.out.println("User:        " + resultPoints + " points");
            System.out.println("Computer:    " + resultPointsBot + " points");
            if (resultPoints > resultPointsBot) {
                
                System.out.println("User is ahead by "+ userWinPoints +" points more.");
            }
            else{
                
                System.out.println("Computer is ahead by " + botWinPoints + " points more.");
            }
            System.out.println("---------------------------------");
            userResultList[i] = resultPoints;
            userPredict[i] = userNumber;
            userDice[i] = points;
            botResultList[i] = resultPointsBot;
            botPredict[i] = botNumber;
            botDice[i] = pointsBot;
        }
        System.out.println("--------- Finish game ---------");
        System.out.println(" Round |           User |      Computer  ");
        int userTotalResult = 0;
        int botTotalResult = 0;
        for (int i = 0; i < 3; i++) {
            System.out.println("-------+----------------+----------------");
            System.out.println("       | Predicted:   "+ userPredict[i] + " | Predicted:  "+ botPredict[i]);
            System.out.println(" - "+(i+1)+" - | Dice:        "+ userDice[i] + " | DIce:       "+ botDice[i]);
            System.out.println("       | Result:      "+ userResultList[i] + " | Predicted:  "+ botResultList[i]);
            userTotalResult += userResultList[i];
            botTotalResult += botResultList[i];
        }
        System.out.println("-------+----------------+----------------");
            System.out.println("Total  | Points:      "+ userTotalResult + " | Points:     "+ botTotalResult);
            if(userTotalResult>botTotalResult){
                int userWinTotalPoints = userTotalResult-botTotalResult;
                System.out.println("Users win " + userWinTotalPoints+" points more.\nCongratulations!");
            }
            else{
                int botWinTotalPoints = botTotalResult - userTotalResult;
                System.out.println("Computer win " + botWinTotalPoints+" points more.\nCongratulations!");
            }

    }
}
