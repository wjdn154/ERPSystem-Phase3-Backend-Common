package com.megazone.ERPSystem_phase3_Monolithic.logistics.repository.product_registration.product;

import com.megazone.ERPSystem_phase3_Monolithic.logistics.model.product_registration.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long>, ProductImageRepositoryCustom {
}
