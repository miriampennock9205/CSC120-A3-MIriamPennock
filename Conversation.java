import java.util.Random;
import java.util.Scanner;
/**
 * The Conversation class simulates a chatbot that mirrors specific words in user input 
 * and responds with either a mirrored version or a canned response.
 */
class Conversation {
  /**
   * The main method where the conversation is started. It handles user input, processes responses, 
   * and stores the transcript.
   * @param arguments
   */
  public static void main(String[] arguments) {
    // You will start the conversation here.

    //Create Scanner to get input from the user
    Scanner scan = new Scanner(System.in);
    
    //Ask for the number of rounds
    System.out.println("How many rounds?");
    int rounds = scan.nextInt();
    scan.nextLine(); //Get the newline after the number input

    //Initialize an array to store the conversation transcript
    String[] transcript = new String[(rounds + 1) * 2]; //plus 2 for greeting and goodbye
    int transcriptIndex = 0;

    //Start the conversation
    
    String greeting = "Hi there! What's on your mind?";
    System.out.println(greeting);
    transcript[transcriptIndex++] = greeting;
  
    //Canned responses for non-mirrored input
    String [] cannedResponses = 
    {
      "Mmm-hm.", "Interesting.", "Can you say more about that?", "Oh, really?"
    };
    Random random = new Random();

    //Loop through the number of rounds
    for (int i = 0; i < rounds; i++)
    {
      //Get user input
      System.out.println("> ");
      String userInput = scan.nextLine();
      transcript[transcriptIndex++] = userInput; //Save user input to transcript

      //Check if words can be mirrored
      String botResponse = mirrorWords(userInput);
      if (botResponse.equals(userInput))
      {
        //if no mirroring was done, give a random canned response
        botResponse = cannedResponses[random.nextInt(cannedResponses.length)];
      }

      //Output the bot's response
      System.out.println(botResponse);
      transcript[transcriptIndex++] = botResponse; //Save the bot response to the transcript
    }


    //End the conversation
    String goodbyeMessage = "See ya!";
    System.out.println(goodbyeMessage);
    transcript[transcriptIndex++] = goodbyeMessage;

    //Print the transcript
    System.out.println("\nTRANSCRIPT:");
    for (String line : transcript)
    {
      System.out.println(line);
    }
    }

    /**
     * This method takes a string input and mirrors specific words based on predefined rules.
     * It replaces words such as "I" with "you", "am" with "are", and so on, to create a 
     * mirrored conversation effect.
     * @param input The user's input string that may contian mirrorable words.
     * @return A mirrored version of the input string, or the original input if no mirroring is done.
     */
    public static String mirrorWords(String input) {
      
      // Store mirrorable words and their replacements
      String[][] wordPairs = {
        {"I", "you"}, {"me", "you"}, {"am", "are"}, {"you", "I"}, {"my", "your"}, {"your", "my"}, {"I'm", "you're"}
      };
  
      // Split the input into words
      String[] words = input.split(" ");
      String[] punctuations = new String[words.length];
      for (int i = 0; i < words.length; i++) {
        if (words[i].matches(".*[!?.]$")) {
            punctuations[i] = words[i].substring(words[i].length() - 1);
            words[i] = words[i].substring(0, words[i].length() - 1);
        } else {
            punctuations[i] = "";
        }
    }
      boolean hasMirrorWord = false;
      
      // Perform word mirroring
      for (int i = 0; i < words.length; i++) {
        for (String[] pair : wordPairs)
        {
          if (words[i].equalsIgnoreCase(pair[0]))
          {
            words[i] = pair[1];
            hasMirrorWord = true;
            break;
          }
        }
      }
      
      //Reconstruct the sentence after word mirroring
      String mirrored = "";
      for (int i = 0; i < words.length; i++)
      {
        mirrored += words[i] + punctuations[i] + " ";
      }
      mirrored = mirrored.trim();
      // Mirror punctuation only if there was a mirrorable word
      if (hasMirrorWord) 
      {
      mirrored = mirrored.replace("!", "?").replace(".", "?");
      }

      // Capitalize the first letter of the sentence
    if (!mirrored.isEmpty()) 
    {
      mirrored = mirrored.substring(0, 1).toUpperCase() + mirrored.substring(1);
    }
      //Punctiation replacements
      return mirrored;
    }
  }