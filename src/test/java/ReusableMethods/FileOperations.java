package ReusableMethods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
	
	private File file;
	private FileWriter fw = null;
	private String tabSpacing = "\t\t\t\t\t";

	public FileOperations(String path) throws IOException {
		file = new File(path);

		if (file.exists()) {
			System.out.println("Textfile already present!");
		} else {

			file.createNewFile();
			System.out.println("New textfile created at: " + path);

		}

		fw = new FileWriter(file);
	}

	public void writeToTextFile(String content) throws Exception {

		if (this.fw == null) {
			throw new Exception("File not found");
		}
		this.fw.write(content);
		this.fw.flush();

	}

	public void writeHeader(String index, String gameName, String pageUrl, String statusCode, String tournamentCount)
			throws Exception {
		writeToTextFile(index + this.tabSpacing + gameName + this.tabSpacing + pageUrl + this.tabSpacing + statusCode
				+ this.tabSpacing + tournamentCount + "\n");
	}

	public void writeData(int index, String gameName, String pageUrl, int statusCode, String tournamentCount)
			throws Exception {
		writeToTextFile(index + this.tabSpacing + gameName + this.tabSpacing + pageUrl + this.tabSpacing + statusCode
				+ this.tabSpacing + tournamentCount + "\n");
	}
}
