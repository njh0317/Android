class Solution_81301 {
    val number = mapOf("zero" to "0", "one" to "1", "two" to "2",
        "three" to "3", "four" to "4", "five" to "5",
        "six" to "6", "seven" to "7", "eight" to "8",
        "nine" to "9")

    fun solution(s: String): Int {
        var answer: Int = 0
        var origin_string = s
        number.keys.forEach { k ->
            if(s.contains(k)) origin_string = origin_string.replace(k, number[k]!!)
        }

        return origin_string.toInt()
    }
}

fun main() {
    val sol = Solution_81301()
    print(sol.solution("oneoneone4seveneight"))
}
