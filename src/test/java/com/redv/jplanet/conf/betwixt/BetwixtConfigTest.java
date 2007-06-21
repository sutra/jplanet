/**
 * 
 */
package com.redv.jplanet.conf.betwixt;

import java.io.File;

import junit.framework.TestCase;

import org.apache.commons.lang.SystemUtils;

import com.redv.jplanet.Planet;
import com.redv.jplanet.Subscription;
import com.redv.jplanet.User;

/**
 * @author sutra
 * 
 */
public class BetwixtConfigTest extends TestCase {

	/*
	 * （非 Javadoc）
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * {@link com.redv.jplanet.conf.betwixt.BetwixtConfigReader#read()} 的测试方法。
	 * 
	 * @throws Exception
	 */
	public void testRead() throws Exception {
		Planet planet = new Planet();
		planet.setAdminEmail("adminEmail");
		planet.setAdminName("adminName");
		planet.setCopyright("copyright");
		planet.setDescription("description");
		planet.setGroupingDateFormat("yyyy-MM-dd");
		planet.setKeywords("keyword,keyword");
		planet.setLanguage("zh-CN");
		planet.setMailSubject("mailSubject");
		planet.setPostDateFormat("yyyy-MM-dd HH:mm:ss");
		planet.setSiteUrl("http://example.com/");
		planet.setTitle("title");
		planet.setUpdatePeriod(12L);

		planet.addEditor(new User("http://sutra.openid.org.cn/"));
		planet.addEditor(new User("http://another.example.com/"));

		Subscription subscription = new Subscription();
		subscription.setDescription("description");
		subscription.setFeedUrl("http://feed.example.com/");
		subscription.setSiteUrl("http://example.com/");
		subscription.setTitle("example");

		planet.addSubscription(subscription);

		File tmpFile = File.createTempFile("jplanet", "BetwixtConfigTest",
				SystemUtils.getJavaIoTmpDir());
		try {
			BetwixtConfigWriter bcw = new BetwixtConfigWriter(tmpFile);
			bcw.write(planet);

			BetwixtConfigReader bcr = new BetwixtConfigReader(tmpFile);
			Planet readPlanet = bcr.read();
			assertEquals(planet, readPlanet);
		} finally {
			if (!tmpFile.delete()) {
				tmpFile.deleteOnExit();
			}
		}
	}

	private void assertEquals(final Planet expected, final Planet actual) {
		assertEquals(expected.getAdminEmail(), actual.getAdminEmail());
		assertEquals(expected.getAdminName(), actual.getAdminName());
		assertEquals(expected.getCopyright(), actual.getCopyright());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getEditors(), actual.getEditors());
		assertEquals(expected.getGroupingDateFormat(), actual
				.getGroupingDateFormat());
		assertEquals(expected.getKeywords(), actual.getKeywords());
		assertEquals(expected.getLanguage(), actual.getLanguage());
		assertEquals(expected.getMailSubject(), actual.getMailSubject());
		assertEquals(expected.getPostDateFormat(), actual.getPostDateFormat());
		assertEquals(expected.getSiteUrl(), actual.getSiteUrl());
		assertEquals(expected.getSubscriptions(), actual.getSubscriptions());
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getUpdatePeriod(), actual.getUpdatePeriod());
	}
}
