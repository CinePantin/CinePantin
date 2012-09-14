package com.cinepantin.shop.domain.DZC;

import java.util.ArrayList;
import java.util.List;

public class TestOfInstanceOf {

	public static void main(String[] args) {
		TestOfInstanceOf me = new TestOfInstanceOf();
		me.runTest();
	}

	private void runTest() {
		
		Object cm = new ClasseMere();
		
		System.out.println("--- Testing o = new ClasseMere() ---");
		System.out.println(cm instanceof ClasseMere);
		System.out.println(cm instanceof Interface);
		System.out.println(cm instanceof ClasseFille);
		
		
		
		Object cf = new ClasseFille();

		System.out.println("--- Testing o = new ClasseFille() ---");
		System.out.println(cf instanceof ClasseMere);
		System.out.println(cf instanceof ClasseFille);
		System.out.println(cf instanceof Interface);
		
		
		
		ClasseMere cf2 = new ClasseFille();

		System.out.println("--- Testing ClasseMere cf2 = new ClasseFille() ---");
		System.out.println(cf2 instanceof ClasseMere);
		System.out.println(cf2 instanceof ClasseFille);
		System.out.println(cf2 instanceof Interface);
		
		
		
		Object parametree = new Parametree();
		
		System.out.println("--- Testing Object parametree = new Parametree() ---");
		System.out.println(parametree instanceof Parametree);
		System.out.println(parametree instanceof Parametree<?>);
		System.out.println(parametree instanceof ParametreeDeClasseMere); // Nok => "Incompatible conditional operand types Parametree<ClasseFille> and ParametreeDeClasseMere"
		System.out.println(parametree instanceof ParametreeDeClasseFille);
		
		
		
		Parametree<ClasseFille> pcf = new Parametree();
		
		System.out.println("--- Testing Parametree<ClasseFille> pcf = new Parametree() ---");
		System.out.println(pcf instanceof Parametree);
		System.out.println(pcf instanceof Parametree<?>);
		// System.out.println(pcf instanceof ParametreeDeClasseMere); // Nok => "Incompatible conditional operand types Parametree<ClasseFille> and ParametreeDeClasseMere"
		System.out.println(pcf instanceof ParametreeDeClasseFille);
		
		
		
		Object pcf2 = new Parametree<ClasseFille>();
		// Parametree<ClasseFille> pcf2 = new Parametree<ClasseFille>();
		
		System.out.println("--- Testing Parametree<ClasseFille> pcf2 = new Parametree<ClasseFille>() ---");
		System.out.println(pcf2 instanceof Parametree);
		System.out.println(pcf2 instanceof Parametree<?>);
		System.out.println(pcf2 instanceof ParametreeDeClasseMere);
		System.out.println(pcf2 instanceof ParametreeDeClasseFille);
		// System.out.println(pcf2 instanceof Parametree<?> super ParametreeDeClasseFille);
		// System.out.println(pcf2 instanceof Parametree<ClasseFille>); // Nok => "Cannot perform instanceof check against parameterized type Parametree<ClasseFille>. Use the form Parametree<?> instead since further generic type information will be erased at runtime"
		// System.out.println(pcf2 instanceof Parametree<? extends ClasseFille>); // Nok => "Cannot perform instanceof check against parameterized type Parametree<? extends ClasseFille>. Use the form Parametree<?> instead since further generic type information will be erased at runtime"
		
		
		
		Object pcf3 = new ParametreeDeClasseFille();
		
		System.out.println("--- Testing Parametree<ClasseFille> pcf3 = new Parametree<ClasseFille>() ---");
		System.out.println(pcf3 instanceof Parametree);
		System.out.println(pcf3 instanceof Parametree<?>);
		System.out.println(pcf3 instanceof ParametreeDeClasseMere);
		System.out.println(pcf3 instanceof ParametreeDeClasseFille);
		
		
		/* @see http://stackoverflow.com/questions/3847162/java-generics-super-keyword */ 
		List<? super ClasseFille> superList = new ArrayList(); // = new ArrayList<? super ClasseFille>();

		System.out.println("--- Testing List<? super ClasseMere> superList ---");
		// superList.add(new ClasseMere());
		superList.add(new ClasseFille());
		for (Object o : superList) {
			System.out.println(o.getClass().getCanonicalName());
		}
		
		
		
	}
	
}
