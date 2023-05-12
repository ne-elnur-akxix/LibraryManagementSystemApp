package net.javaguides.sms.service;

import java.util.List;

import net.javaguides.sms.entity.Borrower;

public interface BorrowerService {
	List<Borrower> getAllBorrowers();

	Borrower saveBorrower(Borrower borrower);

	Borrower getBorrowerById(Long id);

	Borrower updateBorrower(Borrower borrower);

	void deleteBorrowerById(Long id);
}