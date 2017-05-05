package com.kukarobotics.generics.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class CovarianceDemo implements Comparator<java.util.Date>{

	public int compare(Date o1, Date o2) {
		return 0;
	}

	public static void main(String[]args) throws InterruptedException{
		
		List<java.sql.Date> sqlDateList = new ArrayList<java.sql.Date>();
		
		sqlDateList.add(new java.sql.Date(System.currentTimeMillis()));
		Thread.sleep(1000);
		sqlDateList.add(new java.sql.Date(System.currentTimeMillis()));
		Thread.sleep(1000);
		sqlDateList.add(new java.sql.Date(System.currentTimeMillis()));
		Thread.sleep(1000);
		
		Collections.sort(sqlDateList, new CovarianceDemo());
	}
}
