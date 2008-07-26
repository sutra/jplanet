/**
 * 
 */
package com.redv.jplanet.web.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;

import com.redv.jplanet.User;
import com.redv.jplanet.conf.Config;

/**
 * Process admin login.
 * 
 * @author <a href="mailto:zhoushuqun@gmail.com">Sutra Zhou</a>
 * 
 */
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3110230349380386899L;

	private ConsumerManager manager;

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			manager = new ConsumerManager();
		} catch (ConsumerException e) {
			throw new SecurityException(e);
		}

	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Identifier identifier = this.verifyResponse(req);
		boolean ok = false;
		if (identifier != null) {
			User editor = new User(identifier.getIdentifier());
			if (Config.getInstance().getPlanet().getEditors().contains(editor)) {
				req.getSession().setAttribute("editor", editor);
				resp.sendRedirect("planet");
				ok = true;
			} else {
				req.setAttribute("openid_identifier", editor.getOpenid());
				req.setAttribute("error", "error.NOT_EDITOR");
			}
		}
		if (!ok) {
			this.getServletContext().getRequestDispatcher("/admin/login.jsp")
					.forward(req, resp);
		}
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String openid_identifier = req.getParameter("openid_identifier");
		req.setAttribute("openid_identifier", openid_identifier);
		this.authRequest(openid_identifier, req, resp);
	}

	// --- placing the authentication request ---
	@SuppressWarnings("unchecked")
	public String authRequest(String userSuppliedString,
			HttpServletRequest httpReq, HttpServletResponse httpResp)
			throws IOException, ServletException {
		try {
			// configure the return_to URL where your application will
			// receive
			// the authentication responses from the OpenID provider
			String returnToUrl = httpReq.getRequestURL().toString();

			// perform discovery on the user-supplied identifier
			List discoveries = manager.discover(userSuppliedString);

			// attempt to associate with the OpenID provider
			// and retrieve one service endpoint for authentication
			DiscoveryInformation discovered = manager.associate(discoveries);

			// store the discovery information in the user's session
			httpReq.getSession().setAttribute("openid-disc", discovered);

			// obtain a AuthRequest message to be sent to the OpenID
			// provider
			AuthRequest authReq = manager.authenticate(discovered, returnToUrl);

			// Attribute Exchange example: fetching the 'email' attribute
			FetchRequest fetch = FetchRequest.createFetchRequest();
			fetch.addAttribute("email",
			// attribute alias
					"http://schema.openid.net/contact/email", // type URI
					true); // required

			// attach the extension to the authentication request
			authReq.addExtension(fetch);

			if (!discovered.isVersion2()) {
				// Option 1: GET HTTP-redirect to the OpenID Provider endpoint
				// The only method supported in OpenID 1.x
				// redirect-URL usually limited ~2048 bytes
				httpResp.sendRedirect(authReq.getDestinationUrl(true));
				return null;
			} else {
				// Option 2: HTML FORM Redirection (Allows payloads >2048 bytes)

				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/admin/formredirection.jsp");
				httpReq.setAttribute("prameterMap", httpReq.getParameterMap());
				httpReq.setAttribute("message", authReq);
				dispatcher.forward(httpReq, httpResp);
			}
		} catch (OpenIDException e) {
			// present error to the user
			// httpResp.getWriter().write("Error: " + e.getMessage());
			httpReq.setAttribute("openIDException", e);
			this.getServletContext().getRequestDispatcher("/admin/login.jsp")
					.forward(httpReq, httpResp);
		}

		return null;
	}

	// --- processing the authentication response ---
	public Identifier verifyResponse(HttpServletRequest httpReq) {
		try {
			// extract the parameters from the authentication response
			// (which comes in as a HTTP request from the OpenID provider)
			ParameterList response = new ParameterList(httpReq
					.getParameterMap());

			// retrieve the previously stored discovery information
			DiscoveryInformation discovered = (DiscoveryInformation) httpReq
					.getSession().getAttribute("openid-disc");

			// extract the receiving URL from the HTTP request
			StringBuffer receivingURL = httpReq.getRequestURL();
			String queryString = httpReq.getQueryString();
			if (queryString != null && queryString.length() > 0)
				receivingURL.append("?").append(httpReq.getQueryString());

			// verify the response; ConsumerManager needs to be the same
			// (static) instance used to place the authentication request
			VerificationResult verification = manager.verify(receivingURL
					.toString(), response, discovered);

			// examine the verification result and extract the verified
			// identifier
			Identifier verified = verification.getVerifiedId();
			if (verified != null) {
				AuthSuccess authSuccess = (AuthSuccess) verification
						.getAuthResponse();

				if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX)) {
					// FetchResponse fetchResp = (FetchResponse) authSuccess
					// .getExtension(AxMessage.OPENID_NS_AX);

					// List emails = fetchResp.getAttributeValues("email");
					// String email = (String) emails.get(0);
				}

				return verified; // success
			}
		} catch (OpenIDException e) {
			// present error to the user
		}

		return null;
	}
}
