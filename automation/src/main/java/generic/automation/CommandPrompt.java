package generic.automation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandPrompt {
	Process process;
	ProcessBuilder builder;

	public String runCommand(String command) {
		String allLine = "";

		try {
			String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				this.builder = new ProcessBuilder(new String[]{"cmd.exe", "/c", command});
				this.builder.redirectErrorStream(true);
				Thread.sleep(1000L);
				this.process = this.builder.start();
			} else {
				this.process = Runtime.getRuntime().exec(command);
			}

			BufferedReader buffReader = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
			String line = "";

			while ((line = buffReader.readLine()) != null) {
				allLine = allLine + line + "\n";
				if (line.contains("Console LogLevel: debug")) {
					break;
				}
			}
		} catch (Exception var6) {
			var6.printStackTrace();
		}

		return allLine;
	}
}