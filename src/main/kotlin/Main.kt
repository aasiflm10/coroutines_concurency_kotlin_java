//val numList = ArrayList<Int>()
//fun main() {
//    for (i in 0 until 10000) {
//        numList.add(i)
//    }
//    Thread { printList()}.start()
//    /* line 8 is main thread */
//    println("New thread starts threadNo ${Thread.currentThread().id}")
//    Thread { printList()}.start()
//    println("New thread starts threadNo ${Thread.currentThread().id}")
//    Thread { printList()}.start()
//    println("New thread starts threadNo ${Thread.currentThread().id}")
//
//    printList()
//    println("New thread starts threadNo ${Thread.currentThread().id}")
//}
//
//fun printList() {
//    for (i in 0 until 10000) {
//        if(i%100 == 0)
//        { println("${Thread.currentThread().id} $i") }
//    }
//}
//
//val numList = ArrayList<Int>()
//
//fun main(){
//    for(i in 0 until 1000)
//    {
//        numList.add(i)
//    }
//    dropMultiples(3)
//    dropMultiples(5)
//    dropMultiples(7)
//
//    for(i in numList)
//    {
//        println(i)
//    }
//}
//
//fun dropMultiples(n :Int){
//    for(i in numList)
//    {
//        if(i%n == 0)
//        {
//            numList.remove(i)
//        }
//    }
//}
/*dropMultiples function never produces right results
because as soon as you remove an element from numList the size
 is also reduced....
 and your loop never get to the last element which might be
 the multiple of n.
 */


//The below dropMultiples() works on a single thread
//fun dropMultiples(n: Int){
//    val itr = numList.iterator()
//    while (itr.hasNext())
//    {
//        val i = itr.next()
//        if(i %n == 0)
//        {
//            itr.remove()
//        }
//    }
//}

//now we will be working on multiples Thread

//Use CopyOnWriteArrayList instead of ArrayList
//It is a ThreadSafe variant of ArrayList in which all mutative operations
// are implemented by making a fresh copy of the underlying Array
// It is a very costly operation but safe from thread related problem

val numList = ArrayList<Int>()

fun main(){
    for(i in 0 until 1000)
    {
        numList.add(i)
    }
    val itr = numList.iterator()
    Thread{ dropMultiples(3, itr) }.start()
    Thread{ dropMultiples(5, itr) }.start()
    Thread{ dropMultiples(7, itr) }.start()

    for(i in numList)
    {
        println(i)
    }
}

/* Note : in the dropMultiples() function below we have created an iterator,
    but we are creating it in three parallel places that to on the same numList
 */
//fun dropMultiples(n: Int){
//    val itr = numList.iterator()
//    while (itr.hasNext())
//    {
//        val i = itr.next()
//        if(i %n == 0)
//        {
//            itr.remove()
//        }
//    }
//}


//This is the modified version
//We solve this problem by passing the same iterator to all the three threads
//Hence, problem solved...
fun dropMultiples(n: Int, itr : MutableIterator<Int>){

    while (itr.hasNext())
    {
        val i = itr.next()
        if(i %n == 0)
        {
            itr.remove()
        }
    }
}