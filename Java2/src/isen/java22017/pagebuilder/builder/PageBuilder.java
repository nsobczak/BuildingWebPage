package isen.java22017.pagebuilder.builder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nicolas on 17/01/17.
 */
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
    public void build() throws IOException
    {
        BufferedWriter bufferedWriter = Files.newBufferedWriter(
                this.outputFile, StandardCharsets.UTF_8);
    }


}
