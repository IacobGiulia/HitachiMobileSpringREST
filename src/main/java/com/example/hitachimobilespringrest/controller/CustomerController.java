package com.example.hitachimobilespringrest.controller;

import com.example.hitachimobilespringrest.model.CustomerAddress;
import com.example.hitachimobilespringrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/validate-sim")
    public ResponseEntity<List<String>> validateSim(@RequestBody Map<String, String> request) {
        String simNumber = request.get("simNumber");
        String serviceNumber = request.get("serviceNumber");
        List<String> offers = customerService.validateSimAndGetOffers(simNumber, serviceNumber);
        return ResponseEntity.ok(offers);
    }

    @PostMapping("/validate-basics")
    public ResponseEntity<String> validateBasics(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        LocalDate dob = LocalDate.parse(request.get("dob"));
        String result = customerService.validateCustomerBasics(email, dob);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/validate-personal")
    public ResponseEntity<String> validatePersonal(@RequestBody Map<String, String> request) {
        String firstName = request.get("firstName");
        String lastName = request.get("lastName");
        String confirmEmail = request.get("confirmEmail");
        String result = customerService.validateCustomerPersonal(firstName, lastName, confirmEmail);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{uniqueId}/address")
    public ResponseEntity<CustomerAddress> updateAddress(
            @PathVariable String uniqueId,
            @RequestBody CustomerAddress newAddress) {
        CustomerAddress updatedAddress = customerService.updateCustomerAddress(uniqueId, newAddress);
        return ResponseEntity.ok(updatedAddress);
    }

    @PostMapping("/validate-id")
    public ResponseEntity<String> validateIdProof(@RequestBody Map<String, String> request) {
        String aadharId = request.get("aadharId");
        String firstName = request.get("firstName");
        String lastName = request.get("lastName");
        LocalDate dob = LocalDate.parse(request.get("dob"));

        String result = customerService.validateIdProofAndActivateSim(aadharId, firstName, lastName, dob);
        return ResponseEntity.ok(result);
    }
}
