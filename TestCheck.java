import java.io.File;
import java.io.FileNotFoundException;

public class TestCheck
{
    public static void main(String args[]) throws InterruptedException,FileNotFoundException
    {
        final File folder = new File("./check");
        String fileName;
        for( final File fileEntry:folder.listFiles() )
        {
            fileName = folder.getName()+"/"+fileEntry.getName();
            if(fileEntry.isDirectory())
                continue;
            System.out.println(fileName+"\n"); 
            Thread t = new Thread(new RunCheck(fileName));
            t.start();
            t.join();
            System.out.println("\n");
        }
    }
}