package com.megazone.ERPSystem_phase3_FinanceHR.hr.repository.basic_information_management.Users;

import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_information_management.employee.Users;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    private UsersRepository usersRepository;

    public Users saveUsers(Users users){
        return usersRepository.save(users);
    }



}
