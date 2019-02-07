/**
 * 
 */
package com.example.mvc.web;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mvc.dto.Spittle;
import com.example.mvc.exception.SpittleNotFoundException;
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
		return "spittle/spittles"; // we can specify directories as well. if spring files slash in view name it considers them as directory and append then to physical path as it is without doing anything. So, now we can organize our views in directories instead of having them in single flag /views directory
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
		return "spittle/spittle";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String showRegisterPage(Model model) {
		model.addAttribute(new Spittle()); // In order to render <sf:form> tag, there must be object in model, which form tag binds using 'modelAttribute' attribute of <sf:form> tag
		return "spittle/register";
	}
	
	/**
	 * 
	 * @param spittle
	 * @param validationErrors
	 * @param model We need this model attribute in order to put values of spring url template variables.
	 * @return
	 */
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute(name = "spittle") Spittle spittle, BindingResult validationErrors, RedirectAttributes model) {
		logger.info("Insidr register ...");
		logger.info("Recieved file, name :: {}, contentType :: {}, size :: {}", new Object[] {spittle.getProfilepicture().getOriginalFilename(), spittle.getProfilepicture().getContentType(), spittle.getProfilepicture().getSize()});
		if (validationErrors.hasErrors()) {
			validationErrors.addError(new ObjectError("spittle", "Neet bharana bhau form")); // this how we can add custom/object level/irrespective of any field add custom validation error message. It will be carried to view along with other validation errors in same manneer.
			return "spittle/register"; // we can't simply redirect, if we want to populate form again with submitted details, so we need to put submitted spittle object into model and just need to render the view
		}
		spittleRepository.create(spittle);
		model.addAttribute("username", spittle.getUsername()); // since this model attribute matches variable in spring url template, it will replace at matching variable in url template.
		model.addAttribute("spittleId", spittle.getId()); // any attribute of type primitive (single value) added to model will be set as a query parameter if it does not matches with named parameters.
		//model.addAttribute("spittle", spittle); // even though we add this as a attribute it won't be added as a query parameter because it is a java bean
		
		model.addFlashAttribute("spitter", spittle);
		return "redirect:/spittles/profile/{username}";
	}
	
	@RequestMapping(path = "/profile/{username}")
	public String showSpittleProfile(@PathVariable String username, Model model) {
		if (!model.containsAttribute("spitter")) {
			logger.info("Spittle object not available in model, fetching from repository ...");
			Optional<Spittle> spittleOptional = spittleRepository.findByUsername(username);
			if (!spittleOptional.isPresent()) {
				// Spring will check, is there any HTTP status code mapped to this exception using @responseStatus annotation, if yes it sends configured http status code to response.
				throw new SpittleNotFoundException("Spittle not found by username " + username);
			}
			model.addAttribute("spitter", spittleOptional.get());
		} else {
			logger.info("Spittle object available in model via flash attribute from last request, forwarding request to view ...");	
		}
		return "spittle/profile";
	}
}
