/**
 * 
 */
package com.redv.jplanet.conf.betwixt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.betwixt.io.BeanWriter;

import com.redv.jplanet.Constants;
import com.redv.jplanet.Planet;
import com.redv.jplanet.conf.ConfigWriter;

/**
 * The implementation of config writer with <a
 * href="http://jakarta.apache.org/commons/betwixt/">Commons Betwixt</a>.
 * 
 * @author <a href="mailto:zhoushuqun@gmail.com">Sutra Zhou</a>
 * @see <a href="http://jakarta.apache.org/commons/betwixt/">Commons Betwixt</a>
 */
public class BetwixtConfigWriter implements ConfigWriter {

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.redv.jplanet.conf.ConfigWriter#write(com.redv.jplanet.Planet)
	 */
	public synchronized void write(final Planet planet) throws Exception {
		Writer output = new OutputStreamWriter(new FileOutputStream(new File(
				Constants.getDataDir(), "jplanet.xml")), "UTF-8");
		output.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		try {
			BeanWriter writer = new BeanWriter(output);
			writer.enablePrettyPrint();
			writer.write(planet);
		} finally {
			output.close();
		}
	}

}
