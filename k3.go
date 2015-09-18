package main

import ( "fmt"
         "math"
//         "sort"
)

func main() {
// dead-simple sort of set of int

    given := []int{25, 13, 17, 5, 8, 3}
    fmt.Println("given=",given)

    bmap := make(map[int]bool)
    max := float64(given[0])
    for i:=0; i<len(given); i++ {
        if i > 0 {
            max = math.Max(max,float64(given[i]))
        }
        bmap[given[i]] = true
    }
    imax := int(max)
    
    j := 0
    for i:=0; i<=imax; i++ {
        if bmap[i] {
            given[j] = i
            j++
        }
    }
    fmt.Println("result=",given)

}
