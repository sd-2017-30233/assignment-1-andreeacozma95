package business;

import dataSource.Account;
import dataSource.AccountMapper;
import dataSource.Bill;
import dataSource.BillMapper;

public class BillService {
	public boolean processBill(int idAccout, int idBill, float sum, String description){
		BillMapper billMapper = new BillMapper();
		AccountMapper accountMapper = new AccountMapper();
		
		if(accountMapper.getAccount(idAccout))
		{
			Account accountSource = accountMapper.map();
			if (accountSource.getAmount()-sum>=0)
			{
				accountSource.setAmount(accountSource.getAmount()-sum);
				accountMapper.update(accountSource);
				Bill bill = new Bill (idBill,sum,description,accountSource);
				billMapper.create(bill);
				
				return true;
			}		
		}
		
		return false;
	}
}
