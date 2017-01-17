package isen.java22017.pagebuilder.app;

import isen.java22017.pagebuilder.builder.PageBuilder;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by nicolas on 17/01/17.
 */
public class Application
{
    public static void main(String[] args) throws IOException
    {
        test01();

    }


    //_______________________________________________
    /* TEST FUNCTIONS */

    /**
     * create a PageBuilder and write in output.html
     *
     * @throws IOException
     */
    public static void test01() throws IOException
    {
        PageBuilder pageBuilder = new PageBuilder(
                Paths.get("src", "isen", "java22017", "practical2", "pagebuilder", "index.html "),
                Paths.get("src", "isen", "java22017", "practical2", "pagebuilder", "output.html "));
        pageBuilder.build();
    }


}
