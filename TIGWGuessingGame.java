//Programmers: Tsaqif Insani, Gracia Winata
//12 April 2022
//CS 142 
//Lab 1 Guessing Game 
//This program is supposed to play a guessing game with the user. 

import java.util.Scanner; 
import java.util.Random; 
public class TIGWGuessingGame{
  public static void main(String[] args){
    //constant variable for the maximum range of the random numbers 
    int maximumNumber = 20; 
    givePlayerInstructions(maximumNumber);
    playGame(maximumNumber); 
  }

  //present the user with an introduction to the game 
  public static void givePlayerInstructions(int maxNum){
    System.out.println("This program allows you to play a guessing game.");
    System.out.printf("I will think of a number between 1 and %d and", maxNum);
    System.out.println(); 
    System.out.println("will allow you to guess until you get it."); 
    System.out.println("For each guests, I will tell you whether");
    System.out.println("the answer is higher or lower than your guess"); 
  } 

  //plays the game while the user chooses to still play 
  public static void playGame(int maxNum){
    int bestGuess = 9999; 
    int totalGamesPlayed = 0; 
    int totalGuesses = 0; 
    boolean continueGame = true; 
    while(continueGame){
      totalGamesPlayed++; 
      int currentGuessCount = playOneGameInstance(maxNum); 
      totalGuesses += currentGuessCount; 
      if (currentGuessCount < bestGuess) bestGuess = currentGuessCount; 
      continueGame = askIfContinue(); 
    }
    summaryOfGameData(totalGamesPlayed, totalGuesses, bestGuess);
  }

  //plays the guessing game one time, 
  public static int playOneGameInstance(int maxNum){
    int currentNumberOfGuesses = 1; 
    System.out.println(); 
    System.out.println("I'm thinking of a number between 1 and " + maxNum); 
    //setting the answer a random number within the accepted range 
    int expectedAnswer = getRandomNumber(maxNum) + 1; 
    //makes sure the user input is valid and changes it into an integer 
    int userGuess = parseUserInput(maxNum); 
    while(userGuess != expectedAnswer){
      currentNumberOfGuesses++; 
      guessHighOrLow(userGuess, expectedAnswer);
      userGuess = parseUserInput(maxNum); 
    }
    singularOrPluralGuess(currentNumberOfGuesses);
    return currentNumberOfGuesses; 
  } 

  //getting the random number for the expected answer 
  public static int getRandomNumber(int maxNum){
    Random randomObject = new Random(); 
    int range = maxNum; 
    return randomObject.nextInt(range); 
  }

    //retrieving and checking to see if userInput is valid, where it will then, 
    // return the userInput as an integer as opposed to a string 
  public static int parseUserInput(int maxNum){
    Scanner scannerObject = new Scanner(System.in); 
    System.out.print("Your guess? ");
    String userGuess = scannerObject.next(); 
    while(!checkIfInputValid(userGuess, maxNum)){
      userGuess = scannerObject.next(); 
    }
    return Integer.parseInt(userGuess); 
  } 

  //checking to see if the user input is a number and is within the 
  //intended range 
  public static boolean checkIfInputValid(String userInput, int maxNum){
    try{
      int userAnswerInInt = Integer.parseInt(userInput);
      if(userAnswerInInt < 0 || userAnswerInInt > maxNum){
        System.out.println("Please enter a valid number within the range"); 
        return false; 
      }
    }catch(Exception e){
      System.out.println("Please enter a number as your guess"); 
      return false; 
    }
    return true; 
  } 

  //to point out whether your guess is higher than the answer or lower 
  public static void guessHighOrLow(int userGuess, int expectedAnswer){
    if (userGuess < expectedAnswer){
      System.out.println("Your guess is too low");
    } else{
      System.out.println("Your guess is too high"); 
    }
  }

  //to identify whether the ending sentence would be in plural or singular form. 
  public static void singularOrPluralGuess(int numberOfGuesses){
    if (numberOfGuesses > 1){
      System.out.printf("You got it right in %d guesses", numberOfGuesses);
    }else{
      System.out.printf("You got it right in %d guess", numberOfGuesses);
    }
    System.out.println(); 
  }

  // to ask the user if they wan't to continue or stop 
  public static boolean askIfContinue(){
    Scanner scannerObject = new Scanner(System.in);
    System.out.println("Do you want to play again (Y/N)? ");
    String userAnswer = scannerObject.next(); 
    while(!checkIfYesOrNo(userAnswer)){
      userAnswer = scannerObject.next(); 
    }
    if (userAnswer.toLowerCase().startsWith("y")) return true; 
    return false; 
  } 

  //checks if the user Input is either a yes or a no 
  public static boolean checkIfYesOrNo(String userAnswer){
    if (userAnswer.toLowerCase().startsWith("y") || userAnswer.toLowerCase().startsWith("n")){
      return true; 
    }
    System.out.println("Please enter 'yes' or 'no'"); 
    return false; 
  }

  //prints out the final game data 
  public static void summaryOfGameData(int totalGamesPlayed, int totalGuesses, int leastGuesses){
    System.out.println("Overall Results: ");
    System.out.printf("\t total games = %d", totalGamesPlayed);
    System.out.println(); 
    System.out.printf("\t total guesses = %d", totalGuesses); 
    System.out.println(); 
    System.out.printf("\t guesses/game = %.1f", totalGuesses/(totalGamesPlayed * 1.0)); 
    System.out.println(); 
    System.out.printf("\t best game = %d", leastGuesses); 
    System.out.println(); 
  } 
}









