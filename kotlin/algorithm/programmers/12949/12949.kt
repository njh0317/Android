class Solution_12949 {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        var answer = mutableListOf<IntArray>()

        for(i in arr1.indices)
        {
            var newarr = Array<Int>(arr2[0].size){i -> 0}
            for(j in arr2[0].indices)
            {
                //i -> arr1의 i
                var num = 0
                for(k in arr1[i].indices){
                    num+=(arr1[i][k]*arr2[k][j])
                }
                newarr.set(j, num)
            }
            answer.add(newarr.toIntArray())
        }
        return answer.toList().toTypedArray()
    }
}
fun main() {
    val sol = Solution_12949()
    print(sol.solution(arrayOf(intArrayOf(1, 4), intArrayOf(3, 2), intArrayOf(4, 1)),
                        arrayOf(intArrayOf(3, 3, 3), intArrayOf(3, 3, 3))).contentDeepToString())
}
