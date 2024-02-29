package dev.rick.mandjesenpuutjesbackend.utils;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class InputValidator {

    private final StringBuilder builder;

    public InputValidator() {
        this.builder = new StringBuilder();
    }


    public String inputValidator(BindingResult bindingResult) {

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            this.builder.append(fieldError.getField());
            this.builder.append(": ");
            this.builder.append(fieldError.getDefaultMessage());
            this.builder.append("\n");
        }

        return builder.toString();
    }
}
