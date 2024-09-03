package com.example.cruddemo.web;

import com.example.cruddemo.entity.Fine;
import com.example.cruddemo.service.FineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class FineWebController {

    @Autowired
    private FineService fineService;

    @GetMapping("/")
    public String displayHome() {
        return "home";
    }

    @GetMapping("/search")
    public String searchByReference(Model model) {
        model.addAttribute("fine", new FineAmountDTO());
        return "search";
    }

    @PostMapping("/amendAmount")
    public String displayFineForm(@Valid @ModelAttribute("fine") FineAmountDTO fineAmountDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "search";
        }

        try {
            // This may cause an ArithmeticException
            Fine theFine = fineService.findByReference(fineAmountDTO.getReference());
            model.addAttribute("fine", theFine);


            if (theFine != null) {
                FineAmountDTO populatedDTO = new FineAmountDTO();
                populatedDTO.setReference(theFine.getReference());
                populatedDTO.setAmount(theFine.getAmount());
                model.addAttribute("fineAmountDTO", populatedDTO);

                return "fine-form";
            }

        } catch (EmptyResultDataAccessException e) {
            System.out.println("Exception caught: " + e);
        }

        return "search";
    }

    @GetMapping("/confirmation")
    public String displaySuccessPage(Model theModel) {
        theModel.addAttribute("theDate", new java.util.Date());
        return "success";
    }


    @PostMapping("/updateAmount")
    public String updateAmount(@ModelAttribute FineAmountDTO fineAmountDTO) {

        String theReference = fineAmountDTO.getReference();
        double theAmount = fineAmountDTO.getAmount();


        // Check if amount and reference is set to something acceptable
        // i.e. Amount is not zero, and Reference is not an empty string or null
        if(theAmount <= 0 || theReference.trim().isEmpty()) {
            return "redirect:/amendAmount";
        }
        else {
            // Update the specific column of the entity
            Fine theFine = fineService.findByReference(theReference);
            double balance = theFine.getAmount();
            balance = balance - fineAmountDTO.getAmount();
            theFine.setAmount(balance);

            // Save the updated entity
            fineService.save(theFine);

            // Redirect to a success page or handle accordingly
            return "redirect:/confirmation";

        }
    }
}