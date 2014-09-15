package services;

import java.util.List;

import models.Account;
import models.Sale;

public interface SaleService {

	public void sell(List<Sale> salesList, Account consumer);

}
