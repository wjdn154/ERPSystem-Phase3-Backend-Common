package com.megazone.ERPSystem_phase3_Common.financial.repository.basic_information_management.client;

import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.client.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}