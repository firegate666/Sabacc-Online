/*
 * Created on 06.02.2006
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package sabacc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WebStartFileProvider {

	protected static String DEFAULT_RESSOURCE_DIR = "resource/";
	
	public static void setRessourceDir(String pathname) {
		if (!pathname.endsWith("/"))
			pathname += "/";
		DEFAULT_RESSOURCE_DIR = pathname;
	}
	
	public static File getFile(String string) throws IOException {
		File file = new File(string);
		WebStartFileProvider wsfp = new WebStartFileProvider();
		if (file.exists() && file.canRead()) {
			return file;
		} else {
			InputStream is = wsfp.getClass().getResourceAsStream(
					"/" + DEFAULT_RESSOURCE_DIR + file.getName());
			File returnfile = File.createTempFile("temp", file.getName());
			BufferedWriter bw = new BufferedWriter(new FileWriter(returnfile));
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String str;
			while ((str = in.readLine()) != null) {
				bw.write(str);
			}
			bw.flush();
			is.close();
			in.close();
			bw.close();
			return returnfile;
		}
	}

}