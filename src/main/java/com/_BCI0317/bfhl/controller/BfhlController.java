package com._BCI0317.bfhl.controller;

import com._BCI0317.bfhl.model.BfhlRequest;
import com._BCI0317.bfhl.model.BfhlResponse;
import com._BCI0317.bfhl.model.CodeResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins="https://bajaj21bci0317.vercel.app")
public class BfhlController {

    @PostMapping
    public BfhlResponse processRequest(@RequestBody BfhlRequest request) {
        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> highestLowercaseAlphabet = new ArrayList<>();

        char highest = '\0';
        for (String item : request.getData()) {
            if (item.matches("\\d+")) {
                numbers.add(item);
            } else if (item.matches("[a-zA-Z]+")) {
                alphabets.add(item);
                for (char c : item.toCharArray()) {
                    if (Character.isLowerCase(c) && (highest == '\0' || c > highest)) {
                        highest = c;
                    }
                }
            }
        }
        if (highest != '\0') {
            highestLowercaseAlphabet.add(String.valueOf(highest));
        }

        String userId = request.getUserId();
        String email = request.getEmail();
        String rollNumber = request.getRollNumber();

        return new BfhlResponse(
                true,
                userId,
                email,
                rollNumber,
                numbers,
                alphabets,
                highestLowercaseAlphabet
        );
    }

    @GetMapping
    public CodeResponse getOperationCode() {
        return new CodeResponse("BFHL_round1");
    }
}
