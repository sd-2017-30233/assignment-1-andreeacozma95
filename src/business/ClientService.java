package business;

import java.lang.reflect.Field;
import java.util.List;
import dataSource.Account;
import dataSource.AccountMapper;
import dataSource.ActivityAccount;
import dataSource.Client;
import dataSource.ClientMapper;

public class ClientService {

	public String[][] getClients()
	{
		Field []f = Client.class.getDeclaredFields();
		String [] coloane=new String[f.length-1];
		
		ClientMapper mapper = new ClientMapper();
		List<Client> clients = mapper.getClients();
		String [][] date=new String[clients.size()][f.length-1];
		
		int i=0;
		for ( Field field : f )
		{
			if (!field.getName().equals("idClient"))
			{
				coloane[i]=field.getName();
				i++;
			}			
		}
	
		i=0;
		for(int k=0;k<clients.size();k++)
		{
			i=0;
			for ( Field field : f )	
			{
				if (!field.getName().equals("idClient"))
				{
					try {
						field.setAccessible(true);
						date[k][i]=field.get(clients.get(k)).toString();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					i++;
				}
			}
		}
			
		return date;
	}
	
	public String[] getFields()
	{
		Field []f = Client.class.getDeclaredFields();
		String [] coloane=new String[f.length-1];
		
		int i=0;
		for ( Field field : f )
		{
			if (!field.getName().equals("idClient"))
			{
				coloane[i]=field.getName();
				i++;
			}			
		}		
		return coloane;
	}
	
	public void deleteClient(String []dataClient){
		ClientMapper clientMapper = new ClientMapper();
		Client client=null;
		if(clientMapper.getClientByName(dataClient[0]))
			client = clientMapper.map();
		
		clientMapper.delete(client);
	}
	
	
	public String[][] getAccounts(Long pnc)
	{
		ClientMapper clientMapper = new ClientMapper();
		clientMapper.getClientByPNC(pnc);
		Client client = clientMapper.map();
		
		Field []f = Account.class.getDeclaredFields();
		String [] coloane=new String[f.length-1];
		
		AccountMapper accoutMapper = new AccountMapper();
		List<Account> accounts = accoutMapper.getAccountsByClient(client);
		
		String [][] date=new String[accounts.size()][f.length-2];
		
		int i=0;
		for ( Field field : f )
		{
			if (!field.getName().equals("idAccount")&&!field.getName().equals("client"))
			{
				coloane[i]=field.getName();
				i++;
			}			
		}
	
		i=0;
		for(int k=0;k<accounts.size();k++)
		{
			i=0;
			for ( Field field : f )	
			{
				if (!field.getName().equals("idAccount")&&!field.getName().equals("client"))
				{
					try {
						field.setAccessible(true);
						date[k][i]=field.get(accounts.get(k)).toString();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					i++;
				}
			}
		}			
		return date;
	}
	public void addClient(String name, Long pnc, String address){
		ClientMapper clientMapper = new ClientMapper();
		
		List<Client> clients = clientMapper.getClients();
		int max = Integer.MIN_VALUE;
		for (Client client : clients) {
			if (client.getIdClient()>max)
				max = client.getIdClient();
		}
		
		clientMapper.create(new Client(max+1,name,pnc,address));
	
	}
}
