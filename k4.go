package main

import ( "fmt"
         "math"
//         "sort"
)

func main() {
// dead-simple counting sort of array of int

    given := []int{25, 13, 17, 3, 25, 5, 8, 3}
    fmt.Println("given=",given)

    bmap := make(map[int]int)
    max := float64(given[0]) // adding min might help below
    for i:=0; i<len(given); i++ {
        if i > 0 {
            max = math.Max(max,float64(given[i]))
        }
        bmap[given[i]]++
    }
    imax := int(max)
    
    j:=0
    for i:=0; i<=imax; i++ {
        for k:=0; k < bmap[i]; k++ {
            given[j] = i
            j++
        }
    }
    fmt.Println("result=",given)

}
