package isen.java22017.pagebuilder.builder;

import isen.java22017.practical2.nio.sorter.FileSorter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by nicolas on 17/01/17.
 */
//TODO : Overload build method pour faire en sorte qu'elle écrive le contenu d'une ligne dans l'output.html
//TODO : WriteFileContent
public class PageBuilder
{
    /* ATTRIBUTES*/

    private Path root;
    private Path startFile;
    private Path outputFile;

    //_______________________________________________

    /**
     * Default constructor
     */
    public PageBuilder()
    {
        this.root = null;
        this.startFile = null;
        this.outputFile = null;
    }

    /**
     * Constructor
     * It initializes the root attribute by getting the parent of the startFile
     * It fills the startFile attribute with the startFile parameter
     * It fills the outputFile attribute with the outputFile parameter.
     *
     * @param startFile
     * @param outputFile
     */
    public PageBuilder(Path startFile, Path outputFile)
    {

        this.root = startFile.getParent();
        this.startFile = startFile;
        this.outputFile = outputFile;
    }


    //_______________________________________________
    /* SETTERS */
    public void setRoot(Path root)
    {
        this.root = root;
    }

    public void setStartFile(Path startFile)
    {
        this.startFile = startFile;
    }

    public void setOutputFile(Path outputFile)
    {
        this.outputFile = outputFile;
    }


    //_______________________________________________
    /* GETTERS */
    public Path getRoot()
    {
        return root;
    }

    public Path getStartFile()
    {
        return startFile;
    }

    public Path getOutputFile()
    {
        return outputFile;
    }


    //_______________________________________________
    /* METHODS */

    /**
     * BUILD writes a lines in output.html and creates this file if necessary
     *
     * @throws IOException
     */
    public void build() throws IOException
    {
        if (Files.notExists(this.outputFile))
        {
            System.out.println("le fichier n'existe pas => création du fichier");
            Files.createFile(this.outputFile);
        }
        BufferedWriter bufferedWriter = Files.newBufferedWriter(
                this.outputFile, StandardCharsets.UTF_8);
        //write
        bufferedWriter.write("I'm the first line written in this file");
        //save what has been written
        bufferedWriter.flush();
    }

    /**
     * BUILD writes a line in the output file
     *
     * @param line
     * @throws IOException
     */
    public void build(String line) throws IOException
    {
        if (Files.notExists(this.outputFile))
        {
            System.out.println("le fichier n'existe pas => création du fichier");
            Files.createFile(this.outputFile);
        }
        BufferedWriter bufferedWriter = Files.newBufferedWriter(
                this.outputFile, StandardCharsets.UTF_8);
        //write
        bufferedWriter.write("I'm the first line written in this file");
        //save what has been written
        bufferedWriter.flush();
    }


    /**
     * writeFileContent écrit le contenu d'un fichier dans output.html
     *
     * @param filename
     * @param writer
     * @throws IOException
     */
    public void writeFileContent(String filename, Writer writer) throws IOException
    {
        Path root = null;

        System.out.println("filename being written : " + filename);
        Path path = root.resolve(filename);

        BufferedReader bufferedReader = Files.newBufferedReader(
                path, StandardCharsets.UTF_8);

        //TODO HERE: un for
        build(bufferedReader.readLine());


    }

}
