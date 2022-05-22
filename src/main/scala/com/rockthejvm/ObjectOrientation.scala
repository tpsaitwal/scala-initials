package com.rockthejvm

/*
Already extending APP type main method
 */
object ObjectOrientation extends App {

  class Animal {
    val age: Int = 0 // constant

    def eat(): Unit = println("I am eating")

    def die():Unit = println(s"This animal not living indefinitely ${!Animal.canLiveIndefinitely}")
  }

  val anAnimal = new Animal // instantiation
  anAnimal.eat()

  class Dog(var name: String) extends Animal // inheritance
  val aDog = new Dog("Tommy")
  aDog.eat()
  aDog.name = "Tommi 3"
  println(aDog.name)

  val aDog2: Animal = new Dog("Hachi")
  aDog2.eat()


  abstract class WalkingAnimal {
    val hasLegs = true
    def walk():Unit
  }

  class Monkey extends WalkingAnimal {
    override def walk(): Unit = println("Monkey walks")
  }

  trait Carnivore{
    def eat(animal: Animal):Unit
  }

  trait Reptile {
    def crawl(animal: Animal):Unit
  }

  trait Philosopher{
    def ?@(thought: String):Unit
  }
// Inheritance and trait - mixing
  class Crocodile extends Animal with Carnivore with Reptile with Philosopher {
    override def eat(): Unit = super.eat()

    override def eat(animal: Animal): Unit = println(s"I can eat non veg ${animal.age}")

    override def crawl(animal: Animal): Unit = println("I can crawl on land")

  // Allows any type of methodName
    override def ?@(thought:String):Unit = println(s"I was thinking about $thought")
  }

  val croc: Crocodile = new Crocodile
  croc.eat(aDog2) // polymorphism of methods
  croc eat aDog // infix notation
  croc.eat()
  croc.crawl(croc)
  croc ?@ "a nice day"

  val basicMath = 1 + 2 // operators are methods
  val basicAdd = 4.+(5) // same as above
  println(s"basicMath $basicMath basicAdd $basicAdd") //String interpolation

  // Anonymous class
  /**
   * it wil create some
   * class Carnivore_Anonymous_6768 extends Carnivore {
   *    override def eat(animal: Animal): Unit = println("I can eat other dinosaurs")
   * }
   *
   * val dinosaurs = new Carnivore_Anonymous_6768
   */
  val dinosaurs = new Carnivore {
    override def eat(animal: Animal): Unit = println("I can eat other dinosaurs")
  }

  dinosaurs.eat(croc)

  // singleTon object
  // This is the class definition and this is the singleTon object
  object MySingleTon {
    val mySpecialValue:String = "I love scala"
    def mySpecialMethod():String = mySpecialValue
    def apply(x:Int):Int = x * x
  }

  println(MySingleTon.mySpecialMethod())
  // presence of apply method
  println(s"${MySingleTon.apply(5)} is equivalent to ${MySingleTon(5)}")


  object Animal { // class Animal and Object Animal are companions they both are declared in same class
    // companian object
    // can access each others private fields and methods

    // SingleTon animal and instances of Animal are different

    private val canLiveIndefinitely = false
  }

  anAnimal.die()

  /*
   * Lightweight data structures
   * Compiler generates equals and hashCode
   * Compiler generates serialization
   * companion with apply that means a Companion object with apply method is automatically created by Compiler
   * ex. Bob below // can be constructed without new
   * Pattern matching
   */
  case class Person(name: String, age:Int)

  val bob = Person("Bob", 34) // equivalent to Person.apply("Bob", 34)

  // exception
  try {
    val x:Int = 5/0
  } catch {
    case ae:ArithmeticException => println(s"Divide by zero ${ae.getStackTrace.mkString}")
    case ne: Exception => println(s"Might throw null pointer ${ne.getStackTrace.mkString}")
  } finally {
    println("I am done finally")
  }

  // generics
  abstract class MyList[T] {
    def head:T
    def tail: MyList[T]
  }

  // Generic with concrete type
  val aList:List[Int] = List(1,2,3) // companion object List.apply(1,2,3)
  val first = aList.head
  val last = aList.tail

  class MyStringList extends MyList[String] {
    override def head: String = "This is head String"

    override def tail: MyList[String] = new MyStringList()
  }

  val myStringList = new MyStringList()
  println(myStringList.head)
  println(myStringList.tail)

  class MyIntList extends  MyList[Int] {
    override def head: Int = 10

    override def tail: MyList[Int] = new MyIntList()
  }

  val myIntList = new MyIntList()
  println(myIntList.head)
  println(myIntList.tail)

  case class MyBoolList(bool:Boolean) extends MyList[Boolean]{
    override def head: Boolean = bool

    override def tail: MyList[Boolean] = MyBoolList(true)
  }

  val myBoolList = new MyBoolList(false)
  println(myBoolList.head)
  println(myBoolList.tail)

  // Immutable objects
  // ANy modification to an object should return new object
  // Benefits:
  // 1) Helps multithreading
  // 2) Readability
  val reverseList = aList.reverse // new List
  println(aList) // as it is
  println(reverseList) // new Object

  // scala is Closest to Object Oriented
  // Every single code written is part of class or Object

}
