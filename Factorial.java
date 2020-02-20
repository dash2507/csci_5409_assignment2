import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Factorial {
  public static void main(String args[]) {
    storeRandomNumbers();
    List<Integer> numbers = readNumbers();
    printFactorial(numbers);
  }

  public static void storeRandomNumbers() {
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < 100; i++)
      list.add(generateRandomIntIntRange(0,15));
    Collections.shuffle(list);
    try {
      File file = new File("./input.txt");
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file);
      BufferedWriter bw = new BufferedWriter(fw);
      for (int i = 0; i < list.size(); i++) {
        bw.write(list.get(i).toString());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    } catch (IOException e) {

    }
  }

  public static List<Integer> readNumbers() {
    List<Integer> list = new ArrayList<Integer>();
    File file = new File("./input.txt");
    BufferedReader reader = null;

    try {
      reader = new BufferedReader(new FileReader(file));
      String text = null;

      while ((text = reader.readLine()) != null) {
        list.add(Integer.parseInt(text));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
      }
    }
    return list;
  }

  public static void printFactorial(List<Integer> numbers) {
    for (int number : numbers) {
      int i, fact = 1;
      for (i = 1; i <= number; i++) {
        fact = fact * i;
      }
      System.out.println("Factorial of " + number + " is: " + fact);
    }
  }

  public static int generateRandomIntIntRange(int min, int max) {
    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
  }
}
