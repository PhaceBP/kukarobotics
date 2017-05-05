package com.kukarobotics.generics.daoexample;

public interface CustomerDAO extends BaseDAO<Customer, Integer> {

	Customer getCustomerByName(String customerName);
}
