import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RunCheck implements Runnable 
{
	private String fileName;
	static Jagger parser;

	public RunCheck(String fileName)
	{
		this.fileName = fileName;
	}

	public void run()
	{
		InputStream is;
		try {
		    is = new FileInputStream(fileName);
		    if(parser == null)
		    	parser = new Jagger(is);
		    else
		    	parser.ReInit(is);
		    parser.mainloop();
		    is.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}