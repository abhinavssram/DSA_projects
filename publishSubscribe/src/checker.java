import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class checker
{
	public static void main ( String args [])
	{
		BufferedReader br = null;
		pubsubSystem r = new pubsubSystem();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("/Users/abhinavsamala/Desktop/publishSubscribe/src/actions3.txt"));

			while ((actionString = br.readLine()) != null) {
//				r.performAction(actionString);
				System.out.println(r.performAction(actionString));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}
