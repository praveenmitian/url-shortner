package com.praveenmitian.urlshortner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller process the request and return the output value.
 * 
 * @author PRAVEEN
 *
 */
@Controller
public class DashboardController {

	@Autowired
	UrlJDBC urlJDBC;

	/**
	 * Controller process the request and redirect to home page.
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String handleHome() {
		return "redirect:/dashboard";
	}

	/**
	 * Controller process the request and return result to dashboard.
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getHome(Model model) {
		model.addAttribute("urls", urlJDBC.mostViewedUrls());
		model.addAttribute("count", urlJDBC.totalUrlsShortened());
		return "dashboard";
	}

	/**
	 * 
	 * Controller process the request and display output through get method.
	 * 
	 * @param urlId
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/go", method = RequestMethod.GET)
	public String redirectUrl(@RequestParam(value = "url") String urlId,
			Model model) {
		int count = urlJDBC.count(urlId);
		urlJDBC.update(urlId, count + 1);
		List<Url> urlList = urlJDBC.getUrl(urlId);
		if (urlList != null && urlList.size() > 0)
			return "redirect:" + urlList.get(0).getLongUrl();
		else
			return "Match not found for \'" + urlId + "\'";
	}
}
