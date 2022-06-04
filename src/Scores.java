import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

 /**
  * A {@link Scores} object tracks the scores and the high score in a game application,
  * and save them to files.
  *
  * @author IvanZou1
  * @version 1.0
  * @since 05/12/2022
 */
public class Scores {
    private final LinkedList<Integer> allScores;
    private int highScore, score;


     /**
      * Default Scores constructor - Initializes {@link Scores#allScores} to a
      * new LinkedList, {@link Scores#highScore} to a value from the high score
      * file, and {@link Scores#score} to 0.
      */
    public Scores() {
        allScores = new LinkedList<>();
        setHighScoreFromFile();
        score = 0;
    }

     /**
      * Reads highscore.txt and sets the value in the file to {@link Scores#highScore}.
      * If the file does not exist, {@link Scores#highScore} is set to 0.
      */
    private void setHighScoreFromFile() {
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("files/highscore.txt"));
            String line = reader.readLine();
            highScore = getHighScoreFromString(line);
            reader.close();
        } catch (IOException exception) {
            highScore = 0;
        }

    }

     /**
      * A helper method that returns an integer value of the high score from a
      * line in highscore.txt.
      *
      * @param line a string containing the high score value
      * @return the int value in the specified line
      */
    private int getHighScoreFromString(String line) {
        int high;
        try {
            high = Integer.parseInt(line.substring(12));
        } catch (IndexOutOfBoundsException |
                NullPointerException |
                NumberFormatException exception) {
            high = 0;
        }
        return high;
    }

     /**
      * Getter method for {@link Scores#score}.
      *
      * @return {@link Scores#score}
      */
    public int getScore() {
        return score;
    }

     /**
      * Sets {@link Scores#score} to the input, and {@link Scores#highScore}
      * to the input if the input is greater than {@link Scores#highScore}.
      *
      * @param score the current score in the game application
      */
    public void setScores(int score) {
        if (score >= 0) {
            this.score = score;
            if (highScore < score) {
                highScore = score;
            }
        } else {
            this.score = 0;
        }
    }

     /**
      * Resets {@link Scores#score} to 0.
      */
    public void reset() {
        score = 0;
    }

     /**
      * Getter method for {@link Scores#highScore}.
      *
      * @return {@link Scores#highScore}
      */
    public int getHighScore() {
        return highScore;
    }


     /**
      * Getter method for {@link Scores#allScores}.
      *
      * @return {@link Scores#allScores}
      */
    public LinkedList<Integer> getAllRecordedScores() {
        return allScores;
    }

     /**
      * Adds {@link Scores#score} to {@link Scores#allScores}.
      */
    public void recordScore() {
        allScores.add(score);
    }

     /**
      * Writes {@link Scores#allScores} and {@link Scores#highScore} to their
      * respective files.
      */
    public void writeToFile() {
        writeScoresToFile();
        writeHighScoreToFile();
    }

     /**
      * Writes {@link Scores#allScores} to scores.txt.
      */
    private void writeScoresToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("files/scores.txt", false));
            int gameNumber = 0;
            writer.write("Scores:");
            writer.newLine();
            for (int score: allScores) {
                gameNumber++;
                writer.write("game " + gameNumber + ": " + score);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

     /**
      * Writes {@link Scores#highScore} to highscore.txt.
      */
    private void writeHighScoreToFile() {
        try {
            String stringOfHighScore = "High Score: " + highScore;
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("files/highscore.txt", false));
            writer.write(stringOfHighScore);
            writer.flush();
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}