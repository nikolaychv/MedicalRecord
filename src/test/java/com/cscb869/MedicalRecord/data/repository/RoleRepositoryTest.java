package com.cscb869.MedicalRecord.data.repository;

import com.cscb869.MedicalRecord.data.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Role role;

    @Test
    void findByAuthority() {
        String authority = "DIRECTOR";
        role = new Role();
        role.setAuthority(authority);
        testEntityManager.persistAndFlush(role);

        Role foundAuthority = roleRepository.findByAuthority(authority);

        assertThat(foundAuthority.getAuthority()).isEqualTo(authority);
    }
}
