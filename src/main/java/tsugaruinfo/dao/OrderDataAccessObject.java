package tsugaruinfo.dao;

import java.util.ArrayList;
import java.util.List;

import tsugaruinfo.entity.Order;

public class OrderDataAccessObject {

	public List<Order> findByALL(){
		Order o1 = new Order("幕の内弁当", 10, 100, 1000, "お昼用の弁当");
		Order o2 = new Order("上・幕の内弁当", 20, 200, 2000, "お昼用の弁当");
		Order o3 = new Order("特上・幕の内弁当", 30, 300, 3000, "お昼用の弁当");
		Order o4 = new Order("ハンバーグ弁当", 40, 400, 4000, "お昼用の弁当");
		Order o5 = new Order("から揚げ弁当", 50, 500, 5000, "お昼用の弁当");
		Order o6 = new Order("焼肉弁当", 60, 600, 6000, "お昼用の弁当");
		Order o7 = new Order("中華丼", 70, 700, 7000, "お昼用の注文");
		Order o8 = new Order("ホイコーロー", 80, 800, 8000, "お昼用の注文");
		Order o9 = new Order("チンジャオロースー", 90, 900, 9000, "お昼用の注文");
		Order o10 = new Order("チャーハンラーメン", 100, 1000, 10000, "お昼用の注文");
		Order o11 = new Order("エビチリ弁当", 110, 1100, 11000, "お昼用の弁当");
		Order o12 = new Order("広島風お好み焼き", 120, 1200, 12000, "注文");
		Order o13 = new Order("タンメン", 130, 1300, 13000, "お昼用の注文");
		Order o14 = new Order("カルボナーラ", 140, 1400, 14000, "お昼用の弁当");
		Order o15 = new Order("ナポリタン", 150, 1500, 15000, "お昼用の弁当");
		Order o16 = new Order("バジルソース", 160, 1600, 16000, "お昼用の弁当");
		Order o17 = new Order("ラザニア", 170, 1700, 17000, "お昼用の弁当");
		
		Order o18 = new Order("幕の内弁当", 10, 100, 1000, "お昼用の弁当");
		Order o19 = new Order("上・幕の内弁当", 20, 200, 2000, "お昼用の弁当");
		Order o20 = new Order("特上・幕の内弁当", 30, 300, 3000, "お昼用の弁当");
		Order o21 = new Order("ハンバーグ弁当", 40, 400, 4000, "お昼用の弁当");
		Order o22 = new Order("から揚げ弁当", 50, 500, 5000, "お昼用の弁当");
		Order o23 = new Order("焼肉弁当", 60, 600, 6000, "お昼用の弁当");
		Order o24 = new Order("中華丼", 70, 700, 7000, "お昼用の注文");
		Order o25 = new Order("ホイコーロー", 80, 800, 8000, "お昼用の注文");
		Order o26 = new Order("チンジャオロースー", 90, 900, 9000, "お昼用の注文");
		Order o27 = new Order("チャーハンラーメン", 100, 1000, 10000, "お昼用の注文");
		Order o28 = new Order("エビチリ弁当", 110, 1100, 11000, "お昼用の弁当");
		Order o29 = new Order("広島風お好み焼き", 120, 1200, 12000, "注文");
		Order o30 = new Order("タンメン", 130, 1300, 13000, "お昼用の注文");
		Order o31 = new Order("カルボナーラ", 140, 1400, 14000, "お昼用の弁当");
		Order o32 = new Order("ナポリタン", 150, 1500, 15000, "お昼用の弁当");
		Order o33 = new Order("バジルソース", 160, 1600, 16000, "お昼用の弁当");
		Order o34 = new Order("ラザニア", 170, 1700, 17000, "お昼用の弁当");
		
		List<Order> result = new ArrayList<Order>();
		
		result.add(o1);
		result.add(o2);
		result.add(o3);
		result.add(o4);
		result.add(o5);
		result.add(o6);
		result.add(o7);
		result.add(o8);
		result.add(o9);
		result.add(o10);
		result.add(o11);
		result.add(o12);
		result.add(o13);
		result.add(o14);
		result.add(o15);
		result.add(o16);
		result.add(o17);		
		
		result.add(o18);
		result.add(o19);
		result.add(o20);
		result.add(o21);
		result.add(o22);
		result.add(o23);
		result.add(o24);
		result.add(o25);
		result.add(o26);
		result.add(o27);
		result.add(o28);
		result.add(o29);
		result.add(o30);
		result.add(o31);
		result.add(o32);
		result.add(o33);
		result.add(o34);		
		
		return result;
	}
}
