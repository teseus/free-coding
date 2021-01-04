package main

import "fmt"

func main() {
	fmt.Println("hello go world")
	var a = 0
	println(a)
	println("hello go world")

	printTypeValue()
	sliceTest()
}

func printTypeValue() {
	fmt.Printf("%T %v \n", 1000, 1000)
}

func sliceTest() {
	x := make([]float64, 5, 10)
	fmt.Println(x)

	x = append(x, 5, 6)
	fmt.Println(x)

	arr := [5]float64{1, 2, 3, 4, 5}
	fmt.Println(arr)
	x = arr[0:5]
	fmt.Println(x)

	x = arr[1:4]
	fmt.Println(x)
}
