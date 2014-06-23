import java.awt.*;
import java.awt.datatransfer.*;
import java.util.regex.*;

public class CopyKing
{
	String list = ""; //unused 
	String prev = ""; //previous copy, stops infinite printing of last url
	int num = 1; //printed before each url, increments with each url copied

	public void run()
	{
		System.out.println("=============================================================");
		System.out.println("CopyKing by nhampshire");
		System.out.println("=============================================================");
		System.out.println("Listening...");
		while (true) {
                try {
                    Thread.sleep(1000);
                    getCopy();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
		
	}

	public void getCopy()
	{
		/* get clipboard */
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		/* get clipboard context */

		Transferable data = clipboard.getContents(null);

		/* is context string */

		boolean isText = ( ( data != null ) && ( data.isDataFlavorSupported( DataFlavor.stringFlavor ) ) );

		/* if yes print it and/or add to list (depending on version) */

		if ( isText ) {
		try
		{
			String URL_REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
			Pattern p = Pattern.compile(URL_REGEX);
			
			String s = (String)data.getTransferData( DataFlavor.stringFlavor );
			Matcher m = p.matcher(s);//replace with string to compare
			if(!s.equals(prev) && m.find())
			{
				prev = s;
				String thisCopy= num + ". " + s;
				System.out.println(thisCopy);
				num++;
			}

		} catch(Exception e){System.out.println(e);}
		}
	}

	public static void main(String[] args)
	{
		CopyKing king = new CopyKing();
		king.run();
	}
}
