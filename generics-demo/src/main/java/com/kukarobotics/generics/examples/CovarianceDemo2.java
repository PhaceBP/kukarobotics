package com.kukarobotics.generics.examples;

import java.util.List;

public class CovarianceDemo2 {

	
	static class Parent {
		
		public Parent getInstance(Number num){
			return null;
		}
		
		public List<String> getList(){
			return null;
		}
		
		public void receiveList(List<String> list){
		}
	}
	
	static class Child extends Parent {

		// Method overriding is covariant based on return type
		@Override
		public Child getInstance(Number i) {
			return null;
		}

		@Override
		public List getList() {
			// TODO Auto-generated method stub
			return super.getList();
		}

		@Override
		public void receiveList(List list) {
			// TODO Auto-generated method stub
			super.receiveList(list);
		}
		
		
		
		
		
		
	}
	
	
	public static void main(String[]args){
		
		Parent obj = new Child(); // Valid
		//Child obj2 = new Parent(); COMPILE ERROR
		
		
		
	}
	
}
