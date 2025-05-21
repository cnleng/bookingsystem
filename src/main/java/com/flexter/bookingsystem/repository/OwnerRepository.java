package com.flexter.bookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexter.bookingsystem.model.Owner;

@Repository("ownerRepository")
public interface OwnerRepository extends JpaRepository<Owner, String>  {

}
