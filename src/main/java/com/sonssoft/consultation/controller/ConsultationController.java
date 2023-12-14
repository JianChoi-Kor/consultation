package com.sonssoft.consultation.controller;

import com.sonssoft.consultation.dto.ConsultationRequestDto.RegisterConsultation;
import com.sonssoft.consultation.service.interfaces.ConsultationService;
import com.sonssoft.consultation.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultation")
public class ConsultationController {

    private final ApiResponse apiResponse;

    private final ConsultationService consultationService;

    @PostMapping(value = "")
    public ResponseEntity<?> registerConsultation(@RequestBody @Validated RegisterConsultation param,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return apiResponse.fail(bindingResult);
        }

        return apiResponse.success(consultationService.registerConsultation(param));
    }

    @PostMapping(value = "/{consultationId}/feedback")
    public ResponseEntity<?> registerFeedback() {
        return apiResponse.success();
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getConsultationList() {
        return apiResponse.success();
    }

    @GetMapping(value = "/{consultationId}")
    public ResponseEntity<?> getConsultation() {
        return apiResponse.success();
    }

    @GetMapping(value = "/{consultationId}/read")
    public ResponseEntity<?> readConsultation() {
        return apiResponse.success();
    }

    @PutMapping(value = "/{consultationId}/feedback")
    public ResponseEntity<?> modifyConsultation() {
        return apiResponse.success();
    }
}
