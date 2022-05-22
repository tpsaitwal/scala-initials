package com.rockthejvm

object FunctionalProgramming extends App {

  // Object Oriented scala
  class Person (name:String) {
    def apply(age:Int):Unit = println(s"I am $age years old")
  }

  val bob = new Person("Bob")
  bob.apply(43) // This is equal to bob(43)
  bob(56) // That means we are invoking instance bob as a function
  // Presence of apply method allows an instance of a class to be invoked as a function

  /*
  scala runs on JVM and there are many such languages
  but JVM as initially designed for java knows objects but does not know functions as first class citizens
  So, as a part of functional programming we want to
  1) Compose functions
  2) Pass functions as an argument
  3) Return functions as results

  To implement functional programming on JVM scala people invented FunctionX
   */

  // We have simply instantiated a trait Function1 where it takes Int as an argument and returns Int as a result
  val simpleIncrementer = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  // As as we know we can invoke instance as a function so this is it
  println(simpleIncrementer.apply(23)) // is exactly same as simpleIncrementer(23)
  println(simpleIncrementer(23)) // invoking object as a function - it acts as a function and returns some result

  // Conclusion: all scala functions are instances of these FunctionX traits
  // FunctionX - Function1, Function2, .... Function22

  val concatFunc = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = s"$v1$v2"
  }

  println(concatFunc("Tea", " is hot"))

  val noArgFunction = new Function0[String] {
    override def apply(): String = "This function does not take any argument"
  }

  println(noArgFunction())

  val noArgNoReturnFunc = new Function0[Unit]{
    override def apply(): Unit = println("This function does not take or return anything")
  }

  // Unit means = ()
  println(noArgNoReturnFunc()) //This is basically a function which returns void / Unit


  // Syntactic sugar for FunctionX to remove boilerplate code
  /*
  Same as
  val doubler = new Function1[Int, Int] {
    override def apply(x:Int) : Int = x * 2
  }
   */
  val doubler = (x:Int) => x * 2

  println(doubler(3))

  val trippler:Int => Int = (x:Int) => x *3 // This is equivalent to val tripler:Function1[Int, Int] = (x:Int) => x * 3

  println(trippler(3))

  // Higher order functions, take functions as arguments and returns functions as result like map function
  val listOfInt = List(1,2,3).map((x:Int) => x + 2)
  // So map function takes argument a anonymous function which maps x to x+2 // So map is higher order function
  // You don't need to write arg type as it is inferred

  println(listOfInt)

  // Another HOF is flatMap
  val flatMapListOfInt = List(1,2,3).flatMap(x => List(x, x * 3))
  // As u can see here for every x in list it applies another function to create another smaller list by of x and x *3
  // Flatmap function's job is to concat all such small lists and return only one

  println(flatMapListOfInt)

  // Alternative syntax

  val flatMapAlterNative = List(1,3,4) flatMap {
    x => List(x , x * 3)
  }

  println(flatMapAlterNative)

  // Higher Order function filter
  val filteredList = List(1,2,3,4,5,9,7,6,8) filter(x => x % 2 == 0)

  println(filteredList)

  // Equivalent syntax
  val filterEquivalentList = List(1,4,5,6,5) filter( _ % 2 == 0) // _ represent element in list

  println(filterEquivalentList)

  val allPairs = List(1,2,3) flatMap ( x => List('a', 'b', 'c') map ( y => s"$x - $y") )
  println(allPairs)

  // for comprehension
  val alterNativePairs = for {
    number <- List(1,2,3)
    letter <- List('a', 'b', 'c')
  } yield s"$number - $letter"
  // This is equivalent to map and flatmap chain above

  println(alterNativePairs)

  /**
   * Collections
   */
  // List
  val aLst = List(1,2,4,5,6)
  println(s"first element ${aLst.head}, last element ${aLst.tail}")
  // prepend a list with 0 as below
  val aPrependedLst = 0 :: aLst
  println(s"prepended list with :: $aPrependedLst") // List(0,1,2,4,5,6)
  // another operator for prepend list
  val bPrependedLst = 0 +: aLst
  println(s"bPrependedLst list with +: $bPrependedLst")
  // appended or extended list
  val extendList = aLst :+ 7
  println(s"extended list :+ $extendList")

  // Sequences
  val aSequence: Seq[Int] = Seq(1, 2, 4) // Seq.apply(1,2,3) - its a trait
  val accessedIndex = aSequence.apply(1) // return element at index 1
  val accessIndex = aSequence(1) // return same no

  println(s"apply $accessedIndex or not apply $accessIndex")

  // vectors: fast sequence implementation
  val aVector = Vector(1,2,3,4)

  // sets - no duplicates
  val aSet = Set(1,2,3,4,2,3,6)
  println(s"Set $aSet") // No duplicates

  println(s"Has set has 5? ${aSet.contains(5)}")

  // add element to set
  val addedSet = aSet + 5
  println(s"addedset  $addedSet")

  // remove element from set
  val remveset = aSet - 6
  println(s"remov set $remveset")

  // range used for iteration - it does not contain elements but can process as if it did
  val aRange = 1 to 1000
  val multTwo = aRange.map(x => x * 2).toList
  println(multTwo)

  // tuples - group of values under same values
  val aTuple =  Tuple4("Mr", "Tejas", "Pravin", "Saitwal")
  println(s"My name is ${aTuple._1}. ${aTuple._2} ${aTuple._3} ${aTuple._4}")

  // Map
  val aPhoneBook:Map[String, Int] = Map(
    ("Tejas", 781542122), // These are tuples
    ("Dhjkhk", 46546466),
    "Jean" -> 451212  // Same as above tuple ("Jean", 451212)
  ) // Companion object

  println(aPhoneBook)
}
