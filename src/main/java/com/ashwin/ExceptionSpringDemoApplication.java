package com.ashwin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ashwin.repository.Account;
import com.ashwin.repository.AccountRepository;

@Service
@SpringBootApplication
public class ExceptionSpringDemoApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ExceptionSpringDemoApplication.class, args);
		// initialize the account with new values
	}
	
	  @Bean
	  public CommandLineRunner demo(AccountRepository repository) {

		    return (args) -> {
				ExceptionSpringDemoApplication app = new ExceptionSpringDemoApplication();
				Account account = new Account();
				account.setAccountName("ashwin");
				account.setBalance(100000);
				repository.save(account);
				account = new Account();
				account.setAccountName("sriram");
				account.setBalance(100000);
				repository.save(account);
				account = new Account();
				account.setAccountName("rajan");
				account.setBalance(200000);
				repository.save(account);
				// now you can withdraw cash.
				app.cashWithdraw(repository,"ashwin", 1000);
				app.cashWithdraw(repository,"sriram",900);
				app.cashWithdraw(repository,"rajan",100000);
				System.out.println("Program Ended successfully");
		    };
		  
	  }
	    

	public void cashWithdraw(AccountRepository accRepository, String accountName, int amountToWithdraw) {

		Account account = accRepository.findByAccountName(accountName);
		try {
			//go to database and check if the account has enough money. If not allow money within the limit
			debitMoney(accRepository, amountToWithdraw, account,amountToWithdraw);
		}catch(Exception e) {
			e.printStackTrace();
			// caught the exception now handle the error gracefully by default value
			int defaultAmount = 100;
			try {
				debitMoney(accRepository, defaultAmount, account, amountToWithdraw);
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("Insufficient balance");
			}
			
		}
	}
	
	public void debitMoney(AccountRepository accRepository, int amountToWithdraw,Account account, int originalAsked) throws Exception {
		
		if (amountToWithdraw < account.getBalance()) {
			account.setBalance(account.getBalance() - amountToWithdraw);
			accRepository.save(account);
			
			System.out.println("You asked Rs." + originalAsked + " Rupees " + amountToWithdraw + " Money dispensed !!!!!! to Mr." + account.getAccountName() + " Your balance now is Rs." + account.getBalance());
		}
		else {
			throw new Exception("Insufficient balance Mr."  + account.getAccountName());
		}
	}

}
