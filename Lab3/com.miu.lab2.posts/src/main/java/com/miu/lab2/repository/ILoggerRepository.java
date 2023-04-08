package com.miu.lab2.repository;

import com.miu.lab2.entity.LogEntry;
import org.springframework.data.repository.CrudRepository;

public interface ILoggerRepository extends CrudRepository<LogEntry, Integer> {
}
