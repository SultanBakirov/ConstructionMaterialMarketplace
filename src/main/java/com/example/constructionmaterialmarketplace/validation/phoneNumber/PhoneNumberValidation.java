package com.example.constructionmaterialmarketplace.validation.phoneNumber;

import com.example.constructionmaterialmarketplace.exception.BadRequestException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberValidation implements ConstraintValidator<PhoneValid, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (phoneNumber != null && phoneNumber.startsWith("+48") && phoneNumber.length() == 12) {
            // The next digit typically identifies the mobile operator in Poland, it should be 5, 6, or 7 for most carriers.
            char operatorIdentifier = phoneNumber.charAt(3);
            if (operatorIdentifier == '5' || operatorIdentifier == '6' || operatorIdentifier == '7') {
                // Polish mobile numbers have 9 digits after the country code
                String subscriberNumber = phoneNumber.substring(4);
                return subscriberNumber.matches("^[0-9]{9}$");
            } else {
                throw new BadRequestException("The mobile operator code is invalid for a Polish number.");
            }
        } else {
            throw new BadRequestException("The number must start with +48 and contain 12 digits in total.");
        }
    }
}
