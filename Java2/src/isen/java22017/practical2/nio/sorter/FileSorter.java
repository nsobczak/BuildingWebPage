package isen.java22017.practical2.nio.sorter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

//TODO: sortFiles

/**
 * Created by Vincent Reynaert and Nicolas Sobczak on 16/01/2017.
 */
public class FileSorter
{
    /* ATTRIBUTES */
    private Path root;
    private Path archive;
    private Path byExtension;
    private Path animals;


    //_______________________________________________________
    /**
     * Default constructor
     */
    public FileSorter()
    {
        this.root = null;
        this.archive = null;
        this.byExtension = null;
        this.animals = null;
    }

    /**
     * Constructor
     *
     * @param rootDirectory
     * @throws IOException
     */
    public FileSorter(String rootDirectory) throws IOException
    {
        //===initialize the root attribute===
        this.root = Paths.get(rootDirectory);

        //===initialize the animals attribute===
        Path pathToAnimal = Paths.get("src", "isen", "java22017", "practical2", "animal");
        this.animals = root.resolve(pathToAnimal);

        //===initialize the archive attribute===
        Path pathToArchive = Paths.get(this.root.toString(), "src", "isen", "java22017", "practical2");
        try
        {
            //Création du répertoire archive s'il n'existe pas
            this.archive = prepareDirectory("archive", pathToArchive);
        } catch (IOException e)
        {
            System.out.println("Le répertoire archive existe déjà");
            this.archive = pathToArchive.resolve("archive");
//            e.printStackTrace();
        }

        //===initialize the byExtension attribute===
        Path pathToByExt = Paths.get(this.root.toString(), "src", "isen", "java22017", "practical2");
        try
        {
            //Création du répertoire archive s'il n'existe pas
            this.byExtension = prepareDirectory("byext", pathToByExt);
        } catch (IOException e)
        {
            System.out.println("coucou");
            System.out.println("Le répertoire byExtension existe déjà");
            this.byExtension = pathToByExt.resolve("byext");
//            e.printStackTrace();
        }
    }


    //______________________________________________________________
    /* SETTERS */
    public void setRoot(Path root)
    {
        this.root = root;
    }

    public void setArchive(Path archive)
    {
        this.archive = archive;
    }

    public void setByExtension(Path byExtension)
    {
        this.byExtension = byExtension;
    }

    public void setAnimals(Path animals)
    {
        this.animals = animals;
    }


    //______________________________________________________________
    /* GETTERS */
    public Path getRoot()
    {
        return root;
    }

    public Path getArchive()
    {
        return archive;
    }

    public Path getByExtension()
    {
        return byExtension;
    }

    public Path getAnimals()
    {
        return animals;
    }


    //______________________________________________________________
    /* FUNCTIONS */
    /**
     * PREPAREDIRECTORY
     * It creates a Path “by resolving the newDir from the base”.
     * If the created Path does not exist, the method creates it in the file system.
     * Finally, the method returns the Path.
     *
     * @param newDir: le nouveau repertoire
     * @param base:   le chemin vers le dossier ou l'on veut creer le repertoire
     * @return
     * @throws IOException
     */
    public Path prepareDirectory(String newDir, Path base) throws IOException
    {
        Path newDirectory = null;
        // Si c'est un directory
        if (Files.isDirectory(base))
        {
            // S'il n'existe pas
            if (Files.notExists(base))
            {
                // Creation du directory
                System.out.println("Creation du directory");
                Files.createDirectories(base);
            }
            // On append le newDir a la base
            System.out.println("append le newDir a la base");
            newDirectory = base.resolve(newDir);
            try
            {
                Files.createDirectory(newDirectory);
            } catch (FileAlreadyExistsException e)
            {
//                e.printStackTrace();
                System.out.println("prepareDirectory : le répertoire existe deja !");
            }
        } else
        {
            // Sinon on souleve l'exception
            throw new IOException();
        }
        System.out.println(newDirectory);
        return newDirectory;
    }


    /**
     * GETEXTENSION
     * From the entry, it gets the filename then returns a substring
     * which corresponds to everything after the last dot of the filename.
     *
     * @param entry: le chemin du fichier a analyser
     * @return: l'extension du fichier
     */
    public String getExtension(Path entry)
    {
        String file = null;
        String result = null;

        System.out.println(entry);
        file = entry.getFileName().toString();
        String[] parts = file.split("\\.");
        result = parts[parts.length - 1];

        return result;
    }


    /**
     * COPYFILE
     * Inside the directory,
     * it resolves the filename of the entry in order to build the target of the copy.
     * Finally, it copies the entry to the built target.
     *
     * @param entry:     chemin vers le fichier que l'on veut copier
     * @param directory: chemin vers le dossier ou l'on veut copier le fichier
     * @throws IOException
     */
    public void copyFile(Path entry, Path directory) throws IOException
    {
        if (Files.notExists(entry))
        {
            System.out.println("le fichier n'existe pas !");
            throw new IOException();
        } else
        {
            Path filename = entry.getFileName();
            System.out.println("filename : " + filename);
            Path prepDir = directory.resolve(filename);
            System.out.println("prepDir : " + prepDir);
            Files.copy(entry, prepDir);
        }
    }


    /**
     * MOVEFILETOARCHIVE
     * Inside the archive attribute,
     * it resolves the filename of the entry in order to build the target of the copy.
     * Finally, it moves the entry to the target.
     *
     * @param entry
     * @throws IOException
     */
    public void moveFileToArchive(Path entry) throws IOException
    {
        if (Files.notExists(entry))
        {
            System.out.println("le fichier n'existe pas !");
            throw new IOException();
        } else
        {
            //Création du répertoire archive s'il n'existe pas
            try
            {
                prepareDirectory("archive", Paths.get("src", "isen", "java22017", "practical2"));
            } catch (IOException e)
            {
//                e.printStackTrace();
            }
            //Déplacement du fichier
            Path filename = entry.getFileName();
            Path prepDir = archive.resolve(filename);
            Path file = Files.move(entry, prepDir, StandardCopyOption.REPLACE_EXISTING);
        }
    }


    /**
     * SORTFILES
     * Sort files by extension in the "byextension" folder
     *
     * @return
     * @throws IOException
     */
    public int sortFiles() throws IOException
    {
        int numberOfSortedEntry = 0;

        if (this.animals != null)
        {
            //Lister les entrées de animal
            DirectoryStream<Path> listOfPaths = Files.newDirectoryStream(this.animals);

            for (Path entryPath : listOfPaths)
            {
                if (Files.isRegularFile(entryPath))
                {
                    //get the filename of the entry then deduce the extension;
                    Path fileName = (entryPath).getFileName();
                    String extension = getExtension(fileName);
                    System.out.println("extension :" + extension);

                    //create if necessary the folder with the extension name in the Path byExtension (remember prepareDirectory!);
                    Path createdFolder = prepareDirectory(extension, this.byExtension);

                    //copy the entry inside the created folder;
                    copyFile(entryPath, createdFolder);

                    //move the entry to the archive;
                    moveFileToArchive(entryPath);

                    numberOfSortedEntry++;
                } else
                {
                    System.out.println("le document n'est pas un fichier");
                }
            }
        } else
        {
            System.out.println("Path to animals undefined");
        }


        //return the number of entries sorted.
        return numberOfSortedEntry;
    }


}
