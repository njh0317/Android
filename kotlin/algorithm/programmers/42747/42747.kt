class Solution_42747 {
    fun solution(citations: IntArray): Int {
        var answer = 0
        citations.sort()
        val length = citations.size
        for(i in citations.indices)
        {
            if(citations[i]>=length-i) return length-i
        }
        return answer
    }
}

fun main() {
    val sol = Solution_42747()
    print(sol.solution(intArrayOf(9, 7,6, 2, 1)))
}