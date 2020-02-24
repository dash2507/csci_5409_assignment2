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

class Fibonacci {
  static FileWriter csvWriter;

  public static void main(String args[]) throws IOException {
    csvWriter = new FileWriter("fibonacci.csv");
    makeHeaders("Number", "Time");
    storeRandomNumbers();
    List<Integer> numbers = readNumbers();
    printFibonacci(numbers);
  }

  public static void storeRandomNumbers() {
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < 100; i++)
      list.add(generateRandomIntIntRange(1,15));
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

  public static void printFibonacci(List<Integer> numbers) {
    long start, diff;
    for (int number : numbers) {
      int i, fibo = 0;
      start = System.nanoTime();
      System.out.print("Fibonacci of " + number + " is: ");
      for (i = 0; i < number; i++) {
        fibo += i;
        System.out.print(fibo+" ");
      }
      diff = System.nanoTime() - start;
      System.out.println();
      writeRow(number, diff);
    }
  }

  public static int generateRandomIntIntRange(int min, int max) {
    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
  }

  public static void makeHeaders(String ... headers) throws IOException {
    for( int i=0; i<headers.length-1;i++){
      csvWriter.append(headers[i]+",");
  }
  csvWriter.append(headers[headers.length-1]+"\n");
  csvWriter.flush();
  }

  public static void writeRow(int number, long time){
    try {
        csvWriter.append(number + ",");
        csvWriter.append(time + "\n");
        csvWriter.flush();
    }catch (IOException e){
        System.err.println("Can't write Row");
    }
}
}
