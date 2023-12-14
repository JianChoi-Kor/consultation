package com.sonssoft.consultation.controller;

import com.sonssoft.consultation.dto.ConsultationRequestDto.ReadConsultation;
import com.sonssoft.consultation.dto.ConsultationRequestDto.RegisterConsultation;
import com.sonssoft.consultation.dto.FeedbackRequestDto.RegisterOrModifyFeedback;
import com.sonssoft.consultation.service.interfaces.ConsultationService;
import com.sonssoft.consultation.service.interfaces.FeedbackService;
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
    private final FeedbackService feedbackService;

    @PostMapping(value = "")
    public ResponseEntity<?> registerConsultation(@RequestBody @Validated RegisterConsultation param,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return apiResponse.fail(bindingResult);
        }

        return apiResponse.success(consultationService.registerConsultation(param));
    }

    @PostMapping(value = "/{consultationId:[0-9]*}/feedback")
    public ResponseEntity<?> registerFeedback(@PathVariable Long consultationId,
                                              @RequestBody @Validated RegisterOrModifyFeedback param,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return apiResponse.fail(bindingResult);
        }
        param.setConsultationId(consultationId);

        return apiResponse.success(feedbackService.registerFeedback(param));
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getConsultationList() {
        return apiResponse.success();
    }

    @GetMapping(value = "/{consultationId:[0-9]*}")
    public ResponseEntity<?> getConsultation(@PathVariable Long consultationId) {

        return apiResponse.success(consultationService.getConsultation(consultationId));
    }

    @GetMapping(value = "/{consultationId:[0-9]*}/read")
    public ResponseEntity<?> readConsultation(@PathVariable Long consultationId,
                                              @RequestBody @Validated ReadConsultation param,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return apiResponse.fail(bindingResult);
        }
        param.setConsultationId(consultationId);

        return apiResponse.success(consultationService.readConsultation(param));
    }

    @PutMapping(value = "/{consultationId}/feedback")
    public ResponseEntity<?> modifyConsultation() {
        return apiResponse.success();
    }
}
