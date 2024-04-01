package kw.kng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kw.kng.entity.BasicEmployeeV4;

@Repository
public interface BasicEmployeeV4Repo extends JpaRepository<BasicEmployeeV4, Long> 
{

}
