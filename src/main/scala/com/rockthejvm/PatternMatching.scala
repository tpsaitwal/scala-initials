package com.rockthejvm

object PatternMatching extends App {


  // Switch expression
  val anInt = 55
  val order = anInt match {
    case 1 => "First"
    case 2 => "Second"
    case 3 => "Third"
    case _ => s"$anInt th"
  }
  println(order)

  // PattenMatch is an expression that means it can be reduced to a value and we can assign it to value
    // Equivalent to switch expression
  case class Person(name:String, age:Int)
  val bob = Person("Bob", 43)// Person.apply("Bob", 43)

  // Pattern matching for deconstructing case classes
  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and my age is $a" // If bob matches to the construct Person(n, a) then n and a be the constituent part of bob
    case _ => "Something else"
  }

  println(personGreeting)

  // Deconstructing touple
  val aTouple = Tuple2("Bon", "Joe")
  val bandDec = aTouple match {
    case Tuple2(band, genre) => s"Band $band is run by $genre" // if aTouple confirms to this case then band and genre be there member
    case _ => "Something else"
  }
  println(bandDec)

  val aList = List(1,2,3)
  val aListDes = aList match {
    case List(_,2,_) => "3 element List containing 2 at second position "
    case _ => "Unknown list" // if case _ is not mentioned then it will throw matcherror
  }

  println(aListDes)

  // Pattern matching tries all cases in sequence
  // Means if you put _ case first then it will match that not the absoulte case
}
