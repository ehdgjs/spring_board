package com.project.board.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    // 결과 true or false
    private Boolean result;

    // 결과 메세지 에러메세지
    private String msg;

    // 데이터
    private Object data;
}
