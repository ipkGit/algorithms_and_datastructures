package fibonacci

//row fibonacci
//0 1 2 3 4 5 6 7  8  9  10 11 12
//0 1 1 2 3 5 8 13 21 34 55 89 144...

//fun main() {
//    println("iteration: ${iteration(12)}") //(12) -> 144 (maximum 92, more make overflow long)
//    println("fibonacciByMOD:  ${fibonacciByMOD(92, 6)}") //(12, 4) -> 3
//    println("bigFibonacciByMOD: ${bigFibonacciByMOD(121, 7)}") //(121, 7) -> 6
//    println(streamFibonacci().limit(12).forEach(System.out::println)) //0..144
//}
//
//fun iteration(pos: Long): Long {
//    var last: Long = 0
//    var next: Long = 1
//    for (i in 0 until pos) {
//        val oldLast = last
//        last = next
//        next += oldLast
//    }
//    return last
//}
//
//fun fibonacciByMOD(pos: Long, div: Long): Long {
//    return iteration(pos) % div
//}
//
//fun bigFibonacciByMOD(pos: Long, div: Long): Long {
//    val period = periodPisano(div)
//    val newPos = pos % period
//    return fibonacciByMOD(newPos, div)
//}
//
//fun periodPisano(div: Long): Int {
//    var last: Long = 0
//    var next: Long = 1
//    var period = 0
//    while (true) {
//        val oldLast = last
//        last = next % div
//        next = (next + oldLast) % div
//        period++
//        if (last == 0L && next == 1L)
//            return period
//    }
//}
//
//fun streamFibonacci(): LongStream {
//    var last = 0L
//    var next = 1L
//    return LongStream.generate {
//        val oldLast = last
//        last = next
//        next += oldLast
//        last
//    }
//}