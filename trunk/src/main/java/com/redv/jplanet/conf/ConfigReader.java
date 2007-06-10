/**
 * 
 */
package com.redv.jplanet.conf;

import com.redv.jplanet.Planet;

/**
 * @author sutra
 * 
 */
public interface ConfigReader {
	Planet read() throws Exception;
}
