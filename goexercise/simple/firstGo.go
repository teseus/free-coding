package main

import "fmt"

func main() {
	fmt.Println("hello go world")
	var a = 0
	println(a)
	println("hello go world")

	printTypeValue()
}

func printTypeValue() {
	fmt.Printf("%T %v", 1000, 1000)
}
