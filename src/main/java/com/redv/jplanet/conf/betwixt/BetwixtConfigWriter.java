/**
 * 
 */
package com.redv.jplanet.conf.betwixt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.betwixt.io.BeanWriter;

import com.redv.jplanet.Planet;
import com.redv.jplanet.conf.ConfigWriter;
import com.redv.jplanet.conf.Constant;

/**
 * @author sutra
 * 
 */
public class BetwixtConfigWriter implements ConfigWriter {

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.redv.jplanet.conf.ConfigWriter#write(com.redv.jplanet.Planet)
	 */
	public void write(Planet planet) throws Exception {
		Writer output = new OutputStreamWriter(new FileOutputStream(new File(
				Constant.getDataDir(), "jplanet.xml")), "UTF-8");
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
