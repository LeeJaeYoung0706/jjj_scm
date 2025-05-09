package jjj.scm.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "TestSwaggerController", description = "테스트 컨트롤러")
public interface TestSwaggerController {

    @Operation(
            summary = "테스트 요청 경로",
            description = " 테스트 요청 경로 "
    )
    // 리턴 받는 거 정의
    @ApiResponses(
            value = {
                    //http status 200 일 때 이걸로 리턴
                    @ApiResponse(responseCode = "200" , description = "성공" , content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    }),
                    //http status 매개인자 익셉션이면
                    @ApiResponse(responseCode = "405" , description = "매개인자 잘못 삽입")
            }
    )
    // 매개 변수 도
    String test1(@Parameter(description = "테스트 디티오입니다." , required = true) @RequestBody(
            content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(name = " 테스트 디티오 예제" , value = """
                            {
                                "userId" : "testId"
                            }
                            """)
            })

    ) TestDto dto);
}
