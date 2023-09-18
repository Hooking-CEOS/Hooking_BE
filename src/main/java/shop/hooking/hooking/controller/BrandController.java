package shop.hooking.hooking.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.hooking.hooking.dto.response.BrandRes;
import shop.hooking.hooking.service.BrandService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class BrandController {

    private final BrandService brandService;


    //브랜드 전체 조회
    @GetMapping("")
    @ApiOperation(value="전체 브랜드 조회", notes="전체 브랜드의 정보를 보여줍니다.")
    public ResponseEntity<List<BrandRes.BrandDto>> showAllBrand() {
        return ResponseEntity.ok(brandService.getBrandList());

    }

    //브랜드 상세 조회
    @GetMapping("/{brand_id}/{index}")
    @ApiOperation(value="상세 브랜드 조회", notes="해당 브랜드의 상세 정보를 보여줍니다.")
    public ResponseEntity<BrandRes.BrandDetailDto> getOneBrand(HttpServletRequest httpRequest, @PathVariable Long brand_id, @PathVariable int index) {
        return ResponseEntity.ok(brandService.getBrandDetail(httpRequest, brand_id, index));
    }


}
