package tsugaruinfo.dao;

import java.util.ArrayList;
import java.util.List;

import tsugaruinfo.entity.Order;

public class OrderDataAccessObject {

	public List<Order> findByALL(){
		Order o1 = new Order("order1", 1, "幕の内弁当", 10, 100, 1000);
		Order o2 = new Order("order2", 2, "のり弁当", 20, 101, 2020);
		Order o3 = new Order("order3", 3, "上・幕の内弁当", 30, 102, 3060);
		Order o4 = new Order("order4", 4, "特上・幕の内弁当", 40, 103, 4120);
		Order o5 = new Order("order5", 5, "から揚げ弁当", 50, 104, 5200);
		Order o6 = new Order("order6", 6, "チャーハン弁当", 60, 105, 6300);
		Order o7 = new Order("order7", 7, "中華弁当", 70, 106, 7420);
		Order o8 = new Order("order8", 8, "ホイコーロー弁当", 80, 107, 8560);
		Order o9 = new Order("order9", 9, "チンジャオロース弁当", 90, 108, 9720);
		Order o10 = new Order("order10", 10, "中華丼弁当", 100, 109, 10900);
		Order o11 = new Order("order11", 11, "パスタ弁当", 110, 210, 23100);
		Order o12 = new Order("order12", 12, "バジルパスタ弁当", 120, 220, 24400);
		Order o13 = new Order("order13", 13, "ミネストローネ弁当", 130, 230, 29900);
		Order o14 = new Order("order14", 14, "カルボナーラ弁当", 140, 240, 33600);
		Order o15 = new Order("order15", 15, "ミートソース弁当", 150, 250, 32500);
		Order o16 = new Order("order16", 16, "たらこパスタ弁当", 160, 260, 41600);
		Order o17 = new Order("order17", 17, "ピザ弁当", 170, 270, 45900);
		
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
		
		return result;
	}
}
