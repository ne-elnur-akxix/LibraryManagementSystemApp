package net.javaguides.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.sms.entity.Borrower;
import net.javaguides.sms.service.BorrowerService;

@Controller
public class BorrowerController {
	
	private BorrowerService borrowerService;

	public BorrowerController(BorrowerService borrowerService) {
		super();
		this.borrowerService = borrowerService;
	}
	
	// handler method to handle list students and return mode and view
	@GetMapping("/borrowers")
	public String listBorrowers(Model model) {
		model.addAttribute("borrowers", borrowerService.getAllBorrowers());
		return "borrowers";
	}
	
	@GetMapping("/borrowers/new")
	public String createBorrowerForm(Model model) {
		
		// create student object to hold student form data
		Borrower borrower = new Borrower();
		model.addAttribute("borrower", borrower);
		return "create_borrower";
		
	}
	
	@PostMapping("/borrowers")
	public String saveBorrower(@ModelAttribute("borrower") Borrower borrower) {
		borrowerService.saveBorrower(borrower);
		return "redirect:/borrowers";
	}
	
	@GetMapping("/borrowers/edit/{id}")
	public String editBorrowerForm(@PathVariable Long id, Model model) {
		model.addAttribute("borrower", borrowerService.getBorrowerById(id));
		return "edit_borrower";
	}

	@PostMapping("/borrowers/{id}")
	public String updateBorrower(@PathVariable Long id,
			@ModelAttribute("borrower") Borrower borrower,
			Model model) {
		
		// get student from database by id
		Borrower existingBorrower = borrowerService.getBorrowerById(id);
		existingBorrower.setId(id);
		existingBorrower.setFirstName(borrower.getFirstName());
		existingBorrower.setLastName(borrower.getLastName());
		existingBorrower.setEmail(borrower.getEmail());
		existingBorrower.setNumber(borrower.getNumber());
		
		// save updated student object
		borrowerService.updateBorrower(existingBorrower);
		return "redirect:/borrowers";
	}
	
	// handler method to handle delete student request
	
	@GetMapping("/borrowers/{id}")
	public String deleteBorrower(@PathVariable Long id) {
		borrowerService.deleteBorrowerById(id);
		return "redirect:/borrowers";
	}
	
}
