package bank.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bank.app.dao.BranchDaoImpl;
import bank.app.dao.RegionDaoImpl;
import bank.app.entities.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class BranchController {

    //private BranchDao branchDao = new BranchDaoImpl();
    
    @Autowired
	private BranchDaoImpl branchDaoImpl;

    @Autowired
    private RegionDaoImpl regionDaoImpl;
    
    @Autowired
    HttpSession session;
    
    
    
    // Show the add branch form
    @GetMapping("/regional_mgr/addNewBranch")
    public String showAddBranchForm(Model model) {
    	
    	User user = (User) session.getAttribute("userDetails");
    	int regionId = regionDaoImpl.getRegionId(user.getUserId());
    	
    	model.addAttribute("regionId", regionId);
    	
        return "regional_mgr/addNewBranch"; // Return JSP page name
    }

    // Handle form submission
    @PostMapping("/regional_mgr/addNewBranch")
    public String addBranch(String branchName, String address, String phone, String pinCode, String regionId, RedirectAttributes attributes) {
        try {
            boolean isAdded = branchDaoImpl.addNewBranch(branchName, address, phone, pinCode, regionId);

            if (isAdded) {
                attributes.addFlashAttribute("message", "Branch added successfully!");
                return "redirect:/regional_mgr/dashboard"; 
            } else {
                attributes.addFlashAttribute("message", "Failed to add branch.");
                return "redirect:/regional_mgr/addNewBranch"; 
            }

        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Error: " + e.getMessage());
            return "redirect:/regional_mgr/addNewBranch"; 
        }

        
        //return "/regional_mgr/addNewBranch"; 
    }
}
