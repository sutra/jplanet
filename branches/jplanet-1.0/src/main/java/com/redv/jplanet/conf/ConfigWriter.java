/**
 * 
 */
package com.redv.jplanet.conf;

import com.redv.jplanet.Planet;

/**
 * The writer of the config writer.
 * 
 * @author <a href="mailto:zhoushuqun@gmail.com">Sutra Zhou</a>
 * 
 */
public interface ConfigWriter {
	/**
	 * Write planet to the config file or other storage.
	 * 
	 * @param planet
	 *            the planet to save.
	 * @throws Exception
	 *             write exception occured, such as file not found or IO
	 *             exception.
	 */
	void write(Planet planet) throws Exception;
}
