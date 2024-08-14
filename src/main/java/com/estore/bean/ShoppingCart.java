//package com.estore.bean;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.estore.entity.Product;
//
//@Transactional
//@Component("cart")
//@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
//public class ShoppingCart {
//	@Autowired
//	SessionFactory factory;
//	
//	Map<Integer, Product> map = new HashMap<Integer, Product>();
//	public void add(Integer id) {
//		Product p = map.get(id);
//		if(p == null){
//			Session session = factory.getCurrentSession();
//			p = (Product) session.get(Product.class, id);
//			p.setQuantity(1);
//			map.put(id, p);
//		}
//		else{
//			p.setQuantity(p.getQuantity() + 1);
//		}
//	}
//	public void remove(Integer id) {
//		map.remove(id);
//	}
//	public void update(Integer id, int qty) {
//		Product p = map.get(id);
//		p.setQuantity(qty);
//	}
//	public void clear() {
//		map.clear();
//	}
//	public Collection<Product> getItems() {
//		return map.values();
//	}
//	public int getCount() {
//		int count = 0;
//		for(Product p : getItems()){
//			count += p.getQuantity();
//		}
//		return count;
//	}
//	public double getAmount() {
//		double amount = 0;
//		for(Product p : getItems()){
//			amount += p.getQuantity()*p.getUnitPrice()*(1-p.getDiscount());
//		}
//		return amount;
//	}
//}
