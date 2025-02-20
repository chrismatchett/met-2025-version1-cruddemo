package com.example.cruddemo.web;

import com.example.cruddemo.entity.Record;
import com.example.cruddemo.service.RecordService;
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
public class RecordWebController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/")
    public String displayHome() {
        return "home";
    }

    @GetMapping("/search")
    public String searchByReference(Model model) {
        model.addAttribute("record", new RecordAmountDTO());
        return "search";
    }

    @PostMapping("/amendAmount")
    public String displayFineForm(@Valid @ModelAttribute("record") RecordAmountDTO recordAmountDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "search";
        }

        try {
            // This may cause an ArithmeticException
            Record theRecord = recordService.findByReference(recordAmountDTO.getReference());
            model.addAttribute("record", theRecord);


            if (theRecord != null) {
                RecordAmountDTO populatedDTO = new RecordAmountDTO();
                populatedDTO.setReference(theRecord.getReference());
                populatedDTO.setAmount(theRecord.getAmount());
                model.addAttribute("RecordAmountDTO", populatedDTO);

                return "record-form";
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
    public String updateAmount(@ModelAttribute RecordAmountDTO recordAmountDTO) {

        String theReference = recordAmountDTO.getReference();
        double theAmount = recordAmountDTO.getAmount();


        // Check if amount and reference is set to something acceptable
        // i.e. Amount is not zero, and Reference is not an empty string or null
        if(theAmount <= 0 || theReference.trim().isEmpty()) {
            return "redirect:/amendAmount";
        }
        else {
            // Update the specific column of the entity
            Record theRecord = recordService.findByReference(theReference);
            double balance = theRecord.getAmount();
            balance = balance - recordAmountDTO.getAmount();
            theRecord.setAmount(balance);

            // Save the updated entity
            recordService.save(theRecord);

            // Redirect to a success page or handle accordingly
            return "redirect:/confirmation";

        }
    }
}