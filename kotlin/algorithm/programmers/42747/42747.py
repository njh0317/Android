def solution(citations):
    answer = 0
    citations.sort()
    l = len(citations)

    for i in range(l):
        if(citations[i]>=l-i) : return l-i
    return answer

if __name__ == "__main__":
    print(solution([9, 7, 6, 2, 1]))