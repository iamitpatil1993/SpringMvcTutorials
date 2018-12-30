/**
 * 
 */
package com.example.mvc.web;
import java.util.List;

import org.aspectj.lang.annotation.RequiredTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mvc.dto.Spittle;
import com.example.mvc.service.SpittleRepository;

/**
 * @author amit
 *
 */

@Controller
@RequestMapping(path = "/spittles")
public class SpittleCotroller {
	
	private static final Logger logger = LoggerFactory.getLogger(SpittleCotroller.class);

	@Autowired
	private SpittleRepository spittleRepository;
	
	/**
	 * Version -1 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String spittles(@RequestParam(name = "max", defaultValue = "20") Long max,
			@RequestParam(name = "count", defaultValue = "20") Integer count, Model model) {
		List<Spittle> spittles = spittleRepository.findSpittles(max, count);
		model.addAttribute(spittles); // Model attribute name will be auto-generated or inferred from type of attribute object.
		return "spittles";
	}
	
	
	/**
	 * Version -2
	 * This is how can explicitly pass attribute name for model object
	 */
	/*@RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model) {
		List<Spittle> spittles = spittleRepository.findSpittles(10, 20);
		model.addAttribute("spittleList", spittles); 
		return "spittles";
	}*/
	
	/**
	 * Version -3
	 * We can ask for non java type as well for model, if we do not want to use Spring Model class.
	 */
	/*@RequestMapping(method = RequestMethod.GET)
	public String spittles(Map model) { 
		List<Spittle> spittles = spittleRepository.findSpittles(10, 20);
		model.put("spittleList", spittles);
		return "spittles";
	}*/
	
	/**
	 * This is smartest version, 
	 * Model -> will be attribute containing return value
	 * Attribute -> The one which are returning
	 * Attribute Name -> Will be derived from type of attribute we are returning from controller
	 * LogicalView Name -> Will be derived from and same as request path (Get - /spittles after removing leading / it will be 'spittles')
	 * @return
	 */
	/*@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles() { 
		return spittleRepository.findSpittles(10, 20);
	}*/
	
	/**
	 * PathParam - Version -1
	 * 
	 * Explicitly specify path param name in @PathVariable annotation to identify math variable to match to method argument.
	 */
/*	@RequestMapping(path = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable(name = "spittleId") Long spittleId, Model model) {
		model.addAttribute(spittleRepository.findById(spittleId));
		return "spittle";
	}*/
	
	/**
	 * PathParam - Version -2
	 * 
	 * If method parameter name is same as path variable name it automatically takes the value of path variable, we do not need 
	 * to explicitly path param name.
	 * BUT, if we change method argument name or path variable name, it will start failing, so we need to maintain consistency
	 * between path variable name and method argument name.
	 */
	@RequestMapping(path = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable Long spittleId, Model model) {
		model.addAttribute(spittleRepository.findById(spittleId));
		return "spittle";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String showRegisterPage() {
		return "register";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String register(Spittle spittle) {
		logger.info("Insidr register ...");
		spittleRepository.create(spittle);
		return "redirect:/spittles/profile/" + spittle.getUsername();
	}
	
	@RequestMapping(path = "/profile/{username}")
	public String showSpittleProfile(@PathVariable String username, Model model) {
		model.addAttribute("spitter", spittleRepository.findByUsername(username).get());
		return "profile";
	}
}
