package com.miu.lab2.repository;

import com.miu.lab2.entity.ExceptionEntry;
import org.springframework.data.repository.CrudRepository;

public interface IExceptionRepository extends CrudRepository<ExceptionEntry, Integer> {
}
