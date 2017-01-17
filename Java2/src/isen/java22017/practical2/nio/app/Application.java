package isen.java22017.practical2.nio.app;

import isen.java22017.practical2.nio.sorter.FileSorter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by vvinc_000 on 16/01/2017.
 */
public class Application
{
    public static void main(String[] args) throws IOException
    {
        //===Partie 1===
//        testPrepareDirectory();
//        testGetExtension();
//        testCopyFile();
//        testMoveFile();
//        testConstructor("");
//        testSortFiles("");

        //===Partie 2===


    }


    //_______________________________________________________
    /* FONCTIONS DE TEST */

    public static void testPrepareDirectory() throws IOException
    {
        FileSorter sorter = new FileSorter();
        Path path = sorter.prepareDirectory("newdir", Paths.get("src", "isen", "java22017", "practical2"));
        //System.out.println(path.getParent());
    }

    public static void testGetExtension() throws IOException
    {
        FileSorter sorter = new FileSorter();
        Path path = Paths.get("src", "isen", "java22017", "practical2", "test.txt");
        System.out.println(sorter.getExtension(path));
    }

    public static void testCopyFile() throws IOException
    {
        FileSorter sorter = new FileSorter();
        sorter.copyFile(
                Paths.get("src", "isen", "java22017", "practical2", "animal", "adelie-penguin.html"),
                Paths.get("src", "isen", "java22017", "practical2")
        );
    }

    public static void testMoveFile() throws IOException
    {
        FileSorter sorter = new FileSorter();
        Path archive = Paths.get("src", "isen", "java22017", "practical2", "archive");
//        System.out.println(archive);
        sorter.setArchive(archive);
//        System.out.println(sorter.getArchive());

        sorter.moveFileToArchive(
                Paths.get("src", "isen", "java22017", "practical2", "animal", "adelie-penguin.html")
        );
    }

    public static void testConstructor(String pathToRoot) throws IOException
    {
        FileSorter sorter = new FileSorter(pathToRoot);
        System.out.println("getAnimals : " + sorter.getAnimals());
        System.out.println("getArchive : " + sorter.getArchive());
        System.out.println("getRoot : " + sorter.getRoot());
        System.out.println("getByExtension : " + sorter.getByExtension());

    }

    public static void testSortFiles(String pathToRoot) throws IOException
    {
        System.out.println("===test 1===");
        FileSorter sorter1 = new FileSorter();
        System.out.println(sorter1.sortFiles());
        System.out.println("\n===test 2===");
        FileSorter sorter2 = new FileSorter(pathToRoot);
        System.out.println("\n\nnumber of sorted files : " + sorter2.sortFiles());
    }


}
