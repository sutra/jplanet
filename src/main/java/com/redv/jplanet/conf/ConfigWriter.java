/**
 * 
 */
package com.redv.jplanet.conf;

import com.redv.jplanet.Planet;

/**
 * @author sutra
 * 
 */
public interface ConfigWriter {
	void write(Planet planet) throws Exception;
}
