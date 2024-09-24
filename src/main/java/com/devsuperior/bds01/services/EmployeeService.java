package com.devsuperior.bds01.services;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        Page<Employee> page = repository.findAll(pageable);
        return page.map(EmployeeDTO::new);
    }

    @Transactional
    public EmployeeDTO insert(EmployeeDTO dto) {
        Employee entity = new Employee();
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setDepartment(new Department(dto.getDepartmentId(), null));
        entity = repository.save(entity);
        return new EmployeeDTO(entity);
    }
}