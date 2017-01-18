package isen.java22017.practical2.pagebuilder.app;

import isen.java22017.practical2.pagebuilder.builder.PageBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nicolas on 17/01/17.
 */
public class Application {
    public static void main(String[] args) throws IOException {

        //Initialisation des paths
        String current = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir:" + current);
        Path startFile = Paths.get("src", "isen", "java22017", "practical2", "pagebuilder", "index.html");
        System.out.println(startFile);
        Path outputFile = Paths.get("src", "isen", "java22017", "practical2", "pagebuilder", "output.html");
        System.out.println(outputFile);


        try {
//            test01(startFile, outputFile);
            test02(startFile, outputFile);
        }catch (IOException e){
            e.printStackTrace();
        }

//        test03();

    }


    //_______________________________________________
    /* TEST FUNCTIONS */

    /**
     * create a PageBuilder and write in output.html
     *
     * @throws IOException
     */
    public static void test01(Path startFile, Path outputFile) throws IOException {
        System.out.println("Start Test01 : \n");
        PageBuilder pageBuilder = new PageBuilder(startFile, outputFile);
        try {
            pageBuilder.build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test02(Path startFile, Path outputFile) throws IOException {
        System.out.println("Start Test02 : \n");
        PageBuilder pageBuilder = new PageBuilder(startFile, outputFile);
        try {
            System.out.println("start buffer");
            BufferedWriter bufferedWriter = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8);
            System.out.println("start writeFileContent");
            String fileName = startFile.getFileName().toString();
            pageBuilder.writeFileContent(fileName, bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test03(){
        PageBuilder builder = new PageBuilder();
        System.out.println(builder.getFileToInclude("test line")); // prints null
        System.out.println(builder.getFileToInclude("[[path/to/file]]")); // prints path/to/file
    }


}

