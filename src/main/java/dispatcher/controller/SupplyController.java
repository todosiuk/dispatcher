package dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import dispatcher.dao.ProviderDaoImpl;
import dispatcher.dao.SupplyDaoImpl;
import dispatcher.entity.Supply;

@Controller
@RequestMapping("/supplyController")
public class SupplyController {

	@Autowired
	private SupplyDaoImpl supplyDao;

	@Autowired
	private ProviderDaoImpl providerDao;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showFormOfAddingSupply(@RequestParam(value = "idProvider", required = true) Integer idProvider,
			Model model) {
		Supply supply = new Supply();
		supply.setProvider(providerDao.findById(idProvider));
		model.addAttribute("supplyAttribute", supply);
		return "formOfAddingSupply";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addingSupply(@RequestParam(value = "idProvider", required = true) Integer idProvider,
			@ModelAttribute("supplyAttribute") Supply supply) {
		supplyDao.create(idProvider, supply);
		return "success";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteSupply(@RequestParam(value = "idSupply", required = true) Integer idSupply) {
		supplyDao.delete(idSupply);
		return "success";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String showFormOfUpdatingSupply(@RequestParam(value = "idProvider", required = true) Integer idProvider,
			@RequestParam(value = "idSupply", required = true) Integer idSupply, Model model) {
		Supply supply = new Supply();
		supply.setProvider(providerDao.findById(idProvider));
		model.addAttribute("idProvider", idProvider);
		model.addAttribute("supplyAttribute", supplyDao.findById(idSupply));
		return "formOfUpdatingSupply";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatingSupply(@RequestParam(value = "idProvider", required = true) Integer idProvider,
			@RequestParam(value = "idSupply", required = true) Integer idSupply,
			@ModelAttribute("supplyAttribute") Supply supply) {
		supply.setIdSupply(idSupply);
		supplyDao.update(supply);
		return "success";
	}

}
