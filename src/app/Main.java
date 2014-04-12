package app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.script.ScriptException;

import nashorn.NashornEngine;

public class Main {

	public static final NashornEngine ENGINE = new NashornEngine();
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ScriptException {
		try (Reader r = new FileReader("src/app/para.js")) {
			ENGINE.eval(r);
		}
	}

}