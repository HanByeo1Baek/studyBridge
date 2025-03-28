package com.green.acamatch.excel;

import com.green.acamatch.config.model.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "엑셀 관리", description = "엑셀로 내보내기, 수정")
@RestController
@RequiredArgsConstructor
@RequestMapping("/grade")
public class StudentGradeController {

    private final StudentGradeService studentGradeService;

    // 1. 성적 엑셀 파일로 내보내기 (GET 요청)
    @GetMapping("/export")
    @Operation(summary = "엑셀 파일로 내보내기")
    public ResultResponse<String> exportToExcel(@RequestParam("examId") Long examId) {
        return ResultResponse.<String >builder()
                .resultMessage("엑셀 파일 내보내기에 성공하였습니다.")
                .resultData(studentGradeService.exportToExcel(examId))
                .build();
    }

    // 2. 성적 엑셀 파일을 읽어 DB 업데이트 (POST 요청)
    @PostMapping(value = "/import", consumes = "multipart/form-data")
    @Operation(summary = "엑셀 파일을 읽어 DB 업데이트")
    public ResponseEntity<ResultResponse<Integer>> importGrades(@RequestParam("gradeFile") MultipartFile file) {
        ResultResponse<Integer> result = studentGradeService.importFromExcel(file);
        if (result.getResultData() == 1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}