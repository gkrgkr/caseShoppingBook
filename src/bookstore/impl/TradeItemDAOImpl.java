package bookstore.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bookstore.dao.TradeItemDAO;
import bookstore.domain.TradeItem;

public class TradeItemDAOImpl extends BaseDAO<TradeItem> implements TradeItemDAO {

	@Override
	public void batchSave(Collection<TradeItem> items) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tradeitem (bookid, quantity, tradeid) " +
				"VALUES(?,?,?)";
		Object [][] params = new Object[items.size()][3];
		
		List<TradeItem> tradeItems = new ArrayList<>(items);
		for(int i = 0; i < tradeItems.size(); i++){
			params[i][0] = tradeItems.get(i).getBookId();
			params[i][1] = tradeItems.get(i).getQuantity();
			params[i][2] = tradeItems.get(i).getTradeId(); 
		}
		
		batch(sql, params);
	}

	@Override
	public Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId) {
		
		String sql = "select  itemId tradeItemId, bookId, quantity, tradeId from tradeitem where tradeid = ?";
		
		return new HashSet<>(queryForList(sql, tradeId));
	}

}
