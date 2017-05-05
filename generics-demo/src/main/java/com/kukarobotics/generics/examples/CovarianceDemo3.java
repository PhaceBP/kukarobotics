package com.kukarobotics.generics.examples;

public class CovarianceDemo3 {

	static interface A {
		
	}
	static interface B {
		
	}
	
	static interface C extends A,B {
		
	}
	
	static class GenericHolder<T extends A & B> {
		private T member;

		public T getMember() {
			return member;
		}

		public void setMember(T member) {
			this.member = member;
		}

	}

	public static void main(String[] args) {

		A obj = new GenericHolder<C>().getMember();
		B obj2 = new GenericHolder<C>().getMember();
		C obj3 = new GenericHolder<C>().getMember();
	}

}
