package com.estore.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.estore.dao.ProductDAO;
import com.estore.entity.Product;

@SessionScope
@Service
public class CartService {
	@Autowired
	ProductDAO dao;
	Map<Integer, Integer> maxQuantity = new HashMap<>();
	Map<Integer, Product> map = new HashMap<>();
	public void add(Integer id) {
		Product p = map.get(id);
		if(p == null) {
			p = dao.findById(id);
			maxQuantity.put(id, p.getQuantity());
			p.setQuantity(1);
			map.put(id, p);
		}
		else {
			if(p.getQuantity() >= maxQuantity.get(id)) {
				p.setQuantity(maxQuantity.get(id));
			}
			else 
			{
				p.setQuantity(p.getQuantity() + 1);
			}
		}
	}
	public void remove(Integer id) {
		map.remove(id);
		maxQuantity.remove(id);
	}
	public void update(Integer id, Integer qty) {
		Product p = map.get(id);
		p.setQuantity(qty);
	}
	public void clear() {
		map.clear();
		maxQuantity.clear();
	}
	public int getCount() {
		return this.getItems().size();
	}
	public double getAmount() {
		Collection<Product> ps = this.getItems();
		double amount = 0;
		for(Product p: ps){
			amount += (p.getQuantity() * p.getUnitPrice() * (1-p.getDiscount()));
		}
		return amount;
	}
	public Collection<Product> getItems(){
		return map.values();
	}
	public Integer getMaxQuantity(Integer id) {
	    return maxQuantity.get(id);
	}
	public boolean isEmpty() {
		if (map.isEmpty()) 
		{
			return true;
		}
		return false;
	}
}

