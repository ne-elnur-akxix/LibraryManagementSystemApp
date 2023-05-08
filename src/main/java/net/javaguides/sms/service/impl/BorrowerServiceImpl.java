package net.javaguides.sms.service.impl;

import java.util.List;

import net.javaguides.sms.repository.BorrowerRepository;
import org.springframework.stereotype.Service;

import net.javaguides.sms.entity.Borrower;
import net.javaguides.sms.service.BorrowerService;

@Service
public class BorrowerServiceImpl implements BorrowerService {

	private BorrowerRepository borrowerRepository;
	
	public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
		super();
		this.borrowerRepository = borrowerRepository;
	}

	@Override
	public List<Borrower> getAllBorrowers() {
		return borrowerRepository.findAll();
	}

	@Override
	public Borrower saveBorrower(Borrower borrower) {
		return borrowerRepository.save(borrower);
	}

	@Override
	public Borrower getBorrowerById(Long id) {
		return borrowerRepository.findById(id).get();
	}

	@Override
	public Borrower updateBorrower(Borrower borrower) {
		return borrowerRepository.save(borrower);
	}

	@Override
	public void deleteBorrowerById(Long id) {
		borrowerRepository.deleteById(id);
	}

}
