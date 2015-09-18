package main

import (
    "sort"
    "fmt"
)

// sorting is a solved problem

func main() {
    given := []int{25, 13, 17, 5, 8, 3}
    fmt.Println(given)
    sort.Ints(given)
    fmt.Println(given)
}

