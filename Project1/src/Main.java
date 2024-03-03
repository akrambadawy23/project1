import java.io.*;
import java.nio.file.Files;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


static File f1file = new File("f1.txt");
static  File f2file = new File("f2.txt");
static  File f3file = new File("f3.txt");
static  File f4file = new File("f4.txt");
static  File f5file = new File("f5.txt");
static File f6file = new File("f6.txt");



    public static void main(String[] args) {





        long startTime = System.nanoTime();
        System.out.println(readFile(f1file));
        long endTime = System.nanoTime();
        System.out.println("TIME RUN: " + (endTime-startTime)/1000000000 + " SECONDS");
    }

    public static String readFile(File f) {
        String fin = "";
        try(BufferedReader br = new BufferedReader(new FileReader(f))) {
StringBuilder s = new StringBuilder();
String num = br.readLine();
while(num != null) {
    s.append(num);
    s.append(System.lineSeparator());
    num = br.readLine();
}
fin = s.toString();
        } catch (IOException e) {
            System.out.println(f.toString() + " not found");
            System.exit(-1);
        }


        return fin;
    }
}