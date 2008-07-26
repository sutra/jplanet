/**
 * 
 */
package com.redv.jplanet.conf;

import com.redv.jplanet.Planet;

/**
 * The interface of config reader.
 * 
 * @author <a href="mailto:zhoushuqun@gmail.com">Sutra Zhou</a>
 * 
 */
public interface ConfigReader {
	/**
	 * Reader planet configration from file or other storage.
	 * 
	 * @return the planet.
	 * @throws Exception
	 *             read exception occured, such as file not found or IO
	 *             exception.
	 */
	Planet read() throws Exception;
}
