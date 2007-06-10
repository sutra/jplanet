/**
 * 
 */
package com.redv.jplanet;

/**
 * @author sutra
 * 
 */
public class User {
	private String openid;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param openid
	 */
	public User(String openid) {
		super();
		this.openid = openid;
	}

	/**
	 * @return openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * @param openid
	 *            要设置的 openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((openid == null) ? 0 : openid.hashCode());
		return result;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final User other = (User) obj;
		if (openid == null) {
			if (other.openid != null)
				return false;
		} else if (!openid.equals(other.openid))
			return false;
		return true;
	}

}
