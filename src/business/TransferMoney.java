package business;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import dataSource.Account;
import dataSource.AccountMapper;
import dataSource.ActivityAccount;
import dataSource.ActivityAccountMapper;
import dataSource.Employee;

public class TransferMoney {
	public boolean transferMoney(int idSourceAccout, int idTargetAccount, float sum){
		AccountMapper accountMapper = new AccountMapper();
		
		if(accountMapper.getAccount(idSourceAccout)&&accountMapper.getAccount(idTargetAccount))
		{
			Account accountSource = accountMapper.map();			
			Account accountTarget = accountMapper.map();				
			if(accountSource.withdraw(sum))	
				{
					accountTarget.deposit(sum);
					accountMapper.update(accountSource);
					accountMapper.update(accountTarget);
					return true;
				}
		}
		return false;
	}
}
