package com.learnStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {

	public static void main(String[] args) {
		
//		Module - 1
		System.out.println("Module - 1");
		List<User> users = List.of(
				new User("Rachel Green", 101), 
				new User("Monica Geller", 102), 
				new User("Ross Gellser", 103), 
				new User("Chandler Bing", 104), 
				new User("Joey Tribbiani", 105), 
				new User("Phoebe Buffay", 106));
		
		users.stream().map(user -> user.getId()).filter(id -> id>103).forEach(data -> System.out.println(data));
		
//		Flat Map
		City newYork = new City("New York", 
				new User("Rachel Green", 101), 
				new User("Monica Geller", 102));
		City newJersey = new City("New Jersey", 
				new User("Ross Gellser", 103), 
				new User("Chandler Bing", 104));
		City california = new City("California",
				new User("Joey Tribbiani", 105), 
				new User("Phoebe Buffay", 106));
		
		List<City> cities = List.of(newYork, newJersey, california);
		
//		Since flatMap expects a function to return stream, hear it is
		cities.stream().flatMap(city -> city.getUsers().stream()).forEach(user -> System.out.println(user.getName()));
		
//		Module - 2
		System.out.println("\nModule - 2");
		int[] nums = {0,1,2,3,4};
//		The below representation is known as method reference
		Arrays.stream(nums).forEach(System.out::println);
		
		Path path = Path.of("Untitled 1");
		
		try(Stream<String> lines = Files.lines(path)){
			lines.forEach(System.out::println);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		String str = "Hey Jude!";
		String[] strArr = str.split(" ");
		Arrays.stream(strArr).forEach(System.out::println);
		
//		The above strArr is an intermediate result, in-order to ignore such.. here's optimal solution
		Pattern pattern = Pattern.compile(" ");
		pattern.splitAsStream(str).forEach(System.out::println);
		
//		mapToObj will convert IntStream to Stream<T>; 
		str.chars().mapToObj(codePoint -> Character.toString(codePoint)).distinct().sorted().forEach(System.out::println);
//		To ignore white space before '!'
		str.chars().mapToObj(codePoint -> Character.toString(codePoint)).filter(charecter -> !charecter.equals(" ")).distinct().sorted().forEach(System.out::println);
		
		IntStream.range(0, 30).skip(10).limit(10).forEach(System.out::println);
		
		try(Stream<String> lines = Files.lines(path)){
			lines.skip(2).limit(2).forEach(System.out::println);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Class<?> clzz = ArrayList.class.getSuperclass();
//		takeWhile is going to close stream when false is returned
		Stream.<Class<?>>iterate(clzz, c -> c.getSuperclass()).takeWhile(c -> c!=null).forEach(System.out::println);
//		Without takeWhile : uncomment the below line to check the output
//		Stream.<Class<?>>iterate(clzz, c -> c.getSuperclass()).forEach(System.out::println);
		
//		Module - 3
		System.out.println("\nModule - 3");
		double avgID = users.stream().mapToInt(User::getId).filter(id -> id > 104).average().orElseThrow();
		System.out.println(avgID);
		
		int totalIDs = users.stream().mapToInt(User::getId).sum();
		String totalNames = users.stream().map(User::getName).collect(Collectors.joining());
		System.out.println(totalIDs+ " " +totalNames);
		
//		Module - 4
		System.out.println("\nModule - 4");
		List<Integer> ints = List.of(1,1,1,1,1);
		Optional<Integer> reduced = ints.stream().reduce((i1,i2) -> i1+i2);
		System.out.println(reduced);
//		reduced.get();
		Integer sum = reduced.orElseThrow();
		System.out.println("sum = "+sum);
		
		List<Integer> ints2 = List.of();
//		returns 10 if no list elements found
		int result = ints2.stream().reduce(10, (i1,i2)->i1+i2);
		System.out.println(result);
		
//		rank,city,state,population,land area
		String lineForHYD = "1;Hyderabad;TG;8 336 817;780,9";
		Function<String,Double> lineToDensity = line -> {
			String[] split = line.split(";");
			
			String populationAsString =split[3];
			populationAsString = populationAsString.replace(" ", "");
			return Double.parseDouble(populationAsString);
			
		};
		System.out.println(lineToDensity.apply(lineForHYD));
		
	}
}