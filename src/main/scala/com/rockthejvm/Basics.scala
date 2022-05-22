package com.rockthejvm

object Basics extends App {

  // defining value
  val meaningOfLife: Int = 42

  val aBoolean = true

  val anInterpolation = s"The meaning of life is $aBoolean"

  val expression = 2 + 3

  val aCodeBlock = {
    val localVal = 45
    localVal + 45
  }

  println(aCodeBlock)


  def myFunction(name: String, age: Int):String = s"name: $name age: $age"

  println(myFunction("Tejas", 27))

  def myRecursiveFunc(num: Int) : Int = if (num <=1 ) 1 else num * myRecursiveFunc(num - 1)

  println(myRecursiveFunc(5))
}
