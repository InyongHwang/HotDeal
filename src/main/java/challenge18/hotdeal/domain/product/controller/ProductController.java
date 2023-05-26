package challenge18.hotdeal.domain.product.controller;

import challenge18.hotdeal.common.security.UserDetailsImpl;
import challenge18.hotdeal.common.util.Message;
import challenge18.hotdeal.domain.product.dto.ConditionDto;
import challenge18.hotdeal.domain.product.dto.ProductResponseDto;
import challenge18.hotdeal.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.Map;
=======
import java.util.List;
import java.util.Map;
import java.util.Optional;
>>>>>>> c7880106f5638e8300ec5508183d400fd2d3282d

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // 상품 목록 조회
    @GetMapping("/products")
    public List<ProductResponseDto> allProduct(ConditionDto condition) throws IllegalAccessException {
        System.out.println("condition.getMinPrice() = " + condition.getMinPrice());
        System.out.println("condition.getMaxPrice() = " + condition.getMaxPrice());
        System.out.println("condition.getMainCategory() = " + condition.getMainCategory());
        System.out.println("condition.getSubCategory() = " + condition.getSubCategory());
        //ConditionDto condition = new ConditionDto(minPrice, maxPrice, mainCategory, subCategory);
        return productService.allProduct(condition);
    }

    // 상품 상세 조회
    @GetMapping("/{productId}")
    public ProductResponseDto selectProduct(@PathVariable Long productId){
        return productService.selectProduct(productId);
    }

    // 상품 구매
    @PostMapping("/{productId}")
    public ResponseEntity<Message> buyLimitedProduct(@PathVariable Long productId,
<<<<<<< HEAD
                                                     @RequestParam int quantity,
=======
                                                     @RequestBody Map<String, Integer> map,
>>>>>>> c7880106f5638e8300ec5508183d400fd2d3282d
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.buyProduct(productId, map.get("quantity"), userDetails.getUser());
    }
}
