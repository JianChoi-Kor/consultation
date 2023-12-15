package com.sonssoft.consultation.controller;

import com.sonssoft.consultation.dto.ConsultationRequestDto.SearchConsultation;
import com.sonssoft.consultation.dto.ConsultationRequestDto.ReadConsultation;
import com.sonssoft.consultation.dto.ConsultationRequestDto.RegisterConsultation;
import com.sonssoft.consultation.dto.ConsultationResponseDto.ConsultationDetail;
import com.sonssoft.consultation.dto.FeedbackRequestDto.ModifyFeedback;
import com.sonssoft.consultation.dto.FeedbackRequestDto.RegisterFeedback;
import com.sonssoft.consultation.service.interfaces.ConsultationService;
import com.sonssoft.consultation.service.interfaces.FeedbackService;
import com.sonssoft.consultation.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Consultation", description = "Consultation API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/consultation")
public class ConsultationController {

    private final CustomApiResponse apiResponse;

    private final ConsultationService consultationService;
    private final FeedbackService feedbackService;

    @PostMapping(value = "")
    @Operation(summary = "상담 등록 API", description = "학생과 상담원 간의 상담 정보를 등록하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "success", description = "등록 성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDetail.class))}),
            @ApiResponse(responseCode = "fail", description = "등록 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.FailedBody.class))}),
            @ApiResponse(responseCode = "error", description = "오류 발생",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.ErroredBody.class))})
    })
    public ResponseEntity<?> registerConsultation(
            @RequestBody @Validated RegisterConsultation param,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return apiResponse.fail(bindingResult);
        }

        return apiResponse.success(consultationService.registerConsultation(param));
    }

    @PostMapping(value = "/{consultationId:[0-9]*}/feedback")
    @Operation(summary = "상담 피드백 등록 API", description = "담당자가 피드백을 등록하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "success", description = "등록 성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDetail.class))}),
            @ApiResponse(responseCode = "fail", description = "등록 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.FailedBody.class))}),
            @ApiResponse(responseCode = "error", description = "오류 발생",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.ErroredBody.class))})
    })
    public ResponseEntity<?> registerFeedback(
            @Parameter(name = "consultationId", description = "상담 고유값", example = "1")
            @PathVariable Long consultationId,
            @RequestBody @Validated RegisterFeedback param,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return apiResponse.fail(bindingResult);
        }
        param.setConsultationId(consultationId);

        return apiResponse.success(feedbackService.registerFeedback(param));
    }

    @GetMapping(value = "")
    @Operation(summary = "상담 목록 조회 API", description = "필터 및 정렬 기능이 적용된 상담 목록 조회 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "success", description = "조회 성공",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ConsultationDetail.class)))}),
            @ApiResponse(responseCode = "fail", description = "조회 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.FailedBody.class))}),
            @ApiResponse(responseCode = "error", description = "오류 발생",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.ErroredBody.class))})
    })
    public ResponseEntity<?> getConsultationList(
            @Validated SearchConsultation param,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return apiResponse.fail(bindingResult);
        }

        return apiResponse.success(consultationService.getConsultationList(param));
    }

    @GetMapping(value = "/{consultationId:[0-9]*}")
    @Operation(summary = "상담 상세 보기 API", description = "상담 정보 조회 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "success", description = "조회 성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDetail.class))}),
            @ApiResponse(responseCode = "fail", description = "조회 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.FailedBody.class))}),
            @ApiResponse(responseCode = "error", description = "오류 발생",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.ErroredBody.class))})
    })
    public ResponseEntity<?> getConsultation(
            @Parameter(name = "consultationId", description = "상담 고유값", example = "1")
            @PathVariable Long consultationId) {

        return apiResponse.success(consultationService.getConsultation(consultationId));
    }

    @PostMapping(value = "/{consultationId:[0-9]*}/read")
    @Operation(summary = "상담 읽음 처리 API", description = "담당자의 읽음 처리 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "success", description = "조회 성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDetail.class))}),
            @ApiResponse(responseCode = "fail", description = "조회 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.FailedBody.class))}),
            @ApiResponse(responseCode = "error", description = "오류 발생",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.ErroredBody.class))})
    })
    public ResponseEntity<?> readConsultation(
            @Parameter(name = "consultationId", description = "상담 고유값", example = "1")
            @PathVariable Long consultationId,
            @RequestBody @Validated ReadConsultation param,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return apiResponse.fail(bindingResult);
        }
        param.setConsultationId(consultationId);

        return apiResponse.success(consultationService.readConsultation(param));
    }

    @PutMapping(value = "/{consultationId:[0-9]*}/feedback")
    @Operation(summary = "상담 피드백 수정 API", description = "담당자가 피드백을 수정하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "success", description = "수정 성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ConsultationDetail.class))}),
            @ApiResponse(responseCode = "fail", description = "수정 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.FailedBody.class))}),
            @ApiResponse(responseCode = "error", description = "오류 발생",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.ErroredBody.class))})
    })
    public ResponseEntity<?> modifyConsultation(
            @Parameter(name = "consultationId", description = "상담 고유값", example = "1")
            @PathVariable Long consultationId,
            @RequestBody @Validated ModifyFeedback param,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return apiResponse.fail(bindingResult);
        }
        param.setConsultationId(consultationId);

        return apiResponse.success(feedbackService.modifyConsultation(param));
    }
}
