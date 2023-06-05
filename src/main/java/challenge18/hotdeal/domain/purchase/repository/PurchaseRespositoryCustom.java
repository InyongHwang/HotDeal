package challenge18.hotdeal.domain.purchase.repository;

import challenge18.hotdeal.domain.product.dto.AllProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseRespositoryCustom {
    Page<AllProductResponseDto> findTopN(Pageable pageable);
//    List<AllProductResponseDto> findTop90(Pageable pageable);
//    List<AllProductResponseDto> findTop90();
}
